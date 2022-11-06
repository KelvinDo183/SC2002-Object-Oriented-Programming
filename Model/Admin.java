package Model;

import java.security.NoSuchAlgorithmException;

public class Admin extends User {
    public Admin(String username, String password) throws NoSuchAlgorithmException {
        super(username, password, ADMIN);
    }
}
