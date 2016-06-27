package io.lovezx.wx.sdk.receive;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import io.lovezx.wx.sdk.context.WxConnectContext;
import io.lovezx.wx.sdk.context.account.WxAccountEntry;
import io.lovezx.wx.sdk.receive.ReceiveType.EventType;
import io.lovezx.wx.sdk.resource.XMapUtil;
import io.lovezx.wx.sdk.security.aes.AesException;
import io.lovezx.wx.sdk.security.aes.WXBizMsgCrypt;
import io.lovezx.wx.sdk.security.aes.XMLParse;

public class ReceiveFacade {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveFacade.class);
    
    private ReceiveChain chain;
    
    public ReceiveChain getChain() {
        return chain;
    }
    public void setChain(ReceiveChain chain) {
        this.chain = chain;
    }
    
    /**
     * receive接入
     * @param request
     * @param response
     * @throws AesException 
     * @throws IOException 
     */
    public void accpet(String token, EncryptMsg msg, HttpServletRequest request, HttpServletResponse response) throws IOException{
        String echoStr = request.getParameter("echostr");
        
        if(getCurrentAccount(token) == null){
        	LOGGER.warn("接入失败， 无效token; token="+token); 
        	return;
        }
		
		if(!msg.checkSignature(token)){
			LOGGER.warn("无效的签名; token="+token); 
			return;
		}
		
		response.getWriter().println(echoStr);
    }
    
    /**
     * 请求处理者必须在5秒内处理完成，否则会丢失后续对微信的回复机会
     * @param request
     * @param response
     * @throws AesException 
     * @throws SAXException 
     * @throws IOException 
     * @throws ParserConfigurationException 
     */
    public void receive(String token, EncryptMsg msg, HttpServletRequest request, HttpServletResponse response) throws AesException, ParserConfigurationException, IOException, SAXException {
    	WxAccountEntry accountEntry = getCurrentAccount(token);
    	if(accountEntry==null) {
    		LOGGER.error("token错误，请重新接入");
    		return;
    	}
    	
    	Map<String, String> map = new HashMap<String, String>();
    	if(!isEncrypt(request)){
    		LOGGER.warn("您正在使用明文模式，建议使用安全模式！");
    		map = XMapUtil.getMapFromXML(parseReceive(request));
    	}else{
        	
        	/**验证数据是否微信来源*/
        	if(!msg.checkSignature(token)){
        		return;
        	}
        	
        	/**解析xml数据*/
        	map = parseXml(token, request, accountEntry);
    	}
    	
        /**执行接收策略*/
    	ReceiveContext content = createContext(map);
        String returnString = doReceive(content);
        
        if(isEncrypt(request)){
        	/**加密返回数据*/
        	WXBizMsgCrypt wmc = new WXBizMsgCrypt(token, accountEntry.getAesKey(), accountEntry.getAppid());
        	returnString = wmc.encryptMsg(returnString, new Date().getTime()+"", wmc.getRandomStr());
        }
        PrintWriter out = null;
        try {
        	LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>"+returnString);
        	out=response.getWriter();
        	out.println(returnString);
        } catch (IOException e) {
            LOGGER.error("IO错误, 返回消息失败", e);
        }finally{
        	if(out!=null){
	        	out.flush();
	        	out.close();
        	}
        }
    }
	private Map<String, String> parseXml(String token,
			HttpServletRequest request, WxAccountEntry accountEntry)
			throws AesException, ParserConfigurationException, IOException,
			SAXException {
		Map<String, String> map;
		WXBizMsgCrypt wmc = new WXBizMsgCrypt(token, accountEntry.getAesKey(), accountEntry.getAppid());
		String xml = parseReceive(request);
		String source = wmc.decrypt(XMLParse.extract(xml)[1].toString());  
		map = XMapUtil.getMapFromXML(source);
		return map;
	}
	
    
	private String doReceive(ReceiveContext context){
        //校验数据是否安全
        //if(!checkSignature(context)) return "";
       
        /**检查是否重复请求, 根据发送者，接收者，创建时间来判断是否重复*/
        String key = "msgCheck_"+context.getReceiver()+"_"+context.getSender()+"_"+context.getCreateTime();
        boolean hasKey = WxConnectContext.getInstance().getCache().existKey(key);
        if(hasKey){
        	return "";
        }else{
        	WxConnectContext.getInstance().getCache().put(key, "", TimeUnit.SECONDS, 6);
        }
        /**缓存上下文*/
//        expireContext(context);
        /**执行接收动作*/
        Resource resource = chain.receive(context);
        
        if(resource == null) return "";
        
    	resource.setFromUserName(context.getReceiver());
        resource.setToUserName(context.getSender());
        return resource.toXml().toString();
    }
	
    /**
     * 缓存context上下文  从redis改为本地缓存， redis序列化是否线程安全？
     * @param context
     */
