/**
 * 
 */
package com.mastermind.server.logic;

import java.io.IOException;
import java.net.Socket;

import com.mastermind.util.ConsoleUtilities;
import com.mastermind.util.net.ByteComm;

/**
 * @author Alan Ly
 * @version 1.0
 */
public class ServerThread {
    
    /**
     * Set the size of the message buffer for the byte array that holds messages sent back and fourth
     */
    private static final int MESSAGE_BUFFER_SIZE = 4;
    
    private Socket clientSocket;
    private byte[] messageBuffer;
    
    /**
     * Creates a <code>ServerThread</code> with a specified <code>Socket</code> connection to the client. 
     * An <code>IOException</code> may be thrown if the appropriate IO streams cannot be obtained from the client socket connection.
     * @param clientSocket the <code>Socket</code> connection to the client
     * @throws IOException thrown when IO streams cannot be retrieved from client
     */
    public ServerThread(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
		
		// Initialise message buffer
		this.messageBuffer = new byte[MESSAGE_BUFFER_SIZE];
    }
    
    /**
     * Gets the client <code>Socket</code> for the related instance of <code>ServerThread</code>.
     * @return the client socket
     */
    public Socket getClientSocket() {
    	return this.clientSocket;
    }
    
    /**
     * Sets the appropriate client <code>Socket</code> for the <code>ServerThread</code>.
     * @param clientSocket a client socket
     */
    public void setClientSocket(Socket clientSocket) {
    	this.clientSocket = clientSocket;
    }
    
    /**
     * Starts the <code>ServerThread</code> and handles the assigned client connection.
     * An <code>IOException</code> is thrown when the appropriate IO stream cannot be opened.
     * @throws IOException thrown when IO stream cannot be opened
     */
    public void startThread() throws IOException {
		int receiveSize = 0;
		
		System.out.println("[" + ConsoleUtilities.generateTimeStamp() + "] Handling client from " + this.clientSocket.getInetAddress().getHostName());
		
		try {
			
		    while((receiveSize = ByteComm.receive(clientSocket, messageBuffer)) != -1) {
		        // TODO implement Mastermind Game Logic instance code here
		    }
		    
		} catch (IOException ioe) {
			
		    throw new IOException("Unable to open IO stream");
		    
		} finally {
			
		    if(this.clientSocket.isConnected())
			this.clientSocket.close();
		    
		}
		
		System.out.println("[" + ConsoleUtilities.generateTimeStamp() + "] Client from " + this.clientSocket.getInetAddress().getHostName() + " disconnected");
    }
    
}
