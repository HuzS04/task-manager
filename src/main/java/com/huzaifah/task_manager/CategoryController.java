package com.huzaifah.task_manager;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/categories")
    public CategoryDTO createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id){
        boolean deleted = categoryService.deleteCategory(id);
        return deleted ? "Category deleted" : "Category not found";
    }
}
