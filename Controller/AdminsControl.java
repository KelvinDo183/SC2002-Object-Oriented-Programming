package Controller;

import java.io.File;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.security.AllPermission;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

//import BusinessLayer.UsersLayer;

import Model.*;

public class AdminsControl {
    public final static String FILENAME = "MOBLIMA/database/admins.txt";
    public final static int EMAIL = 0;
    public final static int PASSWORDHASHED = 1;
    public final static int ROLE = 2;
    public static ArrayList<Admin> allAdminData = new ArrayList<Admin>();

    public void create(String username, String password) throws NoSuchAlgorithmException {
        if (isValidUser(username)) {
            Admin admin = new Admin(username, password);
            allAdminData.add(admin);
        } else {
            // do nothing
        }
    }

    public ArrayList<Admin> read() {
        return allAdminData;
    }

    public Admin readByEmail(String valueToSearch) {
        ArrayList<Admin> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            Admin u = allData.get(i);
            if (u.getEmail().equals(valueToSearch))
                return u;
        }
        return null;
    }

    public void updatePasswordHashed(String email, String currentPassword, String newPassword) {
        ArrayList<Admin> allData = read();
        ArrayList<Admin> returnData = new ArrayList<Admin>();

        for (int i = 0; i < allData.size(); i++) {
            Admin u = allData.get(i);
            if (u.getEmail().equals(email)) // update Admin if email matches
                u.updatePassword(currentPassword, newPassword);
            returnData.add(u);
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void deleteByEmail(String email) {
        ArrayList<Admin> allData = read();
        ArrayList<Admin> returnData = new ArrayList<Admin>();

        for (int i = 0; i < allData.size(); i++) {
            Admin u = allData.get(i);
            if (!u.getEmail().equals(email)) // add Admin if email does not match
                returnData.add(u);
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void replaceExistingFile(String filename, ArrayList<Admin> returnData) {
        File tempFile = new File(filename);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(returnData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidUser(String username) {

        boolean isValid = true;

        if (isValidEmail(username) == false)
            isValid = false;

        if (isExistingUser(username)) {
            System.out.println("Username existed! Please try another username!");
            isValid = false;
        }

        return isValid;
    }

    public static boolean isExistingUser(String username) {
        for (int i = 0; i < allAdminData.size(); i++) {
            if (allAdminData.get(i).getEmail().equals(username))
                return true;
        }
        return false;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        if (email == null) {
            System.out.println("Email address is invalid!");
        }
        return email.matches(regex);
    }
}
