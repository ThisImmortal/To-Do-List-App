package com.todolist.webbapp.controller;

import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.context.request.WebRequest;

import com.todolist.webbapp.entity.Login;
import com.todolist.webbapp.entity.User;

import com.todolist.webbapp.service.UserService;

import javassist.expr.NewArray;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
	}
	@GetMapping("/showRegistrationForm")
	public String showRegistrationPage(Model theModel) {
		
		theModel.addAttribute("user", new User());
		
		return "registration-form";
	}
	
	@PostMapping("/registrationProcess")
	public String registerUser(@Valid @ModelAttribute("user") User theUser, BindingResult result, WebRequest request, Model theModel,
			                   @ModelAttribute("login") Login login) {
		
		User registeredUser = new User();
		
		String email = theUser.getEmail();
		String password = theUser.getPassword();
		Login theLogin = new Login();
		theLogin.setEmail(email);
		theLogin.setPassword(password);
		
		if(result.hasErrors()) {
			
			return "registration-form";
		}
		
		registeredUser = userService.validateUser(theLogin);
		
		if(registeredUser != null) {
			
			theModel.addAttribute("error", "There is already an account with this email " + email);
			
			return "registration-form";
		}
		
		userService.saveUser(theUser);
		
		
		
		return "registration-success";
	}
	

	
	
}
