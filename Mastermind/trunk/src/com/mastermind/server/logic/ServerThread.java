/**
 * 
 */
package com.mastermind.server.logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.mastermind.server.util.ConsoleUtilities;

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
    private InputStream inStream;
    private OutputStream outStream;
    
    public ServerThread(Socket clientSocket) throws IOException {
	super();
	this.clientSocket = clientSocket;
	
	// Initialise message buffer
	this.messageBuffer = new byte[MESSAGE_BUFFER_SIZE];
	
	// Instantiate IO streams
	try {
	    this.inStream = this.clientSocket.getInputStream();
	    this.outStream = this.clientSocket.getOutputStream();
	} catch (IOException ioe) {
	    throw new IOException("Unable to retrieve IO stream from client socket");
	}
    }

    public Socket getClientSocket() {
	return this.clientSocket;
    }
    
    public void setClientSocket(Socket clientSocket) {
	this.clientSocket = clientSocket;
    }
    
    public void startThread() throws IOException {
	int receiveSize = 0;
	
	System.out.println("[" + ConsoleUtilities.generateTimeStamp() + "] Handling client from " + this.clientSocket.getInetAddress().getHostName());
	
	try {
	    while((receiveSize = inStream.read(messageBuffer)) != -1) {
	        
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
