package net.oasisgames.spring.services;

import lombok.RequiredArgsConstructor;
import net.oasisgames.spring.MineAuthBackendApplication;
import net.oasisgames.spring.dto.LinkDto;
import net.oasisgames.spring.dto.LinkExistDto;
import net.oasisgames.spring.dto.LoginDto;
import net.oasisgames.spring.dto.UserDto;
import net.oasisgames.spring.entity.Link;
import net.oasisgames.spring.entity.Login;
import net.oasisgames.spring.entity.User;
import net.oasisgames.spring.mapping.LinkMapper;
import net.oasisgames.spring.mapping.UserMapper;
import net.oasisgames.spring.repositories.LinkRepository;
import net.oasisgames.spring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final LinkMapper linkMapper;
    private final LinkRepository linkRepository;
    private final UserService userService;

    public LoginDto isLoggedIn(UserDto userDto) {
        return userMapper.userToLoginDto(userRepository.findById(userDto.getUuid()).orElse(null));
    }

    public LinkDto generateLinkCode(UserDto userDto) {
        Link link = MineAuthBackendApplication.getContext().getBean(Link.class);
        link.setUuid(userDto.getUuid());
        link.setLink(linkGenerator(userDto.getUuid()));
        linkRepository.save(link);
        return linkMapper.linkToLinkDto(link);
    }

    private String linkGenerator(String uuid) {
        String output;
        Random random = new Random();
        do {
            output = uuid.substring(0, 4) + String.format("%04d", random.nextInt(10000));
        } while (linkRepository.getAllLinks().contains(output));
        return output;
    }

    public LoginDto attemptLogin(UserDto userDto) {
        if (!userService.userExists(userDto.getUuid())) return null;
        User user = userRepository.findById(userDto.getUuid()).orElse(null);
        if (user == null) return null;
        Login login = user.getLogin();
        if (login == null) return null;
        login.setLoggedIn(user.getUsername().equalsIgnoreCase(userDto.getUsername()) &&
                user.getPassword().equals(userDto.getPassword()));
        user.setLogin(login);
        userRepository.save(user);
        return userMapper.userToLoginDto(user);
    }

    public LinkDto getDtoFromCode(String code) {
        return linkMapper.linkToLinkDto(linkRepository.getUUIDByLink(code));
    }

    public LinkExistDto existsByLink(String code) {
        LinkExistDto exists = new LinkExistDto();
        exists.setCodeExists(linkRepository.existsByLink(code));
        return exists;
    }

}
