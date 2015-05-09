/*
 */
package readline;
import java.util.*;
import java.io.*;
/**
 *
 * @author Preston Lin
 */
public class Readline {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    Scanner input = new Scanner(System.in);
    System.out.println("Please enter the filename:");
    String fName = input.nextLine();
    boolean result = true;
        try {
        	Scanner file = new Scanner(new File(fName));
        }
        
        catch (FileNotFoundException e) {
        	System.out.println("No such file is found.");
        	result = false;                
                System.exit(0);
    }
    if(result=true){
        System.out.println("Please enter the pattern to search for:");
        String pName = input.nextLine();
        indexOf(pName);
        
    }    
  }
}

