package com.t3h.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


@Data
public class UserDto {

    private Integer id;

    private String email;

    private String username;

    private String password;

    private String fullName;
    private String birthdayStr;

    private String genderStr;

    private String userCode;

    private Integer cartId;

    private String[] roleName;


}
