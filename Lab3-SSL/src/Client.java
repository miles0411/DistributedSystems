import java.io.*;
import javax.net.ssl.*;
import java.net.*;
import java.util.Scanner;
import javax.net.*;

public class Client {
    
    public static void main(String args[]) {
        
        int port = 6502;
        try {
            // tell the system who we trust
            System.setProperty("javax.net.ssl.trustStore","mjm.truststore");
            // get an SSLSocketFactory
            SocketFactory sf = SSLSocketFactory.getDefault();
            
            // an SSLSocket "is a" Socket
            Socket s = sf.createSocket("localhost",6502);
            
            
            BufferedWriter out = new BufferedWriter(
                                                    new OutputStreamWriter(
                                                                           s.getOutputStream()));
            BufferedReader in = new 
            BufferedReader(
                           new InputStreamReader(
                                                 s.getInputStream()));
            
            
            
	    String question ="";
            question = in.readLine();
            System.out.println(question);
            Scanner input = new Scanner(System.in);
            out.write(input.nextLine());
            out.flush();
	    question = in.readLine();
	    System.out.println(question);
            input = new Scanner(System.in);
            out.write(input.nextLine());
            out.flush();
            out.close(); 
            in.close();
        }
        catch(Exception e) {
            System.out.println("Exception thrown " + e);
        }
    }
} 