package blogappapis.blogapplication.repository;

import blogappapis.blogapplication.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepo extends JpaRepository<Category, Integer> {
}
