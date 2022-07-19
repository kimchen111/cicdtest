package com.ianunei.noname;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;

/**
 * @author 帅小鸦
 * @date 2022/5/12
 */

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
@EnableJpaAuditing
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        String os = System.getProperty("os.name");
        if (os != null && os.toLowerCase().startsWith("windows")) {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("C:\\Program Files\\Mozilla Firefox\\firefox.exe http://localhost:8080/noname/doc.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
