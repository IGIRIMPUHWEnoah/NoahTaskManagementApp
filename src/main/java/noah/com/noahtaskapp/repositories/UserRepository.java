package noah.com.noahtaskapp.repositories;

import noah.com.noahtaskapp.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component

public interface UserRepository extends JpaRepository<UserModel,Long> {

    boolean existsByEmail(String email);
    Optional<UserModel> findByEmail(String email);
}
