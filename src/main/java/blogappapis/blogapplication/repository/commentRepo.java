package blogappapis.blogapplication.repository;

import blogappapis.blogapplication.entity.Comment;
import blogappapis.blogapplication.entity.Post;
import blogappapis.blogapplication.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface commentRepo extends JpaRepository<Comment,Integer> {
    List<Comment> findByUser(user user);
    List<Comment> findByPost(Post post);
}
