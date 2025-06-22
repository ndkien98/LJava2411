package vn.com.t3h.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.t3h.entity.ClaimStatusEntity;

@RequestMapping
public interface ClaimStatusRepository extends CrudRepository<ClaimStatusEntity, Long> {

    ClaimStatusEntity findByCode(String code);
}
