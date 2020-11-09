package com.jwj.controller;

import com.jwj.pojo.Category;
import com.jwj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("CategoryServiceImpl")
    private CategoryService categoryService;

    @RequestMapping("/addGroup")
    public String addGroup(@RequestParam("categoryTypeSelection") String categoryType, @RequestParam("categoryNameInput") String categoryName,
                           @RequestParam("categoryDescriptionText") String categoryDescription, Model model) {
        System.out.println("add group: type => " + categoryType);
        System.out.println("name => " + categoryName);
        System.out.println("description => " + categoryDescription);

        String errorMsg = "";
        List<String> existedCategoryType = categoryService.queryTypeByName(categoryName);

        if (!existedCategoryType.isEmpty()) {
            System.out.print("current existed type => ");
            for (String s: existedCategoryType) {
                System.out.print(s + "  ");
            }
        }

        if (!existedCategoryType.isEmpty() && existedCategoryType.contains(categoryType)) {
            errorMsg = "This group has been added already!";
            model.addAttribute("errorMsg", errorMsg);
            return "adminAddGroup";
        }

        categoryService.addCategory(new Category(categoryType, categoryName, categoryDescription));

        String msg = "Successfully add group, return";
        model.addAttribute("msg", msg);
        return "adminAddGroup";
    }
}
