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

	/**
	 * The file name of the database file that this controller will access
	 */
	public final static String FILENAME = "src/datastorage/movielisting.txt";

	/**
	 * Some constants
	 */
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

	/**
	 * The Review Controller that this controller will reference
	 */
	public SessionController sessionController;

	/**
	 * Default Constructor
	 */
	public MovieController() {
		this.sessionController = new SessionController();
	}

	/**
	 * Parameterized constructor with user-defined Session Controller
	 * 
	 * @param sessionsCtrl Non-default Session Controller to be referenced instead
	 */
	public MovieController(SessionController sessionController) {
		this.sessionController = sessionController;
	}

	/**
	 * Main function to execute
	 */
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

	/**
	 * CREATE a new Movie and add it into the database file
	 * Attributes are validated before creation
	 * If attributes are not allowed, throw error and do nothing
	 * If Database file exist, existing records are read and new Movie object is
	 * aopended before saving
	 * If Database file does not exist, Movie object will be written to a new file
	 * and saved
	 * 
	 * @param title            This movie's title
	 * @param type             This movie's type
	 * @param description      This movie's description
	 * @param rating           This movie's rating
	 * @param duration         This movie's duration
	 * @param movieReleaseDate This movie's release date
	 * @param movieEndDate     This movie's end date
	 * @param directorName     This movie's director
	 * @param castMembers      This movie's list of cast
	 */
	public void create(String title, MovieType type, String description, double duration, float rating,
			LocalDate releaseDate, LocalDate endDate, String directorName, ArrayList<String> castMembers)
			throws FileNotFoundException {
		// append new movie to database of movies after validation
		if (isValidMovie(title, type, description, duration, rating, releaseDate, endDate, directorName, castMembers)) {
			try {
				Movie movie = new Movie(getLatestId() + 1, title, type, description, duration, rating,
						releaseDate, endDate, directorName, castMembers);

				ArrayList<Movie> allMovies = new ArrayList<Movie>();

				// read text file for list of all movies
				File file = new File(FILENAME);
				if (file.exists()) {
					allMovies = read();

				}
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILENAME));
				allMovies.add(movie);
				output.writeObject(allMovies);
				output.flush();
				output.close();
			} catch (IOException e) {
				// unable to access specified directory
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * UPDATE a Movie by ID
	 */
	@SuppressWarnings("unchecked")
	public void updateById() throws NoSuchAlgorithmException, FileNotFoundException {

		ArrayList<Movie> updatedMovieListing = updateMovieListingMenuUI();

		replaceExistingFile(FILENAME, updatedMovieListing);
	}

	/**
	 * Delete a Movie in the Database file, based on the ID attribute passed
	 * 
	 * @param id ID of Movie which will be deleted
	 */
	public void deleteById(int ID) throws FileNotFoundException {
		ArrayList<Movie> allMovies = read();
		ArrayList<Movie> remainingMovies = new ArrayList<Movie>();

		for (int i = 0; i < allMovies.size(); i++) {
			Movie m = allMovies.get(i);
			if (!(m.getID() == ID))
				remainingMovies.add(m);
		}
		replaceExistingFile(FILENAME, remainingMovies);
	}

	/**
	 * READ and return every Movies in the Database file
	 * 
	 * @return Movie Return list of Movies if found, else empty list
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Movie> read() throws FileNotFoundException {
		try {

			FileInputStream fis = new FileInputStream(FILENAME);
			ObjectInputStream objInputStream = new ObjectInputStream(fis);

			ArrayList<Movie> movieListing = (ArrayList<Movie>) objInputStream.readObject();

			objInputStream.close();

			ArrayList<Movie> returnMovieList = new ArrayList<Movie>();
			// rearrange movie ID before returning ArrayList
			for (int i = 0; i < movieListing.size(); i++) {
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

	/**
	 * READ and return every Movies which are screening in the Database file
	 * 
	 * @return Movie Return list of Movies if found, else empty list
	 */
	public static ArrayList<Movie> readAllScreeningMovies() throws FileNotFoundException {
		ArrayList<Movie> allMovies = read();
		ArrayList<Movie> allScreeningMovies = new ArrayList<Movie>();

		// make use of for loop to include only movies with status of "NOW_SHOWING" and
		// "PREVIEW"
		for (Movie movie : allMovies) {
			MovieStatus status = movie.getStatus();
			if (status == MovieStatus.NOW_SHOWING || status == MovieStatus.PREVIEW) {
				allScreeningMovies.add(movie);
			}
		}

		return allScreeningMovies;
	}

	/**
	 * READ and return every Movie of specific type
	 * 
	 * @return Movie Return list of Movies if found, else empty list
	 */

	public static ArrayList<Movie> readAllMoviesOfType(String type) throws FileNotFoundException {
		ArrayList<Movie> allMovies = read();
		ArrayList<Movie> allApplicableMovieType = new ArrayList<Movie>();

		// make use of for loop to include only movies which match the MovieType
		// (String) provided by user
		for (Movie movie : allMovies) {
			String movieType = movie.getType().toString();
			if (movieType.equals(type)) {
				allApplicableMovieType.add(movie);
			}
		}

		return allApplicableMovieType;
	}

	// this method returns a particular movie specified by the ID provided by user
	/**
	 * READ and returns a particular movie specified by the ID provided by user
	 * 
	 * @return Movie Movie
	 */

	public Movie readSpecificID(int movieID) throws FileNotFoundException {
		ArrayList<Movie> allMovies = read();
		for (int i = 0; i < allMovies.size(); i++) {
			Movie movie = allMovies.get(i);
			// check for matching movie ID
			if (movie.getID() == movieID) {
				return movie;
			}
		}
		// if no matching movie found, return null
		return null;
	}

	// Supporting functions below
	/**
	 * Verify a movie
	 * 
	 * @return boolean
	 */
	public static boolean isValidMovie(
			String title, MovieType type, String description, double duration, float rating, LocalDate releaseDate,
			LocalDate endDate, String directorName, ArrayList<String> castMembers) {
		boolean isValid = true;

		if (isExistingMovie(title)) {
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
				duration < 0 || rating < 0 || releaseDate == null ||
				endDate == null || directorName == null || castMembers == null) {
			try {
				throw new exceptions.ExistingMovieException();
			} catch (ExistingMovieException e) {
				System.out.println(e.getMessage());
			}
			isValid = false;
		}

		return isValid;
	}

	/**
	 * Verify existence of movie
	 * 
	 * @return boolean
	 */
	public static boolean isExistingMovie(String title) {
		ArrayList<Movie> allMovies;
		try {

			allMovies = MovieController.read();

			for (Movie movie : allMovies) {
				String titleData = movie.getTitle();
				if (titleData.trim().equalsIgnoreCase(title.trim())) {
					System.out.println("This is an existing movie! " + title);
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// returns movie that have a title as per the parameter
	/**
	 * Find existing movie
	 * 
	 * @return Movie
	 */
	public static Movie findExistingMovie(String title) throws FileNotFoundException {

		ArrayList<Movie> allMovies;
		try {
			allMovies = readAllScreeningMovies();

			for (Movie movie : allMovies) {
				String titleData = movie.getTitle();
				if (titleData.trim().equalsIgnoreCase(title.trim())) {
					return movie;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Verify screening date
	 * 
	 * @return boolean
	 */
	public static boolean isValidScreeningDate(LocalDate releaseDate, LocalDate endDate) {
		if (releaseDate.isBefore(endDate)) {
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

	/**
	 * Get last movie ID
	 * 
	 * @return int
	 */
	public static int getLatestId() throws FileNotFoundException {
		int movieID;
		int latestId = -1;

		ArrayList<Movie> allData = read();
		for (int i = 0; i < allData.size(); i++) {
			movieID = allData.get(i).getID();
			if (movieID > latestId)
				latestId = movieID;
		}
		return latestId;
	}

	/**
	 * Overwrite Database file with new data of list of Admin
	 * 
	 * @param filename Filename to check for
	 * @param data     New ArrayList of Movies to be written to the file
	 */
	public void replaceExistingFile(String filename, ArrayList<Movie> data) {
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

	/**
	 * UI to movie listing system
	 */
	public void adminMovieListingSystemMenuUI() throws NoSuchAlgorithmException, FileNotFoundException {
		AdminMovieListingSystemMenuUI adminSystemMenu = new AdminMovieListingSystemMenuUI();
		adminSystemMenu.main();
	}

	/**
	 * UI to update movie listing
	 */
	public ArrayList<Movie> updateMovieListingMenuUI() throws NoSuchAlgorithmException, FileNotFoundException {
		UpdateMovieListingMenuUI updateML_UI = new UpdateMovieListingMenuUI();
		ArrayList<Movie> updatedML_AL = updateML_UI.main();
		return updatedML_AL;
	}
}
