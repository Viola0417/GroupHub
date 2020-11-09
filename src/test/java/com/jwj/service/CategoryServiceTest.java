package com.jwj.service;

import com.jwj.pojo.Category;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CategoryServiceTest {
    @Test
    public void testAddCategory() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CategoryService categoryServiceImpl = (CategoryService) context.getBean("CategoryServiceImpl");
        String categoryType = "movies";
        String categoryName = "Gone with the Wind";
        String description = "A manipulative woman and a roguish man conduct a turbulent romance during the American " +
                "Civil War and Reconstruction periods.";
        Category category = new Category(categoryType, categoryName, description);
        categoryServiceImpl.addCategory(category);
        System.out.println("Category add completed!");
        System.out.println(category.toString());
    }

    @Test
    public void testQueryTypeByName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CategoryService categoryServiceImpl = (CategoryService) context.getBean("CategoryServiceImpl");
        String categoryName = "Gone with the Wind";
        List<String> categoryTypeList = categoryServiceImpl.queryTypeByName(categoryName);
        for (String s: categoryTypeList) {
            System.out.println(s);
        }
    }
}
