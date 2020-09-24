package com.kang.project.service;

import com.kang.project.model.RoleType;
import com.kang.project.model.User;
import com.kang.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    public void join(User user) {
        String rawPassword = user.getPassword();
        String hashPassword = encoder.encode(rawPassword);
        user.setPassword(hashPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }
}
