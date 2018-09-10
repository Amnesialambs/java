package com.yzy.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {

	
	@RequestMapping(method=RequestMethod.GET)
	public String sayHello() {
		
		return "Hello World !";
	}
	
}
