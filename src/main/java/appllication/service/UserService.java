package appllication.service;

import appllication.entity.User;
import appllication.model.Exception.EmailExistsException;
import appllication.model.Exception.LoginErrorException;
import appllication.model.dto.NewUserDto;
import appllication.model.login.Login;
import appllication.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userService")
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("userDao") UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User signUp(NewUserDto anUser) {
        if (emailExist(anUser.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email: " + anUser.getEmail());
        }
        User user = new User(anUser.getEmail(), anUser.getName(), anUser.getLastName(), anUser.getPassword(), anUser.getBirthDate());
        userDao.save(user);
        return user;
    }

    @Transactional
    boolean emailExist(String email) {
        return userDao.findByEmail(email) != null;
    }

    @Transactional
    public User signIn(Login aLogin) {
        User user = userDao.findByEmail(aLogin.getEmail());
        boolean correctLogin = user != null && aLogin.getPassword().equals(user.getPassword());
        if(correctLogin){
            return user;
        }
        else {
            throw new LoginErrorException("Incorrect email or password");
        }
    }
}
