package com.ianunei.noname.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ianunei.noname.enums.Sex;
import com.ianunei.noname.enums.UserType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author 帅小鸦
 * @date 2022/5/12
 */

@Getter
@Setter
@Table
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User extends BaseEntity implements Serializable {
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private LocalDate birthday;

    private String phone;

    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;
}
