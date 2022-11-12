package Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

//import BusinessLayer.SessionsLayer;

import static Controller.CinemaController.SESSIONS;
import static Controller.CinemaController.CODE;

import model_Classes.*;
import exceptions.SessionException;

public class SessionController {

    /**
     * The Cineplex Controller that this controller will reference
     */
    private CinemaController cinemaCtrl = new CinemaController();

    /**
     * The file name of the database file that this controller will access
     */
    public String FILENAME;

    /**
     * Declaring constant for better readability and easier referencing to attribute
     */
    public final static int MOVIE = 0;
    public final static int SESSION_DATETIME = 1;
    public final static int SEATS_AVAILABILITY = 2;
    public final static int ID = 3;

    /**
     * Default constructor
     */
    public SessionController() {
        this.cinemaCtrl = new CinemaController();
        this.FILENAME = cinemaCtrl.FILENAME;
    }

    /**
     * Parameterized constructor with user-defined Cinema Controller
     * 
     * @param CinemaCtrl Non-default Cinema Controller to be referenced instead
     */
    public SessionController(CinemaController cinemaCtrl) {
        this.cinemaCtrl = cinemaCtrl;
        this.FILENAME = cinemaCtrl.FILENAME;
    }

    /**
     * Gets the Cinema Controller that this controller is referencing
     * 
     * @return CinemasController This controller's Cinema Controller
     */
    public CinemaController getCinemaController() {
        return this.cinemaCtrl;
    }

    /**
     * Change the Cinema Controller that this controller is referencing
     * 
     * @param CinemaCtrl This controller's Cinema Controller
     */
    public void setCinemaController(CinemaController cinemaCtrl) {
        this.cinemaCtrl = cinemaCtrl;
    }

    /**
     * CREATE a new Session and add it into the database file
     * Attributes are validated before creation
     * If attributes are not allowed, throw error and do nothing
     * If Database file exist, existing records are read and new Session object is
     * aopended before saving
     * If Database file does not exist, Session object will be written to a new file
     * and saved
     * 
     * @param cinemaCode      This Sessions's cinema code
     * @param movie           This Sessions's movie
     * @param sessionDateTime This Sessions's date and time
     */
    public void create(String cinemaCode, Movie movie, LocalDateTime sessionDateTime) {
        if (SessionException.isSessionValid(cinemaCode, movie, sessionDateTime)) {
            SeatingPlan seatingPlan = cinemaCtrl.readByAttribute(CODE, cinemaCode).get(0).getSeatingPlan();
            Session session = new Session(movie, getLastId() + 1, sessionDateTime, seatingPlan);
            ArrayList<Cinema> allData = cinemaCtrl.read();
            System.out.printf("\nCinema Controller Array List = ", allData.toString());

            System.out.println("Seating plan (Col) = " + seatingPlan.getColumn());
            System.out.println("Seating plan (Row) = " + seatingPlan.getRow());
            System.out.println("Cinema Code = " + cinemaCode.toString());
            System.out.println("Movie = " + movie.getTitle());
            System.out.println("Session Date Time = " + sessionDateTime.toString());
            System.out.println("Session Last ID = " + getLastId());
            int lastID_default = 100;
            if (getLastId() != -1)
                lastID_default = getLastId();
            // Session session = new Session(movie, lastID_default + 1, sessionDateTime,
            // seatingPlan);
            // Session session = new Session(movie, getLastId() + 1, sessionDateTime,
            // seatingPlan);
            // Session session = new Session(movie, 101, sessionDateTime, seatingPlan);
            System.out.println("Created Session ID = " + session.getID());
            System.out.println("Created Session MOVIE ID = " + session.getMovie().getID());
            System.out.println("Created Session Start Time = " + session.getStringSessionDateTime());

            // ArrayList<Cinema> allData = this.cinemaCtrl.read();
            // System.out.printf("\nCinema Controller Array List = ", allData.toString());

            ArrayList<Session> sessions = new ArrayList<Session>();
            // System.out.printf("\nSessions Array List = ", sessions.toString());
            for (int i = 0; i < allData.size(); i++) {
                Cinema cinema_i = allData.get(i);
                if (cinema_i.getCode().equals(cinemaCode)) {
                    sessions = cinema_i.getSessions();
                    sessions.add(session);
                    cinema_i.setSessions(sessions);
                    this.cinemaCtrl.updateByAttribute(SESSIONS, cinemaCode, sessions);
                    sessions.clear();
                    break;
                }
            }
        } else {
            // do nothing
        }
    }

