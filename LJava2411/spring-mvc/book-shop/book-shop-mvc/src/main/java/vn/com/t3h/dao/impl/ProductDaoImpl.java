package vn.com.t3h.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import vn.com.t3h.dao.ProductDao;
import vn.com.t3h.model.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductDTO> findAll() {
        String sql = "select * from products";
        List<ProductDTO> productDTOS = jdbcTemplate.query(sql,new RowMapper<ProductDTO>(){
            @Override
            public ProductDTO mapRow(ResultSet rs, int i) throws SQLException {
                System.out.println("Bắt đầu map dữ liệu dạng sql sang object java của sản phẩm thứ: " + i);
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(rs.getInt("id"));
                productDTO.setId(rs.getInt("id"));
                productDTO.setBookTitle(rs.getString("book_title"));
                productDTO.setAuthor(rs.getString("author"));
                productDTO.setPageCount(rs.getInt("page_count"));
                productDTO.setPublisher(rs.getString("publisher"));
                productDTO.setPublicationYear(rs.getInt("publication_year"));
                productDTO.setGenre(rs.getString("genre"));
                productDTO.setPrice(rs.getDouble("price"));
                productDTO.setDiscount(rs.getDouble("discount"));
                productDTO.setStockQuantity(rs.getInt("stock_quantity"));
                productDTO.setDescription(rs.getString("description"));
                return productDTO;
            }
        });
        return productDTOS;
    }
}
