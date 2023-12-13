package blogappapis.blogapplication.service.serviceImpl;
import blogappapis.blogapplication.entity.Post;
import blogappapis.blogapplication.exception.ResourceNotFoundException;
import blogappapis.blogapplication.payloads.postDTO;
import blogappapis.blogapplication.service.postService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import blogappapis.blogapplication.repository.postRepo;
import blogappapis.blogapplication.repository.categoryRepo;
import blogappapis.blogapplication.repository.userRepo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import blogappapis.blogapplication.entity.user;
import blogappapis.blogapplication.entity.Category;

@Service
public class postServiceImpl implements postService{

    @Autowired
    postRepo postRepo;

    @Autowired
    categoryRepo categoryRepo;
    @Autowired
    userRepo userrepo;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public postDTO createPost(postDTO postDTO,Integer userId,Integer categoryId) {

        user user=this.userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userid",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
        Post post1=this.dtoToPost(postDTO);
        post1.setImgName("default.png");
        post1.setDate(new Date());
        post1.setUser(user);
        post1.setCategory(category);
        Post savedPost=this.postRepo.save(post1);
        return this.postToDTO(savedPost);
    }

    @Override
    public postDTO updatePost(postDTO postDTO, Integer postId) {
        Post post1=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postid",postId));
        post1.setTitle(postDTO.getTitle());
        post1.setContent(postDTO.getContent());
       Post updatePost= this.postRepo.save(post1);
        return this.postToDTO(updatePost);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post1=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postid",postId));
        this.postRepo.delete(post1);
    }

    @Override
    public List<postDTO> getAllPost() {
       List<Post> postList=this.postRepo.findAll();
        List<postDTO> postDTOList=postList.stream().map((post)->this.postToDTO(post)).collect(Collectors.toList());
        return postDTOList;
    }

    @Override
    public postDTO getPostById(Integer postId) {
        Post post1=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postid",postId));
        postDTO postDTO1=this.postToDTO(post1);
        return postDTO1;
    }

    @Override
    public List<postDTO> getPostByCategory(Integer categoryId) {
        Category category1=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
        List<Post> postList=this.postRepo.findByCategory(category1);
        List<postDTO> postDTOList=postList.stream().map((post)->this.postToDTO(post)).collect(Collectors.toList());
        return postDTOList;
    }

    @Override
    public List<postDTO> getPostByUser(Integer userId) {
        user user1=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","userid",userId));
        List<Post> postList=this.postRepo.findByUser(user1);
        List<postDTO> postDTOList=postList.stream().map((post)->this.postToDTO(post)).collect(Collectors.toList());
        return postDTOList;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }

    public Post dtoToPost(postDTO postDTO){
        Post post1=this.modelMapper.map(postDTO,Post.class);
        return post1;
    }
    public postDTO postToDTO(Post post)
    {
        postDTO postDTO1=this.modelMapper.map(post,postDTO.class);
        return postDTO1;
    }
}
