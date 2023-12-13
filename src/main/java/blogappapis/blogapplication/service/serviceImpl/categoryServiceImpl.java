package blogappapis.blogapplication.service.serviceImpl;

import blogappapis.blogapplication.exception.ResourceNotFoundException;
import blogappapis.blogapplication.payloads.categoryDTO;
import blogappapis.blogapplication.service.categoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import blogappapis.blogapplication.repository.categoryRepo;
import blogappapis.blogapplication.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class categoryServiceImpl implements categoryService {
    @Autowired
    private categoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public categoryDTO createCategory(categoryDTO categorydto) {
        Category category=this.dtoTOCategory(categorydto);
        Category savedCategory=this.categoryRepo.save(category);

        return this.categoryToDTO(savedCategory);
    }

    @Override
    public categoryDTO updateCategory(categoryDTO categoryDTO, Integer categoryId) {
        Category category1=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryID",categoryId));
        category1.setCategoryTitle(categoryDTO.getCategoryTitle());
        category1.setDescription(categoryDTO.getDescription());
        Category category=this.categoryRepo.save(category1);

        return this.categoryToDTO(category);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","Categoryid",categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public categoryDTO getCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryID",categoryId));

        return this.categoryToDTO(category);
    }

    @Override
    public List<categoryDTO> getAllCategory() {
        List<Category> categoryList = this.categoryRepo.findAll();
        List<categoryDTO>categoryDTOList=categoryList.stream().map(Category->this.categoryToDTO(Category)).collect(Collectors.toList());
        return categoryDTOList;
    }

    private categoryDTO categoryToDTO(Category category){
        categoryDTO categoryDTO1=this.modelMapper.map(category,categoryDTO.class);
        return categoryDTO1;
    }

    private Category dtoTOCategory(categoryDTO categoryDTO){
        Category category1=this.modelMapper.map(categoryDTO, Category.class);
        return category1;
    }

}
