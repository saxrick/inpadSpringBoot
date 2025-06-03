package com.inpad.spring.inpadspringboot.controller;


import com.inpad.spring.inpadspringboot.dto.CredentialsDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpDTO;
import com.inpad.spring.inpadspringboot.dto.UserDTO;
import com.inpad.spring.inpadspringboot.entity.User;
import com.inpad.spring.inpadspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.logging.Logger;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class AuthController {

    Logger log = Logger.getLogger(AuthController.class.getName());

    private final UserService userService;
//    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentialsDTO){
        UserDTO userDTO = userService.login(credentialsDTO);
//        userDTO.setToken(userAuthProvider.createToken(userDTO.getLogin()));
        log.info(java.time.LocalDateTime.now() + " Вошел пользователь: " + userDTO.getLogin());

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User createdUser = userService.createUser(user);
        log.info(java.time.LocalDateTime.now() + " Зарегистрирован пользователь: " + user.getLogin());
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId()))
                .body(createdUser);
    }

}
