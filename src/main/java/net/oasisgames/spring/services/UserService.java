package net.oasisgames.spring.services;

import lombok.RequiredArgsConstructor;
import net.oasisgames.spring.MineAuthBackendApplication;
import net.oasisgames.spring.dto.ExistDto;
import net.oasisgames.spring.dto.UserDto;
import net.oasisgames.spring.entity.Login;
import net.oasisgames.spring.entity.User;
import net.oasisgames.spring.mapping.UserMapper;
import net.oasisgames.spring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void createUser(UserDto userDto) {
        if (userExists(userDto.getUuid())) return;
        User user = userMapper.userDtoToUser(userDto);
        user.setLogin(MineAuthBackendApplication.getContext().getBean(Login.class));
        userRepository.save(user);
    }

    public UserDto getUserByUUID(String uuid) {
        return userMapper.userToUserDto(userRepository.findById(uuid).orElse(null));
    }

    public boolean userExists(String uuid) {
        return userRepository.existsById(uuid);
    }

    public ExistDto userExistsDto(String uuid) {
        ExistDto existDto = new ExistDto();
        existDto.setObjExists(userExists(uuid));
        return existDto;
    }

    public void removeUser(String uuid) {
        userRepository.deleteById(uuid);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).toList();
    }

}
