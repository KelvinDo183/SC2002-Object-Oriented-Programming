package Boundary;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import Controller.*;

public class SearchMovie {

    private static Scanner sc;

    public void main() {

        int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("-----------------------------------");
            System.out.println("-----Book and Purchase Tickets-----");
            System.out.println("-----------------------------------");
            System.out.println("Available Movies: ");
            System.out.println("(1)");
            System.out.println("(2)");
            System.out.println("(3)");
            System.out.println("(4) Return to menu");
            System.out.println("");
            System.out.print("Select choice:");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    exitMenu = true;
                    System.out.println("Returning to menu...");
                    System.out.println("");
                    break;

                default:
                    System.out.println("Please enter a correct number");
                    System.out.println("");

            }
        }

        while (!exitMenu);

    }
    
    public void showAvailableMovies() {

    }

    public void showAvailableCineplexes() {

    }

    public void showAvailableSessions() {

    }

    public void pickSession() {

    }

    public void priceCalculation() {

    }

    public void seatSelection() {

    }

    public void makeBooking() {

    }

}
