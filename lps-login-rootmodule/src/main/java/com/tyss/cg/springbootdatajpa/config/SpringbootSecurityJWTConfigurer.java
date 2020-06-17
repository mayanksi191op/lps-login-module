package com.tyss.cg.springbootdatajpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tyss.cg.springbootdatajpa.filter.JwtRequestFilter;
import com.tyss.cg.springbootdatajpa.security.BootAunthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringbootSecurityJWTConfigurer extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Autowired
	private BootAunthenticationEntryPoint  bootAuthenticationEntryPoint;

	@Autowired 
	private UserDetailsService userDetailsService;

	@Autowired 
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService);

	} // End of configureGlobal()


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable()
		.authorizeRequests().antMatchers("/api/login").permitAll()
		.and()
		.authorizeRequests().antMatchers("/api/application/requested/", "/api/application/rejected/", "/api/application/approved/", "/api/application/requested/{pageNo}/{itemsPerPage}/{fieldname}", "/api/application/requested/{pageNo}/{itemsPerPage}", "/api/loanprograms", "/api/loanprograms/{loan_no}", "/api/update","/api/loanprograms/update", "/api/loanprograms/add", "/api/clients", "/api/user/{email}", "/api/clients", "/api/clients/{userid}").hasRole("ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/loanprograms", "/api/makeloan/{email}", "/api/user/{email}", "/api/customers/{userid}", "/api/customers", "/api/customers/password/put", "/api/customers/application/{email}").hasRole("CUSTOMER")
		.and()
		.authorizeRequests().antMatchers("/api/application", "/api/application/{appId}", "/api/application/requested/", "/api/application/rejected/", "/api/application/approved/", "/api/loanprograms", "/api/application/setapprove/{loanid}").hasRole("LAD")
		.and()
		.authorizeRequests().antMatchers("/api/loanprograms", "/api/loanprograms/{pageNo}/{itemsPerPage}/{fieldname}", "/api/loanprograms/{pageNo}/{itemsPerPage}", "/api/user/{email}", "/api/customers/put/", "/api/application/requested/", "/api/application/rejected/", "/api/application/approved/", "/api/customers/password/put", "/api/application/setreject/{loanid}", "/api/application/rejected/{pageNo}/{itemsPerPage}", "/api/application/rejected/{pageNo}/{itemsPerPage}/{fieldname}", "/api/application/approved/{pageNo}/{itemsPerPage}", "/api/application/approved/{pageNo}/{itemsPerPage}/{fieldname}", "/api/application/requested/{pageNo}/{itemsPerPage}", "/api/application/requested/{pageNo}/{itemsPerPage}/{fieldname}").permitAll()
		.and()
		.authorizeRequests().antMatchers("/api/customers", "/api/customers/{email}/{password}").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(bootAuthenticationEntryPoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}// End of configure()
}
