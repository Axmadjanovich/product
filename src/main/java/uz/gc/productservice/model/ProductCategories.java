package uz.gc.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Subselect;

@Entity
@Getter
@Setter
@Subselect("select name, amount, price from product")
public class ProductCategories {
    @Id
    private Integer id;
    private String name;
    @Column(name = "category_name")
    private String categoryName;
    private Integer amount;
    private Double price;
}
