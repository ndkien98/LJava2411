package com.t3h.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Column(unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "roleEntities")
    private Set<UserEntity> userEntities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
