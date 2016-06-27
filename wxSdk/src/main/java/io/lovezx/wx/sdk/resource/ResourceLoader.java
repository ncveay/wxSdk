package io.lovezx.wx.sdk.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 资源加载
 * @author yuanyi
 *
 */
public class ResourceLoader {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceLoader.class);
    
    public Properties loadProperties(String filePath) throws FileNotFoundException{
    	if(filePath.startsWith("classPath:")){
    		filePath = filePath.substring(10);
        }
    	if(!filePath.startsWith("/")){
    		filePath = "/" + filePath;
    	}
    	LOGGER.info("SDK_Properties_FilePath: " + filePath);
        InputStream is = this.getClass().getResourceAsStream(filePath);
        return loadProperties(is);
    }
    
    public Properties loadProperties(File file) throws FileNotFoundException{
        return loadProperties(new FileInputStream(file));
    }

    /**
     * 加载properties文件
     * @param is
     * @return
     */
    public Properties loadProperties(InputStream is){
        Properties p = new Properties();
        try {
            p.load(is);
        } catch (IOException e) {
           throw new IllegalStateException("读取配置文件错误！", e);
        } finally{
        	try {
        		if(null != is){
        			is.close();
        		}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return p;
    }
    
    public <T> T loadXmlAsObject(String filePath, Class<T> target) throws FileNotFoundException{
        return loadXmlAsObject(new File(filePath), target);
    }
    
    public <T> T loadXmlAsObject(File file, Class<T> target) throws FileNotFoundException{
        return loadXmlAsObject(new FileInputStream(file), target);
    }
    
    public <T> T loadXmlAsObject(InputStream is, Class<T> target) {
        return loadXmlAsObject(new InputStreamReader(is), target);
    }
    
    /**
     * 加载xml文件
     * @param reader
     * @param target
     * @return
     * @throws IllegalStateException xml文件读取错误
     */
    @SuppressWarnings("unchecked")
    public <T> T loadXmlAsObject(Reader reader, Class<T> target) {
        try {
            JAXBContext context = JAXBContext.newInstance(target);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            Object xmlObject = unmarshaller.unmarshal(reader); 
            if(!target.isInstance(xmlObject)){
                throw new IllegalStateException("xml文件读取错误，请检查xml文件是否正确");
            }
            return (T) xmlObject;
        } catch (JAXBException e) {
            throw new IllegalStateException("xml文件读取错误，请检查xml文件是否正确", e);
        }
    }
    
    /**
     * 将对象转换为xml
     * @param bean
     * @return
     */
    public <T extends Object> String beanToXml(T bean){
        try {  
            JAXBContext context = JAXBContext.newInstance(bean.getClass());  
            Marshaller marshaller = context.createMarshaller(); 
            StringWriter writer = new StringWriter();
            marshaller.marshal(bean, writer);  
            return writer.toString();
        } catch (JAXBException e) {  
            throw new IllegalStateException("对象解析错误", e);
        }  
    }
}
