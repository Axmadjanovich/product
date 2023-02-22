package uz.gc.productservice.service;

import org.springframework.stereotype.Service;
import uz.gc.productservice.dto.CategoryDto;
import uz.gc.productservice.dto.ResponseDto;
import uz.gc.productservice.model.Category;
import uz.gc.productservice.repository.CategoryRepository;
import uz.gc.productservice.service.mapper.CategoryMapper;

@Service
public record CategoryService(CategoryMapper categoryMapper,
                              CategoryRepository categoryRepository) {

    public ResponseDto<CategoryDto> addNewCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.getName())){
            return ResponseDto.<CategoryDto>builder()
                    .message("Category with name " + categoryDto.getName() + " is already exists")
                    .build();
        }
        Category category = categoryMapper.toEntity(categoryDto);
        categoryRepository.save(category);
        return ResponseDto.<CategoryDto>builder()
                .success(true)
                .message("OK")
                .data(categoryMapper.toDto(category))
                .build();
    }
}
