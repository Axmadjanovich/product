package uz.gc.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gc.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByIdOrName(Integer id, String name);
}