//    private void expireContext(ReceiveContext context) {
//        String expireTime = WxConnectContext.getInstance().getCommonContant(CommonContants.RECEIVE_CONTEXT_EXPIRE_TIME);
//        int expire = 7200;
//        if(StringUtils.isEmpty(expireTime) || !expireTime.matches("\\d+")) {
//            LOGGER.warn("接收上下文缓存时间配置错误，请检查配置，使用默认缓存时间：2小时");
//        }else{
//            expire = Integer.parseInt(expireTime);
//        }
//        String key = CacheContants.RECEIVE_CONTEXT.name()+"_"+context.getReceiver()+"_"+context.getSender();
//        Object obj = WxConnectContext.getInstance().getCache().get(key);
//        LinkedList<ReceiveContext> cache = null;
//        if(obj == null || !(List.class.isInstance(obj))){
//            LOGGER.warn("缓存读取错误，不存在或不为List类型, 重置receive缓存…… openId:--->"+context.getSender());
//            cache = new LinkedList<ReceiveContext>();
//        }else{
//            cache = (LinkedList<ReceiveContext>)obj;
//        }
//        
//        if(cache.size() >= 10) cache.poll();
//        cache.add(context);
//        ReceiveContext.contextCache.set(cache);
//        WxConnectContext.getInstance().getCache().put(key, cache, TimeUnit.SECONDS, expire);
//    }
    private ReceiveContext createContext(Map<String, String> map) {
        ReceiveContext context = new ReceiveContext();
        ReceiveType receiveType = ReceiveType.valueOf(map.get("MsgType").toUpperCase());

        context.setMsgType(receiveType);
        context.setReceiver(map.get("ToUserName"));
        context.setSender(map.get("FromUserName"));
        context.setCreateTime(map.get("CreateTime"));
        context.setReceiveData(receiveType.parseResource(map));
        if(receiveType==ReceiveType.EVENT){
            context.setEventType(EventType.valueOf(map.get("Event").toUpperCase()));
            context.getReceiveData().setMsgId(System.currentTimeMillis()+"");
        }
        return context;
    }
    private String parseReceive(HttpServletRequest request) {
        try {
            ServletInputStream is = request.getInputStream();
            StringBuffer receivexml = new StringBuffer();
            byte[] buf = new byte[1024];
            int len = -1;
            while((len=is.read(buf))!=-1){
                receivexml.append(new String(buf,0,len, "UTF-8"));
            }
            return receivexml.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("读取xl错误", e);
        }       
    }    
    
    private boolean isEncrypt(HttpServletRequest request) {
		return "aes".equals(request.getParameter("encrypt_type"));
	}
    
    
    private WxAccountEntry getCurrentAccount(String token) {
    	List<WxAccountEntry> entryList = WxConnectContext.getInstance().getAccountSupport().getAllAccount();
    	for(WxAccountEntry entry : entryList){
    		if(StringUtils.equals(entry.getToken(), token)){
    			return entry;
    		};
    	}
		return null;
	}
    
}
