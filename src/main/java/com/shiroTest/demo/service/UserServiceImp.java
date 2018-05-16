package com.shiroTest.demo.service;

import com.shiroTest.demo.model.User;
import com.shiroTest.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImp implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByNamer(String username) {
        return userMapper.findUserByName(username);
    }
}
