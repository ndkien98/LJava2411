package vn.com.t3h.dao;

import vn.com.t3h.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();
}
