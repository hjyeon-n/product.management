package kr.co.hanbit.product.management.presentation;

import kr.co.hanbit.product.management.application.SimpleProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private SimpleProductService simpleProductService;

    public ProductController(SimpleProductService service) {
        this.simpleProductService = service;
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto product) {
        return simpleProductService.add(product);
    }

    @GetMapping("/product/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return simpleProductService.findById(id);
    }

    @GetMapping("/products")
    public List<ProductDto> findProducts(@RequestParam(required = false) String name) {
        if (name == null) {
            return simpleProductService.findAll();
        }
        return simpleProductService.findByName(name);
    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productDto.setId(id);
        return simpleProductService.update(productDto);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        simpleProductService.delete(id);
    }
}
