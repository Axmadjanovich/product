package uz.gc.productservice.rest;

import org.springframework.web.bind.annotation.*;
import uz.gc.productservice.dto.CategoryDto;
import uz.gc.productservice.dto.ProductDto;
import uz.gc.productservice.dto.ResponseDto;
import uz.gc.productservice.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("category")
public record CategoryResources(CategoryService service) {

    @PostMapping
    public ResponseDto<CategoryDto> add(@RequestBody CategoryDto categoryDto){
        return service.addNewCategory(categoryDto);
    }
}
