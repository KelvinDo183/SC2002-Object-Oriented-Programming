package model_Classes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SeatingPlan implements Serializable {

	private Seat [][] layout;
	private int row;
	private int column;
	
	public SeatingPlan(int row, int column) {
		this.row = row;
		this.column = column;
		layout = new Seat [row][column];
		for (int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++)
				layout[i][j] = new Seat(i*this.row+j);
		}
	}

	public void printLayout( ) {
		System.out.print("\n[ X] denotes an occupied seat; Number from 0 to 99 denotes a seat available for booking.\n");
		for (int i = 0; i < row; i++) {
			if (i == 0)
			{
				System.out.print("Seat # " + (i * column) + " to  " + ((i + 1) * column - 1) + ":\t");
			}
			else
			{
				System.out.print("Seat #" + (i * column) + " to " + ((i + 1) * column - 1) + ":\t");	
			}
			for (int j = 0; j < column; j++) {
				if(layout[i][j].isOccupied()) System.out.print("[ X] ");
				else 
				{
					if (i == 0)
					{
						System.out.print("[ " + (i * 10 + j) +"] ");
					}
					else
					{
						System.out.print("[" + (i * 10 + j) +"] ");
					}
				}
				if(j == column/2 - 1) System.out.print("\t");
			}
			System.out.print("\n");
		}
		System.out.println("");
	}

	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getNumSeats() {
		return row*column;
	}
	
	public void assignSeats(int id) {
		int i = id/row;
		layout[i][id - row*i].assign();
	}
	
	public void unassignSeats(int id) {
		int i = id/row;
		layout[i][id - row*i].unassign();
	}

	public boolean checkSeats(int id) {
		int i = id/row;
		return layout[i][id - row*i].isOccupied();
	}
}