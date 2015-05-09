/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two;

/**
 * This program is the spy class used in web service.
 * Last Modified: Mar 21, 2014
 * @author Hongxiao, Preston
 */
public class Spy {

    private String name;
    private String title;
    private String location;
    private String password;
/**
 * Constructor of the spy class
 */
    public Spy() {
    }
/**
 * Return the password of the spy
 * @return 
 */
    public String getPassword() {
        return password;
    }
/**
 * Set the password of the spy
 * @param password 
 */
    public void setPassword(String password) {
        this.password = password;
    }
/**
 * Return the location of the spy
 * @return 
 */
    public String getLocation() {
        return location;
    }
/**
 * Return the name of the spy
 * @return 
 */
    public String getName() {
        return name;
    }
/**
 * Return the title of the spy
 * @return 
 */
    public String getTitle() {
        return title;
    }
/**
 * Set the location of the spy
 * @param location 
 */
    public void setLocation(String location) {
        this.location = location;
    }
/**
 * Set the name of the spy
 * @param name 
 */
    public void setName(String name) {
        this.name = name;
    }
/**
 * Set the title of the spy
 * @param title 
 */
    public void setTitle(String title) {
        this.title = title;
    }
/**
 * Constructor of the spy class, with the value of name, title, location and password
 * @param name
 * @param title
 * @param location
 * @param password 
 */
    public Spy(String name, String title, String location, String password) {
        this.name = name;
        this.title = title;
        this.location = location;
        this.password = password;
    }
/**
 * Constructor of the spy class, with the value of name, title and location
 * @param name
 * @param title
 * @param location 
 */
    public Spy(String name, String title, String location) {
        this.name = name;
        this.title = title;
        this.location = location;
    }
/**
 * Return the spy info in a String which is in the XML format
 * @return 
 */
    public String toXML() {
        StringBuffer xml = new StringBuffer();
        xml.append("<spy>");
        xml.append("<name>" + name + "</name>");
        xml.append("<title>" + title + "</title>");
        xml.append("<location>" + location + "</location>");
        xml.append("<password>" + password + "</password>");
        xml.append("</spy>");
        return xml.toString();
    }
/**
 * Main method to test the constructor
 * @param args 
 */
    public static void main(String args[]) {
        Spy s = new Spy("james", "spy", "Pittsburgh", "james");
        System.out.println(s);
    }
}
