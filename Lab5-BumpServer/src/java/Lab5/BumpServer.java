
package Lab5;

import java.math.BigInteger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "Bump")
public class BumpServer {

    BigInteger big = new BigInteger("0");
    
    @WebMethod(operationName = "Bump")
    public Boolean Bump() {
        
        big = big.add(new BigInteger("1"));
        return true;
        
    }
    
        @WebMethod(operationName = "get")
    public BigInteger get() {
        
        return big;
        
    }
    
}
