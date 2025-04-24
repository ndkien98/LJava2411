package vn.com.t3h.mapper.decorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import vn.com.t3h.entity.RoleEntity;
import vn.com.t3h.entity.UserEntity;
import vn.com.t3h.mapper.UserMapper;
import vn.com.t3h.service.FileService;
import vn.com.t3h.service.dto.UserDTO;

import java.util.stream.Collectors;

@Component
public abstract class UserMapperDecorator implements UserMapper {


    @Autowired
    @Qualifier("delegate")
    private UserMapper userMapper;

    @Autowired
    private FileService fileService;

    @Override
    public UserDTO toDto(UserEntity userEntity) {
        UserDTO userDTO = userMapper.toDto(userEntity);
        String fileBase64 = fileService.getBase64FromPath(userEntity.getPathAvatar());
        if (StringUtils.hasText(fileBase64)){
            userDTO.setStringBase64Avatar(userEntity.getMimeType() + fileBase64);
        }
        String roleName = userEntity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.joining(","));
        userDTO.setRoleName(roleName);
        return userDTO;
    }
}
