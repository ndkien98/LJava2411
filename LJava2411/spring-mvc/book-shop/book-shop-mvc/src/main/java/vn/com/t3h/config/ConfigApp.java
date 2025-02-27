package vn.com.t3h.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.com.t3h.service.ProductService;
import vn.com.t3h.service.impl.ProductServiceImpl;

@Configuration
public class ConfigApp {

    /*
    @Bean: đánh dấu việc custom tạo ra bean cho 1 class nhất định,
    thay vì để spring container tự động tạo ra bean
     */
    @Bean
    public Gson gson(){
        return new Gson();
    }

//    @Bean
//    public ProductService productService(){
//        ProductService productService = new ProductServiceImpl();
//        return productService;
//    }
}
