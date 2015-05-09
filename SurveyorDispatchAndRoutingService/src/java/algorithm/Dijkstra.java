package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;


public class Dijkstra{
	
    private int mEdgNum;        
    private int[] mVexs;       
    //private [][] mMatrix;    
    private static final int INF = Integer.MAX_VALUE;
    private static final int MIN = Integer.MIN_VALUE;  
    private static Travel_Cost tc;
    private Scanner s;
	private static LinkedList<Node> al = new LinkedList<Node>();
	private int workTimeLimit = 36;
    Dijkstra(String tcFile, String address, String section, int n){
    	
    	tc = new Travel_Cost(tcFile, section, n);
		
    	try{	
                     String fname= "C:\\Users\\Rads\\Documents\\NetBeansProjects\\capstone\\src\\java\\algorithm\\"+address;
                    System.out.println(fname);
			File f = new File(address);
			s = new Scanner(f); 
			//s.useDelimiter(",");   //Use the normal expression and exclude data we imagine they are not "WORDS"
		}
    	catch ( FileNotFoundException e) {}
    	
    	while(s.hasNextLine()){
			String[] curr = s.nextLine().split(",");
			al.add(new Node(curr[1], Integer.valueOf(curr[2])).setNo(Integer.valueOf(curr[0])));			
		}
    	
    	mVexs = new int[n]; 
    	for(int i = 0 ; i < n; i++){
    		mVexs[i] = i;
    	}
    }
    
    public void start(int vs, int numVertx, int time) {	
    	
    	
    	int surveyTime = 30;
    	int[] prev = new int[numVertx];
    	int[] dist = new int[numVertx];
	    boolean[] flag = new boolean[numVertx];
	
	    
	    for (int i = 0; i < numVertx; i++) {            
	    	if(vs == i) continue;
	    	int duration = tc.getTravel_Cost(vs, i, 0);
	    	dist[i] = duration;
	    }
	    
	    flag[vs] = true;
	    dist[vs] = 0;
	
	    int k=0;
	    
	    for (int i = 1; i < numVertx; i++) {
	        int min = INF;
	        for (int j = 0; j < numVertx; j++) {
	            if (flag[j]==false && dist[j]<min) {
	                min = dist[j];
	                k = j;
	            }
	        }
	        
	        time = 0;
	        flag[k] = true;

	        for (int j = 1; j < numVertx; j++) {
	            if(k == j) continue;
	            if(time > workTimeLimit) break;
	            int duration = tc.getTravel_Cost(k, j, time);
	            int tmp = (min + tc.getTravel_Cost(k, j, time));
	        	time = time + (tmp + surveyTime)/10 ;
	            if (flag[j]==false && (tmp < dist[j]) ) {
	                dist[j] = tmp;
	                prev[j] = k;
	          
	            }
	        }
            prev[i] = k; 
	    }
	    
	    double result=0;
	    time = 0;
	    for (int i = 1; i < prev.length; i++){
	        if(time > workTimeLimit){
	        	break;
	        }
	    	System.out.println(prev[i-1] +" - "+ prev[i] + " " + tc.getTravel_Cost(prev[i-1], prev[i], time));
	    	int duration = tc.getTravel_Cost(prev[i-1], prev[i], time);
	    	time = time + (duration + surveyTime)/10;
	    	result += duration + surveyTime;
		}
		System.out.println(result);
		
	    
    	/*
	    int surveyTime = 2;
    	int[] prev = new int[numVertx];
    	double[] value = new double[numVertx];
	    boolean[] flag = new boolean[numVertx];
	
	    for (int i = 1; i < numVertx; i++) {            
	    	if(vs == i) continue;
	    	value[i] = al.get(i).getValue()/tc.getTravel_Cost(vs, i, time);
	    }
	    
	    flag[vs] = true;
	    value[vs] = 0;
	
	    int k=0;
	    
	    for (int i = 1; i < numVertx; i++) {
	        double min = MIN;
	        for (int j = 0; j < numVertx; j++) {
	            if (flag[j] == false && value[j] > min) {
	                min = value[j];
	                k = j;
	            }
	        }
	 
	        
	        flag[k] = true;
	        time = 0;
	        for (int j = 0; j < numVertx; j++) {
	        	time = time + surveyTime;
	        	if(k == j) continue;
	            if(time > workTimeLimit) break;
	        	
	            double tmp = (min + al.get(j).getValue()/tc.getTravel_Cost(k, j, time));
	            
	            if (flag[j]==false && (tmp > value[j]) ) {
	                value[j] = tmp;
	                prev[j] = k;
	                
	            }
	        }
            prev[i] = k; 
	    }
	    
	    
	    int accum_value = al.get(prev[0]).getValue();
	    time = 0;
	    for (int i = 1; i < prev.length; i++){
	        if(time > workTimeLimit){
	        	break;
	        }
	        System.out.println(prev[i-1] +" - "+ prev[i] + " ");
	    	//System.out.println(prev[i-1] +" - "+ prev[i] + " " + tc.getTravel_Cost(prev[i-1], prev[i], time));
	    	//accum_value += al.get(prev[i]).getValue()/tc.getTravel_Cost(prev[i-1], prev[i], time);
	    	time = time + surveyTime;
		}
	    System.out.println(value);
	    */
    }
}