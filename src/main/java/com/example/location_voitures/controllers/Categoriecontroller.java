package com.example.location_voitures.controllers;

import com.example.location_voitures.dtos.CategoryDto;
import com.example.location_voitures.entities.CategoryEntity;
import com.example.location_voitures.requests.categories.CategoryRequest;
import com.example.location_voitures.responses.CategoryResponse;
import com.example.location_voitures.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class Categoriecontroller {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    ResponseEntity<List<CategoryResponse>> getAllCategories(){
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (CategoryDto categoryDto : categoryDtos){
            CategoryResponse categoryResponse = mapper.map(categoryDto, CategoryResponse.class);
        }
        return new ResponseEntity<List<CategoryResponse>>(categoryResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryResponse> getOneCategory(@PathVariable("id") long id){
        CategoryDto categoryDto = categoryService.getOneCategories(id);
        CategoryResponse response = mapper.map(categoryDto, CategoryResponse.class);
        return new ResponseEntity<CategoryResponse>(response, HttpStatus.OK);
    }


    @PostMapping()
    ResponseEntity<CategoryResponse> setCategory(@RequestBody CategoryRequest categoryRequest){
        CategoryDto categoryDto = mapper.map(categoryRequest, CategoryDto.class);
        CategoryDto dto = categoryService.setOneCategories(categoryDto);
        CategoryResponse categoryResponse = mapper.map(dto, CategoryResponse.class);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") long id, @RequestBody CategoryRequest categoryRequest){
        CategoryDto categoryDto = mapper.map(categoryRequest, CategoryDto.class);
        CategoryDto dto = categoryService.updateOneCategories(id,categoryDto);
        CategoryResponse categoryResponse = mapper.map(dto, CategoryResponse.class);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteOneCategory(@PathVariable("id") long id){
        categoryService.deleteOneCategories(id);
        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
    }

}
