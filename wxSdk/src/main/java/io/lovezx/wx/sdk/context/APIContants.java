package io.lovezx.wx.sdk.context;

public enum APIContants {
    BASE_ACCESS_TOKEN, 
    WX_SERVER_IP_LIST,
    JS_API_TICKET,
    
    /*-------------------客服相关------------------*/
    /**添加*/
    MESSAGE_KF_ACCOUNT_ADD,
    /**修改*/
    MESSAGE_KF_ACCOUNT_UPDATE,
    /**删除*/
    MESSAGE_KF_ACCOUNT_DEL,
    /**设置头像*/
    MESSAGE_KF_ACCOUNT_HEAD,
    /**列表*/
    MESSAGE_KF_ACCOUNT_LIST,
    /**发送客服消息*/
    MESSAGE_KF_ACCOUNT_SEND,
    /**获取在线客服*/
    MESSAGE_KF_ACCOUNT_ONLINE,
    
    /*-------------------群发相关------------------*/
    /**上传图片*/
    MESSAGE_GS_UPLOAD_IMG,
    /**上传图文*/
    MESSAGE_GS_UPLOAD_NEWS,
    /**分组群发*/
    MESSAGE_GS_SEND_ALL,
    /**根据OPENID群发*/
    MESSAGE_GS_SEND_OPENDID,
    /**delete message */
    MESSAGE_GS_MESSAGE_DEL,
    /**preview interface*/
    MESSAGE_GS_MESSAGE_PREVIEW,
    /**get status from message*/
    MESSAGE_GS_MESSAGE_GETSTATUS,
    /**获取视频MEDIA_ID的特殊接口*/
    MESSAGE_GS_GET_VIDEO,
    
    /*--------------模板-----------------*/
    /**设置模板行业*/
    MESSAGE_TEMP_SET_INDUSTRY,
    /**获取模板ID*/
    MESSAGE_TEMP_ADD_TEMP,
    /**发送模板消息*/
    MESSAGE_TEMP_SEND_TEMP,
    /***素材管理*/
    /**上传临时素材*/
    MEDIA_TEMP_UPLOAD,
    /**获取临时视频素材，特殊接口*/
    MEDIA_TEMP_GET_VIDEO,
    /**获取临时素材*/
    MEDIA_TEMP_GET,
    /**添加永久素材*/
    MEDIA_PERMANENT_ADD,
    /**添加永久图文素材*/
    MEDIA_PERMANENT_ADD_NEWS,
    /**添加永久图文素材-添加图片支持*/
    MEDIA_PERMANENT_ADD_NEWS_SUPPORT,
    /**获取永久素材*/
    MEDIA_PERMANENT_GET,
    /**删除永久素材*/
    MEDIA_PERMANENT_DEL,
    /**获取永久素材列表*/
    MEDIA_PERMANENT_LIST,
    /**修改永久图文*/
    MEDIA_PERMANENT_UPDATE_NEWS,
    /**获取永久素材数量*/
    MEDIA_PERMANENT_COUNT,
    
    /*-------------用户---------------*/
    /**修改用户备注*/
    USER_REMARK_UPDATE,
    /**获取用户信息*/
    USER_INFO_GET,
    /**批量获取用户信息*/
    USER_INFO_BATCH_GET,
    /**获取关注用户的OPENID列表*/
    USER_OPENID_LIST,
    /**添加分组*/
    USER_GROUP_CREATE,
    /**获取分组*/
    USER_GROUP_GET,
    /**根据OPENID获取分组id*/
    USER_GROUP_GET_GROUPID,
    /**修改分组名称*/
    USER_GROUP_UPDATE,
    /**移动单个用户*/
    USER_GROUP_UPDATE_MEMBER,
    /**批量移动用户*/
    USER_GROUP_UPDATE_MEMBERS,
    /**删除分组*/
    USER_GROUP_DEL,
    
    /*----------------菜单------------------*/
    /**创建菜单*/
    MENU_CREATE,
    /**获取菜单*/
    MENU_GET,
    /**删除菜单*/
    MENU_DEL,
    
    /*---------------支付----------------*/
    /**统一下单*/
    PAY_ORDER_UNIFIED,
    /**退款*/
    PAY_ORDER_REFUND,
    /**查询订单*/
    PAY_ORDER_QUERY,
    /**关闭订单*/
    PAY_ORDER_CLOSE,
    /**查询退款订单*/
    PAY_ORDER_QUERY_REFUND,
    /**下载账单*/
    PAY_ORDER_DOWNLOAD,
    
    /*---------------现金红包支付----------------*/
    /**发放现金红包**/
    REDPACKET_PAY,
    /**查询**/
    REDPACKET_QUERY,
    
    /*--------------二维码---------------*/
    /**创建二维码*/
    QRCODE_CREATE,
    /**获取二维码图片*/
    QRCODE_SHOWQRCODE
}
