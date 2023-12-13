package blogappapis.blogapplication.controller;

import blogappapis.blogapplication.payloads.apiResponse;
import blogappapis.blogapplication.payloads.userDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import blogappapis.blogapplication.service.userService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class userController {

    @Autowired
    private userService userService;
    @GetMapping("/getALL")
    public ResponseEntity<List<userDTO>> getAllUser(){
        List<userDTO> userDTOList =this.userService.getAllUsers();
        return  ResponseEntity.ok(userDTOList);
    }

    @PostMapping("/create")
    public ResponseEntity<userDTO> createUser(@Valid @RequestBody userDTO userDTO1){
        userDTO userDTO2=this.userService.createUser(userDTO1);
        return new ResponseEntity<>(userDTO2, HttpStatus.CREATED);
    }

    @GetMapping("/getByID/{userId}")
    public ResponseEntity<userDTO> getUserById(@PathVariable Integer userId){
        userDTO userDTO=this.userService.getUserById(userId);
        return new ResponseEntity<>(userDTO,HttpStatus.FOUND);
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<userDTO> updateUser(@Valid @RequestBody userDTO userDTO,@PathVariable Integer userId){
        userDTO userDTO1=this.userService.updateUser(userDTO,userId);
        return ResponseEntity.ok(userDTO1);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<apiResponse> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity(new apiResponse("user deleted successfully",true),HttpStatus.OK);
    }
}