    /**
     * READ and return every Cinema in the Database file
     * 
     * @return Model.{@link Session} Return list of Sessions if found, else empty
     *         list
     */
    public ArrayList<Session> read() {
        ArrayList<Cinema> allCinemas = this.cinemaCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        Cinema c = null;
        for (int i = 0; i < allCinemas.size(); i++) {
            c = allCinemas.get(i);
            c.getSessions().forEach(n -> allSessions.add(n));
        }
        return allSessions;
    };

    /**
     * READ and return every Session based on a certain value of a given attribute
     * in the Database file
     * 
     * @param col           Given attribute to be check for (based on constant as
     *                      defined)
     * @param valueToSearch Value of given attribute to search for
     * @return Model.{@link Session} Return list of Sessions if any, else empty list
     */
    public ArrayList<Session> readByAttributes(int col, Object valueToSearch) {
        ArrayList<Session> allData = read();
        ArrayList<Session> returnData = new ArrayList<Session>();
        Session s = null;

        for (int i = 0; i < allData.size(); i++) {
            s = allData.get(i);
            switch (col) {
                case MOVIE: // Movies going to be compared by movie ID
                    if (s.getMovie().getID() == (int) valueToSearch)
                        returnData.add(s);
                    break;
                case SESSION_DATETIME:
                    if (s.getSessionDateTime().equals((LocalDateTime) valueToSearch))
                        returnData.add(s);
                    break;
                default:
                    break;
            }
        }
        return returnData;
    };

    /**
     * READ and return every Session of a cinema on a speficic date in the Database
     * file
     * 
     * @param cinemaCode  Cinema's code to be check for
     * @param sessionDate Value of date to search for
     * @return Model.{@link Session} Return list of Sessions if any, else empty list
     */
    @SuppressWarnings("static-access")
    public ArrayList<Session> readByAttributes(String cinemaCode, LocalDate sessionDate) {
        ArrayList<Cinema> cinemas = this.cinemaCtrl.readByAttribute(cinemaCtrl.CODE, cinemaCode);
        if (cinemas.isEmpty()) {
            return null;
        } else {
            ArrayList<Session> allDate = cinemas.get(0).getSessions();
            ArrayList<Session> returnData = new ArrayList<Session>();
            Session s = null;
            for (int i = 0; i < allDate.size(); i++) {
                s = allDate.get(i);
                if (s.getSessionDateTime().toLocalDate().equals(sessionDate))
                    returnData.add(s);
            }
            return returnData;
        }
    }

    /**
     * READ and return a session of a given cinema on a specific date and time in
     * the Database file
     * 
     * @param cinemaCode      Cinema's code to be check for
     * @param sessionDateTime Value of date and time to search for
     * @return Session Return Session if found, else null object
     */
    @SuppressWarnings("static-access")
    public Session readBySession(String cinemaCode, LocalDateTime sessionDateTime) {
        ArrayList<Cinema> cinemas = this.cinemaCtrl.readByAttribute(cinemaCtrl.CODE, cinemaCode);
        Cinema c = null;
        if (cinemas.isEmpty())
            return null;
        else {
            c = cinemas.get(0);
        }

        ArrayList<Session> allData = c.getSessions();
        Session s = null;

        for (int i = 0; i < allData.size(); i++) {
            s = allData.get(i);
            if (s.getSessionDateTime().equals(sessionDateTime))
                return s;
        }
        return null;
    };

