package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.dao.UserDao;
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
import org.springframework.transaction.annotation.Transactional;


import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

//    @Override
//    @Transactional
//    public void saveUser(User user) {
//        userDao.saveUser(user);
//    }


    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }



    public UserDTO login(CredentialsDTO credentialsDTO){
        User user = userRepository.findByLogin(credentialsDTO.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));


        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.getPassword()), user.getPassword())){
            return userMapper.toUserDTO(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDTO register(SignUpDTO userDTO) {


        Optional<User> optionalUser = userRepository.findByLogin(userDTO.getLogin());

        if (optionalUser.isEmpty()) {

            User user = userMapper.signUpToUser(userDTO);

            user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDTO.getPassword())));
            user.setState(userDTO.isState());

            User savedUser = userRepository.save(user);

            return userMapper.toUserDTO(savedUser);
        } else{
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

    }

    public UserDTO findByLogin(String login){
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDTO(user);
    }
}
