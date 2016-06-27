package io.lovezx.wx.sdk.api.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SdkUtil;
import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.AbstractResponse;
import io.lovezx.wx.sdk.api.OpenIdRequest;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.api.user.entity.UserInfoListResponse;
import io.lovezx.wx.sdk.api.user.entity.UserInfoResponse;
import io.lovezx.wx.sdk.api.user.entity.UserOpenIdListResponse;
import io.lovezx.wx.sdk.api.user.entity.UserRemarkRequest;
import io.lovezx.wx.sdk.context.APIContants;
import io.lovezx.wx.sdk.http.JSONContent;

/**
 * 用户API
 * @author yuanyi
 *
 */
public class WxUserAPI extends APIRequestSupport {
    /**
     * 修改备注
     * @param openId 待修改的用户的OPENID
     * @param remark 备注
     * @return
     */
    public AbstractResponse updateRemark(String openId, String remark){
        UserRemarkRequest request = new UserRemarkRequest();
        request.setOpenid(openId);
        request.setRemark(remark);
        Assert.isValid(request);
        return sendRequest(APIContants.USER_REMARK_UPDATE, request, SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    /**
     * 获取用户信息
     * @param openId
     * @return
     */
    public UserInfoResponse getUserInfo(String openId){
        Assert.notNull(openId, "openid不能为空！");
        RequestParameter param1 = new RequestParameter("openid", openId);
        RequestParameter param2 = new RequestParameter("lang", "zh_CN");
        return sendRequest(APIContants.USER_INFO_GET, Arrays.asList(new RequestParameter[]{param1, param2}) , UserInfoResponse.class);
    }
    /**
     * 批量获取用户信息
     * @param openidList
     * @return
     */
    public UserInfoListResponse getBatchUserInfo(List<String> openidList){
        for(String openid : openidList){
            Assert.notNull(openid, "openId不能为空");
        }
        return sendRequest(APIContants.USER_INFO_BATCH_GET, new OpenidList(openidList), SdkUtil.createEmptyList(RequestParameter.class), UserInfoListResponse.class);
    }
    /**
     * 获取用户openid列表
     * @param nextOpenId 从指定openid开始获取， 如果要循环获取，将返回值中的next_openid作为参数传入此方法
     * @return
     */
    public UserOpenIdListResponse getUserList(String nextOpenId){
        List<RequestParameter> params = new ArrayList<RequestParameter>();
        if(StringUtils.isNotEmpty(nextOpenId)){
            params.add(new RequestParameter("next_openid", nextOpenId));
        }
        return sendRequest(APIContants.USER_OPENID_LIST, params, UserOpenIdListResponse.class);
    }
    
    public static class OpenidList extends JSONContent{
        private List<OpenIdRequest> user_list = new ArrayList<OpenIdRequest>();
        
        public OpenidList(List<String> openidList){
            for(String openid : openidList){
                user_list.add(new OpenIdRequest(openid));
            }
        }

        public List<OpenIdRequest> getUser_list() {
            return user_list;
        }
        public void setUser_list(List<OpenIdRequest> user_list) {
            this.user_list = user_list;
        }
    }
}
