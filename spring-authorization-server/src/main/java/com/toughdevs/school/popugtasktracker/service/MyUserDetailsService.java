package com.toughdevs.school.popugtasktracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.toughdevs.school.popugtasktracker.repository.AccountsRepository;
import com.toughdevs.school.popugtasktracker.repository.model.AccountEntity;
import com.toughdevs.school.popugtasktracker.web.domain.MyUserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	
    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
    	logger.info("doing login for: {}", email);
    	
        AccountEntity acc = accountsRepository.findByEmail(email);
        if (acc == null) {
            throw new UsernameNotFoundException(email);
        }
        logger.info("account found in DB: {}", email);
        
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.builder()
      	      .username(acc.getEmail())
      	      .password(acc.getPassword())
      	      .passwordEncoder(encoder::encode)
      	      .roles(acc.getRole())
      	      .build();
        
        return new MyUserPrincipal(user);
    }
}
