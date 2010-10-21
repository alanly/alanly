/**
 * 
 */
package com.mastermind.server;

import java.io.IOException;

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
			
		    int serverPort = (args.length != 1 ? 50000 : Integer.parseInt(args[0]));
		    ServerListener serverListener = new ServerListener(serverPort);
		    serverListener.startListening();
		    System.err.println("[" + ConsoleUtilities.generateTimeStamp() + "] Server has ended gracefully.");
		    
		} catch (NumberFormatException nfe) {
			
		    System.err.println("[" + ConsoleUtilities.generateTimeStamp() + "] ERROR - Invalid port number provided: " + args[0]);
		    System.err.println("[" + ConsoleUtilities.generateTimeStamp() + "] Server has quitted unexpectedly.");
		    
		} catch (Exception e) {
			
		    System.err.println("[" + ConsoleUtilities.generateTimeStamp() + "] ERROR - " + e.getMessage());
		    System.err.println("[" + ConsoleUtilities.generateTimeStamp() + "] Server has quitted unexpectedly.");
		    
		}
    }

}