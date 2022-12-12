package com.rubify.music.controller;

import com.rubify.music.dto.CategoryDTO;
import com.rubify.music.entity.SongCategoryEntity;
import com.rubify.music.repository.ICategoryRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private final ICategoryRepository categoryRepository;

    public CategoryController(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/categories")
    public ResponseEntity<?> findAllCategories(){
        List<SongCategoryEntity> list = categoryRepository.findAll();
        List<EntityModel> categoryDTOS = new ArrayList<>();

        list.stream().forEach((songCategoryEntity -> {
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .id(songCategoryEntity.getId())
                    .name(songCategoryEntity.getCategoryName())
                    .build();
            categoryDTOS.add(EntityModel.of(categoryDTO));
        }));

        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOS);
    }
}
