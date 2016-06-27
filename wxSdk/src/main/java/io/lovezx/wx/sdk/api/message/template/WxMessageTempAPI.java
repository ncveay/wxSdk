package io.lovezx.wx.sdk.api.message.template;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SdkUtil;
import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.AbstractResponse;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.api.message.template.entity.IndustryRequest;
import io.lovezx.wx.sdk.api.message.template.entity.TemplateIDResponse;
import io.lovezx.wx.sdk.api.message.template.entity.TemplateMsgIdResponse;
import io.lovezx.wx.sdk.api.message.template.entity.TemplateSendRequest;
import io.lovezx.wx.sdk.api.message.template.entity.TemplateSendRequest.KeyNote;
import io.lovezx.wx.sdk.context.APIContants;
import io.lovezx.wx.sdk.http.JSONContent;
/**
 * 模板消息接口
 * @author yuanyi
 *
 */
public class WxMessageTempAPI extends APIRequestSupport{
    /**
     * 设置行业
     * @param industry1 行业1
     * @param industry2 行业2
     * @return
     */
    public  AbstractResponse setIndustry(int industry1, int industry2){
        IndustryRequest industry = new IndustryRequest();
        industry.setIndustry_id1(industry1+"");
        industry.setIndustry_id2(industry2+"");
        Assert.isValid(industry);
        return sendRequest(APIContants.MESSAGE_TEMP_SET_INDUSTRY, industry, SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    
    /**
     * 获取模板ID
     * @param tempSortId
     * @return
     */
    public  TemplateIDResponse getTempId(String tempSortId){
        Assert.notNull(tempSortId, "模板id不能为空！");
        return sendRequest(APIContants.MESSAGE_TEMP_ADD_TEMP, new SortId().setTemplate_sort_id(tempSortId), SdkUtil.createEmptyList(RequestParameter.class), TemplateIDResponse.class);
    }
    private static class SortId extends JSONContent implements SelfValid{
        private String template_sort_id;
        
        @SuppressWarnings("unused")
        public String getTemplate_sort_id() {
            return template_sort_id;
        }
        public SortId setTemplate_sort_id(String template_sort_id) {
            this.template_sort_id = template_sort_id;
            return this;
        }
        public boolean checkValid() {
            return StringUtils.isNotEmpty(template_sort_id);
        }
    }
    
    /**
     * 发送模板消息
     * @param openId 发送给指定人
     * @param tempId 模板的ID
     * @param url 模板点击后跳转的页面
     * @param noteMap 模板参数
     * @return
     */
    public TemplateMsgIdResponse sendTempMsg(String openId, String tempId, String url, Map<String ,KeyNote> noteMap){
        TemplateSendRequest sendBody = new TemplateSendRequest();
        sendBody.setData(noteMap);
        sendBody.setTouser(openId);
        sendBody.setTemplate_id(tempId);
        sendBody.setUrl(url);
        Assert.isValid(sendBody);
        return sendRequest(APIContants.MESSAGE_TEMP_SEND_TEMP, sendBody, SdkUtil.createEmptyList(RequestParameter.class), TemplateMsgIdResponse.class);
    }
    
}
