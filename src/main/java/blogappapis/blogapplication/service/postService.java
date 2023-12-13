package blogappapis.blogapplication.service;
import blogappapis.blogapplication.payloads.postDTO;
import blogappapis.blogapplication.entity.Post;

import java.util.List;

public interface postService {
    postDTO createPost(postDTO postDTO, Integer userId,Integer categoryId);
    postDTO updatePost(postDTO postDTO,Integer postId);
    void deletePost(Integer postId);
    List<postDTO> getAllPost();
    postDTO getPostById(Integer postId);
    List<postDTO> getPostByCategory(Integer categoryId);
    List<postDTO> getPostByUser(Integer userId);
    List<Post> searchPost(String keyword);

}
