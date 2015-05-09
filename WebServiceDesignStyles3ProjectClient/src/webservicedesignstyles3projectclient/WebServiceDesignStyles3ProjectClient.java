/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservicedesignstyles3projectclient;

/**
 * This is a class for run main method
 * @author Preston
 * @version Provided by Course Instructor
 * @Date March 21, 2014
 */
public class WebServiceDesignStyles3ProjectClient {

    /**
     * Typical main routine method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        newSpyList newList = new newSpyList();
        String spy = "<spy><name>Michael</name><location>Pittsburgh</location>" + 
        "<password>sesame</password><title>spy master</title></spy>"; 
        System.out.println(newList.putXml(spy)); 
        spy = "<spy><name>Joe</name><location>Philadelphia</location>" + 
        "<password>xyz</password><title>spy agent</title></spy>"; 
        System.out.println(newList.putXml(spy)); 
        System.out.println(newList.getListXml());
        System.out.println(newList.getSpyXml("Joe"));
        System.out.println(newList.deleteSpy("Joe"));
        System.out.println(newList.getListXml());
        newList.close();
    
    }
    
}
