/**
 * Contains general utilities that can be used between the <em>Mastermind</em> client and server.
 */
package com.mastermind.util;

/**
 * The <code>Colours</code> enum class contains the appropriate constant values for every colour used in the Mastermind game.
 * 
 * @author Pedram Balalzadeh, Phillipe Thibault, Alan Ly
 * @version 1.0
 */
public enum Colour {
	RED(1),
	GREEN(2),
	BLUE(3),
	YELLOW(4),
	PURPLE(5),
	AQUA(6),
	ORANGE(7),
	PINK(8);
	
	private final int colour;
	
	Colour(int colour) {
		this.colour = colour;
	}
	
	public int toInt() {
		return this.colour;
	}
}
