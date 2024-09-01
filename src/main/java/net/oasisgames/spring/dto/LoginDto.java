package net.oasisgames.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String uuid;
    private boolean loggedIn;

}
