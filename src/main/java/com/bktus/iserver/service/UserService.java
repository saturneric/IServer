package com.bktus.iserver.service;

import com.bktus.iserver.configure.security.IServerPasswordEncoder;
import com.bktus.iserver.model.User;
import com.bktus.iserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements IUserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private IServerPasswordEncoder passwordEncoder;

    @Override
    public boolean checkUsernameRegistered(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
