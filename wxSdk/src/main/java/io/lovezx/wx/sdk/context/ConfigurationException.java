package io.lovezx.wx.sdk.context;

public class ConfigurationException extends RuntimeException {

    /**
     * v1
     */
    private static final long serialVersionUID = 4404384875742385353L;
    
    public ConfigurationException(){
        super();
    }
    
    public ConfigurationException(Exception e){
        super(e);
    }
    
    public ConfigurationException(String message){
        super(message);
    }
}
