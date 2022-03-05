package com.example.location_voitures.serviceImpl;

import com.example.location_voitures.dtos.CategoryDto;
import com.example.location_voitures.dtos.UserDto;
import com.example.location_voitures.entities.CategoryEntity;
import com.example.location_voitures.entities.UserEntity;
import com.example.location_voitures.exception.EntityNotFoundException;
import com.example.location_voitures.repositories.CategoryRepository;
import com.example.location_voitures.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Service
public class categoryServiceImpl implements CategoryService {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (CategoryEntity category: entities){
            CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto getOneCategories(long id) {
        CategoryEntity category = categoryRepository.findById(id);
        if(category==null){
            throw new EntityNotFoundException(" Category Not found");
        }
        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto setOneCategories(CategoryDto categoryDto) {
        CategoryEntity category = mapper.map(categoryDto, CategoryEntity.class);
        CategoryEntity category1 = categoryRepository.save(category);
        return mapper.map(category1, CategoryDto.class);
    }

    @Override
    public CategoryDto updateOneCategories(long id, CategoryDto categoryDto) {
        CategoryEntity category = categoryRepository.findById(id);
        if(category!=null){
            category.setImage(categoryDto.getImage());
            category.setNom(categoryDto.getNom());
            CategoryEntity category1 = categoryRepository.save(category);
            return mapper.map(category1, CategoryDto.class);
        }else {
            throw new EntityNotFoundException(" Category Not found");
        }
    }

    @Override
    public void deleteOneCategories(long id) {
        CategoryEntity category = categoryRepository.findById(id);
        categoryRepository.delete(category);
    }
}
