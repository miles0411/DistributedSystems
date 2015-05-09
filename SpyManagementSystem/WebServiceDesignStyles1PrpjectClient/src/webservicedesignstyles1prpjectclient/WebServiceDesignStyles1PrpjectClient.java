/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicedesignstyles1prpjectclient;

/**
 * This program is the client side used in web service.
 * The web service accepts normal Strings.
 * Last Modified: Mar 21, 2014
 * @author Hongxiao, Preston
 */
public class WebServiceDesignStyles1PrpjectClient {

    /**Add three spies, get the spy list, delete one spy, and get the updated spy list
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        addSpy("joem", "spy", "Pittsburgh", "joe");
        addSpy("mikem", "spy", "Philadelphia", "mike");
        addSpy("jamesb", "spy", "Toronto", "james");
        System.out.println("Three spies added to spy list");
        System.out.println("The list contains:");
        System.out.println(getList());
        deleteSpy("mikem");
        System.out.println("Mike was deleted");
        System.out.println("The list contains:");
        System.out.println(getList());
    }

    private static String addSpy(java.lang.String name, java.lang.String title, java.lang.String location, java.lang.String password) {
        one.TaskOne_Service service = new one.TaskOne_Service();
        one.TaskOne port = service.getTaskOnePort();
        return port.addSpy(name, title, location, password);
    }

    private static String deleteSpy(java.lang.String name) {
        one.TaskOne_Service service = new one.TaskOne_Service();
        one.TaskOne port = service.getTaskOnePort();
        return port.deleteSpy(name);
    }

    private static String getList() {
        one.TaskOne_Service service = new one.TaskOne_Service();
        one.TaskOne port = service.getTaskOnePort();
        return port.getList();
    }

}
