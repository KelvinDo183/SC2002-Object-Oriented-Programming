package Boundary;

import java.io.FileNotFoundException;
import java.util.Scanner;

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
            System.out.println("(2)");
            System.out.println("(3)");
            System.out.println("(4) Show Available Movies");
            System.out.println("(5)");
            System.out.println("(6)");
            System.out.println("(7) Return to menu");
            System.out.println("");
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
