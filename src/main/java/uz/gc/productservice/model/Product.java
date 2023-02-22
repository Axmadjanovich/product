package uz.gc.productservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@NamedQueries(value = {
        @NamedQuery(query = "select p from Product p where (p.price, p.category.id) in " +
                "(select max(pp.price), pp.category.id from Product pp group by pp.category.id)",
        name = "findExpensiveProductsOfCategory")
})
public class Product {
    @Id
    @GeneratedValue(generator = "productIdGenerator")
    @SequenceGenerator(name = "productIdGenerator", sequenceName = "product_id_seq", initialValue = 1000, allocationSize = 1)
    private Integer id;
    private String name;
    private Integer amount;
    private Double price;
    @ManyToOne
    private Category category;
    private String description;
}
