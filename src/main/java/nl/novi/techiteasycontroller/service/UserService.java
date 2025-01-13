package nl.novi.techiteasycontroller.service;


import nl.novi.techiteasycontroller.dtos.UserDto;
import nl.novi.techiteasycontroller.models.User;
import nl.novi.techiteasycontroller.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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







}
