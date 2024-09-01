package net.oasisgames.spring.resources;

import lombok.RequiredArgsConstructor;
import net.oasisgames.spring.dto.UserDto;
import net.oasisgames.spring.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserResource {

    private final UserService userService;

    @RequestMapping("/create")
    public ResponseEntity<UserDto> createPlayer(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok().body(userService.getUserByUUID(userDto.getUuid()));
    }

    @RequestMapping("/delete")
    public ResponseEntity<UUID> deletePlayer(@RequestBody UUID uuid) {
        if (!userService.userExists(uuid.toString())) return null;
        userService.removeUser(uuid.toString());
        return ResponseEntity.ok().body(uuid);
    }

    @RequestMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

}