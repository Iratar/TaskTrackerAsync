package com.toughdevs.school.popugtasktracker.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DefaultSecurityConfig {

	/**
	 * Spring Security filter chain to apply the default OAuth security and generate
	 * a default form login page.
	 */
	@Bean
	@Order(1)
	SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(withDefaults()); // Enable OpenID Connect 1.0
		return http.formLogin(withDefaults()).build();
	}

	/** Spring Security filter chain for authentication. */
	@Bean
	@Order(2)
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/signup", "/registerAccount").permitAll()
				.anyRequest().authenticated())
				// providing a form-based authentication by invoking the formLogin(defaults()) method
				//.formLogin(withDefaults());
				.formLogin((form) -> form
					.loginPage("/login")
					.permitAll()
				)
				.logout((logout) -> logout.permitAll());
		return http.build();
	}
	
	/** define a set of example users that we’ll use for testing. */
	@Bean
	UserDetailsService users() {
	    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    UserDetails user = User.builder()
	      .username("user")
	      .password("password")
	      .passwordEncoder(encoder::encode)
	      .roles("USER")
	      .build();
	    UserDetails admin = User.builder()
	  	      .username("admin")
	  	      .password("password")
	  	      .passwordEncoder(encoder::encode)
	  	      .roles("ADMIN")
	  	      .build();
	    List<UserDetails> users = new ArrayList<>();
	    users.add(user);
	    users.add(admin);
		return new InMemoryUserDetailsManager(users);
	}
}
