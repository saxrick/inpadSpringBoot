package com.inpad.spring.inpadspringboot.dto;

import com.inpad.spring.inpadspringboot.entity.GeoDataContainer;
import com.inpad.spring.inpadspringboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpProjectDTO {
    private String projectName;
    private String projectInfo;
    private GeoDataContainer projectData;
    private boolean state;
    private List<User> userList;
    private Date dtCreation;
    private Date dtUpdate;
    private String startCoordinates;
    private String insideCoordinates;
    private String outsideCoordinates;
}