    /**
     * READ and return a session of a given session ID in the Database file
     * 
     * @param id Session's ID to check for
     * @return Session Return Session if found, else null object
     */
    public Session readById(int id) {
        ArrayList<Session> allData = read();
        Session s = null;

        for (int i = 0; i < allData.size(); i++) {
            s = allData.get(i);
            if (s.getID() == id)
                return s;
        }
        return s;
    };

    /**
     * UPDATE a Session's with new value based on a given attribute and cinema code
     * in Database file
     * 
     * @param col        Given attribute to be check for (based on constant as
     *                   defined)
     * @param cinemaCode Code of cinema to be updated
     * @param oldValue   Value of given attribute to search for
     * @param newValue   New value of session's attribute
     */
    @SuppressWarnings("static-access")
    public void updateByAttribute(int col, String cinemaCode, Object oldValue, Object newValue) {
        ArrayList<Cinema> allCinemas = this.cinemaCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();

        // loop through all cinemas to find one with matching cinema code
        for (int i = 0; i < allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            if (cinema_i.getCode().equals(cinemaCode)) {

                // loop through all sessions of the matched cinema and update if necessary
                allSessions = cinema_i.getSessions();
                returnSessions.clear(); // ensure it started without existing session

                for (int j = 0; j < allSessions.size(); j++) {
                    Session s = allSessions.get(j);

                    switch (col) {

                        case MOVIE: // Movies going to be compared by movie ID
                            if (s.getMovie().getID() == (int) oldValue) {
                                if (SessionException.isSessionValid(cinemaCode, (Movie) newValue,
                                        s.getSessionDateTime()))
                                    s.setMovie((Movie) newValue);
                            }
                            break;

                        case SESSION_DATETIME:
                            if (s.getSessionDateTime().equals((LocalDateTime) oldValue)) {
                                if (SessionException.isSessionValid(cinemaCode, s.getMovie(), (LocalDateTime) newValue))
                                    s.setSessionDateTime((LocalDateTime) newValue);
                            }
                            break;

                        case ID:
                            if (s.getID() == (int) oldValue)
                                s.setID((int) newValue);
                            break;

                        default:
                            break;

                    }
                    returnSessions.add(s);
                }

                // update DB and break (stop searching other cinema if already found one with
                // matching code)
                this.cinemaCtrl.updateByAttribute(cinemaCtrl.SESSIONS, cinemaCode, returnSessions);
                break;
            }
        }
    }

    /**
     * UPDATE a Session of a given session ID based on given attribute in Database
     * file
     * 
     * @param col      Given attribute to be check for (based on constant as
     *                 defined)
     * @param id       Session's ID to check for
     * @param newValue New value of session's attribute
     */
    @SuppressWarnings("static-access")
    public void updateById(int col, int id, Object newValue) {
        ArrayList<Cinema> allCinemas = this.cinemaCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();
        Session s = null;

        for (int i = 0; i < allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            allSessions = cinema_i.getSessions();
            returnSessions.clear();// ensure it started without existing session

            for (int j = 0; j < allSessions.size(); j++) {
                s = allSessions.get(j);
                if (s.getID() == id)

                    switch (col) {

                        case MOVIE:
                            if (SessionException.isSessionValid(cinema_i.getCode(), (Movie) newValue,
                                    s.getSessionDateTime()))
                                s.setMovie((Movie) newValue);
                            break;

                        case SESSION_DATETIME:
                            if (SessionException.isSessionValid(cinema_i.getCode(), s.getMovie(),
                                    (LocalDateTime) newValue))
                                s.setSessionDateTime((LocalDateTime) newValue);
                            break;

                        case SEATS_AVAILABILITY:
                            s.setSeatsAvailability((SeatingPlan) newValue);
                            break;

                        default:
                            break;

                    }
                returnSessions.add(s);
            }

            // update DB and break (stop searching other cinema if already found one with
            // matching code)
            this.cinemaCtrl.updateByAttribute(cinemaCtrl.SESSIONS, cinema_i.getCode(), returnSessions);
        }
    }

