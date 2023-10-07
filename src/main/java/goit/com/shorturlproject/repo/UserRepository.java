package goit.com.shorturlproject.repo;
import goit.com.shorturlproject.entity.request.SignupRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SignupRequest,Long>{       //<User, Long>
    SignupRequest findByEmailIgnoreCase(String emailId);

    Boolean existsByEmail(String email);
}