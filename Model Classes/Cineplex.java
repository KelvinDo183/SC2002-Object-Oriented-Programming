public class Cineplex implements Serializable {

	private String name;
	private ArrayList<Cinema> cinemas;

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Cinema> getCinemas() {
		return this.cinemas;
	}

	/**
	 * 
	 * @param cinemas
	 */
	public void setCinemas(ArrayList<Cinema> cinemas) {
		this.cinemas = cinemas;
	}

	public String getTID() {
		// TODO - implement Cineplex.getTID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TID
	 */
	public void setTID(String TID) {
		// TODO - implement Cineplex.setTID
		throw new UnsupportedOperationException();
	}

	public String toString() {
		// TODO - implement Cineplex.toString
		throw new UnsupportedOperationException();
	}

}