package uz.gc.productservice.service.mapper;

import org.mapstruct.Mapper;
import uz.gc.productservice.dto.ProductDto;
import uz.gc.productservice.model.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDto dto);
    ProductDto toDto(Product entity);
    List<ProductDto> toDtoList(List<Product> entities);
}
