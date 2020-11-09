package com.jwj.dao;

import com.jwj.pojo.Category;

import java.util.List;

public interface CategoryMapper {

    //add a new category
    int addCategory(Category category);

    //search category type by category name
    List<String> queryTypeByName(String categoryName);
}
