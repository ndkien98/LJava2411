package vn.com.t3h.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.t3h.entity.InsuranceProductEntity;

public interface InsuranceProductRepository extends JpaRepository<InsuranceProductEntity, Long> {


    InsuranceProductEntity findByName(String name);

} 
