package uz.gc.productservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.gc.productservice.dto.ProductDto;
import uz.gc.productservice.dto.ResponseDto;
import uz.gc.productservice.model.Product;
import uz.gc.productservice.model.ProductCategories;
import uz.gc.productservice.repository.ProductRepository;
import uz.gc.productservice.repository.impl.ProductRepositoryImpl;
import uz.gc.productservice.service.mapper.ProductMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductRepositoryImpl productRepositoryImpl;
    private final ProductMapper productMapper;

    public ResponseDto<ProductDto> addProduct(ProductDto product){
         return productRepository.findFirstByIdOrName(product.getId(), product.getName())
                 .map(p -> ResponseDto.<ProductDto>builder()
                            .success(false)
                            .message("Product with ID " + product.getId() + " is already exists")
                            .build())
                .orElse(ResponseDto.<ProductDto>builder()
                            .success(true)
                            .data(productMapper.toDto(
                                    productRepository.save(productMapper.toEntity(product))
                            ))
                            .message("OK")
                            .build());
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

    public ResponseDto<List<ProductDto>> getExpensiveProducts(){
        List<ProductDto> products =  productRepository.expensiveProductsNamedQuery().stream()
                        .map(productMapper::toDto)
                        .toList();
        return ResponseDto.<List<ProductDto>>builder()
                .message("OK")
                .success(true)
                .data(products)
                .build();
    }

    @Transactional
    public ResponseDto<Stream<ProductDto>> getAll() {
        Stream<Product> products = productRepository.findAllBy();
//        if (products.isEmpty()){
//            return ResponseDto.<List<ProductDto>>builder()
//                    .message("Products not found")
//                    .build();
//        }
//        for (Product product : products) {
//            System.out.println(product.getName());
//        }

        return ResponseDto.<Stream<ProductDto>>builder()
                .message("OK")
                .success(true)
                .data(products.map(productMapper::toDto))
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

    public ResponseDto<Page<ProductCategories>> getProductsWithFilter(Map<String, String> params) {

        return ResponseDto.<Page<ProductCategories>>builder()
                .data(productRepositoryImpl.getProductsWithParams(params))
                .success(true)
                .message("OK")
                .build();
    }
}
//JDBC
//JPA - Java persistence API
//Hibernate, MyBatis, Eclipse-Link...
//JDBC Template
//Spring data JPA