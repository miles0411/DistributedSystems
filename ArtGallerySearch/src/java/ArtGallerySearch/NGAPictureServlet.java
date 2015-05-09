/**
 * @Date   Jan 31, 2014
 * @author Preston
 * @Note   This Java servlet is to simulate a search of NGA collections
 */

package Project1Task4;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "NGAPictureServlet",
        urlPatterns = {"/NGAPictureServlet"})

public class NGAPictureServlet extends HttpServlet {

   
    NGAPictureModel searchingModel = null;
    
    //Serving as the main(), instantiate the NGAPictureModel class for further use
    @Override
    public void init() {
        searchingModel = new NGAPictureModel();
    }
    
    
    //Once the user submits request, this method will be called to renew 
    //relevant parameters.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String artistName = request.getParameter("input");
        
        String ua = request.getHeader("User-Agent");
        
        
        boolean mobile;
        // Check the user's brower environment
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
  
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        
         String nextView;
         
         //Check the user's input is valid, if not, redirect the user to the welcome page.
         if (!artistName.equals("")) {
            
             
            //If the user's input is valid, call doSearch method in the instantiated class
            //to retreive data in NGA collections 
            searchingModel.doSearch(artistName);
            request.setAttribute("NGApictureURL",
                    searchingModel.NGAPictureSize(
                            (mobile) ? "mobile" : "desktop"));
            request.setAttribute("NGApictureTitle", searchingModel.getPictureTag());
            nextView = "result.jsp";
        } else {
            
            nextView = "prompt.jsp";
        }
        //If the result is decided, direct the user to the result page
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
        
        
    }

    
}
