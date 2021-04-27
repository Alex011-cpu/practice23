package com.example.practice15.services;

import com.example.practice15.models.SecurityUser;
import com.example.practice15.models.User;
import com.example.practice15.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(null);
    }

    public User saveUser(User user,String cookie){
        user.setSessionId(cookie);
        userRepository.saveAndFlush(user);
        return user;
    }
}
