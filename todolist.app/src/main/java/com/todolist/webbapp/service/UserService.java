package com.todolist.webbapp.service;

import com.todolist.webbapp.entity.Login;
import com.todolist.webbapp.entity.User;


public interface UserService {

	User validateUser(Login theLogin);

	void saveUser(User theUser);

}
