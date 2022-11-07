package Boundary;

import java.util.Scanner;

public class ViewMovieDetails {
    
    private static Scanner sc;

    public void main() {

        int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("----------------------------");
            System.out.println("-----View Movie Details-----");
            System.out.println("----------------------------");
            System.out.println("(1) Enter movie ID to view more details");
            System.out.println("(2) Return to menu");
            System.out.println("");
            System.out.print("Select choice:");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
                case 1:
                    viewMovieDetails();
                    break;
                    
                case 2:
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

    public void viewMovieDetails() {
        //TODO
    }
}
