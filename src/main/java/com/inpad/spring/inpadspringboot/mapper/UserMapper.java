package com.inpad.spring.inpadspringboot.mapper;

import com.inpad.spring.inpadspringboot.dto.SignUpDTO;
import com.inpad.spring.inpadspringboot.dto.UserDTO;
import com.inpad.spring.inpadspringboot.entity.User;


public interface UserMapper {
    UserDTO toUserDTO(User user);

    User signUpToUser(SignUpDTO userDTO);

}
