package com.example.caixaeletronicoapi.security;

import java.util.Optional;

import com.example.caixaeletronicoapi.model.User;
import com.example.caixaeletronicoapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public class JwtService implements UserDetailsService{

        @Autowired
        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                String[] usernameData= username.split(";");
                Optional<User> user = userRepository.findByUsername(usernameData[0], usernameData[1]);
                if(user.isPresent()) {
                        return user.get();
                }
                throw new UsernameNotFoundException("User not found");
        }

}
