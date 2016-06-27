package io.lovezx.wx.sdk.api;

import java.util.List;

import io.lovezx.wx.sdk.context.APIContants;
import io.lovezx.wx.sdk.http.PostContent;

public class Request {
    
    private APIContants api;
    private PostContent content;
    private Class<?> expectedClass;
    private List<RequestParameter> parameters;
    
    public static RequestBuilder builder (){
        return new RequestBuilder();
    }
    
    public APIContants getApi() {
        return api;
    }
    public PostContent getContent() {
        return content;
    }
    public Class<?> getExpectedClass() {
        return expectedClass;
    }
    public List<RequestParameter> getParameters() {
        return parameters;
    }
    public void setApi(APIContants api) {
        this.api = api;
    }
    public void setContent(PostContent content) {
        this.content = content;
    }
    public void setExpectedClass(Class<?> expectedClass) {
        this.expectedClass = expectedClass;
    }
    public void setParameters(List<RequestParameter> parameters) {
        this.parameters = parameters;
    }
    
}