    /**
     * UPDATE a Session's seats availability of a given session ID in Database file
     * 
     * @param id                  Session's ID to check for
     * @param newSeatsAvailabiity New value of session's seats availability
     */
    @SuppressWarnings("static-access")
    public void updateSeatsAvailability(int id, SeatingPlan newSeatsAvailabiity) {
        ArrayList<Cinema> allCinemas = this.cinemaCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();
        Session s = null;

        for (int i = 0; i < allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            allSessions = cinema_i.getSessions();
            returnSessions.clear(); // ensure it started without existing session
            for (int j = 0; j < allSessions.size(); j++) {
                s = allSessions.get(j);
                if (s.getID() == id) {
                    s.setSeatsAvailability(newSeatsAvailabiity);
                }
                returnSessions.add(s);
            }

            // update DB and break (stop searching other cinema if already found one with
            // matching code)
            this.cinemaCtrl.updateByAttribute(cinemaCtrl.SESSIONS, cinema_i.getCode(), returnSessions);
        }
    }

    /**
     * UPDATE a Movie's attribute in Session with matching movieID Database file
     * 
     * @param col      Attribute of movie to update
     * @param movieID  ID of Movie to search for
     * @param newValue New value of Movie's attribute
     */
    @SuppressWarnings({ "static-access", "unchecked" })
    public void updateByMovie(int col, int movieID, Object newValue) {
        ArrayList<Cinema> allCinemas = this.cinemaCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();
        Session s = null;

        for (int i = 0; i < allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            allSessions = cinema_i.getSessions();
            returnSessions.clear(); // ensure it started without existing session
            for (int j = 0; j < allSessions.size(); j++) {
                s = allSessions.get(j);
                if (s.getMovie().getID() == movieID) {

                    switch (col) {

                        case (MovieController.ID):
                            s.getMovie().setID((int) newValue);
                            break;

                        case (MovieController.TITLE):
                            s.getMovie().setTitle((String) newValue);
                            break;

                        case (MovieController.TYPE):
                            s.getMovie().setType((MovieType) newValue);
                            break;

                        case (MovieController.DESCRIPTION):
                            s.getMovie().setDescription((String) newValue);
                            break;

                        case (MovieController.RATING):
                            s.getMovie().setRating((String) newValue);
                            break;

                        case (MovieController.DURATION):
                            s.getMovie().setDuration((double) newValue);
                            break;

                        case (MovieController.RELEASE_DATE):
                            s.getMovie().setReleaseDate((LocalDate) newValue);
                            break;

                        case (MovieController.END_DATE):
                            s.getMovie().setEndDate((LocalDate) newValue);
                            break;

                        case (MovieController.DIRECTOR_NAME):
                            s.getMovie().setDirectorName((String) newValue);
                            break;

                        case (MovieController.CAST_MEMBERS):
                            s.getMovie().setCastMembers((ArrayList<String>) newValue);
                            break;

                        case (MovieController.REVIEWS):
                            s.getMovie().setReviews((ArrayList<Review>) newValue);
                            break;

                        default:
                            break;
                    }
                }
                returnSessions.add(s);
            }

            // update DB and break (stop searching other cinema if already found one with
            // matching code)
            this.cinemaCtrl.updateByAttribute(cinemaCtrl.SESSIONS, cinema_i.getCode(), returnSessions);
        }
    }

