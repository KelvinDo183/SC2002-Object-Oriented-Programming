package Boundary;

import Controller.*;
import Model.*;

public class LoginUIAdmin {
    private String email;
    private String password;
    private AdminsControl adminsCtrl;

    public LoginUIAdmin() {
        this.adminsCtrl = new AdminsControl();
    }

    public LoginUIAdmin(AdminsControl adminsCtrl) {
        this.adminsCtrl = adminsCtrl;
    }

    public boolean main() {
        boolean result;
        boolean exit = false;
        do {
            result = verify();
            if (!result) {
                System.out.println("Wrong password or email.");
                System.out.println("1. Enter again");
                System.out.println("2. Exit");
                if (InputController.getIntFromUser() == 2) {
                    exit = true;
                }
            }
        } while (!result && !exit);
        if (result) {
            System.out.println("You have logged in sucessfully.\n\n");
            return true;
        }
        return false;
    }

    public boolean verify() {
        System.out.println("Please enter your email: ");
        email = InputController.getStringFromUser();
        System.out.println("Password: ");
        password = InputController.getStringFromUser();
        Admin user = adminsCtrl.readByEmail(email);
        if (user == null) {
            return false;
        } else {
            return user.validatePassword(password);
        }
    }
}
