package uz.gc.productservice.service.mapper;

import org.mapstruct.Mapper;
import uz.gc.productservice.dto.CategoryDto;
import uz.gc.productservice.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends CommonMapper<CategoryDto, Category>{
}
