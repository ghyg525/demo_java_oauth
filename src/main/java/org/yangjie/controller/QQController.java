package org.yangjie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yangjie.service.QQService;

/**
 * QQ登录
 * @author YangJie [2017年2月23日 下午3:54:16]
 */
@Controller
@RequestMapping("qq")
public class QQController{
	
	@Autowired
	private QQService qqService;
	
	
	/**
	 * 登录发起
	 * @author YangJie [2017年2月22日 下午6:28:49]
	 * @return
	 */
	@RequestMapping(value="/auth", method=RequestMethod.GET)
	public String auth() {
		String url = new StringBuilder(QQService.AUTH_URL)
				.append("?client_id=").append(QQService.APPID)
				.append("&redirect_uri=").append(QQService.CALLBACK_URL)
				.append("&response_type=code&state=moko").toString();
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
		String token = qqService.getToken(code);
		String openid = qqService.getOpenid(token);
		// 执行登录过程, 然后调整到个人首页
		return "redirect:/my.html?"+openid;
	}	
	
}