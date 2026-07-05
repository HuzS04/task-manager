package com.huzaifah.task_manager;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryDTO(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id){
        return categoryRepository.findById(id)
                .map(category -> new CategoryDTO(category.getId(), category.getName()))
                .orElse(null);
    }

    public CategoryDTO createCategory(Category category){
        Category saved = categoryRepository.save(category);
        return new CategoryDTO(saved.getId(), saved.getName());
    }

    public boolean deleteCategory(Long id){
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
