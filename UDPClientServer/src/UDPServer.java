
import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * @author Preston Lin
 * @version Last Modified: Feb 21, 2014
 * This class is to construct a functioning server, 
 * which provides 5 remote math calculation.
 * They include add, minus, multiply, divide and power.
 */
public class UDPServer{
    
    /**
     *
     * @param args main method default parameter, can be null
     * This is main method for the class to launch as a remote calculator.
     * The DatagramPackert coming will be translated into necessary data
     * for further calculation. Once the calculation is finished, 
     * the outgoing DatagramPacket will be written into DatagramSocket, 
     * And wait for the next complete request.
     */
    public static void main(String args[]){ 
        
    	DatagramSocket aSocket = null;
        
		try{
                        aSocket = new DatagramSocket(6789);   // create socket at agreed port
				
                        byte[] buffer = new byte[1000];       // creat a byte array for further use
                        while(true){
                               
 				DatagramPacket request = new DatagramPacket(buffer, buffer.length); 
                                aSocket.receive(request);  //receive incoming socket from client
                                
                                String getMes = new String(buffer,0,request.getLength()); //translate socket and start to calculate
                                Scanner input = new Scanner(getMes);
                                int x = Integer.parseInt(input.next());
                                String opr = input.next();
                                int y = Integer.parseInt(input.next());
                                
                                int z=0;
                                if(opr.equals("+")){
                                    z = x+y;
                                }
                                else if(opr.equals("-")){
                                    z = x-y;
                                }
                                else if(opr.equals("X")){
                                    z = x*y;
                                }
                                else if(opr.equals("^")){
                                    z = (int) Math.pow(x, y);
                                }
                                else if(opr.equals("/")){
                                    z = x/y;
                                }
                                
                                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), 
    				request.getAddress(), request.getPort());  
                                String result = String.valueOf(z);   
                                
                                //write result into DatagramPacket and sent it back to client
                                reply = new DatagramPacket(result.getBytes(), result.getBytes().length,request.getAddress(), request.getPort());
                                aSocket.send(reply);    
                                
                        }
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
    }
}