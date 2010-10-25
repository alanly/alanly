/**
 * 
 */
package com.mastermind.util;

/**
 * @author Alan Ly
 *
 */
public enum GameMessages {
	START_GAME(0x00000000),
	START_SUCCESS(0x000000FF),
	START_FAIL(0x000000F0),
	
	END_GAME(0xFFFFFFFF),
	END_SUCCESSS(0xFFFFFF00),
	END_FAIL(0xFFFFFF0F),
	
	VALIDATE(0x00000000),
	VALIDATE_RESULT(0xF0F0F0F0),
	VALIDATE_FAIL(0x0F0F0F0F);

	private final int message;
	
	GameMessages(int message) {
		this.message = message;
	}
	
	public int message() {
		return this.message;
	}
}
