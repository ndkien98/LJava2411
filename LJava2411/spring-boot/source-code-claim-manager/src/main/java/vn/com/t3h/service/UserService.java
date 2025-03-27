package vn.com.t3h.service;

import vn.com.t3h.service.dto.UserDTO;
import vn.com.t3h.service.dto.response.Response;
import vn.com.t3h.service.dto.response.ResponsePage;

public interface UserService {

    Response<UserDTO> saveUser(UserDTO user);
}
