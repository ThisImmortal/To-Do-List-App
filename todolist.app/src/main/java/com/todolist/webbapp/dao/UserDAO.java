package com.todolist.webbapp.dao;

import com.todolist.webbapp.entity.Login;
import com.todolist.webbapp.entity.User;

public interface UserDAO {

	User validateUser(Login theLogin);
	
	void saveUser(User user);

}
