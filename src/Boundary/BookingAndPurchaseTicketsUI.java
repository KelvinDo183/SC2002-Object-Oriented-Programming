package Boundary;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.CinemaController;
import Controller.CineplexeController;
import model_Classes.Cinema;
import model_Classes.Cineplex;

public class BookingAndPurchaseTicketsUI {

    private static Scanner sc;

    public void main() throws FileNotFoundException {

        int menuChoice;
        boolean exitMenu = false;

        
        sc = new Scanner(System.in);

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("----------- Book and Purchase Tickets ------------");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) Show Available Movies");
            System.out.println("(2) Show Available Cineplexes");
            System.out.println("(3) Show Available Sessions");
            System.out.println("(4) Price Calculations");
            System.out.println("(5) Pick Session");
            System.out.println("(6) Seat Selection");
            System.out.println("(7) Make Booking");
            System.out.println("(8) Return to Movie Goer Menu");
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
                	priceCalculation();
                    break;

                case 5:
                	pickSession();
                    break;

                case 6:
                	seatSelection();
                    break;
                    
                case 7:
                	makeBooking();
                    break;

                case 8:
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
        //TODO
        System.out.println("\n----------- Available Cineplexes Shown Below: ------------");
        System.out.println("--------------------------------------------------");
    	CineplexeController cineplexControl = new CineplexeController();
    	CinemaController cinemaControl = new CinemaController();
//    	cineplexControl.readByName("AAA");
//    	cineplexControl.readByName("BBB");
//    	cineplexControl.readByName("CCC");
    	
    	ArrayList<Cineplex> allCineplexList = cineplexControl.read();
    	ArrayList<Cinema> allCinemaList = cinemaControl.read();
    	cineplexControl.create("DDD", allCinemaList);
    	cineplexControl.readByName("DDD");    	
    }

    public void showAvailableSessions() {
        //TODO
    }

    public void pickSession() {
        //TODO
    }   

    public void priceCalculation() {
        //TODO
    }

    public void seatSelection() {
        //TODO
    }

    public void makeBooking() {
        //TODO
    }

}
