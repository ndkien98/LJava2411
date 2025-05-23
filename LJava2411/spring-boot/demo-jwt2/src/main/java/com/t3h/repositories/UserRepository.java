package com.t3h.repositories;

import com.t3h.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndPassword(String username,String password);

    boolean existsByUsername(String username);

}
