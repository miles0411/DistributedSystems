package algorithm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author satejwagle
 */
// Import required java libraries
import algorithm.Optimal;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class signinform extends HttpServlet {
    

    public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
	  String title = "Using GET Method to Read Form Data";
      String docType =
      "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";
      String nextView;
      if(request.getParameter("email").equalsIgnoreCase("satejwagle@gmail.com")&&request.getParameter("password").equalsIgnoreCase("pass"))
        nextView = "successfullogin.jsp";  
      else
          nextView = "index.html";
           
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
  }
}
