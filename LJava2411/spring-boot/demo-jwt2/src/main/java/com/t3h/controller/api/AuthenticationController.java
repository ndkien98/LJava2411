package com.t3h.controller.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.t3h.model.request.LoginRequest;
import com.t3h.model.response.BaseResponse;
import com.t3h.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private IUserServices userServices;

    @PostMapping()
    public ResponseEntity<BaseResponse> auth(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userServices.authentication(loginRequest));
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<BaseResponse> refreshToken(@RequestBody JsonNode refreshRequest){
        return ResponseEntity.ok(userServices.refreshToken(refreshRequest));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){

        return ResponseEntity.ok(userServices.logout(request));
    }
}
