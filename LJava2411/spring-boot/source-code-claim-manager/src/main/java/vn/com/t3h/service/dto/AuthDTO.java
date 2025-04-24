package vn.com.t3h.service.dto;

import lombok.Data;

@Data
public class AuthDTO {

    private String token;

    private String refreshToken;

}
