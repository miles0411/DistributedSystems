
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * @author Preston Lin
 * @version Last Modified: Feb 21, 2014
 * This class is to build a ProxyClient side. In this client, 
 * an add method is simply being used as a proxy for the server and the server is where the 
 * addition is actually being carried out. In the real implementation,
 * the main routine of the client contains no socket level programming.
 * The main routine will make k calls on the add method because addition is not done by the client
 * in reality. The call of server will repeat until client finishes its calculation.
 */
public class UDPClientWithProxy {
 
    /**
     * @param args main method default parameter, can be null
     * The main method is the starting point of this program. 
     * The program will ask the user to input a single positive integer. 
     * We assume the user behaves correct and temporarily neglect the exception check
     * as defined by the instructor. After the input is accepted, the client will start to 
     * present its role of caller to repeat the addition from 0 to the input value K as
     * 0+1+2+.....+K. And finally prompts the answer.
     */
    public static void main(String args[]){ 
         
               System.out.println("Enter a random integer > 0:");
               Scanner input = new Scanner(System.in);
               int x = Integer.parseInt(input.next());
               int y = 1;
               int z = 0;
               String accumulate ="";
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
     *
     * @param x: is an integer value as the first operand 
     * @param y: is an integer value as the second operand
     * @return a integer value will be returned as the result of the addition.
     * This add method includes no real calculation function, but only socket level 
     * work and translation. 
     * Note: Several similar explanation shown in Project2Task1A will be
     * skipped over here. 
     */
    public static int add(int x,int y){
                
                DatagramSocket aSocket = null;
                int serverPort = 6789;	
                byte[] buffer = new byte[1000];
                DatagramPacket answerPack = new DatagramPacket(buffer, buffer.length);
                String answer="";
                int result=0;             
                
                //start to sent out request and wait for the result of the calling.
                try {
                            aSocket = new DatagramSocket();    
                         
                            InetAddress aHost = InetAddress.getByName("");                                   

                            String parameter = x + " + " + y;
                           
                            byte [] inputByte=parameter.getBytes(); 
                            
                            DatagramPacket calcrequest = 
                                     new DatagramPacket(inputByte, inputByte.length, aHost, serverPort);
                            aSocket.send(calcrequest);
                                                     	
                            aSocket.receive(answerPack);
                            answer = new String(answerPack.getData());
                            
                            //We only need the integer value for further use; good way to retreive numbers only
                            //by replace all with a regular expression defined.
                            answer = answer.replaceAll("[^0-9]", "");                 
                            result = Integer.parseInt(answer);
                            

		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
                
        return result;    
         
	}	
}
