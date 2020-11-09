package com.jwj.service;

import com.jwj.dao.CategoryMapper;
import com.jwj.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryMapper categoryMapper;

    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    //add a new category
    public int addCategory(Category category) {
        return categoryMapper.addCategory(category);
    }

    //search category type by category name
    public List<String> queryTypeByName(String categoryName) {
        return categoryMapper.queryTypeByName(categoryName);
    }
}
