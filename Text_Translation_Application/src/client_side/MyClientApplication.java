package client_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class MyClientApplication {
	
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		// Launch myClientFrame
				MyClientFrame myClientFrame = new MyClientFrame();
				myClientFrame.setVisible(true);
        
		try {
            // Connect to the server @ localhost, port 51000
            Socket myClientSocket = new Socket(InetAddress.getLocalHost(), 51000);
            
            // Update the status of the connection
            myClientFrame.updateConnectionStatus(myClientSocket.isConnected());
    		
            
           // Get input and output streams
            BufferedReader reader = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(myClientSocket.getOutputStream(), true);
            
            // Send English text and target translated language
            //TextTranslation translator = new TextTranslation();
            String text = "Good Morning ";
            String targetLanguage = "bm"; 
            
            writer.println(text);
            writer.println(targetLanguage);
            
            // Receive translated text from server and display in the client-side
            String translatedText = reader.readLine();
            System.out.println("Translated text: " + translatedText);
            myClientFrame.updateServerDate(translatedText);


            // Close the connection
            reader.close();
            writer.close();
            myClientSocket.close();
            
			}catch (UnknownHostException e) {
				e.printStackTrace();
		
	
			}	
	}
}
