package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.EntityNotFoundException;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SimpleProductServiceTest {

    @Autowired
    SimpleProductService simpleProductService;

    private ProductDto productDto1, productDto2, productDto3;

    @BeforeEach
    void setUp() {
        productDto1 = new ProductDto("연필", 300, 100);
        productDto2 = new ProductDto("볼펜", 500, 200);
        productDto3 = new ProductDto("귀여운 연필", 400, 50);

        simpleProductService.add(productDto1);
        simpleProductService.add(productDto2);
        simpleProductService.add(productDto3);
    }

    @Test
    @DisplayName("상품을 추가한 후 id로 조회하면 해당 상품이 조회되어야 한다.")
    void productAddAndFindByIdTest() {
        ProductDto productDto = new ProductDto("연필", 300, 20);

        ProductDto savedProductDto = simpleProductService.add(productDto);
        Long savedProductId = savedProductDto.getId();

        ProductDto foundProductDto = simpleProductService.findById(savedProductId);

        assertThat(savedProductDto.getId()).isEqualTo(foundProductDto.getId());
        assertThat(savedProductDto.getName()).isEqualTo(foundProductDto.getName());
        assertThat(savedProductDto.getPrice()).isEqualTo(foundProductDto.getPrice());
        assertThat(savedProductDto.getAmount()).isEqualTo(foundProductDto.getAmount());
    }

    @Test
    @DisplayName("findAll")
    void productAddFindAllTest() {
        List<ProductDto> list = simpleProductService.findAll();

        assertThat(list)
                .hasSize(3)
                .extracting(ProductDto::getName)
                .containsExactlyInAnyOrder(productDto1.getName(), productDto2.getName(), productDto3.getName());
    }

    @Test
    @DisplayName("findByNameContaing")
    void productFindByNameContaingTest() {
        List<ProductDto> list = simpleProductService.findByName("연필");

        assertThat(list)
                .hasSize(2)
                .extracting(ProductDto::getName)
                .containsExactlyInAnyOrder(productDto1.getName(), productDto3.getName());
    }

    @Test
    @DisplayName("update")
    void productUpdateTest() {
        ProductDto updatedProduct = new ProductDto("동물 연필", 450, 100);
        updatedProduct.setId(2L);
        simpleProductService.update(updatedProduct);

        ProductDto foundProductDto = simpleProductService.findById(2L);

        assertThat(foundProductDto.getName()).isEqualTo(updatedProduct.getName());
        assertThat(foundProductDto.getPrice()).isEqualTo(updatedProduct.getPrice());
    }

    @Test
    @DisplayName("delete")
    void productDeleteTest() {
        simpleProductService.delete(2L);

        assertThrows(EntityNotFoundException.class,
                () -> simpleProductService.findById(2L));
    }

    @Test
    @DisplayName("존재하지 않는 상품 id로 조회하면 EntityNotFoundException이 발생해야 한다.")
    void findProductNotExistsTest() {
        Long notExistId = -1L;

        assertThrows(EntityNotFoundException.class,
                () -> simpleProductService.findById(notExistId));
    }
}