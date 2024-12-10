package com.lingolearnhub.category;

import java.util.ArrayList;
import java.util.List;

public class CategoryController {

    private final List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public boolean removeCategory(String name) {
        return categories.removeIf(category -> category.getName().equals(name));
    }

    public Category findCategoryByName(String name) {
        return categories.stream().filter(category -> category.getName().equals(name)).findFirst().orElse(null);
    }

}
