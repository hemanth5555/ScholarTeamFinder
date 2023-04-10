package com.web.cyneuro.publication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.web.cyneuro.publication.*;

@Controller
@RequestMapping("/publication")
@ComponentScan(basePackages = {"com.web.cyneuro"})
public class publicationController {

    @Autowired
	publicationRepository publicationService;

    @Autowired
	private Environment env;
	RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/getNumber")
    @ResponseBody
	public String number(HttpServletRequest request){
		
		List<publication> publicationList = publicationService.findAll();
		String number = String.valueOf(publicationList.size());
        System.out.println(number);
		return number;
		
	}

}
