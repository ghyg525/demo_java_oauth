package org.yangjie.util;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * HTTP工具类
 * @author YangJie [2016年5月24日 下午6:15:41]
 */
public class HttpUtil {

	private static final OkHttpClient client = new OkHttpClient();
	
	private static final MediaType jsonMediaType =  MediaType.parse("application/json; charset=utf-8");
	private static final MediaType xmlMediaType =  MediaType.parse("application/xml; charset=utf-8");
	
	
	
    /**
     * execute
     * @param request
     * @return
     */
    public static String execute(Request request){
    	try {
			return client.newCall(request).execute().body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
	
    /**
     * get
     * @param url
     * @return
     * @throws Exception
     */
    public static String get(String url) {
    	return execute(new Request.Builder().url(url).get().build());
    }

    /**
     * post
     * @param url
     * @param body
     * @return
     */
    public static String post(String url, RequestBody body){
    	return execute(new Request.Builder().url(url).post(body).build());
    }
    
    /**
     * post
     * @param url
     * @param formMap
     * @return
     */
    public static String post(String url, Map<String, String> formMap){
    	FormBody.Builder form = new FormBody.Builder();
    	if (formMap!=null && !formMap.isEmpty()) {
    		for(String key : formMap.keySet()){
    			form.add(key, formMap.get(key));
    		}
			return post(url, form.build());
		}
    	return null;
    }
    
    /**
     * post
     * @param url
     * @param body
     * @param mediaType
     * @return
     */
    public static String post(String url, String body, MediaType mediaType) {
    	return post(url, RequestBody.create(mediaType, body));
    }
    
    /**
     * post (json)
     * @param url
     * @return
     */
    public static String postJson(String url, String body) {
    	return post(url, body, jsonMediaType);
    }
    
    /**
     * post (xml)
     * @param url
     * @param body
     * @return
     */
    public static String postXml(String url, String body) {
    	return post(url, body, xmlMediaType);
    }
    
}
