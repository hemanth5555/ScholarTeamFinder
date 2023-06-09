package com.web.cyneuro.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Calendar;
import java.text.DateFormat; 

import com.web.cyneuro.user.users;
import com.web.cyneuro.user.repository.*;

@Controller
@RequestMapping("/user")
@ComponentScan(basePackages = {"com.web.cyneuro"})
public class userController {

	@Autowired
	userRepository userService;
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
  
	/**
	 * Go to register page
	 * 
	 * @return
	 */
	@RequestMapping("/register")
	public String register() {
		return "register_new";
	}
 
	/**
	 * 
	 * Doing registration, then go to index page or go to register page(when failed)
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/doregister")
	public ModelAndView register(HttpServletRequest request, users user, Map<String, Object> map) {
		String username = request.getParameter("uname");
        String user_FirstName = request.getParameter("fname");
        String user_LastName = request.getParameter("lname");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		String affiliation = request.getParameter("affiliation");
		String title = request.getParameter("title");
		String interests = request.getParameter("interests");
		String description = request.getParameter("description");
		
		if (password.equals(password2)) {
			if (registerUser(email) == true) {
				users user1 = new users();
				user1.setUsername(username);
                user1.setUserFirstName(user_FirstName);
                user1.setUserLastName(user_LastName);
				user1.setPassword(password);
				user1.setEmail(email);
				user1.setAffiliation(affiliation);
				user1.setTitle(title);
				user1.setDescription(description);
		        Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);
		        user1.setCreateat(strDate);
                user1.setInterets(interests);
				userService.save(user1);
				ModelAndView mav =new ModelAndView("redirect:/login");
				return mav;
			} else {
				map.put("msg", "Email address is invalid! Please try another email.");
				ModelAndView mav = new ModelAndView("redirect:/user/register?error=true");
				return mav;
			}
		} else {
			map.put("msg", "Two passwords are not equal.");
			ModelAndView mav = new ModelAndView("redirect:/user/register?error=true");
			return mav;
		}
	}
 
	public Boolean registerUser(String email) {
		Boolean a = true;
		if (userService.findByEmail(email) == null) {
			return a;
		} else {
			return false;
		}
	}
 
	
 
	/**
	 * Doing login
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/dologin")
	public ModelAndView dologin(HttpServletRequest request, Map<String, Object> map) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
        System.out.print(email);
		if (email == null || password == null) {
			map.put("msg", "Username or password is required!");
			ModelAndView mav = new ModelAndView("redirect:/login?error=true");
			return mav;
		}
		users user = userService.findByEmailAndPassword(email, password);
        System.out.println(user);
		if (user != null) {
			map.put("msg", "Successfully!");
			map.put("username", user.getUsername());
			System.out.print(user.getUsername());
			ModelAndView mav =new ModelAndView("redirect:/#!");
			return mav;
		} else {
			map.put("msg", "Login failed! Please try again.");
			System.out.println(map);
			ModelAndView mav = new ModelAndView("redirect:/login?error=true");
			return mav;			
		}
	}
	
	@RequestMapping("/logout")
	public String logoutUser( HttpServletRequest request){
		
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("UserSessionVO");
		
		return "redirect:/";
		
	}

	@Autowired
	private Environment env;
	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping("/number")
	@ResponseBody
	public String number(HttpServletRequest request){
		
		List<users> userList = userService.findAll();
		String number = String.valueOf(userList.size());
		System.out.println(number);
		return number;
		
	}

	/**
	 * Starting questionnaire
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSurvay")
	public ModelAndView getSurvay(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("survay");; 
		mav.addObject("id", request.getParameter("id"));
		return mav;
			
	}

}

