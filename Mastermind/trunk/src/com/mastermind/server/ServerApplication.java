/**
 * 
 */
package com.mastermind.server;

import com.mastermind.server.logic.ServerListener;
import com.mastermind.util.ConsoleUtilities;

/**
 * The <code>ServerApplication</code> begins a <code>ServerListener</code> and accepts a port number to listen to client connections on.
 * If a specific port number is not provided, the default port to listen on for the <strong>Mastermind</strong> server is port <code>50,000</code>.
 * 
 * @author Alan Ly
 * @version 1.0
 */
public class ServerApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
		try {
			
			// Determine socket port from arguments (default is 50,000)
		    int serverPort = (args.length != 1 ? 50000 : Integer.parseInt(args[0]));
		    
		    // Print statement when server starts
		    System.out.println("[" + ConsoleUtilities.generateTimeStamp() + "] Mastermind Server is starting...");
		    
		    // Create new ServerListener instance
		    ServerListener serverListener = new ServerListener(serverPort);
		    
		    // Listen for connections
		    serverListener.startListening();
		    
		    // Print statement when server ends
		    System.out.println("[" + ConsoleUtilities.generateTimeStamp() + "] Server has ended gracefully.");
		    
		} catch (NumberFormatException nfe) {
			
		    System.err.println("[" + ConsoleUtilities.generateTimeStamp() + "] ERROR - Invalid port number provided: " + args[0]);
		    System.err.println("[" + ConsoleUtilities.generateTimeStamp() + "] Server has quitted unexpectedly.");
		    
		} catch (Exception e) {
			
		    System.err.println("[" + ConsoleUtilities.generateTimeStamp() + "] ERROR - " + e.getMessage());
		    System.err.println("[" + ConsoleUtilities.generateTimeStamp() + "] Server has quitted unexpectedly.");
		    
		}
    }

}