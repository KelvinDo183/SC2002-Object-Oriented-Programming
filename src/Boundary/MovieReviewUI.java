package Boundary;

import java.io.FileNotFoundException;
import java.util.Scanner;

import Controller.*;
import model_Classes.*;

/*
 * MovieReviewUI is called when a Movie-Goer selects the respective option to do so from MainMenuUI. 
 * They can leave a rating and an additional comment(or left blank) to be recorded as a MovieReview(controlled by ReviewsController), the rating
 * recorded will affect a Movie's rating variable. Each transaction ID/email can leave the same number of reviews(one review per user per movie)
 * as the number of seats(ie. tickets) bought.
 * 
 * Mainly uses the ReviewsController class
 * */

public class MovieReviewUI {
    

    private MovieController moviesController = new MovieController();
    private SearchMovieUI searchMovieUI = new SearchMovieUI();
    private TransactionController txnController = new TransactionController();
    private ReviewsController reviewsController = new ReviewsController();
    Scanner sc = new Scanner(System.in);
    String email;
    String number;
    String tid;
    String title;
    Movie movie;

    public void main() throws FileNotFoundException{
    	boolean exitMenu = false;
		do {
			System.out.println("--------------------------------------------------");
	        System.out.println("------------------ Review Movie ------------------");
	        System.out.println("--------------------------------------------------");
	        System.out.println("(1) Verify via email");
	        System.out.println("(2) Verify via mobile number");
	        System.out.println("(3) Return to menu");
	        int choice = sc.nextInt();
	        switch (choice) {
	        case 1:
	            if(verifyByMail()) {
	            	if(checkExistingMovie() && isMaxReviews()) {
	            		createReview();
	            	}
	            }
	            break;
	
	        case 2:
	            if(verifyByNum()) {
	            	if(checkExistingMovie() && isMaxReviews()) {
	            		createReview();
	            	}
	            }
	            break;
	
	        case 3:
	            exitMenu = true;
	            System.out.println("Returning to Movie Goer menu...");
	            System.out.println("");
	            break;
	
	        default:
	            System.out.println("Please enter a correct number");
	            System.out.println("");
	
	        }
		}while (!exitMenu);
        
        
    }
    
    public boolean verifyByMail() {
    	email = null;
    	tid = null;
    	System.out.print("Enter email: ");
        email = InputController.getEmailFromUser();
        System.out.print("Enter transaction ID: ");
        tid = InputController.getStringFromUser();
        
    	if(!txnController.verifyEmailandTID(tid, email)) {
        	System.out.println("Invalid email and/or transaction ID!");
        	return false;
        }
        else {
        	return true;
        }
    }
    
    public boolean verifyByNum() {
    	number = null;
    	tid = null;
    	System.out.print("Enter mobile number: ");
        number = InputController.getStringFromUser();
        System.out.print("Enter transaction ID: ");
        tid = InputController.getStringFromUser();
        
        if(!txnController.verifyPhoneandTID(tid, number)) {
        	System.out.println("Invalid email and/or transaction ID!");
        	return false;
        }
        else {
        	return true;
        }
    }
    
    public boolean checkExistingMovie(){
    	movie = null;
        Transaction txn = txnController.readByTID(tid);
        movie = txn.getSession().getMovie();
        title = movie.getTitle();
        return true;
    
    }
    
    public boolean isMaxReviews() throws FileNotFoundException {
    	/* Takes in the TID and title and checks if that TID has made the maximum number of Reviews(based on their number of tickets)
    	 * If the transaction happens to not have any seats, it returns -1 so it will always return false.
    	 * */
    	if(reviewsController.countReviewsWithTitleAndTID(title, tid) >= txnController.numberOfPeople(tid)) {
    		System.out.println("You can only leave one review per ticket/person.");
    		return false;
    	}
    	return true;
    }
    
    public void createReview() throws FileNotFoundException {
    	/* 
         */
            System.out.println("Input rating(from 0.0 to 5.0):");
            float rating = sc.nextFloat(); //will throw an error if not a float
            System.out.println("Input additional comment");
            String comment = InputController.getStringFromUser();
            reviewsController.create(movie, title, rating, comment, tid);
    }
}
