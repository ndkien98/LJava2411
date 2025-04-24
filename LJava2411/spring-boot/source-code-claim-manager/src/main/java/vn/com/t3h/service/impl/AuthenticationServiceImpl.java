package vn.com.t3h.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.com.t3h.entity.RoleEntity;
import vn.com.t3h.entity.UserEntity;
import vn.com.t3h.repository.UserRepository;
import vn.com.t3h.security.JwtTokenUtil;
import vn.com.t3h.service.AuthenticationService;
import vn.com.t3h.service.dto.AuthDTO;
import vn.com.t3h.service.dto.request.LoginRequest;
import vn.com.t3h.service.dto.response.Response;
import vn.com.t3h.utils.Constant;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JCacheCacheManager cacheManager;

    public Response<AuthDTO> authentication(LoginRequest loginRequest) {
        Response baseResponse = new Response();

        // Tìm người dùng trong DB
        UserEntity userEntity = userRepository.findByUsername(loginRequest.getUsername());
        if (userEntity == null || !passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
            baseResponse.setCode(HttpStatus.BAD_REQUEST.value());
            baseResponse.setMessage("Username or password invalid");
            return baseResponse;
        }

        // Tạo authorities từ các vai trò
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleEntity roleEntity : userEntity.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(Constant.PREFIX_ROLE + roleEntity.getCode()));
        }

        // Tạo userDetails từ entity
        UserDetails userDetails = new User(userEntity.getUsername(), userEntity.getPassword(), grantedAuthorities);

        // Tạo authentication với userDetails
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // Gán authentication vào context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Tạo JWT và refresh token
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNodes = objectMapper.createObjectNode();

        Date currentDate = new Date();
        String jwt = jwtTokenUtil.generateToken(userDetails);
        jsonNodes.put("token", jwt);
        String refreshToken = jwtTokenUtil.generateRefreshToken(userEntity, currentDate);
        jsonNodes.put("refreshToken", refreshToken);

        // Cache JWT
        Cache cache = cacheManager.getCache(Constant.CACHE_JWT);
        assert cache != null;
        cache.put(userEntity.getUsername(), jwt);

        baseResponse.setMessage("Success");
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setData(jsonNodes);
        return baseResponse;
    }

}
