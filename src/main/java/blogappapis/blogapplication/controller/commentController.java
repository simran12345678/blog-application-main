package blogappapis.blogapplication.controller;

import blogappapis.blogapplication.payloads.apiResponse;
import blogappapis.blogapplication.payloads.commentDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import blogappapis.blogapplication.service.commentService;

import java.util.List;

@RestController
@RequestMapping("api/comment")
public class commentController {
    @Autowired
    commentService commentService;

    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<commentDTO> createComment(@RequestBody commentDTO commentDTO, @PathVariable Integer userId,@PathVariable Integer postId){
       commentDTO commentDTO1=this.commentService.createComment(commentDTO,userId,postId);
       return new ResponseEntity<>(commentDTO1, HttpStatus.CREATED);
    }
    @GetMapping("/getALL")
    public ResponseEntity<List<commentDTO>> getAllComment(){
        List<commentDTO> commentDTOList=this.commentService.findAll();
        return new ResponseEntity<>(commentDTOList,HttpStatus.FOUND);
    }
    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<List<commentDTO>> getByUser(@PathVariable Integer userId){
        List<commentDTO> commentDTOList=this.commentService.findByUser(userId);
        return new ResponseEntity<>(commentDTOList,HttpStatus.FOUND);
    }
    @GetMapping("/getByPost/{postId}")
    public ResponseEntity<List<commentDTO>> getByPost(@PathVariable Integer postId){
        List<commentDTO> commentDTOList=this.commentService.findByPost(postId);
        return new ResponseEntity<>(commentDTOList,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<apiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new apiResponse("deleted successfully",true),HttpStatus.OK);
    }
    @PutMapping("/update/{commentId}")
    public ResponseEntity<commentDTO> updateComment(@RequestBody commentDTO commentDTO,@PathVariable Integer commentId){
        commentDTO commentDTO1=this.commentService.updateComment(commentDTO,commentId);
        return new ResponseEntity<>(commentDTO1,HttpStatus.OK);
    }
}
