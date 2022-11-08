package Controller;

import java.time.LocalDateTime;

import Controller.*;
import model_Classes.*;

import java.util.ArrayList;

public class SessionController {
    private MovieController movieCtrl = new MovieController();
    private CinemaController cinemaCtrl = new CinemaController();
    private CineplexeController cineplexCtrl = new CineplexeController();

    public void main() {
        boolean exit = false;
        while (!exit) {
            System.out.print("\n\nCreate/Update/Remove session: \n\n" +
                    "1. Create Movie Session\n" +
                    "2. Update Movie Session\n" +
                    "3. Remove Movie Session\n" +
                    "4. View Movie Sessions\n" +
                    "5. Return to Main Menu\n\n" +
                    "Select action: ");
            int option = InputController.getIntFromUser();
            switch (option) {
                case 1:
                    createSession();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    listSessions();
                    break;
                case 5:
                    exit = true;
                    break;
            }
        }
    }

    public void createSession() {

    }

    public void listSessions() {
        System.out.println("\nCineplex List:");
        ArrayList<Cineplex> cineplexList = cineplexCtrl.read();
        if (cineplexList.isEmpty()) {
            System.out.println("There are no cineplexes registered!");
            return;
        }
        for (int i = 0; i < cineplexList.size(); i++) {
            Cineplex temp = cineplexList.get(i);
            System.out.println("Name: " + temp.getName());

        }
        System.out.println("\nEnter Cineplex Name:");
        String cineplexName = InputController.getStringFromUser();
        Cineplex cineplex = cineplexCtrl.readByName(cineplexName);
        if (cineplex == null) {
            System.out.println("Cineplex does not exist!\n" +
                    "Returning to menu...");
            return;
        }

        ArrayList<Cinema> cinemaList = cineplex.getCinemas();
        for (int i = 0; i < cinemaList.size(); i++) {
            Cinema temp = cinemaList.get(i);
            printCinema(temp);

        }
    }

    public void printCinema(Cinema cinema) {
        System.out.println("\nCinema code: " + cinema.getCode());
        ArrayList<Session> sessionList = cinema.getSessions();
        for (int k = 0; k < sessionList.size(); k++) {
            Session tempses = sessionList.get(k);
            System.out.print("\n\tSession id: " + tempses.getID() + "\n" +
                    "\tMovie title: " + tempses.getMovie().getTitle() + "\n" +
                    "\tDate: " + tempses.getStringSessionDateTime() + "\n");
        }
    }
}
