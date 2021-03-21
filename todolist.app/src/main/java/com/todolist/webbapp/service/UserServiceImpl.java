package com.todolist.webbapp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.todolist.webbapp.dao.UserDAO;
import com.todolist.webbapp.entity.Login;
import com.todolist.webbapp.entity.User;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	


	@Override
	@Transactional
	public User validateUser(Login theLogin) {
		
		
		return userDAO.validateUser(theLogin);
	}




	@Override
	@Transactional
	public void saveUser(User theUser) {
		
		User user = new User();
		
		user.setFirstName(theUser.getFirstName());
		user.setLastName(theUser.getLastName());
		user.setEmail(theUser.getEmail());
		String encodedPassword = passwordEncoder.encode(theUser.getPassword());
		user.setPassword(encodedPassword);
		user.setRePassword(encodedPassword);
		
		
		userDAO.saveUser(user);
		
	}




	





	
}
