package com.t3h.controller.api;

import com.t3h.model.response.BaseResponse;
import com.t3h.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/user")
public class RestUserController {

    @Autowired
    private IUserServices iUserServices;

    @GetMapping()
    public ResponseEntity<?> users(){
        return ResponseEntity.ok(BaseResponse.builder().message(HttpStatus.OK.name()).errorCode(HttpStatus.OK.value()).data(iUserServices.getUsers()).build());
    }

}
