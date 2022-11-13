package Boundary;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.InputController;
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
            System.out.println("(2) View your past transactions");
            System.out.println("(3) Return to menu");
            System.out.println("");
            System.out.print("Select choice:");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
                case 1:
                    retrieve();
                    break;

                case 2:
                    displayHistory();
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

    public void retrieve() {
        System.out.println("\nEnter transaction ID: ");
        int txnID = sc.nextInt();
        Transaction txn = txnCtrl.readByTID(String.valueOf(txnID));
        System.out.println("The total price paid = " + txn.getTotalPrice());
        System.out.printf(txn.toStringTransaction());
        System.out.println("");
    }

    public void displayHistory() { 
        //TODO
    	// Display all transactions by current user
    	System.out.print("Enter your email: ");
    	String email = InputController.getStringFromUser();
    	ArrayList<Transaction> allUserTxn = txnCtrl.readByMovieGoerUsername(email);

    	System.out.println("\nHistorical Transactions ");
        System.out.println("::::::::::::::::::::::::::::::");
    	for (int i = 0; i < allUserTxn.size(); i++)
    	{
    		Transaction txn = allUserTxn.get(i);
            System.out.printf(txn.toStringTransaction());
            System.out.println("");
    	}
    	

    	System.out.println("");
    }
    
}
