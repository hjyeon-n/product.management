package kr.co.hanbit.product.management.infrastructure;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListProductRepository {

    List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong();

    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L));
        products.add(product);
        return product;
    }
}
