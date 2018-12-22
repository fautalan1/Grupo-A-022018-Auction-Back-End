package appllication.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
public class User {

    @GeneratedValue
    @Id
    private long id;

    private String name;

    private String lastName;

    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    private String password;

    private LocalDateTime birthDate;

    public User() {}

    public User(String email, String name, String lastName, String password, LocalDateTime birthDate) {
        super();
        setEmail(email);
        setName(name);
        setPassword(password);
        setLastName(lastName);
        setBirthDate(birthDate);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
