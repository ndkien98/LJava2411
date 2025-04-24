package com.t3h.service.impl;

import com.t3h.entity.RoleEntity;
import com.t3h.repositories.RoleRepository;
import com.t3h.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoleEntity findByRoleName(String roleName) {
        return repository.findByName(roleName);
    }
}
