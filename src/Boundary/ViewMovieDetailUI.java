package Boundary;

import Controller.*;
import model_Classes.*;

public class ViewMovieDetailUI {

    private MovieController moviesCtrl;

    ViewMovieDetailUI() {
        this.moviesCtrl = new MovieController();
    }

    ViewMovieDetailUI(MovieController moviesCtrl) {
        this.moviesCtrl = moviesCtrl;
    }

    public void main() {
        System.out.println("Enter movie ID to view movie detail (-1 to exit): ");
        int id = InputController.getIntFromUser();
        if (id == -1) {
            return;
        }
        Movie movie = moviesCtrl.readSpecificID(id);
        if (movie == null) {
            System.out.println("Movie with this ID doesn't exist!\n");
        } else {
            String movieString = movie.toString();
            System.out.println(movieString);
        }
    }
}
