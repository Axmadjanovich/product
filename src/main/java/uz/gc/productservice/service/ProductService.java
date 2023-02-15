package uz.gc.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.gc.productservice.dto.ProductDto;
import uz.gc.productservice.dto.ResponseDto;
import uz.gc.productservice.model.Product;
import uz.gc.productservice.repository.ProductRepository;
import uz.gc.productservice.service.mapper.ProductMapper;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public ResponseDto<ProductDto> addProduct(ProductDto product){
        boolean isExists = productRepository.existsByIdOrName(product.getId(), product.getName());

        if (isExists){
            return ResponseDto.<ProductDto>builder()
                    .success(false)
                    .message("Product with ID " + product.getId() + " is already exists")
                    .build();
        }

        productRepository.save(productMapper.toEntity(product));
        return ResponseDto.<ProductDto>builder()
                .success(true)
                .data(product)
                .message("OK")
                .build();
    }

    public ResponseDto<ProductDto> getById(Integer id) {
        return productRepository.findById(id)
                .map(p -> ResponseDto.<ProductDto>builder()
                        .message("OK")
                        .success(true)
                        .data(productMapper.toDto(p))
                        .build())
                .orElse(ResponseDto.<ProductDto>builder()
                        .message("Product with ID " + id + " is not found")
                        .build());
    }

    public ResponseDto<List<ProductDto>> getAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()){
            return ResponseDto.<List<ProductDto>>builder()
                    .message("Products not found")
                    .build();
        }

        return ResponseDto.<List<ProductDto>>builder()
                .message("OK")
                .success(true)
                .data(productMapper.toDtoList(products))
                .build();
    }

    public ResponseDto<Page<ProductDto>> getAllByPage(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);

        Page<Product> products = productRepository.findAll(pageRequest);

        if (products.isEmpty()){
            return ResponseDto.<Page<ProductDto>>builder()
                    .message("There is no item with page " + (page + 1) + " of size " + size)
                    .build();
        }

        return ResponseDto.<Page<ProductDto>>builder()
                .success(true)
                .message("OK")
                .data(products.map(productMapper::toDto))
                .build();
    }
}
//JDBC
//JPA - Java persistence API
//Hibernate, MyBatis, Eclipse-Link...
//JDBC Template
//Spring data JPA