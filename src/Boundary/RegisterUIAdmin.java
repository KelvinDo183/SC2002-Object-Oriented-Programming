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
        System.out.println("--------------------------------------------------");
        System.out.println("----------- REGISTER NEW ADMIN ACCOUNT -----------");
        System.out.println("--------------------------------------------------");
        System.out.print("Please enter your email: ");
        email = InputController.getEmailFromUser();
        System.out.print("Password: ");
        password = InputController.getStringFromUser();
        System.out.print("Confirmation of password: ");
        password2 = InputController.getStringFromUser();
        consistentPassword = password.equals(password2);
        noOftimes = 5;
        while (noOftimes > 0) {
            if (consistentPassword) {
                adminsCtrl.create(email, password);
                if (AdminsControl.valid == 1) {
                    System.out.println("You have registered successfully");
                    break;
                }
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
