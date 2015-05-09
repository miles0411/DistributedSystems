/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicedesignstyles2projectclient;

import java.io.StringReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;

/**
 * This program is the client side used in web service.
 * The web service accepts Strings in XML format.
 * Last Modified: Mar 21, 2014
 * @author Hongxiao, Preston
 */
public class WebServiceDesignStyles2ProjectClient {
    
    /**
     * Add two spies, get the spy list, 
     * delete Michael first, get the spy list,
     * then delete Joe, get the spy list,
     * Add Michael again, get the spy list,
     * find out who is in the list at last
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String spy = "<spy><name>Michael</name><location>Pittsburgh</location>"
                + "<password>sesame</password><title>spy master</title></spy>";
        addSpy(spy);
        spy = "<spy><name>Joe</name><location>Philadelphia</location>"
                + "<password>xyz</password><title>spy agent</title></spy>";
        addSpy(spy);
        String list = getList();
        System.out.println("Two Spies \n" + list);
        String nameSpy = "<spy><name>Michael</name></spy>";
        deleteSpy(nameSpy);
        list = getList();
        System.out.println("One Spy \n" + list);
        nameSpy = "<spy><name>Joe</name></spy>";
        deleteSpy(nameSpy);
        list = getList();
        System.out.println("Zero Spies \n" + list);
        spy = "<spy><name>Michael</name><location>Pittsburgh</location>"
                + "<password>sesame</password><title>spy master</title></spy>";
        addSpy(spy);
        list = getList();
        System.out.println("Again! One Spy \n" + list);
        System.out.println("List as XML");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document spyDoc = null;
        try {
            builder = factory.newDocumentBuilder();
            spyDoc = (Document) builder.parse(new InputSource(new StringReader(list)));
        } catch (Exception e) {
            e.printStackTrace();
        }        
        spyDoc.getDocumentElement().normalize();
        NodeList nl = spyDoc.getElementsByTagName("name");
        Node n = nl.item(0);
        String name = n.getTextContent();
        System.out.println("Should be the spy Mike " + name);
    }

    private static String addSpy(java.lang.String spy) {
        two.TaskTwo_Service service = new two.TaskTwo_Service();
        two.TaskTwo port = service.getTaskTwoPort();
        return port.addSpy(spy);
    }

    private static String deleteSpy(java.lang.String name) {
        two.TaskTwo_Service service = new two.TaskTwo_Service();
        two.TaskTwo port = service.getTaskTwoPort();
        return port.deleteSpy(name);
    }

    private static String getList() {
        two.TaskTwo_Service service = new two.TaskTwo_Service();
        two.TaskTwo port = service.getTaskTwoPort();
        return port.getList();
    }

}
