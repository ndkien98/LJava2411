package vn.com.t3h.entity;

import javax.persistence.*;

@Entity
@Table(name = "production")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_title", nullable = false)
    private String bookTitle;

    /*
    EAGER:
        -> khi query lấy ra dữ liệu 1 product trong database, nó cũng sẽ
        lấy luôn dữ liệu của category cha của products đó và đưa vào trong
        thuộc tính category cua product
    ProductEntity entity.category sẽ không bị null mà có đối tượng category tồn tại
    LAZY:
        -> khi query lấy ra dữ liệu 1 product trong database, nó cũng sẽ
        không lấy luôn dữ liệu của category cha của products đó và đưa vào trong
        thuộc tính category của product
    ProductEntity entity.category sẽ bị null
    -> lý thuyết:
        su dụng cơ chế lazy load ->tức là khi nào gọi tới hàm getter của category thì khi đó mới thực hiện
        câu query lấy ra category của product và đưa vào đây
    nhưng thực tế khi gọi tới method getCategory -> sẽ bị lỗi không thể query lấy ra category được vì session query
    da kếtthuwjusuc, để lấy ra được category -> phải gọi tới dao/repository tạo lại session và query lại từ đâu
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category; // Mỗi sản phẩm có một thể loại

    @Column(name = "author")
    private String author;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "genre")
    private String genre;

    @Column(name = "price")
    private double price;

    @Column(name = "discount")
    private double discount;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;


    // Getter and Setter methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}

