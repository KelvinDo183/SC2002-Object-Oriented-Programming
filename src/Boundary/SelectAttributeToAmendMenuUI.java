package Boundary;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import model_Classes.Movie;
import model_Classes.MovieType;;

/* Called by UpdateMovieListingMenuUI for the Admin to select the attribute of the Movie to be ammended. Only one attribute ammendable each call.
 * Returns the Movie object with the updated attribute.
 * 
 * */

public class SelectAttributeToAmendMenuUI {
	
	private Movie m;

	// constructor requires a valid movie be passed in
	public SelectAttributeToAmendMenuUI(Movie selectedMovie) {
		this.m = selectedMovie;
	}
	
	public Movie main() throws NoSuchAlgorithmException {
		
		Scanner sc = new Scanner(System.in);
		Scanner string_scanner = new Scanner(System.in).useDelimiter("\n");
        int menuChoice;
    	
    	System.out.println("--------------------------------------------------");
        System.out.println("----- Select ONE attribute you wish to amend -----");
        System.out.println("--------------------------------------------------");
        System.out.println("(1) Movie Title ");
        System.out.println("(2) Movie Type ");
        System.out.println("(3) Synopsis ");
        System.out.println("(4) Screening Duration ");
        System.out.println("(5) Rating ");
        System.out.println("(6) Release Date ");
        System.out.println("(7) End of Screening Date ");
        System.out.println("(8) Director ");
        System.out.println("(9) Cast Members ");
        System.out.println("(10) Exit menu without making changes to movie listing ");
        System.out.println("");
        System.out.print("Enter choice: ");

        menuChoice = sc.nextInt();

        System.out.println("");

    	// make changes to movie here
        switch(menuChoice) {
        
        	// to modify Movie Title
        	case 1:
                System.out.println("--------------------------------------------------");
                System.out.println("");
                System.out.print("Enter movie title to amend to: ");
                String newTitle = sc.next();
                
        		m.setTitle(newTitle);
        		System.out.println("Movie listing has new title: " + m.getTitle());
        		System.out.println("--------------------------------------------------");
        		break;
                
        	// to modify Movie Type
        	case 2:
                System.out.println("--------------------------------------------------");
                System.out.println("");
                System.out.println("Enter movie type to amend to:");
                System.out.println("(1) Blockbuster ");
                System.out.println("(2) 2D ");
                System.out.println("(3) 3D ");
                int newType = sc.nextInt();
                // convert user input to MOVIE TYPE
                MovieType movieType = m.getType();
                
                switch(newType) {
                	case 1:
                		movieType = MovieType.BLOCKBUSTER;
                		break;
                	case 2:
                		movieType = MovieType.TWO_D;
                		break;
                	case 3:
                		movieType = MovieType.THREE_D;
                		break;
            		default:
            			movieType = m.getType();
                
                }
          
                // check that admin's changes are registered by system
        		m.setType(movieType);
        		System.out.println("Movie listing has new type: " + m.getType());
        		System.out.println("--------------------------------------------------");
        
        	// to modify Movie Description
        	case 3:
                System.out.println("--------------------------------------------------");
                System.out.println("");
                System.out.print("Enter movie synopsis to amend to: ");
                String newDescription = string_scanner.next();
                
        		m.setDescription(newDescription);
        		System.out.println("Movie listing has new synopsis: \n" + m.getDescription());
        		System.out.println("--------------------------------------------------");
        		break;
                
        	// to modify Movie Duration
        	case 4:
                System.out.println("--------------------------------------------------");
                System.out.println("");
                System.out.print("Enter screening duration to amend to: ");
                Double newDuration = Double.parseDouble(sc.next());
                
        		m.setDuration(newDuration);
        		System.out.println("Movie listing has new screening duration of: " + m.getDuration() + " minutes");
        		System.out.println("--------------------------------------------------");
        		break;
        		
        		
    		// to modify Movie Rating
        	case 5:
                System.out.println("--------------------------------------------------");
                System.out.println("");
                System.out.print("Enter movie rating to amend to: ");
                float newRating = Float.parseFloat(sc.next());
                
        		m.setRating(newRating);
        		System.out.println("Movie listing has new rating: " + m.getRating());
        		System.out.println("--------------------------------------------------");
        		break;
                
        	// to modify Movie Release Date
        	case 6:
                System.out.println("--------------------------------------------------");
                System.out.println("");
                System.out.println("Enter release date to amend to (format yyyy-mm-dd): ");

                LocalDate newReleaseDate = LocalDate.parse(sc.next());
                
        		m.setReleaseDate(newReleaseDate);
        		System.out.println("Movie listing has new release date: " + m.getReleaseDate());
        		System.out.println("--------------------------------------------------");
        		break;
        
        	// to modify End of Screening Date for Movie
        	case 7:
                System.out.println("--------------------------------------------------");
                System.out.println("");
                System.out.print("Enter end of screening date to amend to (format yyyy-mm-dd): ");
                
                LocalDate newEndDate = LocalDate.parse(sc.next());
                
        		m.setEndDate(newEndDate);
        		System.out.println("Movie listing has new end of screening date: " + m.getEndDate());
        		System.out.println("--------------------------------------------------");
        		break;
                
        	// to modify Director Name
        	case 8:
                System.out.println("--------------------------------------------------");
                System.out.println("");
                System.out.print("Enter Director's name to amend to: ");
                String newDirectorName = sc.next();
                
        		m.setDirectorName(newDirectorName);
        		System.out.println("Movie listing has new Director: " + m.getDirectorName());
        		System.out.println("--------------------------------------------------");
        		break;
        		
        	// to modify Cast Members
        	case 9:
                System.out.println("--------------------------------------------------");
                System.out.println("");
                System.out.println("Enter updated list of cast members (new line for each cast member):");
                System.out.println("Type _@ to end input of cast member names");
               
                String newCastMember = sc.next();
                ArrayList<String> newCastMembersAL = new ArrayList<String>();
                
                while (newCastMember != "_@")
                {
                	newCastMembersAL.add(newCastMember);
                	newCastMember = sc.next();
                }
                
                m.setCastMembers(newCastMembersAL);
        		System.out.println("Movie listing has new Cast Members: " + m.getCastMembers());
        		System.out.println("--------------------------------------------------");
        		break;
        		
    		// exit attribute selection menu
        	case 10:
//        		exitMenu = true;
        		break;
        
        }
        
        
        // return updated movie listing
        return m;
		
		
	}

	

}
