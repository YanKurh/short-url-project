package goit.com.shorturlproject.v1.repo;

import goit.com.shorturlproject.v1.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public User findByUserNameAndPassword(String userEmail, String password);
}
