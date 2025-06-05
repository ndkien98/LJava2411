package vn.com.t3h.entity;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /*
    cascade = CascadeType.PERSIST
        -> khi save entity category, mà entity category đã có 1 list products là danh sách
        các sản phẩm -> tất cả sản phẩm đó sẽ được tự động lưu vào database và tham chiếu
        tới đúng category cha


     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private Set<ProductEntity> products;  // danh sách sdản phẩm thuộc thể loại này

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description")
    private String description;



    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }
}
