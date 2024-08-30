package net.oasisgames.spring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LoginDto {

    private UUID uuid;
    private boolean loggedIn;

}
