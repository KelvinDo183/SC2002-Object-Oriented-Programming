package Controller;

import static Controller.CinemaController.SESSIONS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import Controller.MovieController;
import exceptions.InvalidTxnException;

import model_Classes.*;

public class TransactionController {

    /**
     * The file name of the database file that this controller will access
     */
    public final static String FILENAME = "src/datastorage/transaction.txt";

    /**
     * Some constants
     */
    public final static int TID = 0;
    public final static int CINEMA_CODE = 1;
    public final static int NAME = 2;
    public final static int EMAIL = 3;
    public final static int MOBILE_NUMBER = 4;
    public final static int MOVIE = 5;
    public final static int SEATS_SELECTED = 6;
    public final static int TOTAL_PRICE = 7;

    /**
     * The Cinema Controller that this controller will reference
     */
    private CinemaController cinemaCtrl = new CinemaController();

    /**
     * The Cineplex Controller that this controller will reference
     */
    private CineplexeController cineplexCtrl = new CineplexeController();

    /**
     * CREATE a new Transaction and add it into the database file
     * 
     * @param cinemaCode    This transaction's cinema code
     * @param name          This transaction's name
     * @param email         email of this transaction
     * @param mobileNumber  mobile number of this transaction
     * @param session       This transaction's session
     * @param seatsSelected This transaction's selected seats
     * @param totalPrice    This transaction's total price
     */
    public void create(String cinemaCode, String name, String email, String mobileNumber, Session session,
            ArrayList<Integer> seatsSelected, Double totalPrice) throws FileNotFoundException, InvalidTxnException {
        if (isValidTransaction(cinemaCode, name, email, mobileNumber, session.getMovie())) {

            try {
                ArrayList<Transaction> allTxnData = new ArrayList<Transaction>();

                File tempFile = new File(FILENAME);
                if (tempFile.exists()) {
                    allTxnData = read();
                }

                int lengthArray = allTxnData.size();
                int TID;
                if (lengthArray > 0)
                    TID = lengthArray + 2001;
                else
                    TID = 2001;

                Transaction txn = new Transaction(Integer.toString(TID), cinemaCode, name, email, mobileNumber, session,
                        seatsSelected, totalPrice);

                // assume that transaction is successful
                // proceed to update seat availability so selected seats are not subsequently
                // sold
                ArrayList<Cineplex> allCineplexes = this.cineplexCtrl.read();

                for (int i = 0; i < allCineplexes.size(); i++) {
                    ArrayList<Cinema> cinemaList = allCineplexes.get(i).getCinemas();

                    for (int j = 0; j < cinemaList.size(); j++) {
                        if (cinemaList.get(j).getCode().equals(cinemaCode)) {
                            Cinema selectedCinema = cinemaList.get(j);
                            ArrayList<Session> sessions = selectedCinema.getSessions();
                            for (int k = 0; k < sessions.size(); k++) {
                                if (sessions.get(k).getStringSessionDateTime()
                                        .equals(session.getStringSessionDateTime())) {
                                    Session existingSession = sessions.get(k);
                                    // update seatsAvailability with one line?
                                    existingSession.setSeatsAvailability(session.getSeatsAvailability());

                                }

                            }

                            // sessions.add(session);
                            // selectedCinema.setSessions(sessions);
                            this.cinemaCtrl.updateByAttribute(SESSIONS, cinemaCode, sessions);
                            // sessions.clear();
                            break;
                        }

                    }
                }

                System.out.println("Cinema Control updated. Seating arrangement should reflect this transaction. ");

                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
                allTxnData.add(txn);

                out.writeObject(allTxnData);
                out.flush();
                out.close();

            } catch (IOException e) {
                // System.out.println(e.getStackTrace());
                System.out.println(e.getMessage());
            }
        } else {
            // do nothing
            System.out.println("There is an error in creating this transaction.");
        }
    }

    /**
     * READ and return every Cineplex in the Database file
     * If Database file not found, ignore error and return empty list
     * 
     * @return Model.@{@link Transaction} Return list of Transaction if any, else
     *         empty list
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Transaction> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Transaction> allTransactions = (ArrayList<Transaction>) ois.readObject();
            ois.close();
            return allTransactions;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
            // System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
        return new ArrayList<Transaction>();
    }

    /**
     * READ and return every Transaction of a given TID in the Database file
     * 
     * @param TID Transaction ID of Transaction to search for
     * @return Model.@{@link Transaction} Return list of Transaction if found, else
     *         empty list
     */
    public Transaction readByTID(String TID) {
        ArrayList<Transaction> allData = read();
        Transaction transaction = null;

        for (int i = 0; i < allData.size(); i++) {
            transaction = allData.get(i);
            if (transaction.getTID().equals(TID))
                return transaction;
        }
        return transaction; // will return null
    }

