package com.slt.documentmanagment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// implements UserDetailsService
public class CustomUserDetailsService{

//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(input);
//        if (user == null)
//            throw new BadCredentialsException("Bad credentials");
//        new AccountStatusUserDetailsChecker().check(user);
//        return user;
//    }

}
