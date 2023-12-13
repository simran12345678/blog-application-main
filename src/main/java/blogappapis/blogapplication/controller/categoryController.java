package blogappapis.blogapplication.controller;

import blogappapis.blogapplication.payloads.apiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import blogappapis.blogapplication.service.categoryService;
import blogappapis.blogapplication.payloads.categoryDTO;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class categoryController {

    @Autowired
    categoryService categoryService;

    @GetMapping("/getALL")
    public ResponseEntity<List<categoryDTO>> getAllCategory(){
        List<categoryDTO> categoryDTOList=this.categoryService.getAllCategory();
        return ResponseEntity.ok(categoryDTOList);
    }
    @GetMapping("/getById/{categoryId}")
    public ResponseEntity<categoryDTO> getCategoryById(@PathVariable Integer categoryId){
       categoryDTO categoryDTO=this.categoryService.getCategory(categoryId);
       return new ResponseEntity<>(categoryDTO, HttpStatus.FOUND);
    }
    @PostMapping("/create")
    public ResponseEntity<categoryDTO> createcategory(@Valid @RequestBody categoryDTO categoryDTO1)
    {
        categoryDTO categoryDTO2=this.categoryService.createCategory(categoryDTO1);
        return new ResponseEntity<>(categoryDTO2,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<apiResponse> deleteCategory(@PathVariable Integer categoryId)
    {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new apiResponse("category deleted successfully",true),HttpStatus.OK);
    }
    @PatchMapping("/update/{categoryId}")
    public ResponseEntity<categoryDTO> updatecategory(@Valid @RequestBody categoryDTO categoryDTO,@PathVariable Integer categoryId){
        categoryDTO categoryDTO1=this.categoryService.updateCategory(categoryDTO,categoryId);
        return new ResponseEntity<>(categoryDTO1,HttpStatus.OK);
    }
}
