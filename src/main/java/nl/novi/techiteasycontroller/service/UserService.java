package nl.novi.techiteasycontroller.service;


import nl.novi.techiteasycontroller.dtos.UserDto;
import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.Authority;
import nl.novi.techiteasycontroller.models.User;
import nl.novi.techiteasycontroller.repositories.UserRepository;
import nl.novi.techiteasycontroller.utils.RandomStringGenerator;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> list = userRepository.findAll();

        for(User user : list) {
            userDtos.add(fromUser(user));
        }
        return userDtos;
    }

    public UserDto getUser(String username) {
        UserDto userDto = new UserDto();
        Optional<User> optionalUser = userRepository.findById(username);

        if(optionalUser.isPresent()) {
            userDto = fromUser(optionalUser.get());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
        return userDto;
    }

    public Boolean userExists(String username){
        return userRepository.existsById(username);
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateRandomString(20);
        userDto.setApikey(randomString);
        User newUser = userRepository.save(toUser(userDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(username);

        if(optionalUser.isPresent()) {
            User updateUser = userRepository.findById(username).get();
            updateUser.setPassword(userDto.getPassword());
            userRepository.save(updateUser);
        }
        else {
            throw new RecordNotFoundException(username);
        }

    }

    public Set<Authority> getAuthorities(String username){
    if(!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
    User user = userRepository.findById(username).get();
    UserDto userDto = fromUser(user);

    return user.getAuthorities();

    }

    public void addAuthority(String username, String authority){
        if(!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority){
        if(!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();

        Authority removeAuthority = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();

        user.deleteAuthority(removeAuthority);

        userRepository.save(user);
    }

    public static UserDto fromUser(User user){
        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setApikey(user.getApiKey());
        userDto.setAuthorities(user.getAuthorities());

        return userDto;
    }

    public static User toUser(UserDto userDto){
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setApiKey(userDto.getApikey());

        return user;
    }

}
