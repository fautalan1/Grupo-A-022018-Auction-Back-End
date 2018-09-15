package modelTest;

import entity.User;

class UserFactory {

    static User anyUser() {
        return new User("emailExample@gmail.com");
    }
}
