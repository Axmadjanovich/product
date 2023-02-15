package uz.gc.productservice.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.gc.productservice.dto.ProductDto;
import uz.gc.productservice.dto.ResponseDto;
import uz.gc.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductResources {

    @Autowired
    private ProductService productService;

    @GetMapping("{id}")
    public ResponseDto<ProductDto> getProduct(@PathVariable Integer id){
        return productService.getById(id);
    }

    @PostMapping
    public ResponseDto<ProductDto> addProduct(@RequestBody @Valid ProductDto dto){
        return productService.addProduct(dto);
    }

    @GetMapping
    public ResponseDto<List<ProductDto>> getAll(){
        return productService.getAll();
    }

    @GetMapping("by-page")
    public ResponseDto<Page<ProductDto>> getAllByPage(@RequestParam Integer size, @RequestParam Integer page){
        return productService.getAllByPage(page, size);
    }

    //Jackson
    //Serialization -> Java object - JSON
    //Deserialization -> JSON - Java object
}
