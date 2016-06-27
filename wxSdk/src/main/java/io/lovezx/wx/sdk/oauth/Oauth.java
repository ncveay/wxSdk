package io.lovezx.wx.sdk.oauth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class description:用于3g页面进行oauth验证。AJAX请求不可用
 * 如果AJAX请求需要获取openId，则应在AJAX所在的页面进行oauth验证。
 * 在页面的ACTION中不需要对openId进行是否存在的判断，而AJAX中则建议进行判断（session超时后，openId会为空）
 * @author yuanyi
 * 2015-4-15 下午5:28:41
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)  
public @interface Oauth {

	/**
	 * Method description：
	 * Created by yuanyi 2015-4-15 下午5:31:47
	 * @return
	 */
	public boolean value() default false;
	
}
