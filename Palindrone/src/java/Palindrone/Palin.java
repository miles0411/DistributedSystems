/**
 * @Date   Jan 31, 2014
 * @author Preston
 * @Note   This Java servlet is to determine if a string is palidrome or not.
 */

package Project1Task3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Palin",
        urlPatterns = {"/Palin"})

public class Palin extends HttpServlet {


    //When the user submits request, this method will be called to update
    //relevant parameters.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String s = request.getParameter("input");
        request.setAttribute("s", s);
        Scanner scan = new Scanner(s);
        
        //Based on definition, ignore signs and another non alphabets/numerucal input
        scan.useDelimiter("[^a-zA-Z0-9]+");
        String s2="";
        while(scan.hasNext()){
            s2 += scan.next();
        }
        
        //Based on definition, ignore the case sensitivity
        String lowercase = s2.toLowerCase();      
        char [] array = lowercase.toCharArray();  
        
        //default a flag as false
        boolean pali = false;
            
        //Use loop to compare the left side and the right side, 
        //any inconsistency happening will end this loop 
            for(int i=0;i<(s2.length()/2);i++){
                if(array[i]!=array[s2.length()-i-1]){
                    request.setAttribute("pali", "is not a Palidrome!!");
                    pali = false;
                    break;
                }
                //continouse consistency will make the flag true
                else
                { 
                    pali = true;
                }
            }
       
        //After the comparison completes, if the flag is still true, 
        //then the input is a palidrome
        if(pali==true){
            
            request.setAttribute("pali", "is a Palidrome!!");
        }
        
        String nextView = "result.jsp";
        
        //If the outcome is determined, redirect the user to a result page.
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
        
    }

}
