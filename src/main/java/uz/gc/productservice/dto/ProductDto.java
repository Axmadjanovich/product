package uz.gc.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Integer id;
    @NotBlank(message = "Name is null")
    private String name;
    @NotNull(message = "Amount is null")
    @Positive(message = "Amount is less than 0")
    private Integer amount;
    @NotNull(message = "Price is null")
    @Positive(message = "Price is less than 0")
    private Double price;
    private CategoryDto category;
    @NotBlank(message = "Description is null")
    private String description;
}
//Data transfer object
//Mapping
//Data access object