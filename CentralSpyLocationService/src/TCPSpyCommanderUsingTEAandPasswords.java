
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Preston 
 * @version Last Modified: Feb 21, 2014
 * This is a server side program. This server side needs a given symmetric key 
 * to authenticate the remote client's log in information. If the log in in
 * illegal, the provided location of recorded and set into the electronic record.
 * If the log in request isn't illegal, the remote request will be refused. 
 * We assume the server and client side knows the exact same symmetric key. 
 */
public class TCPSpyCommanderUsingTEAandPasswords {

    //set a new string variable to record the initial launch of server side. 
    //Once the server's key is set, the server can operate all day and night without 
    //further operations.
    private static String key="";
    
    //set a hashmap to record the incoming location info of spies.
    private static HashMap<String, String> spyLocation = new HashMap<String, String>();

    /**
     *
     * @param args main method default parameter, can be null
     * This main method will firstly build up an accepting port by prompting a 
     * one time inquiry of the symmetric key that is secretly owned by the spy commander
     * and the spies. Once the key is entered, no further actions need to be taken by the spy 
     * commander. 
     */
    public static void main (String args[]) {
                
		try{
			
                        System.out.println("Server-Enter symmetric key for TEA (taking first sixteen bytes):");
                        Scanner launch = new Scanner(System.in);
                        key = launch.nextLine();
                        int serverPort = 7777; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
                        spyLocation = new HashMap<String, String>(); 
			while(true) {
				Socket clientSocket = listenSocket.accept();
                                //The connection takes 3 parameters including the socket itself, the given symmetric key and
                                //a hashmap to retain a lasting record.
				Connection c = new Connection(clientSocket,  key.getBytes(), spyLocation);
			}
                
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}

/**
 * This class extends Thread class in order to take care of data concurrency. 
 * The connection is once set up, it will keep waiting for remote request of login in.
 * If the remote request is legal.(The username and password matches!), a .kml file 
 * will be written out, which indicates the most updated locations of spies. 
 * Else if the remote request is illegal, no more actions will be done. A refuse reponse
 * will be sent back to the client.
 * If the spy never reports, the location will be kept unknown. (In the middle of the ocean).
 */
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
        private static TEA tea;
        HashMap<String, String> spyLogin;
        HashMap spyLocation;
        
	public Connection (Socket aClientSocket, byte[] b, HashMap hm) {
	    spyLocation = hm;
            //set the database of spies login
            spyLogin = new HashMap<String, String>();
            spyLogin.put("jamesb", "james");
            spyLogin.put("joem", "joe");
            spyLogin.put("mikem", "mike"); 
            tea = new TEA(b);
            
            try {                   
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());                      
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start(); //start the thread
            }
            catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	
        public void run(){
		try {			                        
                       	//Establish a buffer byte array to take incoming socket. 
                        byte[] buffer = new byte[1000];
                        int inLength = in.read(buffer);
                        //Based on the length of incoming message, finalize an
                        //appropriate byte array for decryption.               
                        byte[] crypt = new byte[inLength];
                        for(int i=0;i<crypt.length;i++){
                            crypt[i]=buffer[i];
                        }                     
                        byte[] decrypt = tea.decrypt(crypt);
                        
                        //Use Scanner to translater the decrypted incoming message
                        Scanner spyReport = new Scanner(new String(decrypt));
                        spyReport.useDelimiter(" ");
                        String ID = spyReport.next();
                        String pwd = spyReport.next();
                        String location = spyReport.next();
                        
                        //Determine the login is legal or not
                        if(!spyLogin.containsKey(ID)||!spyLogin.get(ID).equals(pwd)){
                            byte[] warning = tea.encrypt("Illegal user name or password or symmetric key".getBytes());
                            out.write(warning);
                            out.flush();
                        }
                        else{
                            byte[] warning = tea.encrypt("Data securely transmitted to Intelligence Headquarters.".getBytes());
                            out.write(warning);
                            out.flush();
                            depictSpyLocation(ID,location); //if legal, call the depictSpyLocaiton() to record the location of spy
                            
                        
                     }
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch(IOException e) {System.out.println("readline:"+e.getMessage());
		}finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
        
        
        private void depictSpyLocation(String id, String loc) throws FileNotFoundException, UnsupportedEncodingException{
            
            spyLocation.put(id, loc); //update the database of location
            
            PrintWriter writer = new PrintWriter("/Users/Preston/Desktop/SecretAgent.kml");
            
            //Based on the format of KML file, rewrite the most update information.
            writer.println(
            "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n" +
            "<Document>\n" +
            "	<Style id=\"style1\">\n" +
            "		<IconStyle>\n" +
            "			<Icon>\n" +
            "				<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue- dot.png</href>\n" +
            "			</Icon>\n" +
            "		</IconStyle>\n" +
            "	</Style>\n" +
            "	\n" +
            "	<Placemark>\n" +
            "		<name>jamesb</name>\n" +
            "		<description>Spy</description>\n" +
            "		<styleUrl>#style1</styleUrl>\n" +
            "		<Point> <coordinates>"+spyLocation.get("jamesb")+"</coordinates> </Point>\n" +
            "	</Placemark>\n" +
            "\n" +
            "	<Placemark>\n" +
            "		<name>joem</name>\n" +
            "		<description>Spy</description>\n" +
            "		<styleUrl>#style1</styleUrl>\n" +
            "		<Point> <coordinates>"+spyLocation.get("joem")+"</coordinates> </Point>\n" +
            "	</Placemark>\n" +
            "	\n" +
            "	<Placemark>\n" +
            "		<name>mikem</name>\n" +
            "		<description>Spy</description>\n" +
            "		<styleUrl>#style1</styleUrl>\n" +
            "		<Point> <coordinates>"+spyLocation.get("mikem")+"</coordinates> </Point>\n" +
            "	</Placemark> \n" +
            "</Document> \n" +
            "</kml>"
            );
            writer.close();
            
            
          
        }
         
    
}
