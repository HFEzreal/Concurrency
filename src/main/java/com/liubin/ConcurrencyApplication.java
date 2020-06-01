package com.liubin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ConcurrencyApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里一定要指向原先用main方法执行的Application启动类
		return builder.sources(ConcurrencyApplication.class);
	}

	public static void main(String[] args) {

		SpringApplication.run(ConcurrencyApplication.class, args);
	}

}
