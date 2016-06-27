package io.lovezx.wx.sdk.receive;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

public class EncryptMsg {
	
	private String nonce;
	private String timestamp;
	private String signature;
	
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	private String encrypt(String content, String algorithm) {
        byte[] bytes = content.getBytes();
        try {
            MessageDigest msgDigest = MessageDigest.getInstance(algorithm);
            msgDigest.update(bytes);
            byte[] digestBytes = msgDigest.digest();
            return  new String(Hex.encodeHex(digestBytes));
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }
    public boolean checkSignature(String token){
        if(token ==null || timestamp==null || nonce==null){
            return false;
        }
        String[] str = { token, timestamp, nonce};
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        
        String s = encrypt(bigStr, "SHA-1");
        
        if(!signature.equals(s)){
            return false;
        }
        return true;
    }
	
}
