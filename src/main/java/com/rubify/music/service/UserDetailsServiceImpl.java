package com.rubify.music.service;


import com.rubify.music.entity.UserEntity;
import com.rubify.music.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustomPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(passwordEncoder.getPasswordEncoder().encode("aaaaa"));
        return user;
    }
}
