package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.dto.CredentialsDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpDTO;
import com.inpad.spring.inpadspringboot.dto.UserDTO;
import com.inpad.spring.inpadspringboot.entity.User;
import com.inpad.spring.inpadspringboot.exceptions.AppException;
import com.inpad.spring.inpadspringboot.mapper.UserMapper;
import com.inpad.spring.inpadspringboot.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUser(int id) {
        return userRepository.getReferenceById(id);
    }


    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }



    public UserDTO login(CredentialsDTO credentialsDTO){
        User user = userRepository.findByLogin(credentialsDTO.getLogin())
                .orElseThrow(() -> new AppException("Неверный логин", HttpStatus.NOT_FOUND));

        return userMapper.toUserDTO(user);

    }

    public User createUser(User user) {

        Optional<User> optionalUser = userRepository.findByLogin(user.getLogin());

        if (optionalUser.isEmpty()) {


//            user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDTO.getPassword())));
//            user.setState(userDTO.isState());

            User savedUser = userRepository.save(user);

            return savedUser;
        } else{
            throw new AppException("Пользователь с таким логином уже существует", HttpStatus.BAD_REQUEST);
        }

    }

    public UserDTO findByLogin(String login){
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Неверный логин", HttpStatus.NOT_FOUND));
        return userMapper.toUserDTO(user);
    }
}
