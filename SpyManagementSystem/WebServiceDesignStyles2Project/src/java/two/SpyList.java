/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two;

import java.util.Collection; 
import java.util.Iterator; 
import java.util.Map; 
import java.util.TreeMap;

/**
 *
 * @author Preston
 * @version Provided by Course Instructor
 * @Date March 21, 2014
 */
public class SpyList { 
 
        private Map tree = new TreeMap(); 

        private static SpyList spyList = new SpyList(); 

        SpyList() { 
        } 

    /**
     * This is to get the SpyList instance for storing data of spies.
     * @return SpyList type list
     */
    public static SpyList getInstance() { 
        return spyList;  
    }

    /**
     * This is to add a new record of spy in a tree data structure. The key is spy's name, and the rest of spy's info will be that key's value.
     * @param s A Spy type parameter needs to be given
     */
    public void add(Spy s) { 
        tree.put(s.getName(), s); 
    }

    /**
     * This is to delete a new record of spy in a tree data structure.
     * @param s A Spy type parameter needed to be given
     */
    public void delete(Spy s) { 
        tree.remove(s.getName()); 
    }

    /**
     * This is to get the record of a certain spy. 
     * @param userID A String type as representation of the spy needed to be given
     * @return A Spy type record will be returned. 
     */
    public Spy get(String userID) { 
        return (Spy) tree.get(userID); 
    } 

    /**
     * This is a method to represent the entire tree by string
     * @return A String type record representing the entire tree
     */
    public Collection getList() { 
        return tree.values(); 
    } 

   /**
     * This is toString method overriding the definition in the Object class 
     * in order to customize our representation of a Spy type record
     * @return A String type record representing a single Spy will be returned
     */
    public String toString() { 

        StringBuffer representation = new StringBuffer(); 
        Collection c = getList(); 
        Iterator sl = c.iterator(); 
        while(sl.hasNext()) { 
            Spy spy = (Spy)sl.next(); 
            representation.append("Name: " + spy.getName()+" Title: " + spy.getTitle()+ 
            " Location: " + spy.getLocation()+" | "); 
        } 
            return representation.toString(); 
    } 

    /**
     * This is a toXML method helping representing the spylist in XML format
     * @return A String type result representing the entire spylist in XML format will be returned
     */
    public String toXML() { 
        
        StringBuffer xml = new StringBuffer(); 
        xml.append("<spylist>\n"); 

        Collection c = getList();  
        Iterator sl = c.iterator(); 
        while(sl.hasNext()) { 
            Spy spy = (Spy)sl.next(); 
            xml.append(spy.toXML()); 
        } 
        
        xml.append("</spylist>"); 

        System.out.println("Spy list: " + xml.toString()); 
        return xml.toString(); 
    } 

}
