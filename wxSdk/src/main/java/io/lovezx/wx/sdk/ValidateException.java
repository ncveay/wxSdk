package io.lovezx.wx.sdk;

public class ValidateException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    public ValidateException(){}
    
    public ValidateException(String message){
        super(message);
    }
    
    public ValidateException(Exception e){
        super(e);
    }
    
    public ValidateException(String message, Exception e){
        super(message, e);
    }
}
