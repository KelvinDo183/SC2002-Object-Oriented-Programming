package Boundary;

import java.util.Scanner;

public class ViewTopMoviesUI {
    
    private static Scanner sc;

    public void main() {

        int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("---------------- View Top Movies -----------------");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) View top 5 movies by ticket sales");
            System.out.println("(2) View top 5 movies by ratings");
            System.out.println("(3) Return to menu");
            System.out.println("");
            System.out.print("Select choice:");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
                case 1:
                    viewByTicketSales();
                    break;

                case 2:
                    viewByRatings();
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

    public void viewByTicketSales() {
        //TODO
    }

    public void viewByRatings() {
        //TODO
    }
}
