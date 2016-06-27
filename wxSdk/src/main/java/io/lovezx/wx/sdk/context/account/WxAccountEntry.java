package io.lovezx.wx.sdk.context.account;

import java.io.Serializable;

public class WxAccountEntry implements Serializable{
    private static final long serialVersionUID = 1L;
    /**appid*/
    private String appid;
    /***/
    private String secret;
    /**商户id*/
    private String mchid;
    /**证书地址*/
    private String certPath;
    /**key*/
    private String key;
    /**加密Key*/
    private String aesKey;
    /**reiceveToken*/
    private String token;
    /**wxNumber*/
    private String publicKey;
    
    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAesKey() {
		return aesKey;
	}
	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}
	public String getAppid() {
        return appid;
    }
    public String getSecret() {
        return secret;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public String getMchid() {
        return mchid;
    }
    public String getCertPath() {
        return certPath;
    }
    public void setMchid(String mchid) {
        this.mchid = mchid;
    }
    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
}
