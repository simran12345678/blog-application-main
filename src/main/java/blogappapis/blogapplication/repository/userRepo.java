package blogappapis.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import blogappapis.blogapplication.entity.user;

public interface userRepo extends JpaRepository<user,Integer> {
}
