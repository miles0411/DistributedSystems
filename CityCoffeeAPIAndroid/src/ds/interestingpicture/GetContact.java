/**
 * @category Distributed Systems Project4-2
 * @author Preston Lin
 * @date Apr 8, 2014
 * @note Develop Mobile platform for the Web application deployed on Google Web Engine 
 */

package ds.interestingpicture;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

/*
 * This class provides capabilities to search for an image on FourSquare.com given a search term.  The method "explore" is the entry to the class.
 * Network operations cannot be done from the UI thread, therefore this class makes use of an AsyncTask inner class that will do the network
 * operations in a separate worker thread.  However, any UI updates should be done in the UI thread so avoid any synchronization problems.
 * onPostExecution runs in the UI thread, and it calls the WebView contentReady method to do the update.   
 * 
 */
public class GetContact {
	
		CoffeeSearch ip = null;
	/*
	 * search is the public GetContent method.  Its arguments are the search term, and the CoffeeSearch object that called it.  This provides a callback
	 * path such that the contentReady method in that object is called when the coffee shop's info is available from the search.
	 */
		public void search(String searchTerm, CoffeeSearch ip) {
			this.ip = ip;
			new AsyncSearch().execute(searchTerm);
	
		}

	/*
	 * AsyncTask provides a simple way to use a thread separate from the UI thread in which to do network operations.
	 * doInBackground is run in the helper thread.
	 * onPostExecute is run in the UI thread, allowing for safe UI updates.
	 */
    private class AsyncSearch extends AsyncTask<String, Void, String[]> {
    	
		@Override
		protected String[] doInBackground(String... params) {		
			return search(params[0]);
			
		}

        protected void onPostExecute(String[] s) {	
        	ip.contentReady(s.clone());
        }

        /* 
         * Search FourSquare.com for the searchTerm argument, and return a String that can be put in an TextView and WebView
         */
        private String[] search(String searchTerm) {        
  
    	      // At this point, we have the URL of the coffee store info that resulted from the search which comes from the web serive we deploy on the Google Web Engine.  
        	  //Now load the data we have
    	        try {
    	            	URL u = new URL("http://1-dot-stellar-day-540.appspot.com/YoutubeDataAPIServlet?input="+searchTerm);            	            	
    	            	return getRemoteContent(u);
    	            } catch (Exception e) {
    	                e.printStackTrace();
    	                return null; // so compiler does not complain
    	          }

        }
        

        /*
         * Given a URL referring to the result webPage, return an array containing info we need.
         */
        private String[] getRemoteContent(final URL url) {
            try {
                
                URLConnection conn = url.openConnection();          
    			BufferedReader urlcontents = new BufferedReader(
                                   new InputStreamReader(conn.getInputStream()));
                String str="";
                String [] response = new String[3];
                response[1] ="";
                response[2] ="";
                response[0] = "\n"+"We found a coffee shop for you! If the store provides menu, it will be shown below."+"\n";
                while ((str = urlcontents.readLine()) != null ) {
                    if(str.contains("<")||str.equals("\n")||str.equals("null")||str.equals("")||str.equals("-")||str.contains("{")){   
                       
                    }
                    else if(str.contains("http")){
                    	 response[2] = str;
                    }
                    else{
                    	 response[1] += str+"\n";
                    }
                }
                urlcontents.close();
                return response;
            } catch (IOException e) {
                e.printStackTrace();
                return null;   
            }
        }


    }
}