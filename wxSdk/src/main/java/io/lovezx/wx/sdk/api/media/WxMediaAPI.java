package io.lovezx.wx.sdk.api.media;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.Assert.FileLengthUnit;
import io.lovezx.wx.sdk.SdkUtil;
import io.lovezx.wx.sdk.ValidateException;
import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.AbstractResponse;
import io.lovezx.wx.sdk.api.MediaIDEntry;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.api.media.entity.MaterialType;
import io.lovezx.wx.sdk.api.media.entity.MediaIdResponse;
import io.lovezx.wx.sdk.api.media.entity.MediaListCountResponse;
import io.lovezx.wx.sdk.api.media.entity.MediaPageListResponse;
import io.lovezx.wx.sdk.api.media.entity.MediaPageRequest;
import io.lovezx.wx.sdk.api.media.entity.MediaType;
import io.lovezx.wx.sdk.api.media.entity.NewsPageListResponse;
import io.lovezx.wx.sdk.api.media.entity.NewsRequest;
import io.lovezx.wx.sdk.api.media.entity.NewsRequest.Article;
import io.lovezx.wx.sdk.api.media.entity.NewsUpdateRequest;
import io.lovezx.wx.sdk.api.media.entity.VideoUploadRequest;
import io.lovezx.wx.sdk.api.message.groupSend.entity.UploadNewsResponse;
import io.lovezx.wx.sdk.context.APIContants;

/**
 * 素材管理接口
 * @author yuanyi
 *
 */
public class WxMediaAPI extends APIRequestSupport{
    private static final Logger LOGGER = LoggerFactory.getLogger(WxMediaAPI.class);
    
    private static final String[] IMAGE_LIST = new String[]{"bmp", "png", "jpeg", "jpg", "gif"};
    private static final String[] VOICE_LIST = new String[]{"mp3", "wma", "wav", "amr"};
    private static final String[] VIDEO_LIST = new String[]{"mp4", "flv", "f4v", "webm", "m4v",
                                                "mov", "3pg", "3g2", "rm", "rmvb",
                                                "wmv", "avi", "asf", "mpg", "mpeg",
                                                "mpe", "ts", "div", "dv", "divx",
                                                "vob", "dat", "mkv", "swf", "lavf",
                                                "cpk", "dirac", "ram", "qt", "fli", "flc", "mod"};
    
    
    /**
     * 上传临时图文
     * */
    public UploadNewsResponse uploadTempNews(List<Article> articles){
        NewsRequest news = new NewsRequest();
        news.setArticles(articles);
        Assert.isValid(news);
        return sendRequest(APIContants.MESSAGE_GS_UPLOAD_NEWS, news, SdkUtil.createEmptyList(RequestParameter.class), UploadNewsResponse.class);
    }
    /**
     * 上传临时素材
     * @param file
     * @return
     */
    public UploadNewsResponse uploadTempMedia(File file){
        MediaType type = getType(file);
        if(type == MediaType.IMAGE){
            Assert.validFileType(file, IMAGE_LIST, 2, FileLengthUnit.MB);
        }else if(type == MediaType.VOICE){
            Assert.validFileType(file, VOICE_LIST, 2, FileLengthUnit.MB);
        }else if(type == MediaType.VIDEO){
            Assert.validFileType(file, VIDEO_LIST, 10, FileLengthUnit.MB);
        }else{
            throw new ValidateException("不支持的文件类型");
        }
        List<RequestParameter> params = Arrays.asList(new RequestParameter[]{new RequestParameter("type", type.name().toLowerCase())});
        byte[] content = new byte[(int)file.length()];
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(content);
        } catch (Exception e) {
            throw new IllegalArgumentException("错误的文件", e);
        }finally{
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        return sendRequestFormData(APIContants.MEDIA_TEMP_UPLOAD, content, file.getName(), params, UploadNewsResponse.class);
    }
    /**
     * 上传临时素材
     * @param file
     * @return
     */
    public UploadNewsResponse uploadTempMedia(byte[] content, String fileName, String fileType){
        MediaType type = getType(fileType);
        List<RequestParameter> params = Arrays.asList(new RequestParameter[]{new RequestParameter("type", type.name().toLowerCase())});
        return sendRequestFormData(APIContants.MEDIA_TEMP_UPLOAD, content, fileName, params, UploadNewsResponse.class);
    }
    
