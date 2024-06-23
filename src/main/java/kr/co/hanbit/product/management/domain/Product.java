package kr.co.hanbit.product.management.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class Product {
    @Setter
    private Long id;

    @Size(min = 1, max = 100)
    private String name;

    @Min(0)
    @Max(1000000)
    private Integer price;

    @Min(0)
    @Max(9999)
    private Integer amount;

    public boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public boolean containsName(String name) {
        return this.name.contains(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Product product = (Product) obj;
        return Objects.equals(id, product.id);
    }
}