package io.lovezx.wx.sdk.api.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SdkUtil;
import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.AbstractResponse;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.api.menu.entity.MenuRequest;
import io.lovezx.wx.sdk.context.APIContants;
/**
 * 微信菜单API
 * @author yuanyi
 *
 */
public class WxMenuAPI extends APIRequestSupport {
    /**
     * 创建一个菜单示例<br>
     * <code>MenuRequest menu = new MenuRequest();<br>
     * //第一个<br>
     * ClickBody click = new ClickBody();<br>
     * click.setKey("click_01");<br>
     * click.setName("点击一下");<br>
     * menu.getButton().add(click);<br>
     * //第二个<br>
     * ViewBody view = new ViewBody();<br>
     * view.setUrl("www.baidu.com");<br>
     * view.setName("baidu");<br>
     * menu.getButton().add(view);<br>
     * //第三个<br>
     * BaseLevelBody parent = new BaseLevelBody();<br>
     * parent.setName("第一级");<br>
     * parent.getSub_button().add(view);<br>
     * parent.getSub_button().add(click);<br>
     * //parent.getSub_button().add(anOtherOne);<br>
     * menu.getButton().add(parent);<br>
     * </code>
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.MenuRequest 菜单
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.BaseLevelBody 一级菜单（有子集菜单的）
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.ClickBody 点击
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.LocationBody 地理位置
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.MediaIdBody 下发消息（除文本消息）
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.PhotoOrAlbumBody 弹出拍照或者相册发图
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.ScanCodeMsgBody 扫码推事件且弹出“消息接收中”提示框
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.ScanCodePushBody 扫码推事件
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.TakePhotoBody 弹出系统拍照发图
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.ViewBody 跳转URL
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.ViewLimitedBody 跳转图文消息URL
     * @see com.cfwx.rox.weixin.sdk.api.menu.entity.WxPhotoBody 弹出微信相册发图器
     */
    public AbstractResponse createMenu(MenuRequest request){
        Assert.isValid(request);
        return sendRequest(APIContants.MENU_CREATE, request, SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    /**
     * 获取菜单<br>
     * [
         {  <br>
          &nbsp;&nbsp;"type":"click",<br>
          &nbsp;&nbsp;"name":"今日歌曲",<br>
          &nbsp;&nbsp;"key":"V1001_TODAY_MUSIC"<br>
      },
      {<br>
           &nbsp;&nbsp;"name":"菜单",<br>
           &nbsp;&nbsp;"sub_button":[<br>
           &nbsp;&nbsp;&nbsp;&nbsp;{    <br>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"type":"view",<br>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name":"搜索",<br>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"url":"http://www.soso.com/"<br>
            &nbsp;&nbsp;&nbsp;&nbsp;},<br>
            &nbsp;&nbsp;&nbsp;&nbsp;{<br>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"type":"view",<br>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name":"视频",<br>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"url":"http://v.qq.com/"<br>
            &nbsp;&nbsp;&nbsp;&nbsp;},<br>
            &nbsp;&nbsp;&nbsp;&nbsp;{<br>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"type":"click",<br>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name":"赞一下我们",<br>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"key":"V1001_GOOD"<br>
            &nbsp;&nbsp;&nbsp;&nbsp;}]<br>
       }]<br>
     * @return
     */
    public JSONArray getMenu(){
        JSONObject obj = sendRequest(APIContants.MENU_GET, SdkUtil.createEmptyList(RequestParameter.class));
        return obj.getJSONObject("menu").getJSONArray("button");
    }
    /**
     * 删除菜单
     * @return
     */
    public AbstractResponse delMenu(){
        return sendRequest(APIContants.MENU_DEL, SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    
}
