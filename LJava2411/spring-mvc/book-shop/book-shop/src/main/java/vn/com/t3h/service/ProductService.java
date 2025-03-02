package vn.com.t3h.service;

import vn.com.t3h.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductEntity> findAll();
}
