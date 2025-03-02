package vn.com.t3h.dao;

import vn.com.t3h.entity.ProductEntity;

import java.util.List;

public interface ProductRepository {

    List<ProductEntity> getAllProducts();
}
