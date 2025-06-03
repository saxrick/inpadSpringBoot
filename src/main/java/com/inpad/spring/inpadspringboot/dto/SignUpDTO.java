package com.inpad.spring.inpadspringboot.dto;

import com.inpad.spring.inpadspringboot.entity.Project;
import com.inpad.spring.inpadspringboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpDTO {

    private String login;
    private List<Project> projectList;
}
