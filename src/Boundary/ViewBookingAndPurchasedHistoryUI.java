package Boundary;

import java.util.Scanner;
import Controller.TransactionController;
import model_Classes.Transaction;

public class ViewBookingAndPurchasedHistoryUI {

	TransactionController txnCtrl = new TransactionController();
    private static Scanner sc;

    public void main() {

        int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("--------- Booking and Purchased History ---------- ");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) Enter booking ID to retrieve purchase history");
            System.out.println("(2) Return to menu");
            System.out.println("");
            System.out.print("Select choice:");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
                case 1:
                    retrieve();
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

    public void retrieve() {
        System.out.println("\nEnter transaction ID: ");
        int txnID = sc.nextInt();
        Transaction txn = txnCtrl.readByTID(String.valueOf(txnID));
        System.out.println("\n::::::::::::::::::::::::::::::");
        System.out.println("TransactionID = " + txn.getTID());
        System.out.println("Customer Name = " + txn.getMobileNumber());
        System.out.println("Customer Mobile Number = " + txn.getMobileNumber());
        System.out.println("CinemaCode = " + txn.getCinemaCode());
        System.out.println("Movie Title = " + txn.getMovie().getTitle());
        System.out.println("::::::::::::::::::::::::::::::");
        System.out.println("");
    }

    public void displayHistory() { 
        //TODO
    }
    
}
