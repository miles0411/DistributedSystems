
/**
 * @Date   Jan 31, 2014
 * @author Preston
 * @Note   This Java servlet is to retrieve collections from NGA search 
 *         and rearrange for customized outputs
 */

package Project1Task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class NGAPictureModel extends HttpServlet {
    
    private String NGApictureTitle="";
    private String NGApictureURLDesktop="";
    private String NGApictureURLMobile="";

    //When this method is called, a name of an artist will be given for further search
    public void doSearch(String artistName) {
        
        String response = "";
        ArrayList<String> arrayTitle = new ArrayList<String>();
        ArrayList<String> arrayThumbnails = new ArrayList<String>();
        
        //simulate the search through the NGA website, send out the search request, 
        //and retreive pages' elements for our use
        try {
                       
            URL url = new URL("https://images.nga.gov/en/search/show_advanced_search_page/"
                    + "?service=search&action=do_advanced_search&language=en&form_name=default&"
                    + "all_words=&exact_phrase=&exclude_words=&artist_last_name="+ artistName + ""
                    + "&keywords_in_title=&accession_number=&school=&Classification=Painting&medium="
                    + "&year=&year2=&open_access=1");
            
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();

            //record all the elements returned by the URLConnect
            Scanner urlcontents = new Scanner(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            
            //catch necessary info, such as picture's URLlink, picture's title etc...
            while (urlcontents.hasNextLine()) {
                str = urlcontents.nextLine();
                if(str.contains("grid_item_list_text =")){   //determine the set of the artist collections goes after this string
                    response = str;
                }
                else if(str.contains("autoZoomText")){  //determine the title of the collections goes after this string

                      while(str.contains("autoZoomText")){ //starts to search and record data we need              
                      
                        arrayTitle.add(str.substring(str.indexOf("Text\">")+6,str.indexOf("</p>"))); //trunk pictures title
                        arrayThumbnails.add(str.substring(str.indexOf("/assets/thumbnails"),str.indexOf(".jpg")+4)); //trunk thumbnails URL link
                        
                        str = str.replaceFirst("autoZoomText\">","");  //start to replace data we have saved for moving to the next set of data we need
                        str = str.replaceFirst("</p>","");
                        str = str.replaceFirst("/assets/thumbnails", "");
                        str = str.replaceFirst(".jpg", "");
                      }
                }
            }
        } catch (IOException e) {
            
            NGApictureTitle="Encounter error! The error code is "+e.getMessage(); //provide error message for techician to assist in solving problem
            
        }
        
       
        //Check NGA has collections according to the user's input, if not, return warning message.
        if(arrayThumbnails.size()!=0){
            
            //retreive a collection assets number for presentation 
            int start = response.indexOf("\'");
            int end = response.indexOf("';", start); 
            String asset = response.substring(start+1, end); 
            String [] assetArray = asset.split(",");
    
            Random rand = new Random();
            
            //Random present a masterpiece of the artist to the user
            int random = rand.nextInt(assetArray.length);
            
            //update the result elements!
            String randselect = assetArray[random];  
            NGApictureTitle = (String) arrayTitle.get(random);
            NGApictureURLDesktop = "src=https://images.nga.gov/?service=asset&action=show_preview&asset="+randselect;
            NGApictureURLMobile = "src=https://images.nga.gov"+arrayThumbnails.get(random);
        }
        else{
            NGApictureTitle = artistName + " is not found!";
            NGApictureURLDesktop="";
            NGApictureURLMobile="";
            
        }
        
    }
    
     //This method is for outer class to load private member variables
     public String NGAPictureSize(String picsize) {
       
        if(picsize.equals("mobile")){
            return NGApictureURLMobile;  //if the user environment is mobile, return thumbnails size picture
            
        }
        else{
            return NGApictureURLDesktop; //if th euser environment is desktop/laptop, return normal size picture
        }
    }
    
    //This is a method for outer class to load private member variables, the title/name of the artist's masterpiece
    //will be returned!
    public String getPictureTag() {
        return (NGApictureTitle);
    }
}
    