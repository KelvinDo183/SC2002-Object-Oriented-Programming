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

import model_Classes.*;

public class AdminsControl {

    /**
     * File name of Database file to access
     */
    public final static String FILENAME = "src/datastorage/admins.txt";

    /**
     * Some constants
     */
    public final static int EMAIL = 0;
    public final static int PASSWORDHASHED = 1;
    public final static int ROLE = 2;
    public static int valid = 1;

    /**
     * CREATE a new admin and add it into the database file
     * Attributes are validated before creation
     * If attributes are not allowed, throw error and do nothing
     * If Database file exist, existing records are read and new Admin object is
     * aopended before saving
     * If Database file does not exist, Admin object will be written to a new file
     * and saved
     * 
     * @param username Email of the Admin to be created
     * @param password Unencrypted plain text password of Admin to be created
     */
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

    /**
     * READ and return every Admin in the Database file
     * If Database file not found, ignore error and return empty list
     * 
     */
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

    /**
     * READ and return an Admin by searching for one with matching email in the
     * Database file
     * 
     * @param valueToSearch Email of admin to search for
     * @return Admin Return Admin if found, else null object
     */
    public Admin readByEmail(String valueToSearch) {
        ArrayList<Admin> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            Admin u = allData.get(i);
            if (u.getEmail().equals(valueToSearch))
                return u;
        }
        return null;
    }

    /**
     * UPDATE an Admin's password in Database file
     * Validate user's input of current password to ensure password is correct
     * before updating it
     * 
     * @param email           Email of admin who password will be updated
     * @param currentPassword Current password (Unencrypted) of Admin
     * @param newPassword     New password (Unencrypted) of Admin
     *
     */
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

    /**
     * Delete an Admin in the Database file, based on the email attribute passed
     * 
     * @param email Email of Admin who will be deleted
     */
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

    /**
     * Overwrite Database file with new data of list of Admin
     * 
     * @param filename   Filename to check for
     * @param returnData New ArrayList of Admin to be written to the file
     */
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

    /**
     * Validate the user
     * 
     * @param username username to check for
     * @return boolean validation of user
     */
    public static boolean isValidUser(String username) {

        boolean isValid = true;

        if (isValidEmail(username) == false)
            isValid = false;
        valid = 0;

        if (isExistingUser(username)) {
            System.out.println("Username existed! Please try another username!");
            System.out.println("");
            isValid = false;
            valid = 0;
        }

        return isValid;
    }

    /**
     * Check whether user is existing or not
     * 
     * @param username username to check for
     * @return boolean existence of user
     */
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

    /**
     * Validate the email
     * 
     * @param email email to check for
     * @return boolean validation of email
     */
    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        if (email == null) {
            System.out.println("Email address is invalid!");
            System.out.println("");
        }
        return email.matches(regex);
    }
}