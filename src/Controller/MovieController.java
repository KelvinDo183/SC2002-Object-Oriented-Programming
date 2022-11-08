package Controller;

import model_Classes.Movie;
import model_Classes.MovieType;
import model_Classes.MovieStatus;
import Boundary.CreateMovieListingUI;
import Boundary.AdminMovieListingSystemMenuUI;
import Boundary.DetailsMovieToAmendMenuUI;
import Boundary.SelectAttributeToAmendMenuUI;
import Boundary.UpdateMovieListingMenuUI;
import Boundary.RegisterUIAdmin;
import exceptions.ExistingMovieException;
import exceptions.ReleasePastEndException;
import exceptions.IncompleteMovieInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;



public class MovieController {
	
	public final static String FILENAME = "src/datastorage/movielisting.txt";
    public final static int ID = 0;
    public final static int TITLE = 1;
    public final static int TYPE = 2;
    public final static int DESCRIPTION = 3;
    public final static int DURATION = 4;
    public final static int RATING = 5;
    public final static int RELEASE_DATE = 6;
    public final static int END_DATE = 7;
    public final static int DIRECTOR_NAME = 8;
    public final static int CAST_MEMBERS = 9;
    public final static int REVIEWS = 10;
	
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
	public void main() throws IOException, NoSuchAlgorithmException, FileNotFoundException {

		// display menu UI for Admin to interact with Movie Listing System
		adminMovieListingSystemMenuUI();

	}
	
	
	

	/*
	 * 3 key functionality:
	 * (1) Create
	 * (2) Update
	 * (3) Delete/Remove
	 */
	
	
	
	// To append new movie to database of movies if it is valid
	// For the first movie created, a new file is also created to save it
	public void create(String title, MovieType type, String description, double duration, String rating,
					LocalDate releaseDate, LocalDate endDate, String directorName, ArrayList<String> castMembers)
			throws FileNotFoundException
	{
		// append new movie to database of movies after validation
		if (isValidMovie(title, type, description, duration, rating, releaseDate, endDate, directorName, castMembers))
		{
			try {
			Movie movie = new Movie(getLatestId() + 1, title, type, description, duration, rating, 
					releaseDate, endDate, directorName, castMembers);
			
			ArrayList<Movie> allMovies = new ArrayList<Movie>();
			
			// read text file for list of all movies
			File file = new File(FILENAME);
			if (file.exists())
			{
				allMovies = read();
				
			}
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
	
	@SuppressWarnings("unchecked")
    public void updateById() throws NoSuchAlgorithmException, FileNotFoundException {

        ArrayList<Movie> updatedMovieListing = updateMovieListingMenuUI();

        replaceExistingFile(FILENAME, updatedMovieListing);
    }
	
	
	public void deleteById(int ID) throws FileNotFoundException {
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
	public static ArrayList<Movie> read() throws FileNotFoundException {
        try {

    		FileInputStream fis = new FileInputStream(FILENAME);
            ObjectInputStream objInputStream = new ObjectInputStream(fis);   


            ArrayList<Movie> movieListing = (ArrayList<Movie>) objInputStream.readObject();
            
            objInputStream.close();
            
            ArrayList<Movie> returnMovieList = new ArrayList<Movie>();
            // rearrange movie ID before returning ArrayList
            for (int i = 0; i < movieListing.size(); i++)
            {
            	Movie m = movieListing.get(i);
            	m.setID(1001 + i); 
            	m.adjustStatusByDates();
            	returnMovieList.add(m);
            }
            
            return returnMovieList;
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } 
        
        return new ArrayList<Movie>();
    }
    
    
    // this method lists all movies in database (including title & ID so it is easier for user to identify movies)
    // which are currently screening
	public static ArrayList<Movie> readAllScreeningMovies() throws FileNotFoundException 
	{
		ArrayList<Movie> allMovies = read();
		ArrayList<Movie> allScreeningMovies = new ArrayList<Movie>();
		
		// make use of for loop to include only movies with status of "NOW_SHOWING" and "PREVIEW"
		for (Movie movie : allMovies)
		{
			MovieStatus status = movie.getStatus();
			if (status == MovieStatus.NOW_SHOWING || status == MovieStatus.PREVIEW)
			{
				allScreeningMovies.add(movie);
			}
		}
		
		return allScreeningMovies;
	}
	
	public static ArrayList<Movie> readAllMoviesOfType(String type) throws FileNotFoundException 
	{
		ArrayList<Movie> allMovies = read();
		ArrayList<Movie> allApplicableMovieType = new ArrayList<Movie>();
		
		// make use of for loop to include only movies which match the MovieType (String) provided by user
		for (Movie movie : allMovies)
		{
			String movieType = movie.getType().toString();
			if (movieType.equals(type))
			{
				allApplicableMovieType.add(movie);
			}
		}
		
		return allApplicableMovieType;
	}
	
	
    
    
    // this method returns a particular movie specified by the ID provided by user
	public Movie readSpecificID(int movieID) throws FileNotFoundException
	{
		ArrayList<Movie> allMovies = read();
		for (int i = 0; i < allMovies.size(); i++)
		{
			Movie movie = allMovies.get(i);
			// check for matching movie ID
			if (movie.getID() == movieID)
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
    
    public static int getLatestId() throws FileNotFoundException{
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
        	e.printStackTrace();
        }
    }

    
    public void adminMovieListingSystemMenuUI() throws NoSuchAlgorithmException, FileNotFoundException {
    	AdminMovieListingSystemMenuUI adminSystemMenu = new AdminMovieListingSystemMenuUI();
    	adminSystemMenu.main();
    }
    
    public ArrayList<Movie> updateMovieListingMenuUI() throws NoSuchAlgorithmException, FileNotFoundException {
    	UpdateMovieListingMenuUI updateML_UI = new UpdateMovieListingMenuUI();
    	ArrayList<Movie> updatedML_AL = updateML_UI.main();
    	return updatedML_AL;
    }
        
	
}
