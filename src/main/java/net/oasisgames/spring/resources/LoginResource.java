package net.oasisgames.spring.resources;

import lombok.RequiredArgsConstructor;
import net.oasisgames.spring.dto.LinkDto;
import net.oasisgames.spring.dto.ExistDto;
import net.oasisgames.spring.dto.LoginDto;
import net.oasisgames.spring.dto.UserDto;
import net.oasisgames.spring.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @PostMapping("/submit")
    public ResponseEntity<LoginDto> submitUser(@RequestBody UserDto user) {
        if (user.getUuid() == null) return ResponseEntity.ok().body(null);
        return ResponseEntity.ok().body(loginService.attemptLogin(user));
    }

    @CrossOrigin
    @GetMapping("/code/{id}")
    public ResponseEntity<LinkDto> getLoginCode(@PathVariable String id) {
        System.out.println(id);
        return ResponseEntity.ok().body(loginService.getDtoFromCode(id));
    }

    @CrossOrigin
    @GetMapping("/code/exists/{id}")
    public ResponseEntity<ExistDto> getCodeExists(@PathVariable String id) {
        return ResponseEntity.ok().body(loginService.existsByLink(id));
    }

}
