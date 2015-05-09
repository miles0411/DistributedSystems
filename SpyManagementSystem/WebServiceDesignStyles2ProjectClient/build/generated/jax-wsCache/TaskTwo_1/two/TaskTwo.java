
package two;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "TaskTwo", targetNamespace = "http://two/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TaskTwo {


    /**
     * 
     * @param spy
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addSpy", targetNamespace = "http://two/", className = "two.AddSpy")
    @ResponseWrapper(localName = "addSpyResponse", targetNamespace = "http://two/", className = "two.AddSpyResponse")
    @Action(input = "http://two/TaskTwo/addSpyRequest", output = "http://two/TaskTwo/addSpyResponse")
    public String addSpy(
        @WebParam(name = "spy", targetNamespace = "")
        String spy);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getList", targetNamespace = "http://two/", className = "two.GetList")
    @ResponseWrapper(localName = "getListResponse", targetNamespace = "http://two/", className = "two.GetListResponse")
    @Action(input = "http://two/TaskTwo/getListRequest", output = "http://two/TaskTwo/getListResponse")
    public String getList();

    /**
     * 
     * @param name
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hello", targetNamespace = "http://two/", className = "two.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://two/", className = "two.HelloResponse")
    @Action(input = "http://two/TaskTwo/helloRequest", output = "http://two/TaskTwo/helloResponse")
    public String hello(
        @WebParam(name = "name", targetNamespace = "")
        String name);

    /**
     * 
     * @param name
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteSpy", targetNamespace = "http://two/", className = "two.DeleteSpy")
    @ResponseWrapper(localName = "deleteSpyResponse", targetNamespace = "http://two/", className = "two.DeleteSpyResponse")
    @Action(input = "http://two/TaskTwo/deleteSpyRequest", output = "http://two/TaskTwo/deleteSpyResponse")
    public String deleteSpy(
        @WebParam(name = "name", targetNamespace = "")
        String name);

}
