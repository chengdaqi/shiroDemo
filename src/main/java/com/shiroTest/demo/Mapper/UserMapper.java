package com.shiroTest.demo.mapper;

import com.shiroTest.demo.model.User;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserMapper {
    User findUserByName(@RequestParam("username") String username);
}
