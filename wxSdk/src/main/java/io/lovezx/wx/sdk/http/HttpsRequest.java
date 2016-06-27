package io.lovezx.wx.sdk.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http链接客户端
 * @author yuanyi
 *
 */
@SuppressWarnings("deprecation")
public final class HttpsRequest {

	private final static Logger log = LoggerFactory.getLogger(HttpsRequest.class);
	private final static String ENCODE = "UTF-8";

    //连接超时时间，默认5秒 
    private static int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private static int connectTimeout = 10000;

    //请求器的配置 4.3
//    private static RequestConfig requestConfig = RequestConfig.custom()
//            .setSocketTimeout(socketTimeout)
//            .setConnectTimeout(connectTimeout)
//            .build();
    
//    private SSLConnectionSocketFactory sslFactory; 4.3

//    //HTTP请求器
//    private CloseableHttpClient httpClient;
    
//    private static HttpClientConnectionManager manager ; 4.3
    
    private Scheme scheme;
    
    private static HttpParams params = new BasicHttpParams();
    
    private static PoolingClientConnectionManager conMgr = new PoolingClientConnectionManager();
    
    static{
 // 4.3
//        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();
//        pool.setDefaultMaxPerRoute(100);
//        manager = pool;
    	params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeout);
    	params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, socketTimeout);
    	params.setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT, 500L);
    	params.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);
    	conMgr.setMaxTotal(100);
    	conMgr.setDefaultMaxPerRoute(100);
    	
    }
    private HttpsRequest()  {
    }

    public HttpsRequest copy(){
        HttpsRequest request = new HttpsRequest();
        if(scheme != null){
            request.scheme = this.scheme;
        }
        return request;
    }
    
    /**
     * 获取SSL的http服务，每次获取都是一个新的对象， 客户端按需决定管是否对此服务做缓存
     * @param sslContext
     * @return
     */
	public static HttpsRequest getHttpsServer(KeyStore keyStore,String mchid){
        HttpsRequest request = new HttpsRequest();
        
		try {
			SSLSocketFactory sslFactory = new SSLSocketFactory(keyStore,mchid);
			request.scheme = new Scheme("https", sslFactory, 443);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
        return request;
    }
    
    /**
     * 普通http连接，每次获取都是一个新的对象， 客户端按需决定管是否对此服务做缓存
     * @return
     */
    public static HttpsRequest getCommonHttpServer(){
        HttpsRequest request = new HttpsRequest();
        return request;
    }
    
    private HttpClient getClient() throws NoSuchAlgorithmException, KeyManagementException{
    	HttpClient client = new DefaultHttpClient();
        if(scheme==null){
        	SSLContext sslcontext = SSLContext.getInstance("TLSv1"); 
        	sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());   
        	SSLSocketFactory sslFactory = new SSLSocketFactory(sslcontext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); 
			scheme = new Scheme("https", sslFactory , 443);
        }
        client.getConnectionManager().getSchemeRegistry()
        .register(scheme);
        return client;
    }
    
	  //自定义私有类   
	  private static class TrustAnyTrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {	
		}
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return  new X509Certificate[]{};
		}   	      
	  }   
    
    public byte[] downLoad(String url){
    	int tryTime = 1;
        log.debug("send get request : "+url);
        HttpGet get = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = getClient().execute(get);
            if(HttpStatus.SC_OK != response.getStatusLine().getStatusCode()){
                log.error("http post request fail; statusCode:"+response.getStatusLine().getStatusCode());
            }else{
                HttpEntity entity = response.getEntity();
                return EntityUtils.toByteArray(entity);
            }
            return null;
        } catch (UnknownHostException e1){
        	tryTime = checkRetryTime(tryTime, e1);
            return downLoad(url);
        } catch (NoHttpResponseException e2){
        	tryTime = checkRetryTime(tryTime, e2);
            return downLoad(url);
        } catch (Exception e) {
            log.error("发送请求失败，请检查网络配置或者URL是否正确", e);
            throw new IllegalStateException(e);
        } finally {
        	if(response!=null){
        		try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
            get.releaseConnection();
        }
    }
    
	public byte[] downLoadByPost(String url,PostContent content){
    	int tryTime = 1;
        log.debug("send get request : "+url);
        HttpPost post = new HttpPost("url");
        String postDataXML = content.toString();

        log.debug("API，POST过去的数据是：");
        log.debug(postDataXML);
        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        
		try {
			StringEntity postEntity = new StringEntity(postDataXML, ENCODE);
			post.setEntity(postEntity);
		} catch (UnsupportedEncodingException e3) {
			// never happend
		}
		
        //设置请求器的配置
        post.setParams(params);
        HttpResponse response = null;
        try {
            response = getClient().execute(post);
            if(HttpStatus.SC_OK != response.getStatusLine().getStatusCode()){
                log.error("http post request fail; statusCode:"+response.getStatusLine().getStatusCode());
            }else{
                HttpEntity entity = response.getEntity();
                return EntityUtils.toByteArray(entity);
            }
            return null;
        } catch (UnknownHostException e1){
        	tryTime = checkRetryTime(tryTime, e1);
            return downLoadByPost(url, content);
        } catch (NoHttpResponseException e2){
        	tryTime = checkRetryTime(tryTime, e2);
            return downLoadByPost(url, content);
        } catch (Exception e) {
            log.error("发送请求失败，请检查网络配置或者URL是否正确", e);
            throw new IllegalStateException(e);
        } finally {
        	if(response!=null){
        		try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
    }
    
    public String sendGet(String url,int tryTime) {
        log.debug("send get request : "+url);
        HttpGet get = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = getClient().execute(get);
            if(HttpStatus.SC_OK != response.getStatusLine().getStatusCode()){
                log.error("http post request fail; statusCode:"+response.getStatusLine().getStatusCode());
            }else{
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, ENCODE);
            }
            return null;
        } catch (UnknownHostException e1){
        	log.warn("进行重试……", e1);
        	tryTime = checkRetryTime(tryTime, e1);
            return sendGet(url,tryTime);
        } catch (NoHttpResponseException e2){
        	log.warn("进行重试……", e2);
        	tryTime = checkRetryTime(tryTime, e2);
            return sendGet(url,tryTime);
        } catch (Exception e) {
            log.error("发送请求失败，请检查网络配置或者URL是否正确", e);
            throw new IllegalStateException(e);
        } finally {
        	if(response!=null){
        		try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
    }
    
    /**
     * 上传一个文件
     * @param url
     * @param file
     * @return
     */
    public String postFile(String url, byte[] content, String fileName){
        return postFile(url, content, fileName, null);
    }
    
    /**
     * 上传一个文件
     * @param url
     * @param file
     * @param otherContent 文本内容
     * @return
     */
    public String postFile(String url, byte[] content, String fileName, PostContent otherContent){
    	int tryTime  = 1;
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        
        MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                .addBinaryBody("media", content, ContentType.create("application/octet-stream"),"xx"+fileName.substring(fileName.lastIndexOf(".")));
        //上传视频特殊需要
        if(otherContent!=null){
            builder.addTextBody("description", otherContent.toString());
        }
                
        HttpEntity entity = builder.build();
         
        httpPost.setEntity(entity);
        
        log.debug("API，POST过去的数据是：");
        log.debug(fileName);
        
        //设置请求器的配置
        httpPost.setParams(params);

        log.debug("executing request" + httpPost.getRequestLine());
        HttpResponse response = null;
        try {
            response = getClient().execute(httpPost);
            if(HttpStatus.SC_OK != response.getStatusLine().getStatusCode()){
                log.warn("http post request fail; statusCode:"+response.getStatusLine().getStatusCode());
            }else{
                HttpEntity resEntity = response.getEntity();
                result = EntityUtils.toString(resEntity, ENCODE);
            }
        } catch (UnknownHostException e1){
        	tryTime = checkRetryTime(tryTime, e1);
            return postFile(url, content, fileName, otherContent);
        } catch (NoHttpResponseException e2){
        	tryTime = checkRetryTime(tryTime, e2);
            return postFile(url, content, fileName, otherContent);
        } catch (Exception e) {
            log.error("http post throw Exception", e);
            throw new IllegalStateException(e);
        } finally {
        	if(response!=null){
        		try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return result;
    }
    
    /**
     * 通过Https往API post xml数据
     *
     * @param url    API地址
     * @param xmlObj 要提交的XML数据对象
     * @return API 回包的实际数据
     */

    public String sendPost(String url, PostContent content) {
    	int tryTime = 1;
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        String postDataXML = content.toString();

        log.info("API，POST过去的数据是：");
        log.info(postDataXML);
        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
		try {
			StringEntity postEntity = new StringEntity(postDataXML, ENCODE);
			httpPost.setEntity(postEntity);
		} catch (UnsupportedEncodingException e3) {
			//never happend
		}
		

        //设置请求器的配置
        httpPost.setParams(params);

        log.debug("executing request" + httpPost.getRequestLine());
        HttpResponse response = null;
        try {
            response = getClient().execute(httpPost);
            if(HttpStatus.SC_OK != response.getStatusLine().getStatusCode()){
                log.warn("http post request fail; statusCode:"+response.getStatusLine().getStatusCode());
            }else{
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, ENCODE);
            }
        } catch (UnknownHostException e1){
        	tryTime = checkRetryTime(tryTime, e1);
            return sendPost(url, content);
        } catch (NoHttpResponseException e2){
        	tryTime = checkRetryTime(tryTime, e2);
            return sendPost(url, content);
        } catch (Exception e) {
            log.error("http post throw Exception", e);
            throw new IllegalStateException(e);
        } finally {
        	if(response!=null){
        		try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return result;
    }
    
    private int checkRetryTime(int tryTime, Exception e1) {
		if(tryTime > 2){
			log.error("发送请求失败，请检查网络配置或者URL是否正确", e1);
		    throw new IllegalStateException(e1);
		}
		tryTime++;
		return tryTime;
	}
}
