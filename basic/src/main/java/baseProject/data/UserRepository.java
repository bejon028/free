package baseProject.data;

import baseProject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("select u from UserRole r, User u where r.role=?1 and r.userId = u.id")
    List<User> findByRole(String roleName);
}
