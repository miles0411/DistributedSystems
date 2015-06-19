/**
 * @category Distributed Systems Project4-2
 * @author Preston Lin
 * @date Apr 8, 2014
 * @note Implement a web service and deploy it by google web engine 
 */

package andrew.cmu.edu.yuyul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;


//build a servlet that take the http request and pass it to a third party for relevant information
@SuppressWarnings("serial")
public class FourSquareDataAPIServlet extends HttpServlet {

    static String jsonText="";
    static String result="";
    static String phone="";
    static String address="";
    static String menu="";
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 
    	 String city = request.getParameter("input");   
    	 city = city.replace(" ", "%20");
         String nextView;
         //this is the API URL provided by FourSquare
         String url="https://api.foursquare.com/v2/venues/explore?client_id=0DQYYJA5SESHOVXGLGKNWKOOB503VJ2PKV3GMHG0SW03I02V&client_secret=L2K41HHZUGIVIROEORISXDXV3LVMS5C2ZJBZBKPVOL1RBAXA&v=20130815&near="+city+"&section=coffee&limit=50";
         
         //Check the user's input is valid, if not, ask the user to give the city name
         if (!city.equals("")) {
        	try {
				readJsonFromUrl(url);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		 }
         else{
        		result = "Please try enter the city you want to search for.";
                phone = "";
                address = "";	
        }
        	     
        //set parameter for JSP to retreive the data
        request.setAttribute("name", result);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("menu", menu);
    	nextView = "result.jsp";

    	//direct to the next view as specified
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    
    }
	
    //this is a method used to read the text returned by the URL line by line
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
      }
    
    //this is a method used to pass in the URL and anaylyze the JSON format text
      public void readJsonFromUrl(String url) throws IOException, JSONException{
        InputStream is = new URL(url).openStream();
        
        //start to retrive key-value pair in JSON
        try {
          result = "";
          phone = "";
          address = "";
          menu="";
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
         
          jsonText = readAll(rd);
          JSONObject json = new JSONObject(jsonText);
          result = json.getJSONObject("response").toString();
          json = new JSONObject(result);
          org.json.JSONArray array = json.getJSONArray("groups");
          result = array.get(0).toString();
          json = new JSONObject(result);
          Random r = new Random();
          int k= r.nextInt(25);
          array = json.getJSONArray("items");
          result = array.get(k).toString();
          json = new JSONObject(result);
          
          result = json.getJSONObject("venue").getString("name");
          
          try{
        	  phone = json.getJSONObject("venue").getJSONObject("contact").getString("phone");
        	  address = json.getJSONObject("venue").getJSONObject("location").getString("address")+", ";
              address += json.getJSONObject("venue").getJSONObject("location").getString("city")+", ";
              address += json.getJSONObject("venue").getJSONObject("location").getString("country");
              menu = json.getJSONObject("venue").getJSONObject("menu").getString("mobileUrl");
              
          }
          //Mark those information if it's not provided by FourSquare
          catch(Exception e){
        	  
        	  if(result.equals("")){
            	  result = "No Coffee Shop in this city.";
              }
              if(phone.equals("")){
            	  phone = "-";
            	 
              }
              if(address.equals("")){
            	  address= "-";
            	  
              }
              if(menu.equals("")){
            	  menu= "-";
          
              }
        	  
          }
        
        } finally {
          is.close();
        }
      }
}
