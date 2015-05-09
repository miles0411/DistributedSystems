/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * This program is the server side to add a spy, delete a spy and get the spy list.
 * It takes several normal Strings as arguments.
 * Last Modified: Mar 21, 2014
 * @author Hongxiao, Preston
 */
@WebService(serviceName = "TaskOne")
public class TaskOne {
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
    public String addSpy(@WebParam(name = "name") String name,
            @WebParam(name = "title") String title,
            @WebParam(name = "location") String location,
            @WebParam(name = "password") String password) {
        Spy newSpy = new Spy();
        newSpy.setName(name);
        newSpy.setTitle(title);
        newSpy.setLocation(location);
        newSpy.setPassword(password);
        SpyList currentList = SpyList.getInstance();
        currentList.add(newSpy);
        return "Add spy: " + name + ", " + title + ", " + location + ", " + password;
    }
    /**
     * This is the operation to delete a spy.
     */
    @WebMethod(operationName = "deleteSpy")
    public String deleteSpy(@WebParam(name = "name") String name) {
        Spy spyToDelete = new Spy();
        spyToDelete.setName(name);
        SpyList currentList = SpyList.getInstance();
        currentList.delete(spyToDelete);
        return "Delete spy: " + name;
    }
    /**
     * This is the operation to get the spy list.
     */
    @WebMethod(operationName = "getList")
    public String getList() {
        SpyList currentList = SpyList.getInstance();
        return currentList.toString();
    }
}
