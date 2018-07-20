package com.server.auth.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//ref http://www.tinmegali.com/en/2017/06/25/oauth2-using-spring/

@Configuration
@EnableWebSecurity( debug = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private DataSource dataSource;
	
	private final String SQL_LOGIN = "select username, password, 1 from user where username=?";

    private final String SQL_PERMISSION = "SELECT u.username,r.role  FROM user u JOIN user_roles ur on u.user_id =ur.user_id  JOIN role r on ur.role_id = r.role_id  WHERE u.username=?";
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
    	.formLogin().disable() // disable form authentication
        .anonymous().disable() // disable anonymous user
        .authorizeRequests().anyRequest().denyAll(); // denying all access
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       /*auth.inMemoryAuthentication() // creating user in memory
                .withUser("user")
                    .password(getEncoder().encode("password")).roles("USER")
                .and().withUser("admin")
                    .password(getEncoder().encode("password")).authorities("ROLE_ADMIN");*/
    	 auth.jdbcAuthentication()
    	 	 .dataSource(dataSource)
    	 	 .passwordEncoder(getEncoder())
    	 	 .usersByUsernameQuery(SQL_LOGIN)
    	 	 .authoritiesByUsernameQuery(SQL_PERMISSION);
         
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // provides the default AuthenticationManager as a Bean
        return super.authenticationManagerBean();
    }
    
    @Bean
    public PasswordEncoder getEncoder(){
    	return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationSuccessHandler successHandler(){
    	return new AuthenticationSuccessHandler() {
			
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				// TODO Auto-generated method stub
				System.out.println("Hello world");
			}
		};
    }
}