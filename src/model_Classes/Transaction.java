package model_Classes;

public class Transaction implements Serializable {

	private String TID;
	private Movie movie;
	private Movie_Goer movieGoer;

	public Movie getMovie() {
		return this.movie;
	}

	/**
	 * 
	 * @param movie
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Movie_Goer getMovieGoer() {
		return this.movieGoer;
	}

	/**
	 * 
	 * @param movieGoer
	 */
	public void setMovieGoer(Movie_Goer movieGoer) {
		this.movieGoer = movieGoer;
	}

	public String getTID() {
		// TODO - implement Transaction.getTID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TID
	 */
	public Transaction(String TID) {
		// TODO - implement Transaction.Transaction
		throw new UnsupportedOperationException();
	}

}