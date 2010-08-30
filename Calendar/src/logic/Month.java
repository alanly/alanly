/**
 * 
 */
package logic;

import java.util.Calendar;

/**
 * A Month holds all the appropriate days.
 * 
 * @author Alan Ly
 * @version 2010-08-25.0
 */
public class Month {
	private Calendar calendarObj;
	
	/**
	 * Constructs a Month with the current date.
	 */
	public Month() {
		super();
		calendarObj = Calendar.getInstance();
		calendarObj.set(Calendar.DATE, 1);
	}
	
	/**
	 * Constructs a Month with the specified month and year.
	 * 
	 * @param month the month to use
	 * @param year the year to use
	 */
	public Month(int month, int year) {
		super();
		calendarObj = Calendar.getInstance();
		calendarObj.set(year, month, 1);
	}
	
	/**
	 * Sets the month to use.
	 * 
	 * @param month the month to use
	 */
	public void setMonth(int month) {
		calendarObj.set(Calendar.MONTH, month);
	}
	
	/**
	 * Sets the year to use for this month.
	 * 
	 * @param year the year to use
	 */
	public void setYear(int year) {
		calendarObj.set(Calendar.YEAR, year);
	}
	
	/**
	 * Gets the day of the week this month starts on. Sunday is a 0 and Saturday is a 6.
	 * 
	 * @return the value representing the day of the week this month starts on
	 */
	public int getBeginDayOfMonth() {
		return calendarObj.get(Calendar.DAY_OF_WEEK) - 1;
	}
	
	/**
	 * Gets the user-readable name of the month.
	 * 
	 * @return the name of this month
	 */
	public String getNameOfMonth() {
		switch(calendarObj.get(Calendar.MONTH)) {
			case 0:
				return "January";
			case 1:
				return "February";
			case 2:
				return "March";
			case 3:
				return "April";
			case 4:
				return "May";
			case 5:
				return "June";
			case 6:
				return "July";
			case 7:
				return "August";
			case 8:
				return "September";
			case 9:
				return "October";
			case 10:
				return "November";
			case 11:
				return "December";
			default:
				return null;
		}
	}
	
	/**
	 * Gets the year of this month.
	 * 
	 * @return the year of this calendar
	 */
	public int getYear() {
		return calendarObj.get(Calendar.YEAR);
	}
	
	/**
	 * Gets the number of days in this month.
	 * 
	 * @return the number of days in this month
	 */
	public int getNumberOfDaysInMonth() {		
		return calendarObj.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Gets the array containing the days in the calendar. A value of <code>0</code> in any subscript represents a blank.
	 * 
	 * @return the array containing the calendar
	 */
	public int[][] getDaysInMonth() {
		int monthBeginDayOfWeek = getBeginDayOfMonth();
		int numberOfDaysInMonth = getNumberOfDaysInMonth();
		int[][] month = new int[(monthBeginDayOfWeek + numberOfDaysInMonth) / 7 + 1][7];
		int date = 1;
		
		for(int week = 0; week < month.length; week++)
			for(int day = 0; day < month[week].length; day++)
				if(week == 0 && day < monthBeginDayOfWeek || date > numberOfDaysInMonth)
					month[week][day] = 0;
				else
					month[week][day] = date++;
		
		return month;
	}
}
