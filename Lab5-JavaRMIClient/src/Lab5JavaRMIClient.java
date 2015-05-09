/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.rmi.Naming;
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.math.BigInteger;
/**
 *
 * @author Preston
 */
public class Lab5JavaRMIClient {


    public static void main(String[] args) throws Exception{
         
        Bumper bumper = (Bumper) Naming.lookup("//localhost/bumper");
        
        BigInteger ctr = new BigInteger("0");
        BigInteger n = new BigInteger("10000");
        long start = System.currentTimeMillis();
        while(ctr.compareTo(n)<0){
            
            bumper.bump();
            ctr = ctr.add(new BigInteger("1"));
            
        }
        long stop = System.currentTimeMillis();
        System.out.println(bumper.get());
        System.out.println(stop-start);
        
        

    }

    
}
