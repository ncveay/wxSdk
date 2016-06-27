package io.lovezx.wx.sdk.receive;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.lovezx.wx.sdk.receive.ReceiveType.EventType;

/**
 * 接收上下文<br>
 * @author yuanyi
 *
 */
public class ReceiveContext implements Serializable{
	private static final long serialVersionUID = 1L;

	static final ThreadLocal<List<ReceiveContext>> contextCache = new ThreadLocal<List<ReceiveContext>>();
    
    private ReceiveType msgType;
    private EventType eventType;
    private ReceiveResource ReceiveData;
    private String sender;
    private String receiver;
    private String createTime;
    private Map<String, Object> attrs = new HashMap<String, Object>();
    
    public <T extends Resource> T setProperty(T t){
    	t.setCreateTime(new Date().getTime()+"");
    	t.setFromUserName(receiver);
    	t.setToUserName(sender);
    	return t;
    }
    
    public Object getAttribute(String name){
    	return attrs.get(name);
    }
    
    public void setAttribute(String name, Object value){
    	attrs.put(name, value);
    }
    
    public String getCreateTime() {
        return createTime;
    }

    void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public ReceiveType getMsgType() {
        return msgType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public ReceiveResource getReceiveData() {
        return ReceiveData;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }


    void setMsgType(ReceiveType msgType) {
        this.msgType = msgType;
    }

    void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    void setReceiveData(ReceiveResource receiveData) {
        ReceiveData = receiveData;
    }

    void setSender(String sender) {
        this.sender = sender;
    }

    void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 获取上一个操作的上下文，最多向上获取10次
     * @return
     */
    public ReceiveContext getPreContext(){
        List<ReceiveContext> cache = contextCache.get();
        int index = cache.indexOf(this);
        if(index == -1){
            throw new IllegalStateException("缓存不存在此对象！！！程序错误，请检查");
        }
        if(index == 0){
            return null;
        }else{
            return cache.get(index-1);
        }
    }
    
}
