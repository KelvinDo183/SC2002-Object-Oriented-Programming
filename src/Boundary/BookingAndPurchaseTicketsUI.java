package Boundary;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.CinemaController;
import Controller.CineplexeController;
import Controller.InputController;
import Controller.TransactionController;
import Controller.PriceController;
import Controller.SessionController;
import exceptions.InvalidTxnException;
import model_Classes.Cinema;
import model_Classes.Cineplex;
import model_Classes.Movie;
import model_Classes.Session;
import model_Classes.Pair;
import model_Classes.PriceType;
import Boundary.SeatingUI;

public class BookingAndPurchaseTicketsUI {

    private static Scanner sc;
    private CinemaController cinemaCtrl = new CinemaController();
    private CineplexeController cineplexCtrl = new CineplexeController();
    private TransactionController txnCtrl = new TransactionController();
    private PriceController priceCtrl = new PriceController();
    private SessionController sessionCtrl = new SessionController();

    private int noOfTickets;

    private Cinema queriedCinema;
    private Session queriedSession;

    public void main() throws FileNotFoundException, InvalidTxnException {

        int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("\n--------------------------------------------------");
            System.out.println("----------- Book and Purchase Tickets ------------");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) Show Available Movies");
            System.out.println("(2) Show Available Cineplexes");
            System.out.println("(3) Show Available Sessions");
            System.out.println("(4) Price Calculations");
            System.out.println("(5) Seat Selection and Make Booking");
            System.out.println("(6) Return to Movie Goer Menu");
            System.out.print("Select choice:");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
                case 1:
                    showAvailableMovies();
                    break;

                case 2:
                    showAvailableCineplexes();
                    break;

                case 3:
                    showAvailableSessions();
                    break;

                case 4:
                    priceCalculationMenu();
                    break;

                case 5:
                    seatSelection();
                    break;

                case 6:
                    exitMenu = true;
                    System.out.println("Returning to Movie Goer menu...");
                    System.out.println("");
                    break;

                default:
                    System.out.println("Please enter a correct number");
                    System.out.println("");

            }
        }

        while (!exitMenu);

    }

    public void showAvailableMovies() throws FileNotFoundException {
        SearchMovieUI searchMovie = new SearchMovieUI();
        searchMovie.mainMovieGoer();
        searchMovie.listAvailableScreeningMovies();
        System.out.println("");
        System.out.println("--------------------------------------------------");
    }

    public void showAvailableCineplexes() {

        ArrayList<Cineplex> cineplexList = cineplexCtrl.read();
        if (cineplexList.isEmpty()) {
            System.out.println("There are no cineplexes registered!");
            return;
        } else {
            System.out.println("\n----------- Available Cineplexes Shown Below: ------------");
            cineplexList.forEach(Cineplex -> printCineplex(Cineplex));
        }

    }

    public void showAvailableSessions() {
        System.out.println("\nCineplex List:");
        ArrayList<Cineplex> cineplexList = cineplexCtrl.read();
        if (cineplexList.isEmpty()) {
            System.out.println("There are no cineplexes registered!");
            return;
        }
        cineplexList.forEach(Cineplex -> printCineplex(Cineplex));
        System.out.println("\nEnter Cineplex Name:");
        String cineplexName = InputController.getStringFromUser();
        Cineplex cineplex = cineplexCtrl.readByName(cineplexName);
        if (cineplex == null) {
            System.out.println("Cineplex does not exist!\n" +
                    "Returning to menu...");
            return;
        }

        ArrayList<Cinema> cinemaList = cineplex.getCinemas();
        cinemaList.forEach(Cinema -> printCinema(Cinema));
    }
    
    
    public void priceCalculationMenu() {
        
        double price = 0;
        System.out.print("Enter the amount of tickets: ");
        noOfTickets = InputController.getPositiveIntFromUser();
        Cinema requestedCinema = cinemaCtrl.readByCinemaName("BBB");
//        Session weekdaySession = new Session();
        
//        price = priceCalculation(sessionCtrl noOfTickets);
        
        
//        System.out.println("Total price is equal: " + price + " SGD");
    }

    public double priceCalculation(Session session, Cinema cinema, int noOfTickets) {
        double returnPrice = 0;
        for (int i = 0; i < noOfTickets; i++) {
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Enter age type for Ticket #" + (i + 1) + " (Student, Senior, Standard): ");
                String priceTypeString = InputController.getStringFromUser();
                if (priceTypeString.equalsIgnoreCase("Student")) {
                	returnPrice += priceCtrl.computePrice(session, cinema, PriceType.STUDENT);
                    validInput = true;
                } else if (priceTypeString.equalsIgnoreCase("Senior")) {
                	returnPrice += priceCtrl.computePrice(session, cinema, PriceType.SENIOR_CITIZEN);
                    validInput = true;
                } else if (priceTypeString.equalsIgnoreCase("Standard")) {
                	returnPrice += priceCtrl.computePrice(session, cinema, PriceType.NORMAL);
                    validInput = true;
                } else {
                    System.out.println("Wrong input!");
                }
            }
        }

        return returnPrice;
    }

    public void seatSelection() throws FileNotFoundException, InvalidTxnException {

        Pair<String, Session> selectedSessionPair = showSeatingArrangementUI();
        
        // Pass info to makeBooking function
        

        // makeBooking()
        System.out.println("--------------------------------------------------");
        System.out.println("------------ Proceed to make booking: ------------\n");
        makeBooking(selectedSessionPair);

    }

    public void makeBooking(Pair<String, Session> selectedSessionPair)
            throws FileNotFoundException, InvalidTxnException {
        System.out.print("Enter your name: ");
        String name = InputController.getStringFromUser();
        System.out.print("Enter your email: ");
        String email = InputController.getEmailFromUser();
        System.out.print("Enter your mobile number: ");
        String mobileNumber = InputController.getMobileNumberFromUser();
        String cinemaCode = selectedSessionPair.t1;
        Movie movie = selectedSessionPair.t2.getMovie();

        txnCtrl.create(cinemaCode, name, email, mobileNumber, movie);

        System.out.println("Transaction successful!");
    }

    public void printCinema(Cinema cinema) {
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::\nCinema code: " + cinema.getCode());
        ArrayList<Session> sessionList = cinema.getSessions();
        sessionList.forEach(session -> printSession(session));
    }

    public void printCinemaCode(Cinema cinema) {
        System.out.println("Cinema code: " + cinema.getCode());
    }

    public void printCineplex(Cineplex cineplex) {
        System.out.println("Name: " + cineplex.getName());
    }

    public void printSession(Session session) {
        System.out.print("\n\tSession id: " + session.getID() + "\n" +
                "\tMovie title: " + session.getMovie().getTitle() + "\n" +
                "\tDate: " + session.getStringSessionDateTime() + "\n");
    }

    public Pair<String, Session> showSeatingArrangementUI() {
        SeatingUI seatingUI = new SeatingUI();
        Pair<String, Session> selectedSessionPair = seatingUI.main();
        return selectedSessionPair;
    }
}
