package com.t3h.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.t3h.entity.RoleEntity;
import com.t3h.entity.UserEntity;
import com.t3h.model.dto.UserDto;
import com.t3h.model.request.LoginRequest;
import com.t3h.model.response.BaseResponse;
import com.t3h.repositories.UserRepository;
import com.t3h.sercurity.filter.JwtTokenProvider;
import com.t3h.service.IRoleService;
import com.t3h.service.IUserServices;
import com.t3h.utils.Constants;
import com.t3h.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.mortbay.util.StringUtil;
import org.springframework.cache.Cache;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements IUserServices {

    private final UserRepository repository;
    private final IRoleService iRoleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JCacheCacheManager cacheManager;


    private final ModelMapper modelMapper;
    public UserServiceImpl(UserRepository repository, IRoleService iRoleService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, JCacheCacheManager cacheManager, ModelMapper modelMapper) {
        this.repository = repository;
        this.iRoleService = iRoleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.cacheManager = cacheManager;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userDtos = repository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserEntity findByUserName(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = convertUserDtoToEntity(userDto);
        userEntity = repository.save(userEntity);
        userDto = convertEntityToDto(userEntity);
        return userDto;
    }

    private UserEntity convertUserDtoToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto,UserEntity.class);
        try {
            userEntity.setBirthday(DateUtils.convertStringToDate(userDto.getBirthdayStr(),DateUtils.FORMAT_DATE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<RoleEntity> roleEntities = new HashSet<>();

        if (userDto.getRoleName() != null){
            List<String> roleNames = Arrays.asList(userDto.getRoleName());
            roleNames.forEach(roleName -> {
                RoleEntity roleEntity = iRoleService.findByRoleName(roleName);
                if (roleEntity != null){
                    roleEntities.add(roleEntity);
                }
            });
            userEntity.setRoleEntities(roleEntities);
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntity;
    }

    public BaseResponse authentication(LoginRequest loginRequest){
        BaseResponse baseResponse = new BaseResponse();

        UserEntity userEntity = repository.findByUsernameAndPassword(loginRequest.getUsername(), passwordEncoder.encode(loginRequest.getPassword()));
        if (userEntity == null) {
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
            baseResponse.setMessage("Username or password invalid");
            return baseResponse;
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNodes = objectMapper.createObjectNode();

        Date currentDate = new Date();
        String jwt = jwtTokenProvider.generateToken(userEntity,currentDate);
        jsonNodes.put("token",jwt);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userEntity,currentDate);
        jsonNodes.put("refreshToken",refreshToken);

        Cache cache = cacheManager.getCache(Constants.CACHE_JWT);
        assert cache != null;
        cache.put(jwt,userEntity.getUsername());

        baseResponse.setMessage("Success");
        baseResponse.setErrorCode(HttpStatus.OK.value());
        baseResponse.setData(jsonNodes);
        return baseResponse;
    }

    public BaseResponse logout(HttpServletRequest request){
        String jwt = jwtTokenProvider.getJwtFormRequest(request);
        if (jwt == null){
            log.error("jwt not found in request");
            return BaseResponse.builder().errorCode(HttpStatus.BAD_REQUEST.value()).message("jwt not found").build();
        }

        Cache cache = cacheManager.getCache(Constants.CACHE_JWT);
        String username = cache.get(jwt,String.class);
        if (username == null){
            log.error("account logged out");
            return BaseResponse.builder().errorCode(HttpStatus.BAD_REQUEST.value()).message("account logged out").build();
        }

        cache.evictIfPresent(jwt);
        log.info("logout success");
        return BaseResponse.builder().errorCode(HttpStatus.OK.value()).message("logout success").build();

    }

    @Override
    public BaseResponse refreshToken(JsonNode request) {
        String refreshToken = request.get("refreshToken").asText();

        if (!jwtTokenProvider.validateRefreshToken(refreshToken)){
            return BaseResponse.builder().message("refreshToken invalid").errorCode(HttpStatus.BAD_REQUEST.value()).build();
        }
        String username = jwtTokenProvider.getUserNameRefreshToken(refreshToken);

        UserEntity userEntity = repository.findByUsername(username);
        Date currentDate = new Date();
        if (userEntity == null){
            return BaseResponse.builder().message("user in token not found").errorCode(HttpStatus.BAD_REQUEST.value()).build();
        }
        String newToken = jwtTokenProvider.generateToken(userEntity,currentDate);

        Cache cache = cacheManager.getCache(Constants.CACHE_JWT);
        assert cache != null;
        cache.put(newToken,userEntity.getUsername());

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode response = objectMapper.createObjectNode();
        response.put("newToken",newToken);
        return BaseResponse.builder().data(response).message(HttpStatus.OK).errorCode(HttpStatus.OK.value()).build();
    }

    private UserDto convertEntityToDto(UserEntity data) {
        UserDto userDto = modelMapper.map(data,UserDto.class);
        try {
            userDto.setBirthdayStr(DateUtils.convertDateToString(data.getBirthday(),DateUtils.FORMAT_DATE));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (data.getRoleEntities() != null){
            String[] roleNames = data.getRoleEntities().stream().map(RoleEntity::getName).toArray(String[]::new);
            userDto.setRoleName(roleNames);
        }
        if (Constants.MALE.equals(data.getGender())){
            userDto.setGenderStr(Constants.MALE_STR);
        }else {
            userDto.setUserCode(Constants.FEMALE_STR);
        }
        return userDto;
    }
}
