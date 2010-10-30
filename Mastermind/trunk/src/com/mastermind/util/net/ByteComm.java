/**
 * Contains the necessary utility classes to perform data communications between a server and a client system.
 */
package com.mastermind.util.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * A utility class that contains the necessary static methods to send and receive data between a client and a server system over a <code>Socket</code> connection.
 * @author Alan Ly
 * @version 1.1
 */
public class ByteComm {
    
	/**
	 * Gets a message from a connection specified by the <code>socket</code> and stores the message into the <code>buffer</code> array.
	 * The return value either specifies the number of bytes received from the <code>Socket</code> connection or a client disconnect specified by the value <code>-1</code>.
	 * An <code>IOException</code> may be thrown if an input stream from the socket cannot be opened. 
	 * @param socket	the socket containing the connection to a client or server
	 * @param buffer	the single-dimension <code>byte</code> array that references the buffer to store the received message into
	 * @return	the status of the operation
	 * @throws IOException	thrown if an input stream cannot be opened
	 */
    public static int receive(Socket socket, byte[] buffer) throws IOException {
    	return communicate(0, socket, buffer);
    }
    
    /**
     * Sends a message specified by the <code>buffer</code> array to the connection specified by the <code>socket</code>.
     * An <code>IOException</code> may be thrown if an output stream to the socket cannot be opened.
     * @param socket	the socket containing the connection to a client/server
     * @param buffer	the single-dimension <code>byte</code> array that references the buffer containing the message to be sent
     * @throws IOException	thrown if an output stream cannot be opened
     */
    public static void send(Socket socket, byte[] buffer) throws IOException {
    	communicate(1, socket, buffer);
    }
    
    /**
     * Performs an IO operation between a client/server based on the appropriate <code>mode</code> parameter.
     * An <code>IOException</code> may be thrown if the appropriate IO streams cannot be retrieved from the client <code>Socket</code>.
     * The available modes are,
     * <ul>
     * 	<li><strong>0</strong> - specifies a <em>read</em> request from the client/server</li>
     * 	<li><strong>1</strong> - specifies a <em>send</em> request to the client/server</li> 
     * </ul>
     * 
     * The return value contains the result of the operation,
     * <ul>
     * 	<li><strong>Read</strong> - either the number of bytes returned from an IO operation or -1 when the client closes the connection.</li>
     * 	<li><strong>Write</strong> - a fixed-value of 1 to indicate successful operation.</li>
     * </ul>
     * @param mode	specifies the appropriate action to operate on the client/server
     * @param socket	the <code>Socket</code> containing the appropriate connection
     * @param bufferArray	a reference to a single-dimension <code>byte</code> array that either contains the data to be <em>sent</em> or where the data should be buffered from a <em>read</em>
     * @return	an integer value that represents the result of the operation
     * @throws IOException thrown when IO streams with client could not be opened
     */
    public static int communicate(int mode, Socket socket, byte[] bufferArray) throws IOException {
    	int streamStatus = 0;
    	
    	switch(mode) {
    		case 0:    			
    			try {
    				InputStream stream = socket.getInputStream();
    				streamStatus = stream.read(bufferArray);
    			} catch (IOException ioe) {
    				throw new IOException("Unable to open input stream to " + socket.getInetAddress().getHostAddress());
    			}
    			break;
    		case 1:
    			try {
    				socket.getOutputStream().write(bufferArray);
    				streamStatus = 1;
    			} catch (IOException ioe) {
    				throw new IOException("Unable to open output stream to " + socket.getInetAddress().getHostAddress());
    			}
    			break;
    		default:
    			throw new IllegalArgumentException("Illegal communication mode passed: " + mode);
    	}
    	
    	return streamStatus;
    }
    
}
