package com.ecommerce_instrumentos.services.admin.category;

import com.ecommerce_instrumentos.dto.CategoryDto;
import com.ecommerce_instrumentos.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createcategory(CategoryDto categoryDto);

    List<Category> getAllCategories();

}