    /**
     * Delete a Session in the Database file, based on the cinema code and datetime
     * attribute passed
     * 
     * @param cinemaCode      Code of cinema which will be deleted
     * @param sessionDateTime Date and time of session which will be deleted
     */
    @SuppressWarnings("static-access")
    public void delete(String cinemaCode, LocalDateTime sessionDateTime) {
        ArrayList<Cinema> allCinemas = this.cinemaCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();

        // loop through all cinemas to find one with matching cinema code
        for (int i = 0; i < allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            if (cinema_i.getCode().equals(cinemaCode)) {

                // loop through all sessions of the matched cinema and update if necessary
                allSessions = cinema_i.getSessions();
                returnSessions.clear(); // ensure it started without existing session
                for (int j = 0; j < allSessions.size(); j++) {
                    Session s = allSessions.get(j);
                    if (!(s.getSessionDateTime().equals(sessionDateTime)))
                        returnSessions.add(s);
                }

                // update DB and break (stop searching other cinema if already found one with
                // matching code)
                this.cinemaCtrl.updateByAttribute(cinemaCtrl.SESSIONS, cinemaCode, returnSessions);
                break;
            }
        }
    }

    /**
     * Delete a Session in the Database file, based on the session ID
     * 
     * @param id ID of session which will be deleted
     */
    @SuppressWarnings("static-access")
    public void delete(int id) {
        ArrayList<Cinema> allCinemas = this.cinemaCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();

        for (int i = 0; i < allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            allSessions = cinema_i.getSessions();
            returnSessions.clear(); // ensure it started without existing session
            for (int j = 0; j < allSessions.size(); j++) {
                Session s = allSessions.get(j);
                if (!(s.getID() == id))
                    returnSessions.add(s);
            }
            this.cinemaCtrl.updateByAttribute(cinemaCtrl.SESSIONS, cinema_i.getCode(), returnSessions);
        }
    }

    /**
     * Delete Sessions in the Database file with a specific MovieID
     * 
     * @param movieID Sessions with this MovieID will be deleted
     */
    @SuppressWarnings("static-access")
    public void deleteByMovie(int movieID) {
        ArrayList<Cinema> allCinemas = this.cinemaCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();
        Session s = null;

        for (int i = 0; i < allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            allSessions = cinema_i.getSessions();
            returnSessions.clear(); // ensure it started without existing session
            for (int j = 0; j < allSessions.size(); j++) {
                s = allSessions.get(j);
                if (!(s.getMovie().getID() == movieID))
                    returnSessions.add(s);
            }
            this.cinemaCtrl.updateByAttribute(cinemaCtrl.SESSIONS, cinema_i.getCode(), returnSessions);
        }
    }

    /**
     * Return the ID of the last Movie in the Database field
     * 
     * @return int ID of last Movie in the Database
     */
    public int getLastId() {
        int lastId = -1;
        int sessionId;
        ArrayList<Session> allData = read();
        System.out.printf("\nallSession array list = ", allData.toString());
        System.out.printf("\nallSession array list length = ", allData.size());
        for (int i = 0; i < allData.size(); i++) {
            sessionId = allData.get(i).getID();
            System.out.printf("\nThe sessionID = ", sessionId);
            if (sessionId > lastId)
                lastId = sessionId;
        }
        return lastId;
    }

    /**
     * Mark a seat of a session as occupied, based on the seat id and session ID
     * 
     * @param seatingPlan Seating Plan of the session
     * @param seatId      Seat ID to be marked as occupied
     * @param sessionId   Session ID in which the seat will be marked occupied
     * @return boolean Return true if seat has been marked occupied
     *         Return false if seat was already marked occupied prior to this
     */
    public boolean assignSeat(SeatingPlan seatingPlan, int seatId, int sessionId) {
        if (!seatingPlan.checkSeats(seatId)) {
            seatingPlan.assignSeats(seatId);
            this.updateSeatsAvailability(sessionId, seatingPlan);
            return true;
        } else {
            System.out.println("Seat already taken!");
            return false;
        }
    }
}
