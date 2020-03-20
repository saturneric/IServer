package com.bktus.iserver.service;

import com.bktus.iserver.model.User;
import com.bktus.iserver.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class IServerUserDetailService implements UserDetailsService {
    @Resource
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            Optional<User> user = userRepository.findByUsername(s);
            if(!user.isPresent()) throw new UsernameNotFoundException("Username NOT Found In Database");
            return user.get();
    }
}
