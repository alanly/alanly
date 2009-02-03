package ooCalendar;

import javax.swing.*;

public class Main {
	
    public static void main(String[] args) {
		// Variable declarations
		int month;
		int year;
		String inputString;
		
		// Input and parse user data
		inputString = JOptionPane.showInputDialog("Enter the desired month:");
		month = Integer.parseInt(inputString);
		
		inputString = JOptionPane.showInputDialog("Enter the desired year:");
		year = Integer.parseInt(inputString);
		
		// Create objects to process input and generate calendar
		Date calendarDate = new Date(month,1,year);
		MonthlyCalendar calendar = new MonthlyCalendar(calendarDate);
		
		Date calenderDateDefault = new Date();
		MonthlyCalendar calenderDefault = new MonthlyCalendar(calenderDateDefault);
		
		// Display monthly calendar
		calenderDefault.displayMonthlyCalendar();
		System.out.println("\n\n\t\t\t--\n");
		calendar.displayMonthlyCalendar();
		
		// Display project footer
		System.out.println("\n\t\t\t--\n");
    	System.out.println("\n..: Object Oriented Monthly Calendar :..");
    	System.out.println("Written by:\tSteven Cabral & Alan Ly");
    	System.out.println("Last Amended:\tDecember 9, 2008");		
	} // end main

} // end class
