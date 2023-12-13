package blogappapis.blogapplication.service.serviceImpl;

import blogappapis.blogapplication.entity.Comment;
import blogappapis.blogapplication.entity.Post;
import blogappapis.blogapplication.entity.user;
import blogappapis.blogapplication.exception.ResourceNotFoundException;
import blogappapis.blogapplication.payloads.commentDTO;
import blogappapis.blogapplication.payloads.postDTO;
import blogappapis.blogapplication.repository.commentRepo;
import blogappapis.blogapplication.repository.postRepo;
import blogappapis.blogapplication.repository.userRepo;
import blogappapis.blogapplication.service.commentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class commentServiceImpl implements commentService {
    @Autowired
    commentRepo commentRepo;
    @Autowired
    userRepo userRepo;
    @Autowired
    postRepo postRepo;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public commentDTO createComment(commentDTO commentDTO, Integer userId, Integer postId) {
        user user1= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userid",userId));
        Post post1=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postid",postId));
        Comment comment1=this.dtoToComment(commentDTO);
        comment1.setUser(user1);
        comment1.setPost(post1);
        Comment savedComment=this.commentRepo.save(comment1);
        return this.commentToDTO(savedComment);
    }

    @Override
    public List<commentDTO> findByUser(Integer userId) {
        user user1= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userid",userId));
        List<Comment> commentList=this.commentRepo.findByUser(user1);
        List<commentDTO>commentDTOList=commentList.stream().map((comment)->this.commentToDTO(comment)).collect(Collectors.toList());
        return commentDTOList;
    }

    @Override
    public List<commentDTO> findByPost(Integer postId) {
        Post post1=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postid",postId));
        List<Comment> commentList=this.commentRepo.findByPost(post1);
        List<commentDTO> commentDTOList=commentList.stream().map((comment)->this.commentToDTO(comment)).collect(Collectors.toList());
        return commentDTOList;
    }

    @Override
    public List<commentDTO> findAll() {
        List<Comment> commentList=this.commentRepo.findAll();
        List<commentDTO> commentDTOList=commentList.stream().map((comment)->this.commentToDTO(comment)).collect(Collectors.toList());
        return commentDTOList;
    }

    @Override
    public void deleteComment(Integer commentId) {
    Comment comment1=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","commentid",commentId));
    this.commentRepo.delete(comment1);
    }

    @Override
    public commentDTO updateComment(commentDTO commentDTO, Integer commentId) {
        Comment comment1=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","commentid",commentId));
        comment1.setContext(commentDTO.getContext());
        Comment comment2=this.commentRepo.save(comment1);
        return this.commentToDTO(comment2);
    }
    public Comment dtoToComment(commentDTO commentDTO){
        Comment comment1=this.modelMapper.map(commentDTO,Comment.class);
        return comment1;
    }
    public commentDTO commentToDTO(Comment comment)
    {
        commentDTO commentDTO1=this.modelMapper.map(comment,commentDTO.class);
        return commentDTO1;
    }
}
