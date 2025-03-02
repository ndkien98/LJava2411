package vn.com.t3h.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import vn.com.t3h.dao.ProductRepository;
import vn.com.t3h.entity.ProductEntity;
import vn.com.t3h.model.ProductDTO;

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
    @Override
    public List<ProductDTO> searchProducts(Double price, String bookTitle, String publisher, String categoryName) {
        // Mở session Hibernate
        try (Session session = sessionFactory.openSession()) {
            // Xây dựng câu truy vấn HQL với các tiêu chí tìm kiếm
            StringBuilder hql = new StringBuilder("SELECT new vn.com.t3h.model.ProductDTO(p.id, p.bookTitle, p.author, p.price, p.publisher, " +
                    "p.pageCount, p.publicationYear, p.genre, p.discount, p.stockQuantity, p.description, p.category.categoryName) " +
                    "FROM ProductEntity p WHERE 1=1");

            // Thêm điều kiện cho từng tiêu chí tìm kiếm nếu chúng được truyền vào
            if (price != null) {
                hql.append(" AND p.price = :price");  // Đặt tên tham số rõ ràng
            }
            if (bookTitle != null && !bookTitle.isEmpty()) {
                hql.append(" AND p.bookTitle LIKE :bookTitle");
            }
            if (publisher != null && !publisher.isEmpty()) {
                hql.append(" AND p.publisher LIKE :publisher");
            }
            if (categoryName != null && !categoryName.isEmpty()) {
                hql.append(" AND p.category.categoryName LIKE :categoryName");
            }

            // Tạo truy vấn HQL
            Query<ProductDTO> query = session.createQuery(hql.toString(), ProductDTO.class);

            // Thiết lập các tham số của truy vấn
            if (price != null) {
                query.setParameter("price", price);  // Đảm bảo thiết lập tham số price
            }
            if (bookTitle != null && !bookTitle.isEmpty()) {
                query.setParameter("bookTitle", "%" + bookTitle + "%");  // Đảm bảo thiết lập tham số bookTitle
            }
            if (publisher != null && !publisher.isEmpty()) {
                query.setParameter("publisher", "%" + publisher + "%");  // Đảm bảo thiết lập tham số publisher
            }
            if (categoryName != null && !categoryName.isEmpty()) {
                query.setParameter("categoryName", "%" + categoryName + "%");  // Đảm bảo thiết lập tham số categoryName
            }

            // Thực thi truy vấn và trả về danh sách sản phẩm DTO
            List<ProductDTO> productDTOList = query.getResultList();
            return productDTOList;
        }
    }




}
