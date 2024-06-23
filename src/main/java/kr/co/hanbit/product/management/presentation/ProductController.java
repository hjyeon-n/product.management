package kr.co.hanbit.product.management.presentation;

import kr.co.hanbit.product.management.application.SimpleProductService;
import kr.co.hanbit.product.management.domain.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private SimpleProductService service;

    public ProductController(SimpleProductService service) {
        this.service = service;
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto product) {
        return service.add(product);
    }
}
