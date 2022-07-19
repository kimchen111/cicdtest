package com.ianunei.noname.service;

import com.ianunei.noname.db.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 帅小鸦
 * @date 2022/5/12
 */

public interface UserService {
    User register(User user);

    User login(String username, String password);

    boolean existsUsername(String username);

    Page<User> findAll(Pageable pageable, User user);
}
