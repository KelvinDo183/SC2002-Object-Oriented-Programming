package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.security.AllPermission;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import Model.*;

public class AdminsControl {
    public final static String FILENAME = "src/datastorage/admins.txt";
    public final static int EMAIL = 0;
    public final static int PASSWORDHASHED = 1;
    public final static int ROLE = 2;
    public static int valid = 1;
    // public static ArrayList<Admin> allAdminData = new ArrayList<Admin>();

    public void create(String username, String password) {
        if (isValidUser(username)) {
            try {
                Admin admin = new Admin(username, password);
                ArrayList<Admin> allData = new ArrayList<Admin>();
                File tempFile = new File(FILENAME);
                if (tempFile.exists())
                    allData = read();
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
                allData.add(admin);
                out.writeObject(allData);
                out.flush();
                out.close();
            } catch (IOException | NoSuchAlgorithmException e) {
                // ignore error
            }
        } else {
            // do nothing
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Admin> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Admin> adminListing = (ArrayList<Admin>) ois.readObject();
            ois.close();
            return adminListing;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Admin>();
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
        valid = 0;

        if (isExistingUser(username)) {
            System.out.println("Username existed! Please try another username!");
            isValid = false;
            valid = 0;
        }

        return isValid;
    }

    @SuppressWarnings("unchecked")
    public static boolean isExistingUser(String username) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Admin> allAdminData = (ArrayList<Admin>) ois.readObject();
            ois.close();
            for (int i = 0; i < allAdminData.size(); i++) {
                if (allAdminData.get(i).getEmail().equals(username))
                    return true;
            }
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
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