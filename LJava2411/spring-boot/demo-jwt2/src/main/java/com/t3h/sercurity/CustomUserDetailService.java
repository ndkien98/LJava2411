package com.t3h.sercurity;

import com.t3h.entity.RoleEntity;
import com.t3h.entity.UserEntity;
import com.t3h.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IUserServices services;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // query user theo user tai tang Authention Provider
        UserEntity userEntity = services.findByUserName(username);
        if (userEntity == null){
            throw new UsernameNotFoundException(username);
        }

        CustomUserDetail customUserDetail = new CustomUserDetail(userEntity);
        Set<GrantedAuthority> grantedAuthorities = userEntity.getRoleEntities().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        customUserDetail.setGrantedAuthorities(grantedAuthorities);
        return customUserDetail;
    }
}
