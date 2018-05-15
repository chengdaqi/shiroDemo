package com.shiroTest.demo.service;

import com.shiroTest.demo.model.User;

public interface UserService {
    User findUserByNamer(String username);
}
