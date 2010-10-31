/**
 * 
 */
package com.mastermind.util.net;

/**
 * The <code>ByteProtocol</code> class contains the constants used to represent a client/server message for a <strong>Mastermind</strong> session.
 * @author Alan Ly
 * @version 1.1
 */
public class ByteProtocol {
	/**
	 * Specifies the header value in <strong>byte</strong> for a <em>Start Game</em> message.
	 */
	public static final byte START_GAME_HEADER = 0x00;
	/**
	 * Specifies the body value in <strong>byte</strong> for a <em>Start Game</em> request.
	 */
	public static final byte START_GAME_REQUEST = 0x00;
	/**
	 * Specifies the body value in <strong>byte</strong> for a <em>Start Game</em> successful response.
	 */
	public static final byte START_GAME_SUCCESS = 0x01;
	
	/**
	 * Specifies the header value in <strong>byte</strong> for an <em>End Game</em> message.
	 */
	public static final byte END_GAME_HEADER = 0x01;
	/**
	 * Specifies the body value in <strong>byte</strong> for a <em>End Game</em> request.
	 */
	public static final byte END_GAME_REQUEST = 0x00;
	/**
	 * Specifies the prefix value in <strong>byte</strong> for a <em>End Game</em> successful response.
	 * 
	 * This value is prefixed, as the most significant digit, onto each clue value in the message body.
	 */
	public static final byte END_GAME_ANSWER_PREFIX = 0x10;
	
	/**
	 * Specifies the header value in <strong>byte</strong> for a <em>Validate Guess</em> message.
	 */
	public static final byte VALIDATE_HEADER = 0x02;
	/**
	 * Specifies the prefix value in <strong>byte</strong> for a <em>Validate Guess</em> request.
	 * 
	 * This value is prefixed, as the most significant digit, onto each guess value in the message body.
	 */
	public static final byte VALIDATE_GUESS_PREFIX = 0x00;
	/**
	 * Specifies the prefix value in <strong>byte</strong> for a <em>Validate Guess</em> response.
	 * 
	 * This value is prefixed, as the most significant digit, onto each clue value in the message body.
	 */
	public static final byte VALIDATE_CLUE_PREFIX = 0x10;
}
