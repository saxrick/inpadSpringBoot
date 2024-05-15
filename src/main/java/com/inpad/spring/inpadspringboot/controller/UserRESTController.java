package com.inpad.spring.inpadspringboot.controller;

import com.inpad.spring.inpadspringboot.dto.UserDTO;
import com.inpad.spring.inpadspringboot.entity.User;
import com.inpad.spring.inpadspringboot.repositories.UserRepository;
import com.inpad.spring.inpadspringboot.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserRESTController {


    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(){
        UserDTO userDTO = new UserDTO();
        System.out.println(userDTO);
        return userDTO.getUserDTOList(repository.findAll());
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable int id){
        UserDTO userDTO = new UserDTO();
        return userDTO.getUserDTO(repository.getReferenceById(id));
    }

//    @PostMapping("/")
//    public User addNewUser(@RequestBody User user){
//        userService.saveUser(user);
//        return user;
//    }
//
//    @PutMapping("/")
//    public User updateUser(@RequestBody User user){
//        userService.saveUser(user);
//        return user;
//    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        User user = userService.getUser(id);
        userService.deleteUser(id);
        return "User with id = " + id + " was deleted";
    }
}

