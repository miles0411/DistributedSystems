/*
 * Carnegie Mellon University, Heinz College
 */

package Project3Task3;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * REST Web Service
 * @author Preston
 * @version Provided by Course Instructor
 * @Date March 21, 2014
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Project3Task3.SpyList.class);
    }
    
}
