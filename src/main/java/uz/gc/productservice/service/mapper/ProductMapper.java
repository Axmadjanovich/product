package uz.gc.productservice.service.mapper;

import org.mapstruct.Mapper;
import uz.gc.productservice.dto.ProductDto;
import uz.gc.productservice.model.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper extends CommonMapper<ProductDto, Product>{
    List<ProductDto> toDtoList(List<Product> entities);
}
