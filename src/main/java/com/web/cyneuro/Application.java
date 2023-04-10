



package com.web.cyneuro;

import javax.servlet.http.HttpServletRequest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SpringBootApplication
@EnableScheduling
public class Application {
	
	
	public static void main(String[] args) {
		System.setProperty("server.contextPath", "/Scholarteamfinder");

		SpringApplication.run(Application.class, args);
		
	
	}
	
	@RequestMapping("/")
	public ModelAndView welcomePage(HttpServletRequest request) {
				
    	ModelAndView mav =new ModelAndView("index");
    	return mav;
				
	}
	
	@RequestMapping("/login")
	public ModelAndView loginPage(HttpServletRequest request) {
		System.out.println("Done!!!!!!");
    	ModelAndView mav =new ModelAndView();
    	mav.setViewName("login");
    	return mav;
				
	}
	
	
	
}
