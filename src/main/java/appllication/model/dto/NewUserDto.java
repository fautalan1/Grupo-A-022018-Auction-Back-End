package appllication.model.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NewUserDto {

    private String name;

    private String lastName;

    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 4, max = 10,
            message = " password must be between 4 and 10 characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Za-z])[A-Za-z0-9]{1,10}$")
    private String password;

    private LocalDateTime birthDate;

    public NewUserDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

}
