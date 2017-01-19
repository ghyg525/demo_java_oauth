package org.yangjie;


import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序入口, 启动器
 * @author YangJie [2015年11月4日 下午1:28:33]
 */
@SpringBootApplication
public class Application {

	private static Logger logger = Logger.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("server is running...");
	}
	
}
