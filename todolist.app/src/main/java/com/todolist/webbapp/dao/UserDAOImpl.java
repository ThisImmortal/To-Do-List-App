package com.todolist.webbapp.dao;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.todolist.webbapp.entity.Login;
import com.todolist.webbapp.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User validateUser(Login theLogin) {

		Session session = sessionFactory.openSession();

		String email = theLogin.getEmail();
		String enteredPassword = theLogin.getPassword();

		String SQLScript = "from User as u where u.email=:email";

		Query<User> query = session.createQuery(SQLScript);
		query.setParameter("email", email);

		List<User> userList = query.getResultList();
		User user = null;

		if (userList != null && userList.size() > 0) {

			String userPassword = userList.get(0).getPassword();

			if (passwordEncoder.matches(enteredPassword, userPassword)) {
				user = userList.get(0);
			}

		}

		return user;

	}

	@Override
	public void saveUser(User user) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(user);

	}

}
