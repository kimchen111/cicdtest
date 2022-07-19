package com.ianunei.noname.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.ianunei.noname.db.entity.User;
import com.ianunei.noname.dto.UserDTO;
import com.ianunei.noname.enums.UserType;
import com.ianunei.noname.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author 帅小鸦
 * @date 2022/5/12
 */

@Api(tags = "用户管理")
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public User register(@JsonView(UserDTO.Register.class)
                         @Validated(UserDTO.Register.class)
                         @RequestBody UserDTO dto) {
        if (userService.existsUsername(dto.getUsername())) {
            throw new IllegalArgumentException("用户名已存在");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setSex(dto.getSex());
        user.setBirthday(dto.getBirthday());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setType(UserType.USER);
        return userService.register(user);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ModelAndView login(@JsonView(UserDTO.Login.class)
                              @Validated(UserDTO.Login.class)
                              @RequestBody UserDTO dto, HttpSession session) {
        User user = userService.login(dto.getUsername(), dto.getPassword());
        session.setAttribute("user", user);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("main");
        return mv;
    }

    @ApiOperation("用户通用查询")
    @PostMapping("/query")
    public Page<User> query(@PageableDefault Pageable pageable, @RequestBody User user) {
        return userService.findAll(pageable, user);
    }

    @GetMapping("/login.html")
    public ModelAndView jumpLogin() {
        return new ModelAndView("login");
    }

    @GetMapping("/register.html")
    public ModelAndView jumpRegister() {
        return new ModelAndView("register");
    }

}
