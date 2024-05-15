package com.inpad.spring.inpadspringboot.controller;


import com.inpad.spring.inpadspringboot.configuration.UserAuthProvider;
import com.inpad.spring.inpadspringboot.dto.CredentialsDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpDTO;
import com.inpad.spring.inpadspringboot.dto.UserDTO;
import com.inpad.spring.inpadspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentialsDTO){
        UserDTO userDTO = userService.login(credentialsDTO);
        System.out.println(userDTO);

        userDTO.setToken(userAuthProvider.createToken(userDTO.getLogin()));

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody SignUpDTO signUpDTO){
        UserDTO createdUser = userService.register(signUpDTO);
//        System.out.println(createdUser);
        createdUser.setToken(userAuthProvider.createToken(signUpDTO.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId()))
                .body(createdUser);
    }

}
