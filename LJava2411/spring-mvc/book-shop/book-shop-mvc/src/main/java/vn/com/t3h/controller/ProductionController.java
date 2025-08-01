package vn.com.t3h.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.com.t3h.entity.ProductEntity;
import vn.com.t3h.model.ProductDTO;
import vn.com.t3h.service.ProductService;

import java.util.List;

@Controller
public class ProductionController {

//    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String products(Model model) {
        List<ProductEntity> productDTOS = productService.getProducts();
        model.addAttribute("products", productDTOS);
        return "products";
    }

    @GetMapping("/production-detail")
    public String productionDetail(Model model) {
        return "production-detail";
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
