package com.t3h.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.t3h.entity.UserEntity;
import com.t3h.model.dto.UserDto;
import com.t3h.model.request.LoginRequest;
import com.t3h.model.response.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserServices {

    List<UserDto> getUsers();

    UserEntity findByUserName(String username);

    UserDto createUser(UserDto userDto);

    BaseResponse authentication(LoginRequest loginRequest);

    BaseResponse logout(HttpServletRequest request);

    BaseResponse refreshToken(JsonNode request);
}
