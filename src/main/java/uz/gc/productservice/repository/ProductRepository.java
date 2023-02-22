package uz.gc.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.gc.productservice.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByIdOrName(Integer id, String name);
    Optional<Product> findFirstByIdOrName(Integer id, String name);
    @Query(value = "select p from Product p where (p.price, p.category.id) in " +
            "(select max(pp.price), pp.category.id from Product pp group by pp.category.id)")
    List<Product> expensiveProducts();

    @Query(name = "findExpensiveProductsOfCategory")
    List<Product> expensiveProductsNamedQuery();

    Stream<Product> findAllBy();
}
//1. Native query
//2. HQL query
//3. Named query