    /**
     * READ and return every Transaction of a given MovieGoer's username in the
     * Database file
     * 
     * @param movieGoerUsername MovieGoer's username of Transaction to search for
     * @return Model.@{@link Transaction} Return list of Transaction if found, else
     *         empty list
     */
    public ArrayList<Transaction> readByMovieGoerUsername(String movieGoerUsername) {
        ArrayList<Transaction> allData = read();
        Transaction transaction = null;
        String dbUsername = null;
        ArrayList<Transaction> returnData = new ArrayList<Transaction>();

        for (int i = 0; i < allData.size(); i++) {
            transaction = allData.get(i);
            dbUsername = transaction.getEmail();
            if (dbUsername.equals(movieGoerUsername))
                returnData.add(transaction);
        }
        return returnData;
    }

    /**
     * VERIFICATION METHODS used in MovieReviewUI
     * 
     * @param TID   Transaction ID
     * @param email Email of this transaction
     * @return boolean
     */
    public boolean verifyEmailandTID(String TID, String email) {
        ArrayList<Transaction> allData = read();
        Transaction transaction = null;
        String dbTID = null;
        String dbEmail = null;

        for (int i = 0; i < allData.size(); i++) {
            transaction = allData.get(i);
            dbTID = transaction.getTID();
            dbEmail = transaction.getEmail();
            if (dbTID.equals(TID) && dbEmail.equals(email))
                return true;
        }
        return false;
    }

    /**
     * VERIFICATION METHODS used in MovieReviewUI
     * 
     * @param TID    Transaction ID
     * @param number number of this transaction
     * @return boolean
     */
    public boolean verifyPhoneandTID(String TID, String number) {
        ArrayList<Transaction> allData = read();
        Transaction transaction = null;
        String dbTID = null;
        String dbNumber = null;

        for (int i = 0; i < allData.size(); i++) {
            transaction = allData.get(i);
            dbTID = transaction.getTID();
            dbNumber = transaction.getMobileNumber();
            if (dbTID.equals(TID) && dbNumber.equals(number))
                return true;
        }
        return false;
    }

    /**
     * Count the number of people
     * 
     * @param TID Transaction ID
     * @return int
     */
    public int numberOfPeople(String TID) {
        ArrayList<Transaction> allData = read();
        Transaction transaction = null;
        String dbTID = null;
        ArrayList<Integer> seatArray;
        int headCount = -1;

        for (int i = 0; i < allData.size(); i++) {
            transaction = allData.get(i);
            dbTID = transaction.getTID();
            if (dbTID.trim().equalsIgnoreCase(TID)) {
                seatArray = transaction.getSeatsSelected();
                headCount = seatArray.size();
            }
        }
        return headCount;
    }

    /**
     * Delete a Transaction in the Database file, based on the TID and MovieGoer's
     * username attribute passed
     * 
     * @param TID      Transaction ID of Transaction which will be deleted
     * @param username Username of Transaction which will be deleted
     */
    public void delete(String TID, String username) {
        ArrayList<Transaction> allData = read();
        Transaction transaction = null;
        ArrayList<Transaction> returnData = new ArrayList<Transaction>();

        for (int i = 0; i < allData.size(); i++) {
            transaction = allData.get(i);
            if (transaction.getTID().equals(TID)
                    && transaction.getEmail().matches(username))
                continue;
            returnData.add(transaction);
        }
        replaceExistingFile(FILENAME, returnData);
    }

    /**
     * Overwrite Database file with new data of list of Admin
     * 
     * @param filename   Filename to check for
     * @param returnData New ArrayList of Transaction to be written to the file
     */
    public void replaceExistingFile(String filename, ArrayList<Transaction> returnData) {
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
     * Verify the transaction
     * 
     * @param cinemaCode   Code of the cinema
     * @param name         name of the transaction
     * @param email        email of the transaction
     * @param mobileNumber mobile number of the transasction
     * @param movie        Movie of this transaction
     */
    @SuppressWarnings("static-access")
    public static boolean isValidTransaction(String cinemaCode, String name, String email, String mobileNumber,
            Movie movie) throws InvalidTxnException, FileNotFoundException {

        boolean isValid = false;

        MovieController movieCtrl = new MovieController();

        ArrayList<Movie> allScreeningMovies = movieCtrl.readAllScreeningMovies();

        for (int i = 0; i < allScreeningMovies.size(); i++) {
            Movie screeningMovie = allScreeningMovies.get(i);
            if (movie.getID() == screeningMovie.getID()) {
                isValid = true;
            }
        }

        if (!isValid) {
            try {
                throw new InvalidTxnException();
            } catch (InvalidTxnException e) {
                System.out.println(e.getMessage());
            }
        }

        return isValid;
    }

}