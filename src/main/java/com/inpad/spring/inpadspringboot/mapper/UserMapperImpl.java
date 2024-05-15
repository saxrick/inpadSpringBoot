package com.inpad.spring.inpadspringboot.mapper;

import com.inpad.spring.inpadspringboot.dto.SignUpDTO;
import com.inpad.spring.inpadspringboot.dto.UserDTO;
import com.inpad.spring.inpadspringboot.entity.User;

import org.springframework.stereotype.Component;


@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        return userDTO.getUserDTO(user);
    }

    @Override
    public User signUpToUser(SignUpDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( userDTO.getUsername() );
        user.login( userDTO.getLogin() );
        user.projects(userDTO.getProjectList());

        return user.build();
    }
}