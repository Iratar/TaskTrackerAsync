package com.toughdevs.school.popugtasktracker.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		/* every request needs to be authenticated. */
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
			/* we need to configure the login page URL defined in .yml */
			.oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/articles-client-oidc"))
			/* we need to configure the OAuth client. */
			.oauth2Client(withDefaults());
		return http.build();
	}
}
