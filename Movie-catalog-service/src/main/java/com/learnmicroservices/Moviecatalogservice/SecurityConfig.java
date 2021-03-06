package com.learnmicroservices.Moviecatalogservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	   @Autowired
	   UserDetailsService userDetailsService;
	   
	   @Override
	   public void configure(AuthenticationManagerBuilder auth) throws Exception
	   {
		   auth.userDetailsService(userDetailsService);
	   }
	
        @Override //overriding default spring security login -----authorization
		protected void configure(HttpSecurity http) throws Exception {
			http
			     .authorizeRequests()
				     .anyRequest().authenticated()
				     .and()
			         .formLogin()
			     	 .loginPage("/login")
		             .permitAll();
        }
        
        @Bean
        public PasswordEncoder getPasswordEncoder()
        {
        	return NoOpPasswordEncoder.getInstance();
        }
}
