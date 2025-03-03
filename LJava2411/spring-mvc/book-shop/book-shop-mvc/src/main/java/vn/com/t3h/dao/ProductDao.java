package vn.com.t3h.dao;

import vn.com.t3h.model.ProductDTO;

import java.util.List;

public interface ProductDao {

    List<ProductDTO> findAll();
}
