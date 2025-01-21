package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.dtos.UserDto;
import nl.novi.techiteasycontroller.exceptions.BadRequestException;
import nl.novi.techiteasycontroller.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {

        List<UserDto> userDtos = userService.getAllUsers();

        return ResponseEntity.ok(userDtos);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username){

        UserDto userDto = userService.getUser(username);

        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){

        String username = userService.createUser(userDto);
        userService.addAuthority(username, "ROLE_USER");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(username).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto userDto){

        userService.updateUser(username, userDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username){

        userService.deleteUser(username);

        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable String username){
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthorities(@PathVariable String username, @RequestBody Map<String, Object> fields){

        try{
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
        throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable String username, @PathVariable String authority){
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }



}
