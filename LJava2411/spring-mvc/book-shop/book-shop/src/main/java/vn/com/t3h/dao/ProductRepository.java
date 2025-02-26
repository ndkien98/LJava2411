package vn.com.t3h.dao;

import vn.com.t3h.model.Product;

import java.util.List;

public interface ProductRepository {

    public List<Product> getAllProducts();
}