    /**
     * 下载临时素材， 如果下载出错，返回json数据包，客户端需要通过IO流获取
     * @param mediaId
     * @param isVideo 是否为视频文件
     * @return
     */
    public byte[] getTempMedia(String mediaId, boolean isVideo){
        Assert.notNull(mediaId, "mediaId不能为空");
        List<RequestParameter> params = Arrays.asList(new RequestParameter[]{new RequestParameter("media_id", mediaId)});
        if(isVideo){
            return downLoad(APIContants.MEDIA_TEMP_GET_VIDEO, params);
        }
        return downLoad(APIContants.MEDIA_TEMP_GET, params);
    }
    /**
     * 上传永久素材
     * @param file
     * @param body 如果上传的是视频素材，则需要此参数
     * @return
     */
    public MediaIdResponse uploadPermanentMedia(byte[] content, String fileName, String fileType, VideoUploadRequest body){
        MediaType type = getType(fileType);
        if(type==MediaType.VIDEO){
            Assert.isValid(body);
            return sendVideoFile(APIContants.MEDIA_PERMANENT_ADD, content, fileName, body, MediaIdResponse.class);
        }
        List<RequestParameter> params = Arrays.asList(new RequestParameter[]{new RequestParameter("type", type.name().toLowerCase())});
        return sendRequestFormData(APIContants.MEDIA_PERMANENT_ADD, content, fileName, params, MediaIdResponse.class);
    }
    /**
     * 上传永久图文
     * @param news
     * @return
     */
    public MediaIdResponse uploadPermanentNews(List<Article> articles){
        NewsRequest news = new NewsRequest();
        news.setArticles(articles);
        Assert.isValid(news);
        return sendRequest(APIContants.MEDIA_PERMANENT_ADD_NEWS, news, SdkUtil.createEmptyList(RequestParameter.class), MediaIdResponse.class);
    }
    /**
     * 获取上传图文需要的图文链接
     * @param file 图片，支持jpg,png 大小为1M
     * @return
     */
    public MediaIdResponse uploadNewsSupport_img(byte[] content, String fileName, String fileType){
        Assert.validFileType(content, fileType, new String[]{"jpg", "png"}, 2, FileLengthUnit.MB);
        return sendRequestFormData(APIContants.MEDIA_PERMANENT_ADD_NEWS_SUPPORT, content, fileName, SdkUtil.createEmptyList(RequestParameter.class), MediaIdResponse.class);
    }
    /**
     * 获取上传永久素材
     * @return
     */
    public byte[] getPermanentMedia(String mediaId){
        MediaIDEntry entry = new MediaIDEntry(mediaId);
        Assert.isValid(entry);
        return downLoadByPost(APIContants.MEDIA_PERMANENT_GET, entry);
    }
    /**
     * 获取上传的永久图文或视频， 图文和视频返回JSON
     * @return
     */
    public AbstractResponse getPermanentMedia_video_news(String mediaId){
        MediaIDEntry entry = new MediaIDEntry(mediaId);
        Assert.isValid(entry);
        //TODO
        throw new UnsupportedOperationException("当前版本不支持");
    }
    /**
     * 删除永久素材
     * @param id
     * @return
     */
    public AbstractResponse delPermanentMedia(String mediaId){
        MediaIDEntry entry = new MediaIDEntry(mediaId);
        Assert.isValid(entry);
        return sendRequest(APIContants.MEDIA_PERMANENT_DEL, SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    /**
     * 修改永久素材--图文
     * @param mediaId 要修改的图文的media_id
     * @param index 要修改的文章的位置
     * @param article 修改的文章内容
     * @return
     */
    public AbstractResponse updatePermanentMedia_news(String mediaId, int index, Article article){
        NewsUpdateRequest update = new NewsUpdateRequest();
        update.setArticles(article);
        update.setIndex(index);
        update.setMedia_id(mediaId);
        Assert.isValid(update);
        return sendRequest(APIContants.MEDIA_PERMANENT_UPDATE_NEWS, update, SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    /**
     * 获取永久素材总数
     * @return
     */
    public MediaListCountResponse getPermanentMediaListCount(){
        return sendRequest(APIContants.MEDIA_PERMANENT_COUNT, SdkUtil.createEmptyList(RequestParameter.class), MediaListCountResponse.class);
    }
    /**
     * 获取永久素材列表（非图文）
     * @param start 开始位置
     * @param count 获取数量 1-20
     * @param type 素材类型
     * @return
     * @throws UnsupportedOperationException 当type的值为NEWS时抛出，请使用 {@link #getPermanentNewsList(int, int)}
     */
    public MediaPageListResponse getPermanentMediaList(int start, int count, MaterialType type){
        if(MaterialType.NEWS==type){
            throw new UnsupportedOperationException("请使用 #getPermanentNewsList()");
        }
        MediaPageRequest page = new MediaPageRequest();
        page.setCount(count);
        page.setOffset(start);
        page.setType(type.name());
        Assert.isValid(page);
        return sendRequest(APIContants.MEDIA_PERMANENT_LIST, page, SdkUtil.createEmptyList(RequestParameter.class), MediaPageListResponse.class);
    }
    /**
     * 获取永久素材列表（图文）
     * @param start 开始位置
     * @param count 获取数量 1-20
     * @return
     */
    public NewsPageListResponse getPermanentNewsList(int start, int count){
        MediaPageRequest page = new MediaPageRequest();
        page.setCount(count);
        page.setOffset(start);
        page.setType(MaterialType.NEWS.name());
        Assert.isValid(page);
        return sendRequest(APIContants.MEDIA_PERMANENT_LIST, page, SdkUtil.createEmptyList(RequestParameter.class), NewsPageListResponse.class);
    }
    
    private MediaType getType(File file){
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        if(index < 0){
            return MediaType.UNKNOW;
        }
        String fileType = fileName.substring(index+1);
        LOGGER.debug("fileType = "+fileType);
        if(Arrays.asList(IMAGE_LIST).contains(fileType)){
            return MediaType.IMAGE;
        }else if(Arrays.asList(VOICE_LIST).contains(fileType)){
            return MediaType.VOICE;
        }else if(Arrays.asList(VIDEO_LIST).contains(fileType)){
            return MediaType.VIDEO;
        }
        return MediaType.UNKNOW;
    }
    
    private MediaType getType(String fileType){
        if(Arrays.asList(IMAGE_LIST).contains(fileType)){
            return MediaType.IMAGE;
        }else if(Arrays.asList(VOICE_LIST).contains(fileType)){
            return MediaType.VOICE;
        }else if(Arrays.asList(VIDEO_LIST).contains(fileType)){
            return MediaType.VIDEO;
        }
        return MediaType.UNKNOW;
    }
}
