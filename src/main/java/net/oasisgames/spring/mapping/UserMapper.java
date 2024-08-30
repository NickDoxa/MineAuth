package net.oasisgames.spring.mapping;

import net.oasisgames.spring.dto.LoginDto;
import net.oasisgames.spring.dto.UserDto;
import net.oasisgames.spring.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password")
    @Mapping(target = "uuid")
    @Mapping(target = "username")
    @Mapping(target = "login", ignore = true)
    User userDtoToUser(UserDto userDto);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "uuid")
    @Mapping(target = "username")
    UserDto userToUserDto(User user);

    @Mapping(target = "loggedIn", source = "login.loggedIn")
    LoginDto userToLoginDto(User user);

}
