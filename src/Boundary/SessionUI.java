package Boundary;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import Controller.*;
import model_Classes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class SessionUI {
    /**
     * All controllers (movie, cinema, cineplex, session)
     */
    private MovieController movieCtrl = new MovieController();
    private CinemaController cinemaCtrl = new CinemaController();
    private CineplexeController cineplexCtrl = new CineplexeController();
    private SessionController sessionCtrl = new SessionController();
    private static Scanner sc;

    /**
     * Main method to load - display all options and ask user to choose one
     * 
     * @throws FileNotFoundException
     */
    public void main() throws FileNotFoundException {
        boolean exit = false;
        sc = new Scanner(System.in);
        
        while (!exit) {
            System.out.print("\n\nCreate/Update/Remove session: \n\n" +
                    "1. Create Movie Session\n" +
                    "2. Update Movie Session\n" +
                    "3. Remove Movie Session\n" +
                    "4. View Movie Sessions\n" +
                    "5. Return to Main Menu\n\n" +
                    "Select action: ");
            int option = sc.nextInt();
            System.out.println("");
            
            switch (option) {
                case 1:
                    createMovieSession();
                    break;
                case 2:
                    updateMovieSession();
                    break;
                case 3:
                    removeMovieSession();
                    break;
                case 4:
                    listMovieSessions();
                    break;
                case 5:
                    exit = true;
                    break;
            }
        }
    }

    /**
     * Create a new session with user's input
     * Any invalid input will return user to main menu
     * 
     * @throws FileNotFoundException
     */
    public void createMovieSession() throws FileNotFoundException {

        System.out.println("Creating Session:\n");
        System.out.println("Cineplex List:\n");
        ArrayList<Cineplex> cineplexList = cineplexCtrl.read();
        if (cineplexList.isEmpty()) {
            System.out.println("There are no cineplexes registered!");
            return;
        }
        cineplexList.forEach(Cineplex -> printCineplex(Cineplex));
        System.out.println("\n--------------------------------------------------");
        System.out.println("Enter Cineplex Name:");
        String cineplexName = InputController.getStringFromUser();
//	    Scanner string_scanner = new Scanner(System.in).useDelimiter("\n");
//        String cineplexName = string_scanner.next();
        
        Cineplex cineplex = cineplexCtrl.readByName(cineplexName);
        if (cineplex == null) {
            System.out.println("Cineplex does not exist!\n" +
                    "Returning to menu...");
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("\nCinemas in " + cineplex.getName() + ": \n");
        ArrayList<Cinema> cinemaList = cineplex.getCinemas();
        cinemaList.forEach(Cinema -> printCinemaCode(Cinema));

       
        System.out.println("\nEnter cinema code: ");
        String cinemaCode = InputController.getStringFromUser();
//        String cinemaCode = sc.next();
//        String cinemaCode = string_scanner.next();
        if (cinemaCtrl.readByAttribute(0, cinemaCode).isEmpty()) {
            System.out.println("Cinema does not exist!\n" +
                    "Returning to menu...");
            return;
        }

        System.out.println("\nEnter movie id: ");
//        int movie_id = InputController.getIntFromUser();
        int movie_id = sc.nextInt();
        System.out.println("");
        
        
        if (movieCtrl.readSpecificID(movie_id) == null) {
            System.out.println("Movie does not exist!\n" +
                    "Returning to menu...");
            return;
        }
        else
        {
        	System.out.println("You are adding session to the movie with title " + movieCtrl.readSpecificID(movie_id).getTitle());
        }

        System.out.println("Enter session date and time (format DD/MM/YYYY HH:mm) : ");
        LocalDateTime sessionDateTime = InputController.getDateTimeFromUser();
        Movie movie = movieCtrl.readSpecificID(movie_id);
        sessionCtrl.create(cinemaCode, movie, sessionDateTime);

        System.out.println("Session successfully created!");

    }

    /**
     * Update an existing session of user's choosing
     * Any invalid input will return user to main menu
     * 
     * @throws FileNotFoundException
     */
    public void updateMovieSession() throws FileNotFoundException {

        System.out.println("Updating Session...\n\n");

        System.out.println("Cineplex List:\n");
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

        System.out.println("Cinemas in" + cineplex.getName() + ":\n ");
        ArrayList<Cinema> cinemaList = cineplex.getCinemas();
        cinemaList.forEach(Cinema -> printCinema(Cinema));
        System.out.print("Enter session id: ");
        int session_id = InputController.getIntFromUser();
        if (sessionCtrl.readById(session_id) == null) {
            System.out.println("Session id does not exist!\n" +
                    "Returning to menu...");
            return;
        }

        System.out.println("Select attribute to update: \n" +
                "1. Movie\n" +
                "2. Date & Time \n");
        int choice = InputController.getIntFromUser();

        switch (choice) {
            case 1:
                System.out.println("Enter Movie id to be updated: ");
                int movie_id = InputController.getIntFromUser();
                if (movieCtrl.readSpecificID(movie_id) == null) {
                    System.out.println("Movie does not exist!\n" +
                            "Returning to menu... ");
                    return;
                }
                ;
                sessionCtrl.updateById(0, session_id, movieCtrl.readSpecificID(movie_id));
                break;

            case 2:
                System.out.println("Enter new Date & Time: ");
                LocalDateTime dateTime = InputController.getDateTimeFromUser();
                sessionCtrl.updateById(1, session_id, dateTime);
                break;

        }
        System.out.println("Session " + session_id + " successfully updated!");
    }

    /**
     * Remove an existing session of user's choosing
     * Any invalid input will return user to main menu
     */
    public void removeMovieSession() {

        System.out.println("Deleting Session...\n\n");
        System.out.println("\nCineplex List:");
        ArrayList<Cineplex> cineplexList = cineplexCtrl.read();
        if (cineplexList.isEmpty()) {
            System.out.println("There are no cineplexes registered!");
            return;
        }
        cineplexList.forEach(Cineplex -> printCineplex(Cineplex));
        System.out.println("Enter Cineplex Name:");
        String cineplexName = InputController.getStringFromUser();
        Cineplex cineplex = cineplexCtrl.readByName(cineplexName);
        if (cineplex == null) {
            System.out.println("Cineplex does not exist!\n" +
                    "Returning to menu...");
            return;
        }

        System.out.println("Cinemas in" + cineplex.getName() + ":\n ");
        ArrayList<Cinema> cinemaList = cineplex.getCinemas();
        cinemaList.forEach(Cinema -> printCinema(Cinema));
        System.out.print("Enter session id: ");
        int sessionId = InputController.getIntFromUser();
        if (sessionCtrl.readById(sessionId) == null) {
            System.out.println("Session id does not exist!\n" +
                    "Returning to menu...");
            return;
        }
        sessionCtrl.delete(sessionId);
        System.out.println("Session " + sessionId + " successfully deleted!");
    }

    /**
     * List all existing sessions
     */
    public void listMovieSessions() {
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

    /**
     * Print all sessions of a cinema
     * 
     * @param cinema Cinema to be printed
     */
    public void printCinema(Cinema cinema) {
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::\nCinema code: " + cinema.getCode());
        ArrayList<Session> sessionList = cinema.getSessions();
        sessionList.forEach(session -> printSession(session));
    }

    /**
     * Print a cinema's code
     * 
     * @param cinema Cinema to have its code printed
     */
    public void printCinemaCode(Cinema cinema) {
        System.out.println("Cinema code: " + cinema.getCode());
    }

    /**
     * Print a session - id, title, date
     * 
     * @param session Session to be printed
     */
    public void printSession(Session session) {
        System.out.print("\n\tSession id: " + session.getID() + "\n" +
                "\tMovie title: " + session.getMovie().getTitle() + "\n" +
                "\tDate: " + session.getStringSessionDateTime() + "\n");
    }

    /**
     * Print a cineplex's name
     * 
     * @param cineplex Cineplex to have its name printed
     */
    public void printCineplex(Cineplex cineplex) {
        System.out.println("Name: " + cineplex.getName());
    }
}
