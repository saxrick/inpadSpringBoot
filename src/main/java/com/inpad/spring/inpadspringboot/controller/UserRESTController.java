package com.inpad.spring.inpadspringboot.controller;

import com.inpad.spring.inpadspringboot.dto.UserDTO;
import com.inpad.spring.inpadspringboot.entity.User;
import com.inpad.spring.inpadspringboot.repositories.UserRepository;
import com.inpad.spring.inpadspringboot.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserRESTController {

    Logger log = Logger.getLogger(UserRESTController.class.getName());

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(){
        UserDTO userDTO = new UserDTO();
        log.info(java.time.LocalDateTime.now() + " Запрошен список пользователей");
        return userDTO.getUserDTOList(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable int id){
        UserDTO userDTO = new UserDTO();
        log.info(java.time.LocalDateTime.now() + " Запрошен пользователь с id " + id);
        return userDTO.getUserDTO(userService.getUser(id));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        User user = userService.getUser(id);
        userService.deleteUser(id);
        return "User with id = " + id + " was deleted";
    }
}

