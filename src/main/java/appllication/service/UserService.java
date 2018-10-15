package appllication.service;

import appllication.entity.User;
import appllication.model.Exception.EmailExistsException;
import appllication.model.Exception.LoginErrorException;
import appllication.model.dto.NewUserDto;
import appllication.model.login.Login;
import appllication.model.login.SesionToken;
import appllication.repository.UserDao;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userService")
public class UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, @Qualifier("userDao") UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @Transactional
    public User signUp(NewUserDto anUser) {
        if (emailExist(anUser.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email: " + anUser.getEmail());
        }
        User user = new User(anUser.getEmail(), anUser.getName(), anUser.getLastName(), passwordEncoder.encode(anUser.getPassword()), anUser.getBirthDate());
        userDao.save(user);
        return user;
    }

    @Transactional
    boolean emailExist(String email) {
        return userDao.findByEmail(email) != null;
    }

    @Transactional
    public SesionToken signIn(Login aLogin) {
        User user = userDao.findByEmail(aLogin.getEmail());
        boolean correctLogin = user != null && passwordEncoder.matches(aLogin.getPassword(), user.getPassword());
        if(correctLogin){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
            .withIssuer("auth0")
            .withClaim("email", user.getEmail())
            .sign(algorithm);
        return new SesionToken(user, token);
        }
        else {
            throw new LoginErrorException("Incorrect email or password");
        }
    }
}