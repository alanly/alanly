package ooCalendar;

public class MonthlyCalendar {
    private Date theDate;
	
    // Constructors
    public MonthlyCalendar() {
		theDate = new Date();
	}

    public MonthlyCalendar(Date dateObject) {
		theDate = dateObject;
	}
	
    // Accessors
    public int getMonth() {
    	return theDate.getMonth();
    }
    
    public int getDay() {
    	return theDate.getDay();
    }
    
    public int getYear() {
    	return theDate.getYear();
    }
	
    // Custom method
    public void displayMonthlyCalendar() {
    	// Variable declarations
    	int daysInMonth;
    	int monthStartDay;
    	int dayCount = 1;
    	int printCount;
    	String nameOfMonth;
    	String day;

    	// Parse date to determine number of days
    	daysInMonth = theDate.determineDaysInMonth();

    	// Find the weekday the first of the month lands on
    	monthStartDay = theDate.determineDayOfWeek();
    	printCount = monthStartDay;

    	// Find the name of the month
    	nameOfMonth = theDate.determineMonthName();

    	// Print calendar header
    	System.out.println ("\t\t.: " + nameOfMonth + " " + theDate.getYear() + " :.\n");
    	System.out.println ("Sun\tMon\tTue\tWed\tThu\tFri\tSat");

        // Print spacing for beginning of month
    	while (monthStartDay != 0) {
    		System.out.print ("\t");
    		monthStartDay = monthStartDay - 1;
    	}

        // Print days of the month
    	while (daysInMonth > 0) {

    		// Determine which days indicate end of week
    		if (printCount != 6) {
    			day = " " + dayCount + "\t";
    			printCount = printCount + 1;
    		} else {
    			day = " " + dayCount + "\n";
    			printCount = 0;
    		}

    		// Determine spacing for single digit days
    		if (dayCount < 10) {
    			day = " " + day;
    		}

    		// Print the day
    		System.out.print (day);
    		dayCount = dayCount + 1;
    		daysInMonth = daysInMonth - 1;
    	}
    } // end displayMonthlyCalendar	
} // end class
