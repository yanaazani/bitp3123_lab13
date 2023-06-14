package server_site;

import java.io.BufferedReader;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Launch server side application using TCP
 * 
 * @author Shaufy Yana Ezani
 *
 */
public class MyServerApplication {
	
	/**
	 * Main entry point to the server side application
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException 
	{
	
		// Launch server frame
		MyServerFrame myServerFrame = new MyServerFrame();
		myServerFrame.setVisible(true);
		
		// 1. Bind to a port
		int portNo=51000;
		ServerSocket serverSocket= new ServerSocket(portNo);

		try {
			
			
			System.out.println("Waiting for request");
			// Counter to keep track the number of requested connection
			int totalRequest = 0;
			
			//Server site must alive 24/7
			while(true) {
				
				// Message to indicate server is alive
				myServerFrame.updateServerStatus(false);
				
				// 2. Accept client request for connection
				Socket clientSocket = serverSocket.accept();
				
				// Check if the client is connected to the server
				if (clientSocket.isConnected()) {
					System.out.println("Client connected: " + clientSocket.getInetAddress());
				} else {
				    System.out.println("Failed to connect to the server");
				}
	
	            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
	            
	            // Receive English text and target translated language from client
                String text = reader.readLine();
                String targetLanguage = reader.readLine();

                
                // Translate the text
                TextTranslation translator = new TextTranslation();
                String translatedText = translator.translateText(text, targetLanguage);
                System.out.println("Translated text: " + translatedText);

                // Send translated text to client
               writer.println(translatedText);
	            
				// Message to indicate server is alive
				myServerFrame.updateServerStatus(false);
				
				// Close the connection
                reader.close();
                writer.close();
                clientSocket.close();
                
             // Update the request status
                myServerFrame.updateRequestStatus(
    					"Data sent to the client");
                myServerFrame.updateRequestStatus("Accepted connection to from the "
    					+ "client. Total request = " + ++totalRequest );
			}
			
			
			
		}catch (IOException ioe) {
			if(serverSocket != null)
				serverSocket.close();
			
			ioe.printStackTrace();
		}
	
		
		
	}



}
