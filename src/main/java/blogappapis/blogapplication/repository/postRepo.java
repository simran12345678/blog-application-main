package blogappapis.blogapplication.repository;

import blogappapis.blogapplication.entity.Category;
import blogappapis.blogapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import blogappapis.blogapplication.entity.user;
import java.util.List;

public interface postRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(user user);
    List<Post> findByCategory(Category category);

}
