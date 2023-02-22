package uz.gc.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gc.productservice.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
}
