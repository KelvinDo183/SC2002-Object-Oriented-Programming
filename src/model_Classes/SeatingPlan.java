package model_Classes;

public class SeatingPlan implements Serializable {

	private Seat[][] singlesLayout;
	private CoupleSeat[][] couplesLayout;
	private int totalRow;
	private int totalColumn;

	/**
	 * 
	 * @param totalRow
	 * @param totalColumn
	 * @param coupleRows
	 */
	public SeatingPlan(int totalRow, int totalColumn, int coupleRows) {
		// TODO - implement SeatingPlan.SeatingPlan
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param row
	 * @param column
	 */
	public int calculateID(int row, int column) {
		// TODO - implement SeatingPlan.calculateID
		throw new UnsupportedOperationException();
	}

	public int getTotalRow() {
		return this.totalRow;
	}

	public int getTotalColumn() {
		return this.totalColumn;
	}

	public int getTotalSeats() {
		// TODO - implement SeatingPlan.getTotalSeats
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void assignSeats(int id) {
		// TODO - implement SeatingPlan.assignSeats
		throw new UnsupportedOperationException();
	}

	public void unassignSeats() {
		// TODO - implement SeatingPlan.unassignSeats
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public boolean checkSeats(int id) {
		// TODO - implement SeatingPlan.checkSeats
		throw new UnsupportedOperationException();
	}

}