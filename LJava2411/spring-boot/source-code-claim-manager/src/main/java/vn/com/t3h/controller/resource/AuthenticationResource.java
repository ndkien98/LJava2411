package vn.com.t3h.controller.resource;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.t3h.service.AuthenticationService;
import vn.com.t3h.service.dto.AuthDTO;
import vn.com.t3h.service.dto.request.LoginRequest;
import vn.com.t3h.service.dto.response.Response;

@RestController
@RequestMapping("auth")
public class AuthenticationResource {


    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/token")
    public ResponseEntity<Response<AuthDTO>> login(@RequestBody final LoginRequest loginRequest) {
        Response response = authenticationService.authentication(loginRequest);
        return ResponseEntity.ok(response);
    }
}
