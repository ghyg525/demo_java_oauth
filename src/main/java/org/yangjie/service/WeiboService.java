package org.yangjie.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.yangjie.util.HttpUtil;
import org.yangjie.util.JsonUtil;

/**
 * 微博登录
 * @author YangJie [2017年2月23日 下午2:47:29]
 */
@Service
public class WeiboService{
	
	public static String APPID = "";
	public static String SECRET = "";
	public static String CALLBACK_URL = "http:///weibo/callback";
	
	// 发起授权地址
	public static String AUTH_URL = "https://api.weibo.com/oauth2/authorize";
	// 获取token地址
	public static String TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
	// 获取用户信息地址
	public static String INFO_URL = "https://api.weibo.com/2/users/show.json";
	
	
	/**
	 * 获取uid
	 * @author YangJie [2017年2月22日 下午6:28:49]
	 * @param code
	 * @return
	 */
	public String getUid(String code) {
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("client_id", APPID);
		formMap.put("client_secret", SECRET);
		formMap.put("grant_type", "authorization_code");
		formMap.put("redirect_uri", CALLBACK_URL);
		formMap.put("code", code);
		String result = HttpUtil.post(TOKEN_URL, formMap);
		// {"access_token": "ACCESS_TOKEN","expires_in": 1234,"remind_in":"798114","uid":"12341234"}
		Map<String, String> resultMap = JsonUtil.toObject(result, HashMap.class, Map.class, String.class, String.class);
		return resultMap.get("uid");
	}	
	
	/**
	 * 获取用户信息
	 * @author YangJie [2017年2月22日 下午6:28:49]
	 * @param openid
	 * @param token
	 * @return
	 */
	public Map<String, String> getInfo(String openid, String token) {
		String url = new StringBuilder(INFO_URL)
				.append("?oauth_consumer_key=").append(APPID)
				.append("&openid=").append(openid)
				.append("&access_token=").append(token).toString();
		String result = HttpUtil.get(url);
		// http://open.weibo.com/wiki/2/users/show
		return JsonUtil.toObject(result, HashMap.class, Map.class, String.class, String.class);
	}	
	
}