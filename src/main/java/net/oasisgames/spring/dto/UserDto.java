package net.oasisgames.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String uuid;
    private String username;
    private String password = "";

    @Override
    public String toString() {
        return "UserDto [uuid=" + uuid + ", username=" + username + ", password=" + password + "]";
    }

}
