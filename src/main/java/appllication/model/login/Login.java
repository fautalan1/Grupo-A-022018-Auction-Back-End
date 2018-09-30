package appllication.model.login;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Login {

    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 4, max = 10,
            message = " password must be between 4 and 10 characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Za-z])[A-Za-z0-9]{1,10}$")
    private String password;

    public Login() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
