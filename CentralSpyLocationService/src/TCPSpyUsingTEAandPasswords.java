
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Preston 
 * @version Last Modified: Feb 21, 2014
 * This class is to build a interface for spies to report their location. 
 * The main method will need 2 arguments IP address and port to connect to the server. 
 * The arguments are set by the run configuration in NetBeans. 
 * We assume only one request and one response will be done for each reporting.
 */
public class TCPSpyUsingTEAandPasswords {

    /**
     * @param args the command line arguments take IP address and port number for further actions
     * @throws java.io.IOException: if the given arguments is not legal, exceptions will be thrown.
     * Once the main method is successfully launched with proper arguments, 
     * the socket will be established to the server.
     * The program will ask for ID, password, and current location.
     * Finally, it will prompt the server's response of the current reporting.
     */
    public static void main (String args[]) throws IOException {
		
		Socket s = null;
                TEA tea;
                Scanner input = new Scanner(System.in);
		try{
		
                        s = new Socket(args[0], Integer.parseInt(args[1])); 
                        System.out.println("Connected to Server for reporting."); //Connection is established
                        
			System.out.println("Enter symmetric key for TEA (taking first sixteen bytes):");
			tea = new TEA(input.nextLine().getBytes());
                        System.out.println("Enter your ID:");
                        String ID = input.nextLine();
                        System.out.println("Enter your Password:");
                        String pwd = input.nextLine();
                        System.out.println("Enter your location");
                        String location = input.nextLine();
                        //Summarize the input info
                        System.out.println(ID+" "+pwd+" "+location);
                        
                        //Encrypt the message
                        byte[] crypt = tea.encrypt((ID+" "+pwd+" "+location).getBytes());
                           
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
			
                        //Send out the message
                        out.write(crypt);
                        out.flush(); 
                        
                        //Receive the incoming response from server
                        byte[] buffer = new byte[1000];
                        int inLength = in.read(buffer);
                        byte[] response = new byte[inLength];
                        for(int i=0;i<response.length;i++){
                            response[i]=buffer[i];
                        }                     
                        byte[] decrypt = tea.decrypt(response);
                        
                        //Print the result/response
                        System.out.println(new String(decrypt));
 
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
    
}
