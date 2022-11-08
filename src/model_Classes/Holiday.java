package model_Classes;

import java.time.LocalDate;

public class Holiday implements Serializable {

	private LocalDate holidayDate;
	
	
	// constructor
	public Holiday(LocalDate holidayDate)
	{
		this.holidayDate = holidayDate;
	}

	public LocalDate getHolidayDate() {
		return this.holidayDate;
	}

	/**
	 * 
	 * @param holidayDate
	 */
	public void setHolidayDate(LocalDate holidayDate) {
		this.holidayDate = holidayDate;
	}

}