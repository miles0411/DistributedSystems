/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab4.customerdbclientproject1;

/**
 *
 * @author Preston
 */
public class CustomerDBClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        NewJerseyClient client = new NewJerseyClient();
        String allXML = client.findAll_XML(String.class);
        System.out.println(allXML);
        client.close();
        
        
    }
    
}
