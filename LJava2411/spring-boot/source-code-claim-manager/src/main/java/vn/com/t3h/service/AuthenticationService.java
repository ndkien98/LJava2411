package vn.com.t3h.service;

import vn.com.t3h.service.dto.AuthDTO;
import vn.com.t3h.service.dto.request.LoginRequest;
import vn.com.t3h.service.dto.response.Response;

public interface AuthenticationService {

    public Response<AuthDTO> authentication(LoginRequest loginRequest);
}
