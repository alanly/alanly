/**
 * 
 */
package com.mastermind.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.mastermind.util.net.ByteProtocol;

/**
 * @author Alan Ly
 *
 */
public class ClientTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
    	String server = "127.0.0.1";
    	int port = 50000;
    	byte[] message = new byte[5];
    	
    	message[0] = ByteProtocol.END_GAME_HEADER;
    	
    	for(int i = 1; i < 5; i++)
    		message[i] = ByteProtocol.END_GAME_REQUEST;
    	
    	Socket socket = new Socket(server, port);
    	
    	System.out.println("Connected to server...");
    	
    	InputStream in = socket.getInputStream();
    	OutputStream out = socket.getOutputStream();
    	
    	out.write(message);
    	
    	System.out.println("Request sent to server...");
    	
    	int totalBytesReceived = 0;
    	int bytesReceived = 0;
    	
    	while(totalBytesReceived < message.length) {
    		if((bytesReceived = in.read(message)) == -1)
    			throw new SocketException("Connection closed prematurely");
    		
    		totalBytesReceived += bytesReceived;
    	}
    	
    	String receivedMessage = "";
    	
    	for(byte value : message)
    		receivedMessage += "[" + value + "] ";
    	
    	System.out.println("Answer received from server: " + receivedMessage);
    	
    	socket.close();
    }

}
