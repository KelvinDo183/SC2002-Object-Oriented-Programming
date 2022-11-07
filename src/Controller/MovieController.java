package Controller;

import model_Classes.Movie;
import model_Classes.MovieType;
import model_Classes.MovieStatus;
import exceptions.ExistingMovieException;
import exceptions.ReleasePastEndException;
import exceptions.IncompleteMovieInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;



public class MovieController {
	
	public final static String FILENAME = "src/datastorage/movielisting.txt";
	
	// controller for each new session by users
	public SessionController sessionController;
	
	// Constructor for MovieController
	public MovieController() {
		this.sessionController = new SessionController();
	}
	
	// Constructor including parameter for session
	public MovieController(SessionController sessionController) {
		this.sessionController = sessionController;
	}
	
	// To print the following menu options upon loading of menu
	public void main() throws IOException {
		boolean exitMenu = false;
		while (!exitMenu)
		{
        	System.out.println("--------------------------------------------------");
		    System.out.println("------- Admin View: Movie Listings System --------");
		    System.out.println("--------------------------------------------------");
		    System.out.println("(1) Create New Movie Listing ");
		    System.out.println("(2) Update Existing Movie Listing ");
		    System.out.println("(3) Delete a Movie Listing ");
		    System.out.println("(4) Return to Menu");
		    System.out.println("");
		    System.out.print("Select choice: ");


		    Scanner sc = new Scanner(System.in);
		    Scanner string_scanner = new Scanner(System.in).useDelimiter("\n");
		    int menuChoice = sc.nextInt();


		    System.out.println("");


		    switch (menuChoice) {
		    // create new movie listing
		    case 1:
			System.out.println("--------------------------------------------------");
			System.out.println("----- Details of the movie listing to create -----");
			System.out.println("--------------------------------------------------");
			System.out.println("Enter Movie Title: ");
		//                String movieTitle = sc.nextLine();
			String movieTitle = string_scanner.next();
			System.out.println("Enter Movie Type: ");
			System.out.println("(1) Blockbuster ");
			System.out.println("(2) 2D ");
			System.out.println("(3) 3D ");
			MovieType movieType = MovieType.BLOCKBUSTER;
			int movTypeInput = sc.nextInt();
					// convert user input to MOVIE TYPE

					switch(movTypeInput) {
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
							movieType = MovieType.BLOCKBUSTER;

					}


			System.out.println("Enter Synopsis: ");
		//                String movieDescription = sc.nextLine();
			String movieDescription = string_scanner.next();
			System.out.println("");

			System.out.println("Enter Screening Duration: ");
			String durationStringInput = string_scanner.next();
			Double movieDuration = Double.parseDouble(durationStringInput);
			System.out.println("Enter Rating: ");
			String movieRating = string_scanner.next();
			System.out.println("Enter Release Date (format yyyy-mm-dd): ");
			String releaseDateStringInput = sc.next();
		//            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-Mmm-yyyy");
		//                LocalDate movieReleaseDate = LocalDate.parse(releaseDateStringInput, formatter);
		//                LocalDate movieReleaseDate = LocalDate.parse("26-Oct-2022", formatter);
			LocalDate movieReleaseDate = LocalDate.parse(releaseDateStringInput);
			System.out.println("Enter End of Screening Date (format yyyy-mm-dd): ");

			String endDateStringInput = sc.next();
			LocalDate movieEndDate = LocalDate.parse(endDateStringInput);
		//                LocalDate movieEndDate = LocalDate.parse(endDateStringInput, formatter);
			System.out.println("Enter Director Name: ");
		//                String movieDirectorName = sc.nextLine();
			String movieDirectorName = string_scanner.next();
			System.out.println("Enter list of Cast Members (new line for each cast member): ");
			System.out.println("Type 'N' to end input of cast member names");

			String newCastMember = sc.nextLine();
		//                String newCastMember = string_scanner.next();
			ArrayList<String> newCastMembersAL = new ArrayList<String>();

			Boolean continueLooping = true;

			do
			{
				newCastMembersAL.add(newCastMember);
				String additionalCast = sc.next();
				if (additionalCast.toUpperCase().matches("N")) 
					{
						System.out.println("No more cast members to add.");
						continueLooping = false;
					}

			} while (continueLooping);


			System.out.println("");
			Movie movielisting = new Movie(MovieController.getLatestId()+1, movieTitle, movieType, movieDescription,
								movieDuration, movieRating, movieReleaseDate, movieEndDate, movieDirectorName, newCastMembersAL);

			System.out.println("Movie listing with ID #" + movielisting.getID() + " and title '" 
							+ movielisting.getTitle() + "' has been created.");

			break;

		    // update existing movie listing
		    case 2:
			break;

		    // delete a movie listing
		    case 3:
			break;

		    // return to previous
		    case 4:
			exitMenu = true;
			System.out.println("Returning to menu ...");
			System.out.println("");
			break;

		    default:
			System.out.println("Please enter a correct number");
			System.out.println("");
		}
		} while (!exitMenu);




		}
	
	
	
