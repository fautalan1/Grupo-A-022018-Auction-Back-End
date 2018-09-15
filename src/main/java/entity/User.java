package entity;

import java.util.Date;

public class User {

    private long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Date birthdate;
    private Date invalidationDate;

    public User(String email){

    }

    public User(){

    }

    public String getEmail() {
        return email;
    }
}
