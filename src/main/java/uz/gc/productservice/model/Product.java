package uz.gc.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    private Integer id;
    private String name;
    private Integer amount;
    private Double price;
    private String description;
}
