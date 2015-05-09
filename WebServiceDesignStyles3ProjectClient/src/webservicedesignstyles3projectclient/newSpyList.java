/*
 * Carnegie Mellon University, Heinz College
 */
package webservicedesignstyles3projectclient;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:SpyList [SpyList]<br>
 * USAGE:
 * <pre>
 *        newSpyList client = new newSpyList();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Preston Lin
 * @version Provided by Course Instructor
 * @Date March 21, 2014
 */
public class newSpyList {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/WebServiceDesignStyles3Project/webresources";

    /**
     * This is to invoke server's moethod via URL, please refer to server's description of this method
     */
    public newSpyList() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("SpyList");
    }

    /**
     * This is to invoke server's moethod via URL, please refer to server's description of this method
     */
    public String putXml(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), String.class);
    }

     /**
     * This is to invoke server's moethod via URL, please refer to server's description of this method
     */
    public String getListXml() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    /**
     * This is to invoke server's moethod via URL, please refer to server's description of this method
     */
    public String getSpyXml(String spyName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{spyName}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    /**
     * This is to invoke server's moethod via URL, please refer to server's description of this method
     */
    public String deleteSpy(String spyName) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{spyName})).request().delete(String.class);
    }

    /**
     * This is to inherent client class's method close, in order to close open source safely.
     */
    public void close() {
        client.close();
    }
    
}
