package io.lovezx.wx.sdk.api.message.groupSend;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
/**
 * 接口请使用以下子类<br>
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.CardGroupSendRequest 卡券
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.ImageGroupSendRequest 图片
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.NewsGroupSendRequest 图文
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.TextGroupSendRequest 文本
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.VideoGroupSendRequest 视频
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.VoiceGroupSendRequest 音频
 * @author yuanyi
 *
 */
public abstract class GroupSendSupport extends MsgTypeSupport implements Serializable{
    

	private static final long serialVersionUID = 5700012063653297318L;
	
	protected Filter filter;
    
    public Filter getFilter() {
        return filter;
    }
    public void setFilter(Filter filter) {
        this.filter = filter;
    }
    @Override
    public boolean checkValid() {
       if(!super.checkValid()) return false;
       if(filter == null) return false;
       if(!filter.is_to_all && StringUtils.isEmpty(filter.group_id)){
           return false;
       } 
       return true;
    }

    public static class Filter implements Serializable{

		private static final long serialVersionUID = 2010117380155290554L;
		
		private boolean is_to_all;
        private String group_id;
        public boolean isIs_to_all() {
            return is_to_all;
        }
        public String getGroup_id() {
            return group_id;
        }
        public void setIs_to_all(boolean is_to_all) {
            this.is_to_all = is_to_all;
        }
        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }
    }
}
