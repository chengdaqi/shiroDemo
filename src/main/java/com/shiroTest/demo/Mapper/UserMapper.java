package com.shiroTest.demo.Mapper;

import com.shiroTest.demo.model.User;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserMapper {
    User findUserByNamer(@RequestParam("username") String username);
}
