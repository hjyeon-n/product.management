package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.infrastructure.DatabaseProductRepository;
import kr.co.hanbit.product.management.infrastructure.ProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private ProductRepository productRepository;
    private ValidationService validationService;
    private ModelMapper modelMapper;

    public SimpleProductService(DatabaseProductRepository databaseProductRepository, ValidationService validationService, ModelMapper modelMapper) {
        this.productRepository = databaseProductRepository;
        this.validationService = validationService;
        this.modelMapper = modelMapper;
    }

    public ProductDto add(ProductDto productDto) {
        // ProductDto를 Product로 변환
        Product product = modelMapper.map(productDto, Product.class);

        // 검증
        validationService.checkValid(product);

        // 레포지토리를 호출
        Product savedProduct = productRepository.add(product);

        // Product를 ProductDto로 변환하는 코드
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        return savedProductDto;
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

        return productDtos;
    }

    public List<ProductDto> findByName(String name) {
        List<Product> products = productRepository.findByName(name);

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

        return productDtos;
    }

    public ProductDto update(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product updateProduct = productRepository.update(product);
        return modelMapper.map(updateProduct, ProductDto.class);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
