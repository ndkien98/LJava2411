package vn.com.t3h.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import vn.com.t3h.dao.ProductRepository;
import vn.com.t3h.entity.ProductEntity;

import java.util.List;

@Repository("productHibernateRepositoryImpl")
public class ProductHibernateRepositoryImpl implements ProductRepository {

    private final SessionFactory sessionFactory;

    public ProductHibernateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        // Mở session Hibernate
        try (Session session = sessionFactory.openSession()) {
            // Tạo truy vấn HQL để lấy tất cả sản phẩm
            String hql = "FROM ProductEntity";  // HQL để lấy tất cả bản ghi từ bảng ProductEntity
            Query<ProductEntity> query = session.createQuery(hql, ProductEntity.class);
            // Thực thi truy vấn và lấy danh sách sản phẩm
            List<ProductEntity> productList = query.getResultList();
            return productList;  // Trả về danh sách sản phẩm
        }
    }
}
