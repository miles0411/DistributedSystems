/**
 * @Date   Jan 31, 2014
 * @author Preston
 * @Note   This Java servlet is to serve encoding function.
 */

package Project1Task1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;


@WebServlet(name = "ComputeHashes",
        urlPatterns = {"/ComputeHashes"})

public class ComputeHashes extends HttpServlet {
   
   
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @note   When this method is called, it will take in user's input,
     *         and starts to encode a string into a 64base and hex code; 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String input ="";
        input = request.getParameter("input");
         
        String choice = request.getParameter("choice");
        request.setAttribute("choice", choice);
       
            //take in the string for encoding. use method defined in JAVA library
            //to encode string based on 64base or hex. Then return the result.
            try{
                MessageDigest md = MessageDigest.getInstance(choice);
                String output64base = (new BASE64Encoder()).encode(md.digest(input.getBytes()));   
                request.setAttribute("output", output64base);
                String outputHex = getHexString(md.digest(input.getBytes()));
                request.setAttribute("outputHex", outputHex);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        
        String nextView;
        
        /*
         * Check if nothing is put in by the user.
         * If it is not, then give the user instructions and prompt for a search
         * string.
         * when the outcome is decided, redirect the page to result.jsp
         * Or direct the user back to the welcome page.
        */
        if (!"".equals(input)) {
          
            request.setAttribute("input", input);
            
            nextView = "result.jsp";
        } 
        else {
            
            nextView = "prompt.jsp";
        }
        
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
 
    }
    
    /*When this method is called, a bypte array needs to be given,
    it will transfer binary arrays into a hex*/
    public String getHexString(byte[] b) throws Exception { 
        String result = ""; 
        for (int i=0; i < b.length; i++) { 
        result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1); 
        } 
        return result; 
    }
}
