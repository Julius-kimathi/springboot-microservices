package com.cleviro.user.service;

import com.cleviro.user.VO.Department;
import com.cleviro.user.VO.ResponseTemplateVO;
import com.cleviro.user.entity.User;
import com.cleviro.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside of saveUser method of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside of getUserWithDepartment method of UserService");
        ResponseTemplateVO vo  = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        //Get department object from user-service microservice using Rest template object
        Department department =
                restTemplate.getForObject("http://localhost:8080/departments/"+user.getDepartmentId(),
                        Department.class);
        vo.setUser(user);
        vo.setDepartment(department);

        return vo;
    }
}
