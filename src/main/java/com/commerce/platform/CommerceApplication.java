package com.commerce.platform;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;


//@SuppressWarnings("deprecation")
@SuppressWarnings("deprecation")
@SpringBootApplication
@MapperScan("com.commerce.platform.mapper")
public class CommerceApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{
	public static final Logger log = Logger.getLogger(CommerceApplication.class);

	static {
		try {
			// 初始化log4j
			String log4jPath = CommerceApplication.class.getClassLoader().getResource("").getPath()
					+ "log4j.properties";
			// log.debug("初始化Log4j。。。。");
			// log.debug("path is " + log4jPath);
			PropertyConfigurator.configure(log4jPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CommerceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CommerceApplication.class, args);
		log.debug("============= SpringBoot Start Success =============");
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(9000);
	}
}
