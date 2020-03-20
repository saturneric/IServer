package com.bktus.iserver.service;

import com.bktus.iserver.model.User;

public interface IUserService {

    boolean checkUsernameRegistered(String username);

    User createUser(String username, String password);

    User save(User user);
}
