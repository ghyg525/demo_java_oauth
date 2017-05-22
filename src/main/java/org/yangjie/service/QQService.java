package org.yangjie.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.yangjie.util.HttpUtil;
import org.yangjie.util.JsonUtil;

/**
 * QQ登录
 * @author YangJie [2017年2月23日 下午2:47:29]
 */
@Service
public class QQService{
	
	public static String APPID = "";
	public static String SECRET = "";
	public static String CALLBACK_URL = "http:///qq/callback";
	
	// 发起授权地址
	public static String AUTH_URL = "https://graph.qq.com/oauth2.0/authorize";
	// 获取token地址
	public static String TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
	// 获取授权信息地址
	public static String ME_URL = "https://graph.qq.com/oauth2.0/me";
	// 获取用户信息地址
	public static String INFO_URL = "https://graph.qq.com/user/get_user_info";
	
	
	/**
	 * 获取token
	 * @author YangJie [2017年2月22日 下午6:28:49]
	 * @param code
	 * @return
	 */
	public String getToken(String code) {
		String url = new StringBuilder(TOKEN_URL)
				.append("?client_id=").append(APPID)
				.append("&client_secret=").append(SECRET)
				.append("&grant_type=").append("authorization_code")
				.append("&redirect_uri=").append(CALLBACK_URL)
				.append("&code=").append(code).toString();
		String result = HttpUtil.get(url);
		// access_token=x&expires_in=x&refresh_token=x
		return result.split("&")[0].split("=")[1];
	}	
	
	/**
	 * 获取openid
	 * @author YangJie [2017年2月22日 下午6:28:49]
	 * @param token
	 * @return
	 */
	public String getOpenid(String token) {
		String url = new StringBuilder(ME_URL)
				.append("?access_token=").append(token).toString();
				// 如果申请过多应用联合, 可以通过添加参数&unionid=1获取
		String result = HttpUtil.get(url);
		// callback( {"client_id":"x","openid":"x"} );
		return result.split("\"")[7];
	}	
	
	
	/**
	 * 获取用户信息
	 * @author YangJie [2017年2月22日 下午6:28:49]
	 * @param openid
	 * @param token
	 * @return http://wiki.connect.qq.com/get_user_info
	 */
	public Map<String, String> getInfo(String openid, String token) {
		String url = new StringBuilder(INFO_URL)
				.append("?oauth_consumer_key=").append(APPID)
				.append("&openid=").append(openid)
				.append("&access_token=").append(token).toString();
		String result = HttpUtil.get(url);
		return JsonUtil.toObject(result, HashMap.class, Map.class, String.class, String.class);
	}	
	
}