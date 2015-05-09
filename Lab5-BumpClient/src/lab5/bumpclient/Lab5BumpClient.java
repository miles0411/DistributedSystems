

package lab5.bumpclient;
import java.math.BigInteger;
import lab5.Bump;


public class Lab5BumpClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        BigInteger ctr = new BigInteger("0");
        BigInteger n = new BigInteger("10000");
        Bump b = new Bump() {

            @Override
            public BigInteger get() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Boolean bump() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        
        while(ctr.compareTo(n)==-1){
           
            b.bump();
            System.out.println(1);
            
        }
        
        System.out.println(b);
        
        
    }


    
}
