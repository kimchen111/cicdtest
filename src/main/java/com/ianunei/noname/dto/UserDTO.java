package com.ianunei.noname.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.ianunei.noname.enums.Sex;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author 帅小鸦
 * @date 2022/5/12
 */

@Getter
@Setter
public class UserDTO {
    public interface Uuid {
    }

    public interface Register {
    }

    public interface Login {
    }

    @JsonView(Uuid.class)
    @NotNull(groups = Uuid.class)
    @Size(min = 32, max = 32, groups = Uuid.class)
    private String uuid;

    @JsonView({Register.class, Login.class})
    @NotNull(groups = {Register.class, Login.class})
    @Size(min = 3, max = 20, groups = {Register.class, Login.class})
    private String username;

    @JsonView({Register.class, Login.class})
    @NotNull(groups = {Register.class, Login.class})
    @Size(min = 6, max = 20, groups = {Register.class, Login.class})
    private String password;

    @JsonView(Register.class)
    private Sex sex;

    @JsonView(Register.class)
    private LocalDate birthday;

    @JsonView(Register.class)
    private String phone;

    @JsonView(Register.class)
    private String email;
}
