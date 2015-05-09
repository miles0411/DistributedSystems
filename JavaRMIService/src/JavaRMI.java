/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.rmi.*;
import java.rmi.Naming;

/**
 *
 * @author Preston
 */
public class JavaRMI {
    	public static void main(String args[]){
          System.out.println("Calculator Server Running");
          try{
            // create the servant
            Bumper c= new JavaRMIService();
            System.out.println("Created Calculator object");
            System.out.println("Placing in registry");
            // publish to registry
	    Naming.rebind("bumper", c); 
            System.out.println("CalculatorServant object ready");
           
           }catch(Exception e) {
            System.out.println("CalculatorServer error main " + e.getMessage());
        }
    }
}


