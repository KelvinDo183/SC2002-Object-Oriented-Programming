package Boundary;

import Controller.*;
import model_Classes.*;

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
                System.out.println("");
                System.out.println("1. Enter again");
                System.out.println("2. Return to menu");
                System.out.println("");
                System.out.print("Select choice: ");
                if (InputController.getIntFromUser() == 2) {
                    exit = true;
                }
            }
        } while (!result && !exit);
        if (result) {
            System.out.println("You have logged in sucessfully");
            System.out.println("");
            return true;
        }
        return false;
    }

    public boolean verify() {
        System.out.println("--------------------------------------------------");
        System.out.println("------------------ ADMIN LOGIN -------------------");
        System.out.println("--------------------------------------------------");
        System.out.print("Please enter your email: ");
        email = InputController.getStringFromUser();
        System.out.print("Password: ");
        password = InputController.getStringFromUser();
        Admin user = adminsCtrl.readByEmail(email);
        if (user == null) {
            return false;
        } else {
            return user.validatePassword(password);
        }
    }
}
