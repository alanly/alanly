package ooCalendar;

public class Date {
    // Declare instance variables
    private int month;
    private int day;
    private int year;
	
    // Constructors
    public Date() {
		// Default constructor
		this.month = 1;
		this.day = 1;
		this.year = 1900;
	}

    public Date(int month, int day, int year) {
		// Secondary constructor
		this.month = month;
		this.day = day;
		this.year = year;
	}

    // Mutators
    public void setMonth(int month) {
    	this.month = month;
    }
    
    public void setDay(int day) {
    	this.day = day;
    }
    
    public void setYear(int year) {
    	this.year = year;
    }
    
    public void setDate(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
    // Accessors
    public int getMonth() {
		return month;
	}

    public int getDay() {
		return day;
	}

    public int getYear() {
		return year;
	}
	
    // Custom methods
    public int determineDayOfWeek() {
        // Variable Declarations
        int monthAdj;
        int yearAdj = year;
        int yearFrontDigits, yearRDig;
        int dayOfWeek;

        // Process Month Value
        if (month <= 2) {
            monthAdj = (month - 2) + 12;
            yearAdj = yearAdj - 1;
        } else {
            monthAdj = month - 2;
        }

        // Process Year Values
        yearFrontDigits = yearAdj / 100;
        yearRDig = yearAdj - (yearFrontDigits*100);

        // Calculate Day of the Week
        dayOfWeek = (((26 * monthAdj - 2)/10) + day + yearRDig + (yearRDig/4) + (yearFrontDigits/4) + (5*yearFrontDigits)) % 7;
        return dayOfWeek;
    } //end determineDayNumber method
    
    public String determineMonthName() {
    	// Variable declarations
    	String monthName;

    	// Determine the name of the month
    	switch (month) {
	    	case 1:
	    		monthName = "January";
	    		break;
	    	case 2:
	    		monthName = "February";
	    		break;
	    	case 3:
	    		monthName = "March";
	    		break;
	    	case 4:
	    		monthName = "April";
	    		break;
	    	case 5:
	    		monthName = "May";
	    		break;
	    	case 6:
	    		monthName = "June";
	    		break;
	    	case 7:
	    		monthName = "July";
	    		break;
	    	case 8:
	    		monthName = "August";
	    		break;
	    	case 9:
	    		monthName = "September";
	    		break;
	    	case 10:
	    		monthName = "October";
	    		break;
	    	case 11:
	    		monthName = "November";
	    		break;
	    	case 12:
	    		monthName = "December";
	    		break;
	    	default:
	    		monthName = "ERROR";
    	}

    	// Return the name of the month
    	return monthName;
    } // end determineMonthName

    public boolean isLeapYear() {
    	// Declare variables
    	boolean leapYear;

    	// Determine if the year is a leap year or not
    	if ((year % 4.0) != 0)
    		leapYear = false;
    	else
    		if ((year % 100.0) != 0)
    			leapYear = true;
    		else
    			if ((year % 400.0) != 0)
    				leapYear = false;
    			else
    				leapYear = true;

    	// Return results
    	return leapYear;
    } //end isLeapYear  
    
    public int determineDaysInMonth() {
    	// Declare variables
    	int numberOfDays = 0;

    	// Determine number of days
    	switch (month) {
	    	case 1:
	    	case 3:
	    	case 5:
	    	case 7:
	    	case 8:
	    	case 10:
	    	case 12:
	    		numberOfDays = 31;
	    		break;
	    	case 4:
	    	case 6:
	    	case 9:
	    	case 11:
	    		numberOfDays = 30;
	    		break;
	    	case 2:
	    		// February and leap year factor
	    		if (isLeapYear())
	    			numberOfDays = 29;
	    		else
	    			numberOfDays = 28;
	    		break;
	    	default:
	    		numberOfDays = 0;
    	}

    	// Return the number of days in month
    	return numberOfDays;
    } //end determineDaysInMonth

} // end class
