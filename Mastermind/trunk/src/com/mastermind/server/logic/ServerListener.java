/**
 * Contains the logical classes that make up the <em>Mastermind</em> server.
 */
package com.mastermind.server.logic;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.mastermind.util.ConsoleUtilities;

/**
 * The <code>ServerListener</code> class listens for and handles client connections to the Mastermind server.
 *  
 * @author Pedram Balalzadeh, Phillipe Thibault, Alan Ly
 * @version 1.2
 */
public class ServerListener {
	
	/**
	 * Specifies the maximum number of <code>ServerThread</code>s that may be spawned and active at any time.
	 */
	private static final int MAX_SERVER_THREADS = 10;
        
    private int listeningPort;
    private boolean listenForConnection;
    private ThreadGroup threadGroup;
    
    /**
     * Create a <code>ServerListener</code> with a specified listening port to listen for and handle client connections to the Mastermind server.
     * @param listeningPort the port on which to listen to for client connections
     */
    public ServerListener(int listeningPort) {
		super();
		this.listeningPort = listeningPort;
		this.threadGroup = new ThreadGroup("mastermind-server");
    }
    
    /**
     * Gets the current listening port number of the server.
     * @return the port number to listen on
     */
    public int getListeningPort() {
    	return this.listeningPort;
    }
    
    /**
     * Sets the current listening port number of the server.
     * @param listeningPort the port number to listen on
     */
    public void setListeningPort(int listeningPort) {
    	this.listeningPort = listeningPort;
    }
    
    /**
     * Starts listening for connections from clients on the appropriate listening port.
     * 
     * When a client connects, a <code>Socket</code> reference is created for the client and a new <code>ServerThread</code> is created to handle the client.
     * @throws IOException thrown when a socket is unable to be opened
     */
    public void startListening() throws IOException {
    	
    	// Create sockets
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		
		// Opens a ServerSocket on the port specified by the 'listeningPort' variable
		try {
			
			// Initialise the server socket based on the assigned listening port
		    serverSocket = new ServerSocket(this.listeningPort);
		    
		    // Enable server to listen for connection
		    this.listenForConnection = true;
		    
			// Print out listening header for status
		    System.out.println(ConsoleUtilities.generateLogHeader() + "Server started on " + InetAddress.getLocalHost().getHostAddress() + ":" + serverSocket.getLocalPort() + "...");
		    
		    // Run loop while condition is true to listen for clients
		    while(this.listenForConnection) {
		    	// Check if the current number of threads is less than the allowed limit
		    	if(this.threadGroup.activeCount() < MAX_SERVER_THREADS) {			    	
			    	// Print status message
			    	System.out.println(ConsoleUtilities.generateLogHeader() + "Listening for client connections...");
			    	
					// Get a socket connection to the client
					clientSocket = serverSocket.accept();
					
					// Print status message for client connection
					System.out.println(ConsoleUtilities.generateLogHeader() + "Client connection from " + clientSocket.getInetAddress().getHostAddress());
					
					// Create a ServerThread and start it
					new Thread(this.threadGroup, new ServerThread(clientSocket)).start();
		    	} else
		    		// Sleep for 50 milliseconds before checking again
		    		Thread.sleep(50);
		    }
		    	    
		} catch (IOException ioe) {
			
		    // Throw new IOException with the appropriate message
			throw new IOException(ioe.getMessage());
		    
		} catch (InterruptedException e) {

			// Print out the stack trace if such an Exception occurs.
	        e.printStackTrace();
	        
	        // Exit from the server application
	        System.exit(1);
	        
        } finally {
		    
		    // Close the server socket if necessary
		    if(!serverSocket.isClosed())
		    	serverSocket.close();
		}
    }
    
    public void stopListening() {
    	this.listenForConnection = false;
    }
    
}
