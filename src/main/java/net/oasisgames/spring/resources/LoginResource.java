package net.oasisgames.spring.resources;

import lombok.RequiredArgsConstructor;
import net.oasisgames.spring.dto.LinkDto;
import net.oasisgames.spring.dto.LoginDto;
import net.oasisgames.spring.dto.UserDto;
import net.oasisgames.spring.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginResource {

    private final LoginService loginService;

    @RequestMapping("/status")
    public ResponseEntity<LoginDto> getLoginStatus(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(loginService.isLoggedIn(userDto));
    }

    @RequestMapping("/link")
    public ResponseEntity<LinkDto> generateLink(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(loginService.generateLinkCode(userDto));
    }

}
