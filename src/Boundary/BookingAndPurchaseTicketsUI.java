package Boundary;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.CinemaController;
import Controller.CineplexeController;
import Controller.InputController;
import Controller.TransactionController;
import Controller.PriceController;
import Controller.SessionController;
import exceptions.InvalidTxnException;
import model_Classes.Cinema;
import model_Classes.CinemaType;
import model_Classes.Cineplex;
import model_Classes.Movie;
import model_Classes.MovieType;
import model_Classes.Session;
import model_Classes.Pair;
import model_Classes.PriceType;
import Boundary.SeatingUI;

public class BookingAndPurchaseTicketsUI {

    private static Scanner sc;
    private CinemaController cinemaCtrl = new CinemaController();
    private CineplexeController cineplexCtrl = new CineplexeController();
    private TransactionController txnCtrl = new TransactionController();
    private PriceController priceCtrl = new PriceController();
    private SessionController sessionCtrl = new SessionController();

    private int noOfTickets;

    private Cinema queriedCinema;
    private Session queriedSession;

    public void main() throws FileNotFoundException, InvalidTxnException {

        int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("\n--------------------------------------------------");
            System.out.println("----------- Book and Purchase Tickets ------------");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) Show Available Movies");
            System.out.println("(2) Show Available Cineplexes");
            System.out.println("(3) Show Available Sessions");
            System.out.println("(4) Price Calculations");
            System.out.println("(5) Seat Selection and Make Booking");
            System.out.println("(6) Return to Movie Goer Menu");
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
                    priceCalculationMenu();
                    break;

                case 5:
                    seatSelection();
                    break;

                case 6:
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

