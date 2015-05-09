


import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class JavaRMIService extends UnicastRemoteObject implements Bumper{
    
    public JavaRMIService() throws RemoteException{
        
    }
  
    BigInteger big = new BigInteger("0");
    
    //@Override
    public boolean bump() throws RemoteException{
        
        big = big.add(new BigInteger("1"));
        return true;
        
    }
    
    //@Override
    public BigInteger get() throws RemoteException{
        
        return big;
        
    }
    
}
