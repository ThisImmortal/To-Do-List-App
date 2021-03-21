package com.todolist.webbapp.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.aopalliance.intercept.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.CacheControl;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.net.MailSender;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = "com.todolist.webbapp")
@PropertySource("classpath:persistence-mysql.properties")
public class WebAppConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;

	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");

		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

	}
	
	

	@Bean
	public DataSource securityDataSource() {

		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {

			throw new RuntimeException(e);
		}

		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));

		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return securityDataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setDataSource(securityDataSource());

		sessionFactory.setPackagesToScan(new String[] { "com.todolist.webbapp.entity" });

		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	private Properties hibernateProperties() {

		Properties properties = new Properties();

		properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));

		return properties;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {

		HibernateTransactionManager transactionManager = new HibernateTransactionManager();

		transactionManager.setSessionFactory(sessionFactory().getObject());

		return transactionManager;
	}
	
	
	//Password encoder bean
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	
	
	

	// Helper class
	private int getIntProperty(String propName) {

		String propVal = env.getProperty(propName);

		int intPropVal = Integer.parseInt(propVal);

		return intPropVal;
	}

}
