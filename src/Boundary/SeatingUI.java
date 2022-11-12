
package Boundary;

import Controller.*;
import model_Classes.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SeatingUI {
	
	/** 
     * Temporary seating plan storage & all necessary controllers (session, cineplex, cinema)
     */
    private SeatingPlan seatsAvailability;
    private SessionController sessionsCtrl;
    private CineplexeController cineplexesCtrl;
    private CinemaController cinemasCtrl;
    
    /** 
     * Default Constructor - make the necessary controllers
     */
    public SeatingUI(){
        this.sessionsCtrl = new SessionController();
        this.cineplexesCtrl = new CineplexeController();
        this.cinemasCtrl = new CinemaController();
    }

    /** 
     * Main method to load - if there are available sessions, user can choose one to load the layout
     */
    public Pair<String, Session> main(){
        if(showAvailableSessions()){
            return printLayout();
        }
        else{
            return null;
        }
    }

    // user can retrieve layout of seating arrangement by providing date & cinema code of movie session
    // this function returns movie associated with session to the main() method
    // so that user can proceed to make booking after selecting seat
    @SuppressWarnings("unlikely-arg-type")
	public Pair<String, Session> printLayout(){
        int choice = 0;
        System.out.println("\nSelect session by providing Date and Cinema Code");
		System.out.print("Enter date : ");
		LocalDateTime sessionDateTime = InputController.getDateTimeFromUser();

        System.out.print("Enter cinema code: ");
        String cinemaCode = InputController.getStringFromUser();

        Session session = sessionsCtrl.readBySession(cinemaCode, sessionDateTime);
        if(session == null){
            System.out.println("Incorrect input...");
            printLayout();
            return null;
        }
        else{
            System.out.println("Seating layout for this session: ");
            seatsAvailability = session.getSeatsAvailability();
            seatsAvailability.printLayout();
            
            // Allow user to select seats from layout
            System.out.println("--------------------------------------------------");
            System.out.println("Select an available seat: ");
            Scanner sc = new Scanner(System.in);
            int seatChoice = sc.nextInt();
            ArrayList<Integer> seatChoices = new ArrayList<Integer>();
            
            
            do{
                System.out.println("To exit SeatingUI, input -1 ...");
                
                // functionality to add/remove to ArrayList based on whether it is already included
                if (seatChoices.contains(seatChoice)) 
            	{
                	int indexFirstOccurrence = seatChoices.indexOf(seatChoice);
                	seatChoices.remove(indexFirstOccurrence);
                	}
                else
                {
                    seatChoices.add(seatChoice);	
                }
                
                System.out.println("After update ... seatChoicesArray = " + seatChoices.toString());
                
            }while((seatChoice = sc.nextInt())!=-1);
            
            // run through loop to adjust seatsAvailability
            for (int seatIndex : seatChoices) {
            	seatsAvailability.assignSeats(seatIndex);
            }
            
            System.out.println("Seating layout after selected seats are assigned: ");
            seatsAvailability.printLayout();
            session.setSeatsAvailability(seatsAvailability);
            
            // Compute prices for selected number of tickets here
            BookingAndPurchaseTicketsUI bookingTicketsUI = new BookingAndPurchaseTicketsUI();
            Cinema requestedCinema = cinemasCtrl.readByCinemaName(cinemaCode);
            
            double totalTicketPrice = bookingTicketsUI.priceCalculation(session, requestedCinema, seatChoices.size());
            System.out.println("Total price of tickets = " + totalTicketPrice + " SGD.");
            
            Pair newPair = Pair(cinemaCode, session);
            return newPair;
        }
    }
    

    private Pair Pair(String cinemaCode, model_Classes.Session session2) {
		Pair newPair = new Pair();
    	newPair.setPair(cinemaCode, session2);
    	return newPair;
	}


    private boolean showAvailableSessions(){
        ArrayList<Cineplex> cineplexes = cineplexesCtrl.read();
        boolean validInput = false;
        int userChoice = 0;

        if(cineplexes.isEmpty()){
            System.out.println("No cineplexes in the system!");
            System.out.println("Returning to menu...");
            return false;
        }
        else{
            System.out.println("Available cineplexes: ");
            for (int i = 0; i<cineplexes.size();i++) {
                System.out.println("\t" + (i+1) + ". " + cineplexes.get(i).getName());
            }
        }
        

        while(!validInput){
            System.out.print("Choose cineplex by ID: ");
	          Scanner sc = new Scanner(System.in);
	          userChoice = sc.nextInt();
            if(userChoice < 1 || userChoice > cineplexes.size()){
                System.out.println("Incorrect input provider.");
            }
            else{
                validInput = true;
            }
        }

        ArrayList<Cinema> cinemas = cineplexes.get(userChoice-1).getCinemas();
        int counter = 0;

        System.out.println("------------------------------------------------------------------");
        System.out.println(" ***** Available sessions in cineplex " + cineplexes.get(userChoice-1).getName() + ": ");
        for(int i =0;i<cinemas.size();i++){
            Cinema cinema = cinemas.get(i);
            ArrayList<Session> sessions = cinema.getSessions();
            for(int j=0;j<sessions.size();j++){
                System.out.println("\t" + (counter+1) + ". Cinema: " + cinema.getCode() + "\n\t   Movie: " + sessions.get(j).getMovie().getTitle()
                + "\n\t   Date: " + sessions.get(j).getStringSessionDateTime());
                counter++;
            }
        }
        if(counter == 0){
            System.out.println("No available sessions found within this cineplex!");
            System.out.println("Returning to menu....");
            return false;
        }
        return true;
    }
    
}