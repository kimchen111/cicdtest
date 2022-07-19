package com.ianunei.noname.db.repository;

import com.ianunei.noname.db.entity.User;

/**
 * @author 帅小鸦
 * @date 2022/5/12
 */

public interface UserRepository extends BaseRepository<User, String> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
