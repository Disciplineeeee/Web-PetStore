package csu.web.petstore.persistence;

import csu.web.petstore.domain.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> getCategoryList();

    Category getCategory(String categoryId);

}
