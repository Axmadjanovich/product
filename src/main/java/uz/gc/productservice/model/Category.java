package uz.gc.productservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "category",
//        uniqueConstraints = @UniqueConstraint(name = "unique_name", columnNames = "name"),
        indexes = @Index(name = "category_name_ix", columnList = "name", unique = true)
)
public class Category {
    @Id
    @GeneratedValue(generator = "categoryIdGenerator")
    @SequenceGenerator(name = "categoryIdGenerator", sequenceName = "category_id_seq", initialValue = 1000, allocationSize = 1)
    private Integer id;
    private String name;
    private Integer parentId;
}
//1 Elektronika null
//2 kiyim       null
//3 Erkaklar kiyimi 2
//4 Sport kiyimlari 3
