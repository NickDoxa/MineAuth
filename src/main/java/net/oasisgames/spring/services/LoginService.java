package net.oasisgames.spring.services;

import lombok.RequiredArgsConstructor;
import net.oasisgames.spring.MineAuthBackendApplication;
import net.oasisgames.spring.dto.LinkDto;
import net.oasisgames.spring.dto.LoginDto;
import net.oasisgames.spring.dto.UserDto;
import net.oasisgames.spring.entity.LinkGen;
import net.oasisgames.spring.mapping.LinkMapper;
import net.oasisgames.spring.mapping.UserMapper;
import net.oasisgames.spring.repositories.LinkRepository;
import net.oasisgames.spring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final LinkMapper linkMapper;
    private final LinkRepository linkRepository;

    public LoginDto isLoggedIn(UserDto userDto) {
        return userMapper.userToLoginDto(userRepository.findById(userDto.getUuid()).orElse(null));
    }

    public LinkDto generateLinkCode(UserDto userDto) {
        LinkGen link = MineAuthBackendApplication.getContext().getBean(LinkGen.class);
        link.setUuid(userDto.getUuid());
        link.setLink(linkGenerator(userDto.getUuid()));
        linkRepository.save(link);
        return linkMapper.linkGenToLinkDto(link);
    }

    private String linkGenerator(UUID uuid) {
        String output;
        Random random = new Random();
        do {
            output = uuid.toString().substring(0, 4) + String.format("%04d", random.nextInt(10000));
        } while (linkRepository.getAllLinks().contains(output));
        return output;
    }

}
