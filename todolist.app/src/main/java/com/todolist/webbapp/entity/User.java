package com.todolist.webbapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.dialect.pagination.FirstLimitHandler;

import com.todolist.webbapp.validation.FieldMatch;
import com.todolist.webbapp.validation.ValidEmail;

@Entity
@Table(name = "users")

@FieldMatch.List({
		@FieldMatch(first = "password", second = "rePassword", message = "The password fields must match!") })

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@NotNull(message = "First name is required")
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	

	@ValidEmail
	@Column(name = "email")
	private String email;

	@NotNull(message = "Password is required")
	@Size(min = 5, message = "Password must contain at least 5 symbols")
	@Column(name = "password")
	private String password;

	
	
	@Column(name = "rePassword")
	private String rePassword;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Plan> plans;

	public User() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	// convenience method
	public void addPlans(Plan thePlan) {

		if (plans == null) {
			plans = new ArrayList<Plan>();
		}

		plans.add(thePlan);
	}

}
