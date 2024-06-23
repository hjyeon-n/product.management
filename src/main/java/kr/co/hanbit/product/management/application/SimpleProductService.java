package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.infrastructure.ListProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SimpleProductService {

    private ListProductRepository repository;
    private ModelMapper modelMapper;

    public SimpleProductService(ListProductRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public ProductDto add(ProductDto productDto) {
        // ProductDto를 Product로 변환
        Product product = modelMapper.map(productDto, Product.class);

        // 레포지토리를 호출
        Product savedProduct = repository.add(product);

        // Product를 ProductDto로 변환하는 코드
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        return savedProductDto;
    }
}
