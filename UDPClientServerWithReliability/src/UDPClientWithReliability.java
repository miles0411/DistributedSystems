
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;


/**
 * @author Preston Lin
 * @version Last Modified: Feb 21, 2014
 * This client provides the same function as Project2Task1B's UDPClientWithProxy.
 * However, this server is set to be a reliable server, which will make sure the 
 * response will be returned in a given time (2 seconds). Once the response is
 * not returned timely, this client will keep sending the same request until 
 * the result is satisfying. 
 */
public class UDPClientWithReliability {

    /**
     * @param args main method default parameter, can be null
     * The main method is the starting point of this program. 
     * The program will ask the user to input a single positive integer. 
     * We assume the user behaves correct and temporarily neglect the exception check
     * as defined by the instructor. After the input is accepted, the client will start to 
     * present its role of caller to repeat the addition from 0 to the input value K as
     * 0+1+2+.....+K. And finally prompts the answer.
     * 
     */
    public static void main(String args[]){ 
         
               System.out.println("Enter a random integer > 0:");
               Scanner input = new Scanner(System.in);
               int x = Integer.parseInt(input.next());
               int y = 1;
               int z = 0;
               String accumulate = "";
               while(y<=x){               
                   z = add(z, y);
                   if(y!=x){
                        accumulate += y + "+";
                        y++;
                   }
                   else{
                        accumulate += y; 
                        y++;
                   }
                   
               }
               System.out.println(accumulate + " = " + z);
     }

    /**
     * @param x: is an integer value as the first operand 
     * @param y: is an integer value as the second operand
     * @return a integer value will be returned as the result of the addition.
     * This add method includes no real calculation function, but only socket level 
     * work and translation. 
     * If the response can't be returned in 2 seconds no mater what reasons, 
     * the add method will be sending the same request to the server until
     * a satisfying answer is returned.
     * Note: Several similar explanation shown in Project2Task1A will be
     * skipped over here. 
     */
    public static int add(int x,int y){
                
                DatagramSocket aSocket = null;
                int serverPort = 6789;	
                byte[] buffer = new byte[1000];
                
                String answer="";
                int result=0;             
                
                try {
                    aSocket = new DatagramSocket();    

                    InetAddress aHost = InetAddress.getByName("");                                   

                    String parameter = x + " + " + y;

                    byte [] inputByte=parameter.getBytes(); 

                    DatagramPacket calcrequest = 
                             new DatagramPacket(inputByte, inputByte.length, aHost, serverPort);

                    // keep sending data 
                    while(true){          

                        aSocket.send(calcrequest); 
                        aSocket.setSoTimeout(2000); //set the timeout as 2 seconds
                        
                        try {                                   
                            DatagramPacket answerPack = new DatagramPacket(buffer, buffer.length);
                            aSocket.receive(answerPack);

                            answer = new String(answerPack.getData());

                            answer = answer.replaceAll("[^0-9]", "");                 
                            result = Integer.parseInt(answer);
                            break;

                        }
                    
                        catch (SocketTimeoutException e) {  // timeout exception happends
                            
                            System.out.println("Timeout reached!!! ");
                            continue;  //keep the while loop for sending the original request
                        }
                    }
                           
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
                
        return result;    
         
	}	
}
