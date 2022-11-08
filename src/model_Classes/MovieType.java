package model_Classes;

public enum MovieType implements PriceChanger {
	TWO_D,
	THREE_D,
	BLOCKBUSTER;

	private String text;

	public String toString() {
		
		String toReturn = "";
		toReturn += "Type: " + text + "\n";
		
		return toReturn;
		
	}

}