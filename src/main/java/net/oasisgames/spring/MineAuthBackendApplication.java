package net.oasisgames.spring;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MineAuthBackendApplication {

    @Getter
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(MineAuthBackendApplication.class, args);
    }

}
