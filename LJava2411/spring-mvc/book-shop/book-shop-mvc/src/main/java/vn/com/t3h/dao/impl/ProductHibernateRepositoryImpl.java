package vn.com.t3h.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
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
    public List<ProductEntity> findAll() {
        Session session = sessionFactory.openSession();
        String hql = "from ProductEntity";
        Query<ProductEntity> query = session.createQuery(hql,ProductEntity.class);
        List<ProductEntity> repositories = query.getResultList();
        session.close();
        return repositories;
    }


    public List<ProductEntity> findByCondition(Double price, String bookTitle,String publisher,String categoryName){
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder("Select p from ProductEntity p join p.category c where 1 = 1");
        if (price != null) {
            sql.append("and p.price = :price");
        }
        if (StringUtils.hasText(bookTitle)){
            sql.append("and p.bookTitle = :bookTitle");
        }
        if (StringUtils.hasText(publisher)){
            sql.append("and p.publisher = :publisher");
        }

        if (StringUtils.hasText(categoryName)){
            sql.append("and c.categoryName = :categoryName");
        }
        Query<ProductEntity> query = session.createQuery(sql.toString(),ProductEntity.class);

        if (price != null) {
            query.setParameter("price", price);
        }
        if (StringUtils.hasText(bookTitle)){
            query.setParameter("bookTitle", bookTitle);
        }
        if (StringUtils.hasText(publisher)){
            query.setParameter("publisher", publisher);
        }
        if (StringUtils.hasText(categoryName)){
            query.setParameter("categoryName", categoryName);
        }
        List<ProductEntity> productEntities = query.getResultList();
        session.close();
        return productEntities;
    }
}
