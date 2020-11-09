package com.jwj.service;

import com.jwj.pojo.Category;

import java.util.List;

public interface CategoryService {

    //add a new category
    public int addCategory(Category category);

    //search category type by category name
    public List<String> queryTypeByName(String categoryName);
}
