

import java.net.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Preston Lin
 * @version Last Modified: Feb 21, 2014
 * This new server provides the same function as Project2Task1 but is intended to set to randomly ignores 80% of 
 * requests – a very unreliable server.
 * Note: Similar minor comments at lowest level will be ignored. We don't repeat again.
 */
public class UDPServerThatIgnoresYou{
    
    /**
     *
     * @param args main method default parameter, can be null
     * This is main method for the class to launch as a remote calculator.
     * The DatagramPackert coming will be translated into necessary data
     * for further calculation. Once the calculation is finished, 
     * the outgoing DatagramPacket will be written into DatagramSocket, 
     * And wait for the next complete request. However, 
     * the server is set to be a not reliable server, 
     * 80% of request will be dropped. 
     */
    public static void main(String args[]){ 
        
    	DatagramSocket aSocket = null;
        Random rand = new Random();
        
		try{
                        aSocket = new DatagramSocket(6789);
				
                        byte[] buffer = new byte[1000];
                        while(true){
                            
                                
                                DatagramPacket request = new DatagramPacket(buffer, buffer.length); 
                                aSocket.receive(request);
                                
                                //This part is the highlight. 80% of incoming request will be intentionlly ingorned.
                                if(rand.nextInt(10)<8){
                                    
                                    System.out.println("Ignores it.");
                                    continue;  
                                }
                                else{                                
                                 
                                    String getMes = new String(buffer,0,request.getLength());
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


                                    reply = new DatagramPacket(result.getBytes(), result.getBytes().length,request.getAddress(), request.getPort());
                                    
                                    aSocket.send(reply);
                                    
                                    
                                }
                                
                        }
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
    }
}