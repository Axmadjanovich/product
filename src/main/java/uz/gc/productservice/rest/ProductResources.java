package uz.gc.productservice.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.gc.productservice.dto.ProductDto;
import uz.gc.productservice.dto.ResponseDto;
import uz.gc.productservice.model.ProductCategories;
import uz.gc.productservice.service.ProductService;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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


    @GetMapping("expensive")
    public ResponseDto<List<ProductDto>> getExpensiveProducts(){
        return productService.getExpensiveProducts();
    }

    @GetMapping
    public ResponseDto<Stream<ProductDto>> getAll(){
        return productService.getAll();
    }

    @GetMapping("params")
    public ResponseDto<Page<ProductCategories>> getAllWithFilter(@RequestParam Map<String, String> params){
        return productService.getProductsWithFilter(params);
    }

    @GetMapping("by-page")
    public ResponseDto<Page<ProductDto>> getAllByPage(@RequestParam Integer size, @RequestParam Integer page){
        return productService.getAllByPage(page, size);
    }

    //Jackson
    //Serialization -> Java object - JSON
    //Deserialization -> JSON - Java object
}
