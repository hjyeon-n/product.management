package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.domain.ProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
//import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private ProductRepository productRepository;
    private ValidationService validationService;

    public SimpleProductService(ProductRepository productRepository, ValidationService validationService) {
        this.productRepository = productRepository;
        this.validationService = validationService;
    }

    public ProductDto add(ProductDto productDto) {
        // ProductDto를 Product로 변환
        Product product = ProductDto.toEntity(productDto);

        // 검증
        validationService.checkValid(product);

        // 레포지토리를 호출
        Product savedProduct = productRepository.add(product);

        // Product를 ProductDto로 변환하는 코드
        ProductDto savedProductDto = ProductDto.toDto(product);

        return savedProductDto;
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        ProductDto productDto = ProductDto.toDto(product);
        return productDto;
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))
                .toList();

        return productDtos;
    }

    public List<ProductDto> findByName(String name) {
        List<Product> products = productRepository.findByName(name);

        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.toDto(product))
                .toList();

        return productDtos;
    }

    public ProductDto update(ProductDto productDto) {
        Product product = ProductDto.toEntity(productDto);
        Product updateProduct = productRepository.update(product);
        return ProductDto.toDto(updateProduct);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
