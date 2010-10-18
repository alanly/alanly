/**
 * 
 */
package com.mastermind.server;

import java.io.IOException;

import com.mastermind.server.logic.ServerListener;

/**
 * @author Alan Ly
 * @version 1.0
 */
public class ServerApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
		try {
		    int serverPort = (args.length != 1 ? 30000 : Integer.parseInt(args[0]));
		    
		    ServerListener serverListener = new ServerListener(serverPort);
		    
		    serverListener.startListening();
		} catch (NumberFormatException nfe) {
		    System.err.println("Please provide a valid port number!");
		} catch (IOException ioe) {
		    System.err.println(ioe.getMessage());
		}
    }

}
