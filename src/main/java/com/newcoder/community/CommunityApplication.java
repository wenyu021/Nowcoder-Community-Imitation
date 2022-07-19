package com.newcoder.community;

import com.newcoder.community.util.ComputerInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.io.IOException;

@SpringBootApplication
@MapperScan({"com.newcoder.community.dao"})
public class CommunityApplication {

	public static void main(String[] args) throws IOException {

		//SpringApplication.run(CommunityApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(CommunityApplication.class, args);
		Environment environment = context.getBean(Environment.class);
		//System.out.println("启动成功，首页地址：http://" + ComputerInfo.getIpAddr() + ":"  + environment.getProperty("server.port") + "/community/index");
		System.out.println("启动成功，首页地址：http://" + ComputerInfo.getIpAddr() + ":"  + "8080" + "/community/index");
	}

}
