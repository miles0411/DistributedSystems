/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author satejwagle
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    
   Optimal opt = null;
   boolean end;
   String leave_hours = "21:00";
   String hours;
   int VisitCount;
   String Visited = "";
   String Missed = "";

   
   
//    @Override
    public void init()
    {
        VisitCount = 0;
        hours = "15:00";
        end = false;
        int size = 10;
       opt = new Optimal("pairwise_travel_time.csv", "address_list.txt", "section.csv", size);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        VisitCount += 1;
        if(request.getParameter("leave_hours")!= leave_hours && request.getParameter("leave_hours")!= "")
        {
            System.out.println("this is the start"+request.getParameter("leave_hours")+"end");
            opt.earlyOff(request.getParameter("leave_hours"));
            leave_hours = request.getParameter("leave_hours");
        }
            
        if(request.getParameter("hours")!=null)    
            hours = request.getParameter("hours");
            
            opt.CalcCycle(hours);
            String Address[] = opt.getCurrentAndNextAddress();
            request.setAttribute("Address1", Address[0]);
            request.setAttribute("Address2", Address[1]);
            request.setAttribute("Coordinates", Address[2]);
            request.setAttribute("time_to_leave",leave_hours);
            String page = null;
            
            if(end)
            {
                Time defaultStartTime = new Time(9,0,0);
                page = "endSurvey.jsp";	
		String[] currentTime = leave_hours.split(":");
		Time t = new Time(Integer.valueOf(currentTime[0]), Integer.valueOf(currentTime[1]), 0);
		long diffFromBegin = t.getTime() - defaultStartTime.getTime();
                long minute = (diffFromBegin / (1000 * 60)) % 60;
                long hour = (diffFromBegin / (1000 * 60 * 60)) % 24;
                String difference = hour + " hrs " + minute + " mins";
                long average_time = diffFromBegin/(VisitCount-2) ;
                minute = (average_time / (1000 * 60)) % 60;
                hour = (average_time / (1000 * 60 * 60)) % 24;
                String average = hour + " hrs " + minute + " mins";
                Scanner s = null;
                try {
                    String address ="address.csv";
                       String dname = "/Users/satejwagle/NetBeansProjects/capstone/src/java/algorithm/" + address;
                       File f = new File(dname);
                       s = new Scanner(f);
                       //s.useDelimiter(",");   //Use the normal expression and exclude data we imagine they are not "WORDS"
                   } catch (FileNotFoundException e) {

                   }
                while (s.hasNextLine()) {
                       String[] curr = s.nextLine().split(",");
                       if(!Visited.contains(curr[1]))
                       Missed=Missed+"<br/>"+curr[1];
                   }
                request.setAttribute("totaltime", difference);
                request.setAttribute("avgtime", average);
                request.setAttribute("Visited", Visited);
                request.setAttribute("Missed", Missed);
                
            }
            else 
                page = "nlocation.jsp";
            RequestDispatcher view = request.getRequestDispatcher(page);
            System.out.println("start"+ Address[1]+"end");
            if(Address[1].equals("100 BONIFAY ST"))
            {
                end = true;
            }
            else
                Visited += "<br/>" + Address[1] ; 
            opt.finishSurvey();
            view.forward(request, response);
    }


}
