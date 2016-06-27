package io.lovezx.wx.sdk.api.user.qrcode;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class QrcodeRequest extends JSONContent implements SelfValid {
	
	private String action_name;
	private Integer expire_seconds;
	private ActionInfo action_info = new ActionInfo();
	
	/** 永久二维码(字符串形式的场景值ID) */
	public QrcodeRequest(String scene_str){
		this.action_name = "QR_LIMIT_STR_SCENE";
		this.action_info.setScene(scene_str);
	}
	
	/** 永久二维码(整数型的场景值ID) */
	public QrcodeRequest(Long scene_id){
		this.action_name = "QR_LIMIT_SCENE";
		this.action_info.setScene(scene_id);
	}
	
	/** 临时二维码 */
	public QrcodeRequest(Integer expire_seconds, Long scene_id){
		this.action_name = "QR_SCENE";
		this.expire_seconds = expire_seconds;
		this.action_info.setScene(scene_id);
	}

	public boolean checkValid() {
		if(null == action_name || "".equals(action_name)){
			return false;
		}
		
		if("QR_SCENE".equals(action_name) && null == expire_seconds){
			return true;
		}
		
		if("QR_SCENE".equals(action_name) && (expire_seconds > 2592000 || expire_seconds < 1)){
			return false;
		}
		
		if("QR_LIMIT_SCENE".equals(action_name) && (action_info.getScene().getScene_id() > 100000 || action_info.getScene().getScene_id() < 1)){
			return false;
		}
		if("QR_LIMIT_STR_SCENE".equals(action_name) && (action_info.scene.getScene_str().length() > 64 || action_info.getScene().getScene_str().length() < 1)){
			return false;
		}
		return true;
	}
	
	public Integer getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(Integer expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public ActionInfo getAction_info() {
		return action_info;
	}

	public void setAction_info(Long scene_id) {
		this.action_info.setScene(scene_id);
	}
	
	public void setAction_info(String scene_str) {
		this.action_info.setScene(scene_str);
	}

	private class ActionInfo{
		private Scene scene = new Scene();
		
		public Scene getScene() {
			return scene;
		}

		public void setScene(Long scene_id) {
			scene.setScene_id(scene_id);
		}

		public void setScene(String scene_str) {
			scene.setScene_str(scene_str);
		}

		private class Scene{
			private Long scene_id;
			private String scene_str;

			public Long getScene_id() {
				return scene_id;
			}
			public void setScene_id(Long scene_id) {
				this.scene_id = scene_id;
			}
			public String getScene_str() {
				return scene_str;
			}
			public void setScene_str(String scene_str) {
				this.scene_str = scene_str;
			}
		}
	}

}
