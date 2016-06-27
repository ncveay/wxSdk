package io.lovezx.wx.sdk.receive;

public class AbstractReceiveProcessor implements ReceiveProcessor {

    public Resource onText(ReceiveContext context) {
        return null;
    }

    public Resource onImage(ReceiveContext context) {
        return null;
    }

    public Resource onVoice(ReceiveContext context) {
        return null;
    }

    public Resource onVideo(ReceiveContext context) {
        return null;
    }

    public Resource onLocation(ReceiveContext context) {
        return null;
    }

    public Resource onLink(ReceiveContext context) {
        return null;
    }

    public Resource onShortVideo(ReceiveContext context) {
        return null;
    }

    public Resource onSubscribe(ReceiveContext context) {
        return null;
    }
    
	public Resource onUnSubscribe(ReceiveContext context) {
		return null;
	}
    public Resource onScan(ReceiveContext context) {
        return null;
    }

    public Resource onLocationReport(ReceiveContext context) {
        return null;
    }

    public Resource onClick(ReceiveContext context) {
        return null;
    }

    public Resource onView(ReceiveContext context) {
        return null;
    }

    public Resource onDKFclose(ReceiveContext context){
    	return null;
    }

   	public Resource onDKFcreate(ReceiveContext context){
   		return null;
   	}

   	public Resource endMassSendJob(ReceiveContext context){
   		return null;
   	}
    

}
