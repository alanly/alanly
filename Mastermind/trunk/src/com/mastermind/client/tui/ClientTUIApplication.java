/**
 * 
 */
package com.mastermind.client.tui;

/**
 * @author Alan Ly
 *
 */
public class ClientTUIApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TextInterface tui = new TextInterface();
		
		try{
			tui.run();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

}
