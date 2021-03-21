package com.todolist.webbapp.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todolist.webbapp.entity.Login;
import com.todolist.webbapp.entity.Plan;
import com.todolist.webbapp.entity.User;
import com.todolist.webbapp.service.PlanService;
import com.todolist.webbapp.service.UserService;

@Controller
@SessionAttributes("user")


public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlanService planService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
	}
	
	@GetMapping("/")
	public String showLoginPage(Model theModel) {
		
		Login login = new Login();
		theModel.addAttribute("login", login);
		
		return "fancy-login";
		}
	
	@GetMapping("/loginProcess")
	public String loginProcess(@Valid @ModelAttribute("login") Login theLogin, BindingResult result, ModelMap modelMap) {
		
		
		if(result.hasErrors()) {
			
			return "fancy-login";
		}
		User theUser = userService.validateUser(theLogin);
		
		if(theUser != null) {
			
			long theId = theUser.getId();
			List<Plan> plans = planService.getPlanList(theId);
			
			modelMap.addAttribute("user", theUser);
			modelMap.addAttribute("planList", plans);
			
			
			return "home-page";
		}
		else {
			modelMap.addAttribute("error", "Invalid username or password");
		}
		
		
		return "fancy-login";
	}
	
	@GetMapping("/logout")
	public String logOut(HttpServletResponse response, HttpServletRequest request, Model theModel) {

		HttpSession session = request.getSession(false);
		Login theLogin = new Login();
		theModel.addAttribute("login", theLogin);
		theModel.addAttribute("logout", "You have been logged out.");
		if (request.isRequestedSessionIdValid() && session != null) {

			session.removeAttribute("user");
			Cookie [] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			session.invalidate();

		}

		return "fancy-login";
	}

}
