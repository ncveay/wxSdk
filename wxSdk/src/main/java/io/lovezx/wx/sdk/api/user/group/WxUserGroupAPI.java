package io.lovezx.wx.sdk.api.user.group;
	
import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SdkUtil;
import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.AbstractResponse;
import io.lovezx.wx.sdk.api.OpenIdRequest;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.api.user.group.entity.UserGroupBatchMoveRequest;
import io.lovezx.wx.sdk.api.user.group.entity.UserGroupListResponse;
import io.lovezx.wx.sdk.api.user.group.entity.UserGroupMoveRequest;
import io.lovezx.wx.sdk.api.user.group.entity.UserGroupRequest;
import io.lovezx.wx.sdk.api.user.group.entity.UserGroupResponse;
import io.lovezx.wx.sdk.api.user.group.entity.UserGroupUpdateRequest;
import io.lovezx.wx.sdk.context.APIContants;
import io.lovezx.wx.sdk.http.JSONContent;
/**
 * 用户分组API， 最多支持100个分组
 * @author yuanyi
 *
 */
public class WxUserGroupAPI extends APIRequestSupport{
    /**
     * 创建分组
     * @param request
     * @return
     */
    public UserGroupResponse createUserGroup(UserGroupRequest request){
        Assert.isValid(request);
        return sendRequest(APIContants.USER_GROUP_CREATE, request, SdkUtil.createEmptyList(RequestParameter.class), UserGroupResponse.class);
    }
    /**
     * 获取分组
     * @param request
     * @return
     */
    public UserGroupListResponse getUserGroup(){
        return sendRequest(APIContants.USER_GROUP_GET, SdkUtil.createEmptyList(RequestParameter.class), UserGroupListResponse.class);
    }
    /**
     * 获取指定用户的分组
     * @param openId
     * @return
     */
    public int getGroupIdByOpenId(String openId){
        Assert.notNull(openId, "openId不能为空！");
        GroupIdResponse res = sendRequest(APIContants.USER_GROUP_GET_GROUPID, new OpenIdRequest(openId), SdkUtil.createEmptyList(RequestParameter.class), GroupIdResponse.class);
        return res.getGroupid();
    }
    /**
     * 修改分组名称
     * @param request
     * @return
     */
    public AbstractResponse updateGroupName(UserGroupUpdateRequest request){
        Assert.isValid(request);
        return sendRequest(APIContants.USER_GROUP_UPDATE, request, null, AbstractResponse.class);
    }
    /**
     * 移动用户分组
     * @param request
     * @return
     */
    public AbstractResponse moveUserFromGroup(UserGroupMoveRequest request){
        Assert.isValid(request);
        return sendRequest(APIContants.USER_GROUP_UPDATE_MEMBER, request, SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    /**
     * 批量移动用户分组
     * @param request
     * @return
     */
    public AbstractResponse moveUserFromGroup(UserGroupBatchMoveRequest request){
        Assert.isValid(request);
        return sendRequest(APIContants.USER_GROUP_UPDATE_MEMBERS, request, SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    /**
     * 删除分组
     * @param request
     * @return
     */
    public AbstractResponse delGroup(int groupid){
        return sendRequest(APIContants.USER_GROUP_DEL, new GroupIdRequest(groupid), SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    
    
    public static class GroupIdRequest extends JSONContent {
        private int groupid;
        
        public GroupIdRequest(int id){
            this.groupid = id;
        }
        public int getGroupid() {
            return groupid;
        }
        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }
    }
    public static class GroupIdResponse extends AbstractResponse{
        private int groupid;

        public int getGroupid() {
            return groupid;
        }
        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }
    }
}   
