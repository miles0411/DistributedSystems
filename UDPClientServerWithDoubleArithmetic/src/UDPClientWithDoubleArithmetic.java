
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Scanner;


/**
 * @author Preston Lin
 * @version Last Modified: Feb 21, 2014
 * This client will use the server and will compute the sum 1.75 + 2.75 + 3.75 +…+100.75. 
 * The main routine simply makes calls on a proxy to compute the sum.
 */
 public class UDPClientWithDoubleArithmetic {
     
    /**
     * @param args main method default parameter, can be null
     * The main method is the starting point of this program. 
     * The program will calculate 1.75 + 2.75 + .... + 100.75. 
     * And finally prompts the answer.
     */
     public static void main(String args[]){ 
               
               double y = 1.75;
               double z = 0;
               String accumulate ="";
               while(y<=100.75){
                   z = add(z, y); 
                   if(y!=100.75){
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
     * @param z: is an integer value as the first operand 
     * @param y: is an integer value as the second operand
     * @return a integer value will be returned as the result of the addition.
     * This add method includes no real calculation function, but only socket level 
     * work and translation. 
     * When the DatagramPacket is being constructed, this time we use 
     * marshaling and un-marshalling of parameters other than simple integers or character text. 
     * That is, this only do reading and writing binary data rather than string data.
     */
    public static double add(double z, double y){
                
                DatagramSocket aSocket = null;
                int serverPort = 6789;	
                byte[] bufferA;
                byte[] bufferOpr;
                byte[] bufferB;             
                char opr = '+';
                long a = Double.doubleToLongBits(z);
                long operator = Double.doubleToLongBits((double) opr);
                long b = Double.doubleToLongBits(y);
                
                
                ByteBuffer bytebufferA = ByteBuffer.allocate(8);
                ByteBuffer bytebufferOpr = ByteBuffer.allocate(8);
                ByteBuffer bytebufferB = ByteBuffer.allocate(8);
                bytebufferA.putLong(a);
                bytebufferOpr.putLong(operator);
                bytebufferB.putLong(b);
                bufferA = bytebufferA.array();
                bufferOpr = bytebufferOpr.array();
                bufferB = bytebufferB.array();


                String answer="";
                double result=0;             
                
                try {
                    aSocket = new DatagramSocket();    

                    InetAddress aHost = InetAddress.getByName("");                                   

                    
                    DatagramPacket  calcrequest1 = 
                             new DatagramPacket(bufferA, bufferA.length, aHost, serverPort);                        
                        aSocket.send(calcrequest1);  
                        
                    DatagramPacket  calcrequest2 = 
                             new DatagramPacket(bufferOpr, bufferOpr.length, aHost, serverPort);
                        aSocket.send(calcrequest2); 
                        
                    DatagramPacket  calcrequest3 = 
                             new DatagramPacket(bufferB, bufferB.length, aHost, serverPort);
                        aSocket.send(calcrequest3); 
                        byte [] buffer = new byte[8];                          
                        
                        DatagramPacket answerPack = new DatagramPacket(buffer, buffer.length);
                        aSocket.receive(answerPack);
                        byte [] backByte = answerPack.getData();
                        
                        long v=0;
                        for (int i = 0; i < backByte.length; i++){ 
                         
                           v = (v << 8) + (backByte[i] & 0xff); 
                           
                        } 
                                     
                        result = Double.longBitsToDouble(v);
                      

                        
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
                
        return result;    
         
	}	
    
}
