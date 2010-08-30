/**
 * 
 */
package ui;

import logic.Month;

/**
 * @author Alan Ly
 *
 */
public class Console {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Parse control input
		int month = Integer.parseInt(args[0]);
		int year = Integer.parseInt(args[1]);
		
		// Declare Month
		Month mon = new Month(month, year);
		
		// Print calendar headers
		System.out.println(mon.getNameOfMonth() + " " + mon.getYear());
		System.out.println("--------------------");
		System.out.println("Su Mo Tu We Th Fr Sa");
		
		// Print days of month
		int[][] calendar = mon.getDaysInMonth();
		
		for(int[] week : calendar) {
			for(int day : week)
				if(day == 0)
					System.out.print("   ");
				else
					System.out.print(String.format("%2s ", day));
			
			System.out.println();
		}
	}

}
