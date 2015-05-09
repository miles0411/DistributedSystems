import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * @author Preston Lin
 * @version Last Modified: Feb 21, 2014
 * This class is to construct a functioning client, 
 * which provides a platform for the user to input a simple
 * formula. The formula can only include 2 operands and one operator.
 * The operator should be only "+" "-" "X" "/" "^"/.
 */
public class UDPClient{

    /**
     *
     * @param args main method default parameter, can be null
     * The main method is the point launching the program. A socket linked to 
     * the server will then be built. The program will wait for the input of user.
     * The user is assumed to enter all correct data whenever the program prompts
     * for user's input. once the input is complete, the outgoing socket will
     * sent the request and wait for the response from server. 
     * At last, the result of the request by users will be presented.
     */
    public static void main(String args[]){ 
		// args give message contents and destination hostname
		DatagramSocket aSocket = null;
                int serverPort = 6789;	
                byte[] buffer = new byte[1000];
		try {
                        aSocket = new DatagramSocket();   
			InetAddress aHost = InetAddress.getByName("");                                  
			                 
                        System.out.println("Enter simple expression to be computed by the server: ");
                        Scanner input = new Scanner(System.in);
                        String formula = input.nextLine();
                        
                        //Translate user's input into byte array in order to get written to DatagramPacket
                        //Then the socket will be sent as request.
                        byte [] inputByte = formula.getBytes(); 
                        DatagramPacket calcrequest = 
                                 new DatagramPacket(inputByte, inputByte.length, aHost, serverPort);
                        aSocket.send(calcrequest);
                        
                        //The socket is waiting for the response from server and will print out the result
                        //once the server reaches an answer.
                        byte[] answer = new byte[1000];
                        DatagramPacket answerPack = new DatagramPacket(buffer, buffer.length);	
			aSocket.receive(answerPack);
			System.out.println(formula + " = "+ new String(answerPack.getData()));
                        
                    
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
	}		      	
}