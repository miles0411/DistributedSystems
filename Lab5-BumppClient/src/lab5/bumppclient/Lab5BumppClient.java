/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab5.bumppclient;

import java.math.BigInteger;

/**
 *
 * @author Preston
 */
public class Lab5BumppClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BigInteger ctr = new BigInteger("0");
        BigInteger n = new BigInteger("10000");
        long start = System.currentTimeMillis();
        while(ctr.compareTo(n)<0){
            bump();
            ctr = ctr.add(new BigInteger("1"));
            System.out.println(ctr);
        }
        long stop = System.currentTimeMillis(); 
        System.out.println(get());
        long runtime = stop-start;
        System.out.println(runtime);
    }

    private static Boolean bump() {
        lab5.Bump_Service service = new lab5.Bump_Service();
        lab5.BumpServer port = service.getBumpServerPort();
        return port.bump();
    }

    private static BigInteger get() {
        lab5.Bump_Service service = new lab5.Bump_Service();
        lab5.BumpServer port = service.getBumpServerPort();
        return port.get();
    }
    
}
