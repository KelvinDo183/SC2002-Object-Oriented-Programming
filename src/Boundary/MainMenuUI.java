package Boundary;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import Controller.*;
import model_Classes.*;

/* The starting point for the whole MOBLIMA program(main function begins here). Admins and Movie-Goers will access their respective functions
 * for MOBLIMA as this class calls methods from the other Boundary classes.
 * 
 * Functions for Admin
 * 1) Create / Update / Delete Movie Listing(a movie)
 * 2) Create / Update / Delete Movie Session(a screening on a given date at a given cinema)
 * 3) Search Movie Listing
 * 4) View Movie Details
 * 5) Configure System Settings
 * 
 * Functions for MovieGoer
 * 1) Search Movie Listing
 * 2) View Top 5 Movies (varies depending on Admin's configuration ー by ratings or ticket sales)
 * 3) View Movie Details
 * 4) Check Available Seats
 * 5) Book Tickets
 * 6) View Booking History
 * 7) Rate/Review Movie
 * */

public class MainMenuUI {

    private static Scanner sc;

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        initialize();

        int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("------------------ MOBLIMA MENU ------------------");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) Admin ");
            System.out.println("(2) Movie Goer");
            System.out.println("(3) Register New Admin Account");
            System.out.println("(4) Exit");
            System.out.println("");
            System.out.print("Select choice: ");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
                case 1:
                    adminLoginMenu();
                    break;

                case 2:
                    movieGoerMenu();
                    break;

                case 3:
                    registerAdminAccountMenu();
                    break;

                case 4:

                    exitMenu = true;
                    System.out.println("Terminating App...");
                    System.out.println("");
                    break;

                default:
                    System.out.println("Please enter a correct number");
                    System.out.println("");

            }
        }

        while (!exitMenu);

    }

    private static void initialize() {

        CineplexeController cineplexeController = new CineplexeController();

        ArrayList<Cinema> orchardCinemas = new ArrayList<>();

        orchardCinemas.add(new Cinema("AAA", CinemaType.PREMIUM, new SeatingPlan(10, 10)));
        orchardCinemas.add(new Cinema("BBB", CinemaType.PREMIUM, new SeatingPlan(10, 10)));
        orchardCinemas.add(new Cinema("CCC", CinemaType.STANDARD, new SeatingPlan(10, 10)));

        if (cineplexeController.read().size() == 0) {
            System.out.println(orchardCinemas.size());
            cineplexeController.create("Orchard Cineplex", orchardCinemas);
        }

        // Display created cineplex and its cinemas
        // System.out.println(cineplexeController.read());

    }

    public static void adminLoginMenu() throws IOException, NoSuchAlgorithmException {
        LoginUIAdmin admin_login = new LoginUIAdmin();
        boolean loggedIn = admin_login.main();
        // admin login menu
        if (loggedIn) {
            // do nothing
        }

        int adminChoice;
        boolean exitMenu = false;

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("--------------- MOBLIMA ADMIN MENU ---------------");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) Create/Update/Delete Movie Listing");
            System.out.println("(2) Create/Update/Delete Movie Session");
            System.out.println("(3) Search Movie Listings");
            System.out.println("(4) View Movie Details");
            System.out.println("(5) Configure System Settings");
            System.out.println("(6) Log Out");
            System.out.println("");
            System.out.print("Select choice: ");

            adminChoice = sc.nextInt();

            System.out.println("");

            switch (adminChoice) {
                case 1:
                    // run the MovieController class
                    movieControllerMenu();
                    break;

                case 2:
                    sessionControllerMenu();
                    break;

                case 3:
                    SearchMovieUI searchMovie = new SearchMovieUI();
                    searchMovie.main();
                    break;

                case 4:
                    ViewMovieDetailsUI viewMovieDetail = new ViewMovieDetailsUI();
                    viewMovieDetail.main();
                    break;

                case 5:
                    ConfigSettingsUI configSettings = new ConfigSettingsUI();
                    configSettings.main();
                    break;

                case 6:
                    exitMenu = true;
                    System.out.println("Logging Out...");
                    System.out.println("");
                    break;

                default:
                    System.out.println("Please enter a correct number");
                    System.out.println("");
            }
        } while (!exitMenu);
    }

    public static void movieGoerMenu() throws FileNotFoundException {
        // movie goer menu
        int movieGoerChoice;
        boolean exitMenu = false;
        ;

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("--------------- MOBLIMA MOVIE GOER MENU ---------------");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) Search Movie Listings");
            System.out.println("(2) View Top 5 Movies");
            System.out.println("(3) View Movie Details");
            System.out.println("(4) Check Available Seats");
            System.out.println("(5) Book Tickets");
            System.out.println("(6) View Booking History");
            System.out.println("(7) Rate/Review Movie");
            System.out.println("(8) Exit");
            System.out.println("");
            System.out.print("Select choice: ");

            movieGoerChoice = sc.nextInt();

            System.out.println("");

            switch (movieGoerChoice) {
                case 1:
                    SearchMovieUI searchMovie = new SearchMovieUI();
                    searchMovie.mainMovieGoer();
                    break;

                case 2:
                    ViewTopMoviesUI viewTopMovies = new ViewTopMoviesUI();
                    viewTopMovies.main();
                    break;

                case 3:
                    ViewMovieDetailsUI viewMovieDetails = new ViewMovieDetailsUI();
                    viewMovieDetails.main();
                    break;

                case 4:
                    ViewAvailableSeatsUI viewAvailableSeats = new ViewAvailableSeatsUI();
                    viewAvailableSeats.main();
                    break;

                case 5:
                    BookingAndPurchaseTicketsUI bookingAndPurchaseTickets = new BookingAndPurchaseTicketsUI();
                    bookingAndPurchaseTickets.main();
                    break;

                case 6:
                    ViewBookingAndPurchasedHistoryUI viewBookingAndPurchasedHistory = new ViewBookingAndPurchasedHistoryUI();
                    viewBookingAndPurchasedHistory.main();
                    break;

                case 7:
                    RateOrReviewUI rateOrReview = new RateOrReviewUI();
                    rateOrReview.main();
                    break;

                case 8:
                    exitMenu = true;
                    System.out.println("Exiting...");
                    System.out.println("");
                    break;

                default:
                    System.out.println("Please enter a correct number");
                    System.out.println("");

            }
        } while (!exitMenu);
    }

    public static void registerAdminAccountMenu() throws NoSuchAlgorithmException {
        // register new admin menu
        RegisterUIAdmin registerUIAdmin = new RegisterUIAdmin();
        registerUIAdmin.main();
    }

    public static void movieControllerMenu() throws NoSuchAlgorithmException, IOException {
        // Movie Controller menu
        MovieController movController = new MovieController();
        movController.main();
    }

    public static void sessionControllerMenu() throws NoSuchAlgorithmException, IOException {
        // Movie Controller menu
        SessionUI sessionUI = new SessionUI();
        sessionUI.main();
    }

}
