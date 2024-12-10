package com.inpad.spring.inpadspringboot.dto;

import com.inpad.spring.inpadspringboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpModelDTO {
    private String modelName;
    private String modelInfo;
    private boolean state;
    private List<User> userList;
}
