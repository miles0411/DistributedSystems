
/**
 *
 * @author Preston
 */


import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Preston Lin
 * @version Last Modified: Feb 21, 2014
 * This is to build a UDP server called that receives two double operands and an
 * operator from a UDP client and returns the double result after applying the operator 
 * (‘+’, ‘X’, ‘-‘, or ‘/’) to the two double operands.
 */
public class UDPServerWithDoubleArithmetic{
    
    /**
     *
     * @param args main method default parameter, can be null
     * This is main method for the class to launch as a remote calculator.
     * The DatagramPackert coming will be translated into necessary data
     * for further calculation. Once the calculation is finished, 
     * the outgoing DatagramPacket will be written into DatagramSocket, 
     * And wait for the next complete request.
     * When the DatagramPacket is being constructed, this time we use 
     * marshaling and un-marshalling of parameters other than simple integers or character text. 
     * That is, this only do reading and writing binary data rather than string data.
     */
    public static void main(String args[]){ 
        
    	DatagramSocket aSocket = null;
        byte[] buffer1 = new byte[8];           
                           
		try{
                    
                
                    aSocket = new DatagramSocket(6789);
				
                    while(true){
                        
                        DatagramPacket request = new DatagramPacket(buffer1, buffer1.length); 
                        aSocket.receive(request);
                        byte[] buffer = request.getData();
                        long v = 0; 
                        
                        //use bit operation to translate incoming request
                        //This method is provided by professor which is cited from StockOverFlow
                        for (int i = 0; i < buffer.length; i++){ 
                            
                           v = (v << 8) + (buffer[i] & 0xff); 
                           
                        } 
                        double a = Double.longBitsToDouble(v);
                       
                        
                        
                        aSocket.receive(request);
                        buffer = request.getData();
                        v = 0; 
                        for (int i = 0; i < buffer.length; i++){ 
                         
                           v = (buffer[i] & 0xff) + (v << 8); 
                           
                        } 
                        double opr = Double.longBitsToDouble(v);
                       
                        
                                                
                        aSocket.receive(request);
                        buffer = request.getData();
                        v = 0; 
                        for (int i = 0; i < buffer.length; i++){ 
                       
                           v = (v << 8) + (buffer[i] & 0xff); 
                           
                        } 
                        double b = Double.longBitsToDouble(v);
                                       
                    
                        double z=0;
                        if(opr=='+'){
                            z = a+b;
                        }
                        else if(opr=='-'){
                            z = a-b;
                        }
                        else if(opr=='X'){
                            z = a*b;
                        }
                        else if(opr=='^'){
                            z = (double) Math.pow(a, b);
                        }
                        else if(opr=='/'){
                            z = a/b;
                        }
                        
                        
                        long result = Double.doubleToLongBits(z);
                        ByteBuffer bytebuffer = ByteBuffer.allocate(8);
                        bytebuffer.putLong(result);
                        DatagramPacket reply = new DatagramPacket(bytebuffer.array(), bytebuffer.array().length, 
                        request.getAddress(), request.getPort());  
                       
                        aSocket.send(reply);
                        
                    }        
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
    }
}
