package appllication.controller;

import appllication.entity.User;
import appllication.model.dto.NewUserDto;
import appllication.model.login.Login;
import appllication.model.login.SesionToken;
import appllication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User signIn(@RequestBody @Valid NewUserDto anUser){
        return userService.signUp(anUser);
    }

    @PostMapping("/users/sessions")
    public SesionToken signIn(@RequestBody @Valid Login anUser){
        return userService.signIn(anUser);
    }

}
