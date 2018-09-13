package com.yzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.yzy.action.SocketServerListener;

@SpringBootApplication
public class Application {

	
 	@Bean
    public ServletRegistrationBean<SocketServerListener> MyServlet1(){
        return new ServletRegistrationBean<SocketServerListener>(new SocketServerListener(),"/myserv/*");
    }	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
