package appllication.repository;

import appllication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("userDao")
public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String anEmail);

}
