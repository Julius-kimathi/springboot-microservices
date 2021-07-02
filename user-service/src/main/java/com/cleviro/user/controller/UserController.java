package com.cleviro.user.controller;

import com.cleviro.user.VO.ResponseTemplateVO;
import com.cleviro.user.entity.User;
import com.cleviro.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("Inside of saveUser method of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId){
        log.info("Inside of getUserWithDepartment method of UserController");
        return userService.getUserWithDepartment(userId);
    }
}
