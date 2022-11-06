package Boundary;

import java.security.NoSuchAlgorithmException;

import Controller.*;

public class RegisterUIAdmin {
    private String email;
    private String password;
    private String password2;
    private int noOftimes;
    private boolean consistentPassword = false;

    private AdminsControl adminsCtrl;

    RegisterUIAdmin() {
        this.adminsCtrl = new AdminsControl();
    }

    public void main() throws NoSuchAlgorithmException {
        System.out.println("Please enter your email: ");
        email = InputController.getEmailFromUser();
        System.out.println("Password: ");
        password = InputController.getStringFromUser();
        System.out.println("Confirmation of password: ");
        password2 = InputController.getStringFromUser();
        consistentPassword = password.equals(password2);
        noOftimes = 5;
        while (noOftimes > 0) {
            if (consistentPassword) {
                adminsCtrl.create(email, password);
                System.out.println("You have registered successfully");
                break;
            } else {
                noOftimes -= 1;
                System.out.println("Password again to verify: " + "(Number of attempts left: )" + noOftimes + "(/5)");
                password2 = InputController.getStringFromUser();
                consistentPassword = password.equals(password2);
            }
        }
    }
}
