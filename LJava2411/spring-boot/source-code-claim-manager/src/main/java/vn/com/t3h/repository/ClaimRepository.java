package vn.com.t3h.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.t3h.entity.ClaimEntity;

// triên khai spring data jpa
@Repository
public interface ClaimRepository extends JpaRepository<ClaimEntity,Long> {


}