	// To append new movie to database of movies if it is valid
	// For the first movie created, a new file is also created to save it
	

	/*
	 * 3 key functionality:
	 * (1) Create
	 * (2) Update
	 * (3) Delete/Remove
	 */
	
	public void create(String title, MovieType type, String description, double duration, String rating,
					LocalDate releaseDate, LocalDate endDate, String directorName, ArrayList<String> castMembers)
	{
		// append new movie to database of movies after validation
		if (isValidMovie(title, type, description, duration, rating, releaseDate, endDate, directorName, castMembers))
		{
			Movie movie = new Movie(getLatestId() + 1, title, type, description, duration, rating, 
					releaseDate, endDate, directorName, castMembers);
			
			ArrayList<Movie> allMovies = new ArrayList<Movie>();
			
			// read text file for list of all movies
			File file = new File(FILENAME);
			if (file.exists())
			{
				allMovies = read();
			}
			else {
				try {
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILENAME));
					allMovies.add(movie);
					output.writeObject(allMovies);
					output.flush();
					output.close();
				}
				catch (IOException e) {
					// unable to access specified directory
	                System.out.println(e.getMessage());
				}
			}
		}	
	}
	
	@SuppressWarnings("unchecked")
    public void updateById(int col, int ID, Object newValue) {
        ArrayList<Movie> allMovies = read();
        ArrayList<Movie> remainingMovies = new ArrayList<Movie>();

     // invoke updateByMovie() method in sessionController
//        sessionController.updateByMovie(col, id, newValue);
                
        for (int i=0; i<allMovies.size(); i++){
            Movie m = allMovies.get(i);
            if (m.getID() == ID)
            {
            	// print out details of movie selected by User
            	System.out.println("--------------------------------------------------");
                System.out.println("-- Details of movie you have selected to amend ---");
                System.out.println("--------------------------------------------------");
                System.out.println("Movie Title: " + m.getTitle());
                System.out.println("Movie Type: " + m.getType());
                System.out.println("Synopsis: " + m.getDescription());
                System.out.println("Screening Duration: " + m.getDuration());
                System.out.println("Rating: " + m.getRating());
                System.out.println("Release Date: " + m.getReleaseDate());
                System.out.println("End of Screening Date: " + m.getEndDate());
                System.out.println("Director: " + m.getDirectorName());
                System.out.println("Cast Members: " + m.getCastMembers());
                System.out.println("");
            	
            	
                Scanner sc = new Scanner(System.in);
                int menuChoice;
            	
            	System.out.println("--------------------------------------------------");
                System.out.println("----- Select the attribute you wish to amend -----");
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
                System.out.println("");
                System.out.print("Enter choice: ");

                menuChoice = sc.nextInt();

                System.out.println("");
                

            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-Mmm-yyyy");
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
                        String newDescription = sc.next();
                        
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
                        String newRating = sc.next();
                        
                		m.setRating(newRating);
                		System.out.println("Movie listing has new rating: " + m.getRating());
                		System.out.println("--------------------------------------------------");
                		break;
                        
                	// to modify Movie Release Date
                	case 6:
                        System.out.println("--------------------------------------------------");
                        System.out.println("");
                        System.out.println("Enter release date to amend to (format d-Mmm-yyyy): ");

                        LocalDate newReleaseDate = LocalDate.parse(sc.next(), formatter);
                        
                		m.setReleaseDate(newReleaseDate);
                		System.out.println("Movie listing has new release date: " + m.getReleaseDate());
                		System.out.println("--------------------------------------------------");
                		break;
                
                	// to modify End of Screening Date for Movie
                	case 7:
                        System.out.println("--------------------------------------------------");
                        System.out.println("");
                        System.out.print("Enter end of screening date to amend to (format d-Mmm-yyyy): ");
                        
                        LocalDate newEndDate = LocalDate.parse(sc.next(), formatter);
                        
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
                
                }
         
            	// create new movie listing (with updated attribute values) with new ID

                Movie updatedMovieCopy = m;
                updatedMovieCopy.setID(getLatestId());
                remainingMovies.add(updatedMovieCopy);


            	// remove original movie listing
                MovieController movController = new MovieController();
//                movController.deleteById(m.getID());
                remainingMovies.remove(m.getID());
            	
            }

            	
//            returnData.add(updatedMovieCopy);
        }

        replaceExistingFile(FILENAME, remainingMovies);
    }
	
	
	public void deleteById(int ID) {
        ArrayList<Movie> allMovies = read();
        ArrayList<Movie> remainingMovies = new ArrayList<Movie>();

        // invoke deleteByMovie() method in sessionController
//        sessionController.deleteByMovie(ID);
        
        for (int i=0; i<allMovies.size(); i++){
            Movie m = allMovies.get(i);
            if (!(m.getID() == ID))
            	remainingMovies.add(m);
        }

        // update text file of movie listings to reflect latest deletion of movie
        replaceExistingFile(FILENAME, remainingMovies);
    }

	
	
	
    // this method reads the entire database of movie listings (including movies past their screening date)
    @SuppressWarnings("unchecked")
	public static ArrayList<Movie> read() {
        try {
            ObjectInputStream objInputStream = new ObjectInputStream(new FileInputStream(FILENAME));   
            ArrayList<Movie> movieListing = (ArrayList<Movie>) objInputStream.readObject();
            objInputStream.close();
            return movieListing;
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        } 
        return new ArrayList<Movie>();
    }
    
    
    // this method lists all movies in database (including title & ID so it is easier for user to identify movies)
    // which are currently screening
	public ArrayList<Movie> readAllScreeningMovies() 
	{
		ArrayList<Movie> allMovies = read();
		ArrayList<Movie> allScreeningMovies = new ArrayList<Movie>();
		
		// make use of for loop to include only movies with status of "NOW_SHOWING" and "PREVIEW"
		for (Movie movie : allMovies)
		{
			MovieStatus status = movie.getShowStatus();
			if (status == MovieStatus.NOW_SHOWING || status == MovieStatus.PREVIEW)
			{
				allScreeningMovies.add(movie);
			}
		}
		
		return allScreeningMovies;
	}
    
    
    // this method returns a particular movie specified by the ID provided by user
	public Movie readSpecificID(int movieID)
	{
		ArrayList<Movie> allMovies = read();
		for (int i = 0; i < allMovies.size(); i++)
		{
			Movie movie = allMovies.get(i);
			// check for matching movie ID
			if (movieID == movie.getID())
			{
				return movie;
			}
		}
		// if no matching movie found, return null
		return null;
	}
    
    
	
	// Supporting functions below
	public static boolean isValidMovie(
	        String title, MovieType type, String description, double duration, String rating, LocalDate releaseDate, 
	        LocalDate endDate, String directorName, ArrayList<String> castMembers
	    ) {
	        boolean isValid = true;

	        if (isExistingMovie(title)){
	            try {
	                throw new exceptions.ExistingMovieException();
	            } catch (ExistingMovieException e) {
	                System.out.println(e.getMessage());
	            }
	            isValid = false;
	        }
	            
	        if (isValidScreeningDate(releaseDate, endDate) == false) 
	            isValid = false;

	        if (title == null || type == null || description == null || 
	        		duration < 0  || rating == null || releaseDate == null ||
	        		endDate == null || directorName == null || castMembers == null	        		
        		)
	        {
	            try {
	                throw new exceptions.ExistingMovieException();
	            } catch (ExistingMovieException e) {
	                System.out.println(e.getMessage());
	            }
	        	isValid = false;
	        }
	           
	        return isValid;
	    }
		
    public static boolean isExistingMovie(String title) {
        ArrayList<Movie> allMovies;
		try {
			allMovies = MovieController.read();
			
	        for (Movie movie : allMovies) {
	            if (movie.getTitle().equals(title))
	                return true;
	        }
	        return false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }
	
    
    public static boolean isValidScreeningDate(LocalDate releaseDate, LocalDate endDate){
        if (releaseDate.isBefore(endDate)){
            return true;
        } else {
            try {
                throw new ReleasePastEndException();
            } catch (ReleasePastEndException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }
    }
    
    public static int getLatestId(){
        int movieID;
    	int latestId = -1;

        ArrayList<Movie> allData = read();
        for (int i=0; i<allData.size(); i++){
            movieID = allData.get(i).getID();
            if (movieID > latestId)
                latestId = movieID;
        }
        return latestId;
    }
    
    
    public void replaceExistingFile(String filename, ArrayList<Movie> data){
        File file = new File(filename);
        if (file.exists()) 
            file.delete();
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));
            output.writeObject(data);
            output.flush();
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
	
}
