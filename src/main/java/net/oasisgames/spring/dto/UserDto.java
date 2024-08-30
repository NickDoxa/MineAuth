package net.oasisgames.spring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID uuid;
    private String username;
    private String password = "";

}
