package server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The server will count the number of words
 * in the text and returns it to the client
 *  
 *  
 *  
 *  
 * @author Shaufy Yana Ezani
 * @apiNote B032110265
 * 
 *
 */

public class ServerApplication {
	
	/**
	 * This program is the main entry point to the 
	 * server side application
	 */
	public static void main (String[] args) throws Exception
	{
		
		ServerFrame serverFrame = new ServerFrame();
		serverFrame.setVisible(true);
		
		// 1. Bind to a port
		int portNo = 4228;
		try(ServerSocket serverSocket = new ServerSocket(portNo)){
			TextGenerator textGenerator = new TextGenerator();
			
			
			// Counter to keep track the number requested connection
			int totalRequest = 0;
			
			// Server need to be alive 24/7
			while(true) {
				//Message to say that the server is alive
				serverFrame.updateServerStatus(false);
				
				// 3. Accept client request for connection
				Socket clientSocket = serverSocket.accept();
				
				// Generate current date
				int currentDate = TextGenerator.wordCount(null);
				
				// Create stream to write data on the network
				DataOutputStream outputStream = 
						new DataOutputStream(clientSocket.getOutputStream());
				
				// Send current date back to the client
				outputStream.writeInt(currentDate);
				
				// Close the socket
				clientSocket.close();
			
				// Update the request status
				serverFrame.updateRequestStatus(
						"Data sent to the client: " + currentDate);
				serverFrame.updateRequestStatus("Accepted connection to from the "
						+ "client. Total request = " + ++totalRequest );
			}
		}
	
		
	}
	
	
}
