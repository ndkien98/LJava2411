package vn.com.t3h.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.com.t3h.dao.ProductDao;
import vn.com.t3h.dao.impl.ProductDaoImpl;
import vn.com.t3h.model.Product;
import vn.com.t3h.service.ProductService;
import vn.com.t3h.service.impl.ProductServiceImpl;

import java.util.List;

@Controller
public class ProductionController {

//    @Autowired
    private ProductService productService;

    public ProductionController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = productService.getProducts();
//        Gson gson = new Gson();
        // convert object java to String json
//        String jsonProjection = gson.toJson(products);
        model.addAttribute("products", products);
        return "products";
    }

}
