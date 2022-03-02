package com.example.location_voitures.services;

import com.example.location_voitures.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    public List<CategoryDto> getAllCategories();
    public CategoryDto getOneCategories(long id);
    public CategoryDto setOneCategories(CategoryDto categoryDto);
    public CategoryDto updateOneCategories(long id, CategoryDto categoryDto);
    public void deleteOneCategories(long id);
}
