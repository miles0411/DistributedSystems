
/**
 * @Date   Jan 31, 2014
 * @author Preston
 * @Note   This Java servlet is to do BigInteger calculation
 */

package Project1Task2;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BigCal extends HttpServlet {
    
    //When the user send out request, this method will be called and starts to define 
    //variables for further calculations based on the user's choice.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Do numbers check! If the input is not valid for math calculations, return error message. 
        if(request.getParameter("x").matches("-?[0-9]+")&&request.getParameter("y").matches("-?[0-9]+")) {
            
            //Use BigInteger class to hold integers as instructed.
            BigInteger x = new BigInteger(request.getParameter("x"));
            BigInteger y = new BigInteger(request.getParameter("y"));
            String operator = request.getParameter("operator");
         
            //start to determine the operator and output the result
            if(operator.equals("add")){

                BigInteger result = new BigInteger("0");
                result = x.add(y);
                request.setAttribute("result", x + " + " + y + " = " + result);
                

            }
            else if(operator.equals("multiply")){

                BigInteger result = x.multiply(y);
                request.setAttribute("result", x + " x " + y + " = " + result);

            }
            else if(operator.equals("relativelyPrime")){

                if((x.gcd(y)).compareTo(new BigInteger("1"))==0){

                    request.setAttribute("result", x +" is a coprime of "+ y);   
                }
                else{

                    request.setAttribute("result", "X is not a coprime of Y"); 
                }

            }
            else if(operator.equals("mod")){

                try{
                BigInteger result = x.mod(y);
                request.setAttribute("result", x + " % " + y + " = " + result);
                }
                catch(Exception e){
                    request.setAttribute("result", "Error - "+ e.getMessage());
                }

            }
            else if(operator.equals("modInverse")){
                try{
                BigInteger result = x.modInverse(y);
                request.setAttribute("result", x+"^-1 % " + y + " = " + result);
                }
                catch(Exception e){
                    request.setAttribute("result", "Error - "+ e.getMessage());
                }
            }
            else if(operator.equals("power")){
                
                try{
                BigInteger result = x.pow(Integer.parseInt(y.toString()));
                request.setAttribute("result", x +"^"+ y +" = " + result);
                }
                catch(Exception e){
                    request.setAttribute("result", "Error - "+ e.getMessage());
                }
            }
            else{
                
                request.setAttribute("result", "Internal ERROR!!");
            }
  
        }
        
        else{
            
            request.setAttribute("result", "At lease one number entered is not an integer. Please re-enter!");
            
        }
        
        //when the outcome is decided, redirect the page to result.jsp
        String nextView = "result.jsp";
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
        
    }
        
}

  