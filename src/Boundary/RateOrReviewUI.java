package Boundary;

import java.io.FileNotFoundException;
import java.util.Scanner;

import Controller.MovieController;

/* Boundary class acting as an intermediate UI from MainMenuUI(when a MovieGoer chooses to "Rate/Review Movie") to either MoviewReviewUI or RateMovieUI. 
 * 
 * */

class RateOrReviewUI {
	private static Scanner sc;
    private MovieController moviesCtrl;


    public void main() throws FileNotFoundException {

        int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("--------------- Rate/Review Movie ---------------");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) Rate Movie");
            System.out.println("(2) Review Movie");
            System.out.println("(3) Return to menu");
            System.out.println("");
            System.out.print("Select choice:");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
                case 1:
                	RateMovieUI rateUI = new RateMovieUI();
                	rateUI.main();
                    break;
                case 2:
                	MovieReviewUI reviewUI = new MovieReviewUI();
                    reviewUI.main();
                	break;
                case 3:
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
}
