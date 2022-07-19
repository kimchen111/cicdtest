package com.ianunei.noname;

import com.ianunei.noname.db.entity.User;
import com.ianunei.noname.db.repository.UserRepository;
import com.ianunei.noname.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@RequiredArgsConstructor
@SpringBootTest
class ApplicationTests {
    private final UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void test1() {
        User user = new User();
        user.setUsername("萌小鸦");
        user.setPassword("123456");
        user.setType(UserType.ADMIN);
        userRepository.save(user);
    }

    @Test
    void test2() {
        System.out.println("测试FastGithub效果");
    }
}
