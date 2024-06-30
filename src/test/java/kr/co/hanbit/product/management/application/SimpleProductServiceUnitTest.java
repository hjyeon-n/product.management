package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.domain.ProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleProductServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private SimpleProductService simpleProductService;

    @Test
    @DisplayName("상품 추가 후에는 추가된 상품이 반환되어야 한다.")
    void productAddTest() {
        ProductDto productDto = new ProductDto("연필", 300, 20);
        Long PRODUCT_ID = 1L;

        Product product = ProductDto.toEntity(productDto);
        product.setId(PRODUCT_ID);
        when(productRepository.add(any())).thenReturn(product);

        ProductDto savedProductDto = simpleProductService.add(productDto);

        assertThat(savedProductDto.getId()).isEqualTo(PRODUCT_ID);
        assertThat(savedProductDto.getName()).isEqualTo(productDto.getName());
        assertThat(savedProductDto.getPrice()).isEqualTo(productDto.getPrice());
        assertThat(savedProductDto.getAmount()).isEqualTo(productDto.getAmount());
    }

    @Test
    @DisplayName("findById")
    void findByIdTest() {
        ProductDto productDto = new ProductDto("연필", 300, 20);
        Long PRODUCT_ID = 1L;

        Product product = ProductDto.toEntity(productDto);
        when(productRepository.findById(PRODUCT_ID)).thenReturn(product);

        ProductDto foundProductDto = simpleProductService.findById(PRODUCT_ID);

        assertThat(productDto.getId()).isEqualTo(foundProductDto.getId());
        assertThat(productDto.getName()).isEqualTo(foundProductDto.getName());
        assertThat(productDto.getPrice()).isEqualTo(foundProductDto.getPrice());
        assertThat(productDto.getAmount()).isEqualTo(foundProductDto.getAmount());
    }

    @Test
    @DisplayName("findAll")
    void findAllTest() {
        Product product1 = new Product(1L, "연필", 300, 20);
        Product product2 = new Product(2L, "샤프", 500, 10);
        Product product3 = new Product(3L, "지우개", 400, 30);

        List<Product> products = Arrays.asList(product1, product2, product3);

        when(productRepository.findAll()).thenReturn(products);

        List<ProductDto> productDtos = simpleProductService.findAll();

        assertThat(products.size()).isEqualTo(productDtos.size());

        IntStream.range(0, productDtos.size()).forEach(i -> {
            ProductDto productDto = productDtos.get(i);
            Product product = products.get(i);

            assertThat(productDto.getId()).isEqualTo(product.getId());
            assertThat(productDto.getName()).isEqualTo(product.getName());
            assertThat(productDto.getPrice()).isEqualTo(product.getPrice());
            assertThat(productDto.getAmount()).isEqualTo(product.getAmount());
        });
    }

    @Test
    @DisplayName("findByContainingName")
    void findByContainingNameTest() {
        Product product1 = new Product(1L, "연필", 300, 20);
        Product product2 = new Product(2L, "동물 연필", 500, 10);
        Product product3 = new Product(3L, "지우개", 400, 30);

        String name = "연필";

        List<Product> products = Stream.of(product1, product2, product3)
                .filter(product -> product.containsName(name))
                .toList();

        when(productRepository.findByName(name)).thenReturn(products);

        List<ProductDto> productDtos = simpleProductService.findByName(name);

        assertThat(productDtos.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("update")
    void updateTest() {
        Product originalProduct = new Product(1L, "연필", 200, 20);
        ProductDto updateProductDto = new ProductDto(1L, "연필", 300, 50);
        Product updateProduct = ProductDto.toEntity(updateProductDto);

        when(productRepository.update(originalProduct)).thenReturn(updateProduct);

        ProductDto updatedProduct = simpleProductService.update(updateProductDto);

        assertThat(updatedProduct.getId()).isEqualTo(updateProduct.getId());
        assertThat(updatedProduct.getName()).isEqualTo(updateProduct.getName());
        assertThat(updatedProduct.getPrice()).isEqualTo(updateProduct.getPrice());
        assertThat(updatedProduct.getAmount()).isEqualTo(updateProduct.getAmount());
    }

    @Test
    @DisplayName("delete")
    void deleteTest() {
        Long PRODUCT_ID = 1L;
        simpleProductService.delete(PRODUCT_ID);

        verify(productRepository, times(1)).delete(PRODUCT_ID);
    }
}