        ArrayList<Cineplex> cineplexList = cineplexCtrl.read();
        if (cineplexList.isEmpty()) {
            System.out.println("There are no cineplexes registered!");
            return;
        } else {
            System.out.println("\n----------- Available Cineplexes Shown Below: ------------");
            cineplexList.forEach(Cineplex -> printCineplex(Cineplex));
        }

    }

    public void showAvailableSessions() {
        System.out.println("\nCineplex List:");
        ArrayList<Cineplex> cineplexList = cineplexCtrl.read();
        if (cineplexList.isEmpty()) {
            System.out.println("There are no cineplexes registered!");
            return;
        }
        cineplexList.forEach(Cineplex -> printCineplex(Cineplex));
        System.out.println("\nEnter Cineplex Name:");
        String cineplexName = InputController.getStringFromUser();
        Cineplex cineplex = cineplexCtrl.readByName(cineplexName);
        if (cineplex == null) {
            System.out.println("Cineplex does not exist!\n" +
                    "Returning to menu...");
            return;
        }

        ArrayList<Cinema> cinemaList = cineplex.getCinemas();
        cinemaList.forEach(Cinema -> printCinema(Cinema));
    }
    
    
    public void priceCalculationMenu() {

        System.out.println("\n--------------------------------------------------");
        System.out.println("Displaying table for all possible ticket prices: ");
        System.out.println("--------------------------------------------------\n");
        
        regularPriceMenu();
        weekdayHolidayPriceMenu();
        weekendNonHolidayPriceMenu();
        weekendHolidayPriceMenu();
        
    }
    
    public void regularPriceMenu() {
        System.out.println("\nRegular Ticket Pricing based on MovieType and CinemaType: ");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        
        String outputString = "";
        // 3 rows for MovieType
        for (int i = 0; i <= 4; i ++)
        {
        		// 6 columns for two CinemaType multiplied by three ticket prices based on age
        		if (i == 0)
        		{
            		// headings for 'outer' columns
            		outputString += "            |          NORMAL          |      SENIOR CITIZEN      |         STUDENT         ";
        		}
        	
        		if (i == 1)
            	{
            		// headings for 'inner' columns
            		outputString += "\n            |  STANDARD  |   PREMIUM   |  STANDARD  |   PREMIUM   |  STANDARD  |   PREMIUM   ";
            		}
            	if (i == 2)
            	{
            		outputString += "\n   2D Movie |";
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, false, false, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, false, false, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, false, false, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, false, false, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |      " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += "   " + premiumPrice + "   |    ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, false, false, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, false, false, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += " " + standardPrice + "   |   ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += "  " + premiumPrice;
            		}
            		
            		
            	}
            	if (i == 3)
            	{
            		outputString += "\n   3D Movie |";

            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, false, false, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, false, false, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, false, false, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, false, false, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |    " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += " " + premiumPrice + "    |    ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, false, false, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, false, false, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "" + standardPrice + "    |  ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += "  " + premiumPrice;
            		}
            		
            	}
            	if (i == 4)
            	{
            		outputString += "\nBLOCKBUSTER |";
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, false, false, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, false, false, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, false, false, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, false, false, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |    " + standardPrice + "    |    ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += " " + premiumPrice + "    |   ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, false, false, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, false, false, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += " " + standardPrice + "    |   ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += " " + premiumPrice;
            		}
            	}
        }
        System.out.println(outputString);
        System.out.println("\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }
   
    public void weekdayHolidayPriceMenu() {
        System.out.println("\nWeekday (Holiday) Ticket Pricing based on MovieType and CinemaType: ");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        
        String outputString = "";
        // 3 rows for MovieType
        for (int i = 0; i <= 4; i ++)
        {
        		// 6 columns for two CinemaType multiplied by three ticket prices based on age
        		if (i == 0)
        		{
            		// headings for 'outer' columns
            		outputString += "            |          NORMAL          |      SENIOR CITIZEN      |         STUDENT         ";
        		}
        	
        		if (i == 1)
            	{
            		// headings for 'inner' columns
            		outputString += "\n            |  STANDARD  |   PREMIUM   |  STANDARD  |   PREMIUM   |  STANDARD  |   PREMIUM   ";
            		}
            	if (i == 2)
            	{
            		outputString += "\n   2D Movie |";
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, false, true, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, false, true, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, false, true, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, false, true, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |    " + standardPrice + "    |   ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += " " + premiumPrice + "     |    ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, false, true, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, false, true, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "" + standardPrice + "    |    ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += "" + premiumPrice;
            		}
            		
            		
            	}
            	if (i == 3)
            	{
            		outputString += "\n   3D Movie |";

            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, false, true, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, false, true, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, false, true, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, false, true, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |    " + standardPrice + "    |    ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += "" + premiumPrice + "     |    ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, false, true, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, false, true, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "" + standardPrice + "    |  ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += "  " + premiumPrice;
            		}
            		
            	}
            	if (i == 4)
            	{
            		outputString += "\nBLOCKBUSTER |";
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, false, true, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, false, true, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, false, true, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, false, true, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |    " + standardPrice + "    |    ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += "" + premiumPrice + "     |   ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, false, true, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, false, true, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += " " + standardPrice + "    |   ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += " " + premiumPrice;
            		}
            	}
        }
        System.out.println(outputString);
        System.out.println("\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }
    
    public void weekendNonHolidayPriceMenu() {
        System.out.println("\nWeekend (Non-holiday) Ticket Pricing based on MovieType and CinemaType: ");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        
        String outputString = "";
        // 3 rows for MovieType
        for (int i = 0; i <= 4; i ++)
        {
        		// 6 columns for two CinemaType multiplied by three ticket prices based on age
        		if (i == 0)
        		{
            		// headings for 'outer' columns
            		outputString += "            |          NORMAL          |      SENIOR CITIZEN      |         STUDENT         ";
        		}
        	
        		if (i == 1)
            	{
            		// headings for 'inner' columns
            		outputString += "\n            |  STANDARD  |   PREMIUM   |  STANDARD  |   PREMIUM   |  STANDARD  |   PREMIUM   ";
            		}
            	if (i == 2)
            	{
            		outputString += "\n   2D Movie |";
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, true, false, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, true, false, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, true, false, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, true, false, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |      " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += "   " + premiumPrice + "   |    ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, true, false, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, true, false, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += " " + standardPrice + "    |  ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += "  " + premiumPrice;
            		}
            		
            		
            	}
            	if (i == 3)
            	{
            		outputString += "\n   3D Movie |";

            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, true, false, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, true, false, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, true, false, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, true, false, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |    " + standardPrice + "    |    ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += " " + premiumPrice + "    |    ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, true, false, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, true, false, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "" + standardPrice + "    |  ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += "  " + premiumPrice;
            		}
            		
            	}
            	if (i == 4)
            	{
            		outputString += "\nBLOCKBUSTER |";
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, true, false, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, true, false, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, true, false, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, true, false, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |    " + standardPrice + "    |    ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += " " + premiumPrice + "    |   ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, true, false, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, true, false, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += " " + standardPrice + "    |   ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += " " + premiumPrice;
            		}
            	}
        }
        System.out.println(outputString);
        System.out.println("\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }
    
    public void weekendHolidayPriceMenu() {
        System.out.println("\nWeekend (Holiday) Ticket Pricing based on MovieType and CinemaType: ");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        
        String outputString = "";
        // 3 rows for MovieType
        for (int i = 0; i <= 4; i ++)
        {
        		// 6 columns for two CinemaType multiplied by three ticket prices based on age
        		if (i == 0)
        		{
            		// headings for 'outer' columns
            		outputString += "            |          NORMAL          |      SENIOR CITIZEN      |         STUDENT         ";
        		}
        	
        		if (i == 1)
            	{
            		// headings for 'inner' columns
            		outputString += "\n            |  STANDARD  |   PREMIUM   |  STANDARD  |   PREMIUM   |  STANDARD  |   PREMIUM   ";
            		}
            	if (i == 2)
            	{
            		outputString += "\n   2D Movie |";
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, true, true, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, true, true, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, true, true, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, true, true, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |    " + standardPrice + "    |   ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += "  " + premiumPrice + "    |    ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.STANDARD, true, true, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.TWO_D, CinemaType.PREMIUM, true, true, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "" + standardPrice + "    |   ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += " " + premiumPrice;
            		}
            		
            		
            	}
            	if (i == 3)
            	{
            		outputString += "\n   3D Movie |";

            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, true, true, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, true, true, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, true, true, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, true, true, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |    " + standardPrice + "    |   ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += "  " + premiumPrice + "    |    ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.STANDARD, true, true, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.THREE_D, CinemaType.PREMIUM, true, true, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "" + standardPrice + "    |  ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += "  " + premiumPrice;
            		}
            		
            	}
            	if (i == 4)
            	{
            		outputString += "\nBLOCKBUSTER |";
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary
            		double standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, true, true, PriceType.NORMAL);
            		double premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, true, true, PriceType.NORMAL);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "     " + standardPrice + "   |      ";
            		}
            		else
            		{
            			outputString += "     " + standardPrice + "   |     ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += premiumPrice;
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Senior Citizen)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, true, true, PriceType.SENIOR_CITIZEN);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, true, true, PriceType.SENIOR_CITIZEN);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "    |     " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += "    |    " + standardPrice + "    |    ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += "  " + premiumPrice + "    |   ";
            		}
            		else
            		{
            			outputString += " " + premiumPrice + "    |   ";
            		}
            		
            		// Formatting spaces when displaying ticket prices depending on $10.0 boundary (Student)
            		standardPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.STANDARD, true, true, PriceType.STUDENT);
            		premiumPrice = priceCtrl.queryTicketPrice(MovieType.BLOCKBUSTER, CinemaType.PREMIUM, true, true, PriceType.STUDENT);
            		
            		if (standardPrice < 10)
            		{
            			outputString += "  " + standardPrice + "    |    ";
            		}
            		else
            		{
            			outputString += " " + standardPrice + "    |   ";
            		}
            		
            		if (premiumPrice < 10)
            		{
            			outputString += " " + premiumPrice;
            		}
            		else
            		{
            			outputString += " " + premiumPrice;
            		}
            	}
            	
            	
        }
        System.out.println(outputString);
        System.out.println("\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }
    
    

    public double priceCalculation(Session session, Cinema cinema, int noOfTickets) {
        double returnPrice = 0;
        for (int i = 0; i < noOfTickets; i++) {
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Enter age type for Ticket #" + (i + 1) + " (Student, Senior, Standard): ");
                String priceTypeString = InputController.getStringFromUser();
                if (priceTypeString.equalsIgnoreCase("Student")) {
                	returnPrice += priceCtrl.computePrice(session, cinema, PriceType.STUDENT);
                    validInput = true;
                } else if (priceTypeString.equalsIgnoreCase("Senior")) {
                	returnPrice += priceCtrl.computePrice(session, cinema, PriceType.SENIOR_CITIZEN);
                    validInput = true;
                } else if (priceTypeString.equalsIgnoreCase("Standard")) {
                	returnPrice += priceCtrl.computePrice(session, cinema, PriceType.NORMAL);
                    validInput = true;
                } else {
                    System.out.println("Wrong input!");
                }
            }
        }

        return returnPrice;
    }

    public void seatSelection() throws FileNotFoundException, InvalidTxnException {

//        Pair<String, Session> selectedSessionPair = showSeatingArrangementUI();
        Pair<Pair, Pair> pairParent = showSeatingArrangementUI();
        
        // Pass info to makeBooking function
        

        // makeBooking()
        System.out.println("--------------------------------------------------");
        System.out.println("------------ Proceed to make booking: ------------\n");
        makeBooking(pairParent);

    }

    @SuppressWarnings("rawtypes")
	public void makeBooking(Pair<Pair, Pair> pairParent)
            throws FileNotFoundException, InvalidTxnException {
        System.out.print("Enter your name: ");
        String name = InputController.getStringFromUser();
        System.out.print("Enter your email: ");
        String email = InputController.getEmailFromUser();
        System.out.print("Enter your mobile number: ");
        String mobileNumber = InputController.getMobileNumberFromUser();
        
        // PairOne holds information related to screening cinema and session (i.e. timeslot)
        String selectedCinemaCode = pairParent.t1.t1.toString(); 
        Session selectedSession = (Session) pairParent.t1.t2;
        // PairTwo holds information about user seat selection and total ticket price
        ArrayList<Integer> seatsSelected = (ArrayList<Integer>) pairParent.t2.t1;
        Double selectedTicketTotalPrice = (Double) pairParent.t2.t2;

//        Movie selectedMovie = selectedSession.getMovie();
        

        txnCtrl.create(selectedCinemaCode, name, email, mobileNumber, selectedSession, seatsSelected, selectedTicketTotalPrice);

        System.out.println("Transaction successful!");
    }

    public void printCinema(Cinema cinema) {
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::\nCinema code: " + cinema.getCode());
        ArrayList<Session> sessionList = cinema.getSessions();
        sessionList.forEach(session -> printSession(session));
    }

    public void printCinemaCode(Cinema cinema) {
        System.out.println("Cinema code: " + cinema.getCode());
    }

    public void printCineplex(Cineplex cineplex) {
        System.out.println("Name: " + cineplex.getName());
    }

    public void printSession(Session session) {
        System.out.print("\n\tSession id: " + session.getID() + "\n" +
                "\tMovie title: " + session.getMovie().getTitle() + "\n" +
                "\tDate: " + session.getStringSessionDateTime() + "\n");
    }

    public Pair<Pair, Pair> showSeatingArrangementUI() {
        SeatingUI seatingUI = new SeatingUI();
        Pair<Pair, Pair> selectedSessionPair = seatingUI.main();
        return selectedSessionPair;
    }
}
