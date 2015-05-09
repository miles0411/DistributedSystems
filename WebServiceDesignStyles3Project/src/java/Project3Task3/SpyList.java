/*
 * Carnegie Mellon University, Heinz College
 */

package Project3Task3;
import java.io.StringReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


/**
 * REST Web Service
 * @author Preston
 * @version Provided by Course Instructor
 * @Date March 21, 2014
 */
@Path("SpyList")
@Singleton 
public class SpyList {

    @Context //PREPARE RELEVENT DATA STRUCTURES FOR FURTHER USE
    private UriInfo context;
    private Map tree = new TreeMap(); 
    private static SpyList spyList = new SpyList();
    
    
    /**
     * Retrieves representation of an instance of SpyList
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getListXml() {
       
       StringBuffer xml = new StringBuffer(); 
       xml.append("<spylist>\n"); 

       Collection c = tree.values(); ;  
       Iterator sl = c.iterator(); 
       while(sl.hasNext()) { 
            Spy spy = (Spy)sl.next(); 
            xml.append(spy.toXML()); 
       } 
       
       xml.append("</spylist>"); 
       return xml.toString(); 
    }
    
    /**
     * Given the spyName, the method is to return the single spy info by the XML representation
     * @param spyName
     * @return
     */
    @GET
    @Path("{spyName}")
    @Produces("application/xml")
    public String getSpyXml(@PathParam("spyName") String spyName) {
       
       Spy spy = (Spy) tree.get(spyName);
       return "The Spy in XML = \n" + spy.toXML(); 
      
    }
    
    /**
     * PUT method for updating or creating an instance of SpyList
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    @Produces("application/xml")
    public String putXml(String content) {
        
        String name=null;
        String location=null;
        String title=null;
        String password=null;
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance(); 
        DocumentBuilder builder;
        Document doc = null;
        try{
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(content))); 
            NodeList nl = doc.getElementsByTagName("spy"); 
            name = doc.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
            location = doc.getElementsByTagName("location").item(0).getFirstChild().getNodeValue();
            title = doc.getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
            password = doc.getElementsByTagName("password").item(0).getFirstChild().getNodeValue();
        }
        catch(Exception e){ 
            e.printStackTrace(); 
        } 

            Spy spy = new Spy(name, title, location, password);        
            tree.put(spy.getName(), spy);     
            return spy.getName() +" has been added to the list.";
        
    }
    
    /**
     * Given the spyname, the spylist will delete the spyName exiting in the list. 
     * @param spyName
     * @return A string will be returned for confirming the spy has been removed!
     */
    @DELETE 
    @Path("{spyName}") 
    @Produces("application/xml") 
    public String deleteSpy(@PathParam("spyName") String spyName){
        
        Spy spy = new Spy(spyName, null, null);   
        tree.remove(spy.getName());
        
        return spyName+" has been removed.";
        
    }
    
}
