package com.t3h.service;


import com.t3h.entity.RoleEntity;

public interface IRoleService {

    RoleEntity findByRoleName(String roleName);
}
