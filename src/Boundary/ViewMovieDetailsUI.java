package Boundary;

import Controller.*;
import model_Classes.*;
import java.util.Scanner;

public class ViewMovieDetailsUI {

    private static Scanner sc;
    private MovieController moviesCtrl;

    ViewMovieDetailsUI() {
        this.moviesCtrl = new MovieController();
    }

    ViewMovieDetailsUI(MovieController moviesCtrl) {
        this.moviesCtrl = moviesCtrl;
    }

    public void main() {

        int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("--------------- View Movie Details ---------------");
            System.out.println("--------------------------------------------------");
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

        System.out.print("Enter Movie ID: ");
        int id = InputController.getIntFromUser();
        if (id == -1) {
            return;
        }
        Movie movie = moviesCtrl.readSpecificID(id);
        if (movie == null) {
            System.out.println("Movie ID doesn't exist!\n");
        } else {
            String movieString = movie.toString();
            System.out.println(movieString);
        }

    }
}
