package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.dto.CredentialsDTO;
import com.inpad.spring.inpadspringboot.dto.SignUpDTO;
import com.inpad.spring.inpadspringboot.dto.UserDTO;
import com.inpad.spring.inpadspringboot.entity.User;

import java.util.List;
public interface UserService {
    public List<User> getAllUsers();
//    public void saveUser(User user);
    public User getUser(int id);
    public void deleteUser(int id);
    public UserDTO findByLogin(String login);
    public UserDTO login(CredentialsDTO credentialsDTO);
    public UserDTO register(SignUpDTO userDTO);
}
