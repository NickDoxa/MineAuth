package net.oasisgames.spring.services;

import lombok.RequiredArgsConstructor;
import net.oasisgames.spring.MineAuthBackendApplication;
import net.oasisgames.spring.dto.UserDto;
import net.oasisgames.spring.entity.Login;
import net.oasisgames.spring.entity.User;
import net.oasisgames.spring.mapping.UserMapper;
import net.oasisgames.spring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public UserDto getUserByUUID(UUID uuid) {
        return userMapper.userToUserDto(userRepository.findById(uuid).orElse(null));
    }

    public boolean userExists(UUID uuid) {
        return userRepository.existsById(uuid);
    }

    public void removeUser(UUID uuid) {
        userRepository.deleteById(uuid);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).toList();
    }

}
