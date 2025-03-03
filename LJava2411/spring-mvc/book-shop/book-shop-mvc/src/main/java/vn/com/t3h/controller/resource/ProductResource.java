package vn.com.t3h.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.t3h.entity.ProductEntity;
import vn.com.t3h.model.ProductDTO;
import vn.com.t3h.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> search(@RequestParam(required = false,name = "price") Double price,
                                                   @RequestParam(required = false,name = "bookTitle") String bookTitle,
                                                   @RequestParam(required = false,name = "publisher") String publisher,
                                                   @RequestParam(required = false,name = "categoryName") String categoryName) {
        List<ProductDTO> productEntities = productService.findByCondition(price, bookTitle, publisher, categoryName);
        return ResponseEntity.ok(productEntities);
    }
}
