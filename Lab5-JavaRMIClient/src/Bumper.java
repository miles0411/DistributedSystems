/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Preston
 */
interface Bumper extends Remote{
    
    public boolean bump() throws RemoteException;
    public BigInteger get() throws RemoteException;
    
}
