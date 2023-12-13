package blogappapis.blogapplication.service;
import blogappapis.blogapplication.payloads.commentDTO;

import java.util.List;

public interface commentService {
    commentDTO createComment(commentDTO commentDTO,Integer userId,Integer postId);
    List<commentDTO> findByUser(Integer userId);
    List<commentDTO> findByPost(Integer postId);
    List<commentDTO> findAll();
    void deleteComment(Integer commentId);
    commentDTO updateComment(commentDTO commentDTO,Integer commentId);
}
