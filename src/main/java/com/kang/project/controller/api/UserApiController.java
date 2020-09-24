package com.kang.project.controller.api;

import com.kang.project.model.User;
import com.kang.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinPost")
    public ResponseEntity<User> join(@RequestBody User user) {
        userService.join(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
