package vn.com.t3h.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import vn.com.t3h.dao.ProductDao;
import vn.com.t3h.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() {
        String sql = "select * from products";
        List<Product> products = jdbcTemplate.query(sql,new RowMapper<Product>(){
            @Override
            public Product mapRow(ResultSet rs, int i) throws SQLException {
                System.out.println("Bắt đầu map dữ liệu dạng sql sang object java của sản phẩm thứ: " + i);
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setId(rs.getInt("id"));
                product.setBookTitle(rs.getString("book_title"));
                product.setAuthor(rs.getString("author"));
                product.setPageCount(rs.getInt("page_count"));
                product.setPublisher(rs.getString("publisher"));
                product.setPublicationYear(rs.getInt("publication_year"));
                product.setGenre(rs.getString("genre"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getDouble("discount"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setDescription(rs.getString("description"));
                return product;
            }
        });
        return products;
    }
}
