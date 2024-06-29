package kr.co.hanbit.product.management.presentation;

import jakarta.validation.constraints.NotNull;
import kr.co.hanbit.product.management.domain.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductDto {
    @Setter
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer amount;

    public ProductDto(String name, Integer price, Integer amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public ProductDto(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public ProductDto() {
    }

    public static Product toEntity(ProductDto productDto) {
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getAmount()
        );

        return product;
    }

    public static ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getAmount()
        );

        productDto.setId(product.getId());

        return productDto;
    }
}
