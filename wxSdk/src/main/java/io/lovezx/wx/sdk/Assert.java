package io.lovezx.wx.sdk;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Assert {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(Assert.class);
    
    public static void isValid(SelfValid obj){
        if(!obj.checkValid()){
            LOGGER.error("参数校验失败，请检查传递的参数是否符合要求");
            throw new ValidateException("参数校验失败，请检查传递的参数是否符合要求");
        }            
    }
    
    public static void notNull(String content, String message){
        if(StringUtils.isBlank(content)){
            LOGGER.error(message);
            throw new ValidateException(message);
        }
    }
    
    /**
     * 文件校验
     * @param file
     * @param expectedTypes
     * @param length
     * @param unit 目前只支持M
     */
    public static void validFileType(File file, String[] expectedTypes, int length, FileLengthUnit unit){
        if(length!=0){
            if(file.length() > length*1024*1024){
                throw new ValidateException("文件过大, 期望大小："+length*1024*1024+"; 实际大小:"+file.length());
            }
        }
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(Arrays.asList(expectedTypes).contains(fileType)){
            String message = "文件类型错误, 期望类型："+Arrays.toString(expectedTypes)+"; 实际类型:"+fileType;
            LOGGER.error(message);
            throw new ValidateException(message);
        }
    }
    
    

    /**
     * 文件校验
     * @param file
     * @param expectedTypes
     * @param length
     * @param unit 目前只支持M
     */
    public static void validFileType(byte[] content, String fileType, String[] expectedTypes, int length, FileLengthUnit unit){
        if(length!=0){
            if(content.length > length*1024*1024){
                throw new ValidateException("文件过大, 期望大小："+length*1024*1024+"; 实际大小:"+content.length);
            }
        }
        if(Arrays.asList(expectedTypes).contains(fileType)){
            String message = "文件类型错误, 期望类型："+Arrays.toString(expectedTypes)+"; 实际类型:"+fileType;
            LOGGER.error(message);
            throw new ValidateException(message);
        }
    }
    
    
    public static enum FileLengthUnit{
       B, KB, MB, GB, TB;
    }
    
}
