/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two;

import java.io.StringReader;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * This program is the server side to add a spy, delete a spy and get the spy list.
 * It takes the String in XML format as arguments.
 * Last Modified: Mar 21, 2014
 * @author Hongxiao, Preston
 */
@WebService(serviceName = "TaskTwo")
public class TaskTwo {

    /**
     * This is a sample web service operation.
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
     /**
     * This is the operation to add a spy.
     */
    @WebMethod(operationName = "addSpy")
    public String addSpy(@WebParam(name = "spy") String inputXML) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document spyDoc = null;
        try {
            builder = factory.newDocumentBuilder();
            spyDoc = (Document) builder.parse(new InputSource(new StringReader(inputXML)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Spy newSpy = new Spy();
        NodeList nl = spyDoc.getElementsByTagName("name");
        Node n = nl.item(0);
        String name = n.getTextContent();
        nl = spyDoc.getElementsByTagName("location");
        n = nl.item(0);
        String location = n.getTextContent();
        nl = spyDoc.getElementsByTagName("title");
        n = nl.item(0);
        String title = n.getTextContent();
        nl = spyDoc.getElementsByTagName("password");
        n = nl.item(0);
        String password = n.getTextContent();
        newSpy.setName(name);
        newSpy.setTitle(title);
        newSpy.setLocation(location);
        newSpy.setPassword(password);
        SpyList currentList = SpyList.getInstance();
        currentList.add(newSpy);
        return "<spy><name>" + name + "</name><location>"
                + location + "</location><password>"
                + password + "</password><title>"
                + title + "</title></spy>";
    }
     /**
     * This is the operation to delete a spy.
     */
    @WebMethod(operationName = "deleteSpy")
    public String deleteSpy(@WebParam(name = "name") String nameXML) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document spyDoc = null;
        try {
            builder = factory.newDocumentBuilder();
            spyDoc = (Document) builder.parse(new InputSource(new StringReader(nameXML)));
        } catch (Exception e) {
            e.printStackTrace();
        }        
        Spy spyToDelete = new Spy();
        NodeList nl = spyDoc.getElementsByTagName("name");
        Node n = nl.item(0);
        String name = n.getTextContent();
        spyToDelete.setName(name);
        SpyList currentList = SpyList.getInstance();
        currentList.delete(spyToDelete);
        return "Spy " + name + "was deleted from the list.";
    }
     /**
     * This is the operation to get the spy list.
     */
    @WebMethod(operationName = "getList")
    public String getList() {
        SpyList currentList = SpyList.getInstance();
        return currentList.toXML();
    }
}
