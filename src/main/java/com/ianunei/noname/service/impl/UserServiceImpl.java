package com.ianunei.noname.service.impl;

import com.ianunei.noname.db.entity.User;
import com.ianunei.noname.db.repository.UserRepository;
import com.ianunei.noname.enums.UserType;
import com.ianunei.noname.service.UserService;
import com.ianunei.noname.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author 帅小鸦
 * @date 2022/5/12
 */

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService, InitializingBean {
    private final UserRepository userRepository;

    @Override
    public void afterPropertiesSet() {
        if (!existsUsername("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("ianunei-noname");
            user.setType(UserType.ADMIN);
            register(user);
        }
    }

    @Override
    public User register(User user) {
        user.setPassword(PasswordUtils.encrypt(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("用户名不存在");
        }

        if (!PasswordUtils.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("密码不正确");
        }

        return user;
    }

    @Override
    public boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Page<User> findAll(Pageable pageable, User user) {
        return userRepository.findAll(pageable, user);
    }
}
