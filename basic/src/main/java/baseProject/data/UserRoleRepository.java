package baseProject.data;

import baseProject.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select r.role from UserRole r, User u where u.email=?1 and r.userId = u.id")
    List<String> findRoleByUserName(String userName);
}

