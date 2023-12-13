package blogappapis.blogapplication.service;

import blogappapis.blogapplication.payloads.categoryDTO;

import java.util.List;

public interface categoryService {

    categoryDTO createCategory(categoryDTO categorydto);
    categoryDTO updateCategory(categoryDTO categoryDTO,Integer categoryId);
    public void deleteCategory(Integer categoryId);
    categoryDTO getCategory(Integer categoryId);
    List<categoryDTO> getAllCategory();
}
