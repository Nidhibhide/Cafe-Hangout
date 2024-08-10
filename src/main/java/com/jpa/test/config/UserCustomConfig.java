package com.jpa.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserCustomConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getDetailsService()
	{
		return new UserService();
	}
	
	@Bean
	public DaoAuthenticationProvider geAuthenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests(authorize->{
			//authorize.requestMatchers("/","/signup","/base","/login","/frame","/signup").permitAll();
			authorize.requestMatchers("/user/**").authenticated();
			authorize.anyRequest().permitAll();	
		});
		http.formLogin(formLogin->{
			formLogin.loginPage("/login");
			formLogin.loginProcessingUrl("/userLogin");
			formLogin.defaultSuccessUrl("/user/dashboard");
	
				
		});
		http.logout(logout->{
			logout.logoutUrl("/signout");
			logout.logoutSuccessUrl("/login?logout=true");
			
		});
		
		http.csrf(AbstractHttpConfigurer::disable);
		//http.formLogin(Customizer.withDefaults());
		return http.build();
	}
	

}
