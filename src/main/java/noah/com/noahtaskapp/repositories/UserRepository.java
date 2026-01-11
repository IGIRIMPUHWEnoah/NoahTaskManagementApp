package noah.com.noahtaskapp.repositories;

import noah.com.noahtaskapp.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component

public interface UserRepository extends JpaRepository<UserModel,Long> {
}
