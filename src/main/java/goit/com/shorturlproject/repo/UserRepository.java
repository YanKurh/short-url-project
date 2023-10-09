package goit.com.shorturlproject.repo;
import goit.com.shorturlproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByEmail(String email);

    @Override
    void delete(User user);

    // User findByEmailIgnoreCase(String emailId);
   // Boolean existsByEmail(String email);
}