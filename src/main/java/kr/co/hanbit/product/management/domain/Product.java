package kr.co.hanbit.product.management.domain;

import lombok.Setter;

import java.util.Objects;

public class Product {
    @Setter
    private Long id;
    private String name;
    private Integer price;
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