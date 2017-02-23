package org.yangjie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yangjie.service.WeiboService;

/**
 * 微博登录
 * @author YangJie [2017年2月23日 下午3:54:01]
 */
@Controller
@RequestMapping("weibo")
public class WeiboController{
	
	@Autowired
	private WeiboService weiboService;
	
	
	/**
	 * 登录发起
	 * @author YangJie [2017年2月22日 下午6:28:49]
	 * @return
	 */
	@RequestMapping(value="/auth", method=RequestMethod.GET)
	public String auth() {
		String url = new StringBuilder(WeiboService.AUTH_URL)
				.append("?client_id=").append(WeiboService.APPID)
				.append("&redirect_uri=").append(WeiboService.CALLBACK_URL)
				.append("&state=moko").toString();
		return "redirect:" + url;
	}	
	
	/**
	 * 登录授权回调
	 * @author YangJie [2017年2月22日 下午6:28:49]
	 * @param code
	 * @param state
	 * @return
	 */
	@RequestMapping(value="/callback", method=RequestMethod.GET)
	public String callback(String code, String state) {
		String uid = weiboService.getUid(code);
		// 执行登录过程, 然后调整到个人首页
		return "redirect:/my.html?"+uid;
	}	
	
}