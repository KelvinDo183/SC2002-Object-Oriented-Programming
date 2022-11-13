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
        System.out.println("Enter your mobile number to verify your identity: ");
        String mobileNum = sc.next();
        
        Transaction txn = txnCtrl.readByTID(String.valueOf(txnID));
        if (txn.getMobileNumber().equalsIgnoreCase(mobileNum))
        {
            System.out.println("The total price paid = " + txn.getTotalPrice());
            System.out.printf(txn.toStringTransaction());
            System.out.println("");        	
        }
        else
        {
            System.out.println("Identity verification failed. You are not able to view this transaction.");
        }
        

    }

    public void displayHistory() { 
        //TODO
    	// Display all transactions by current user
    	System.out.print("Enter your email: ");
    	String email = InputController.getStringFromUser();
    	ArrayList<Transaction> allUserTxn = txnCtrl.readByMovieGoerUsername(email);
    	boolean matchingMobile = false;
        System.out.println("Enter your mobile number to verify your identity: ");
        String mobileNum = sc.next();
    	
    	// first for loop to check if there is matching email & mobile number (at least 1 coinciding record)
    	for (int j = 0; j < allUserTxn.size(); j++)
    	{
    		Transaction txn = allUserTxn.get(j);
    		if (txn.getMobileNumber().equalsIgnoreCase(mobileNum))
    		{
    			// update matchingMobile to true and then break out of loop
    			matchingMobile = true;
    			break;
    		}    		
    	}
    	
    	if (matchingMobile)
    	{
        	System.out.println("\nHistorical Transactions ");
            System.out.println("::::::::::::::::::::::::::::::");
        	for (int i = 0; i < allUserTxn.size(); i++)
        	{
        		Transaction txn = allUserTxn.get(i);
                System.out.printf(txn.toStringTransaction());
                System.out.println("");
        	}	
    	}
    	else
    	{
            System.out.println("Identity verification failed. You are not able to view transactions associated with this email address.");
    	}
    	
    	System.out.println("");
    }
    
}
