/**
 * Contains the logical classes that make up the <em>Mastermind</em> server.
 */
package com.mastermind.server.logic;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import com.mastermind.util.ConsoleUtilities;
import com.mastermind.util.GameConstants;

/**
 * The <strong>ServerThread</strong> is spawned when a client connects to the Mastermind server listener and it is responsible for handling that particular client connection.
 * 
 * @author Pedram Balalzadeh, Phillipe Thibault, Alan Ly
 * @version 1.3
 */
public class ServerThread {
    
    private Socket clientSocket;
    private byte[] messageBuffer;
    private GameLogic game;
    
    /**
     * Creates a <code>ServerThread</code> with a specified <code>Socket</code> connection to the client. 
     * An <code>IOException</code> may be thrown if the appropriate IO streams cannot be obtained from the client socket connection.
     * 
     * @param clientSocket the <code>Socket</code> connection to the client
     * @throws IOException thrown when IO streams cannot be retrieved from client
     */
    public ServerThread(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
		
		// Initialise message buffer
		this.messageBuffer = new byte[GameConstants.BUFFER_LENGTH];
    }
    
    /**
     * Gets the client <code>Socket</code> for the related instance of <code>ServerThread</code>.
     * 
     * @return the client socket
     */
    public Socket getClientSocket() {
    	return this.clientSocket;
    }
    
    /**
     * Sets the appropriate client <code>Socket</code> for the <code>ServerThread</code>.
     * 
     * @param clientSocket a client socket
     */
    public void setClientSocket(Socket clientSocket) {
    	this.clientSocket = clientSocket;
    }
    
    /**
     * Starts the <code>ServerThread</code> and handles the assigned client connection.
     */
    public void startThread() {
    	try {
		
			System.out.println(ConsoleUtilities.generateLogHeader() + "Handling client over " + this.clientSocket.getInetAddress().getHostAddress() + ":" + this.clientSocket.getPort() + "...");
			
			// Create GameLogic and start game
			this.game = new GameLogic(this.clientSocket, this.messageBuffer);
			this.game.start();
	
			System.out.println(ConsoleUtilities.generateLogHeader() + "Client from " + this.clientSocket.getInetAddress().getHostAddress() + ":" + this.clientSocket.getPort() + " disconnected");
			
    	} catch (SocketException se) {
    		
    		System.err.println(ConsoleUtilities.generateLogHeader() + "Client from " + this.clientSocket.getInetAddress().getHostAddress() + ":" + this.clientSocket.getPort() + " disconnected unexepctedly and uncleanly!");
    		
    	}
    }
    
}
