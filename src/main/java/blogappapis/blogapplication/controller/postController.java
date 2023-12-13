package blogappapis.blogapplication.controller;

import blogappapis.blogapplication.payloads.apiResponse;
import blogappapis.blogapplication.payloads.postDTO;
import blogappapis.blogapplication.service.postService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class postController {
    @Autowired
    postService postService;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public postDTO createPost(@RequestBody postDTO postDTO1, @PathVariable Integer userId,@PathVariable Integer categoryId){
        postDTO postDTO2=this.postService.createPost(postDTO1,userId,categoryId);
        return postDTO2;
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<postDTO>> getPostByCategory(@PathVariable Integer categoryId){
        List<postDTO> postDTOList=this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDTOList,HttpStatus.FOUND);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<postDTO>> getPostByUser(@PathVariable Integer userId){
        List<postDTO> postDTOList=this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postDTOList,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<apiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new apiResponse("delete successfully",true),HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<postDTO>> getAllPost(){
        List<postDTO> postDTOList=this.postService.getAllPost();
        return new ResponseEntity<>(postDTOList,HttpStatus.FOUND);
    }
    @GetMapping("/getById/{postId}")
    public ResponseEntity<postDTO> getById(@PathVariable Integer postId){
        postDTO postDTO=this.postService.getPostById(postId);
        return new ResponseEntity<>(postDTO,HttpStatus.FOUND);
    }
    @PutMapping("/update/{postId}")
    public ResponseEntity<postDTO> updatePost(@RequestBody postDTO postDTO, @PathVariable Integer postId){
        postDTO postDTO1=this.postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(postDTO1,HttpStatus.ACCEPTED);
    }
}
