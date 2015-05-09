/**
 * @category Capstone Project - US Census Bureau
 * @date Sep 21, 2014
 * @note Find the optimal path for a given set of address based on the calculated travel cost
 */

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**This class is to define several methods and initialize the loaded data with a customized constrcutor.
 * After the crime data is given, each possibility of path will be travsed to compare their distance. 
 * A path will finally be produced with the minimym distance.
 * Note: No need to consider Big Theta 
 */
public class Optimal {
	
	/**private data variable for holding given crime data based on user's input*/
	private static LinkedList<Node> al = new LinkedList<Node>();
	/**private data variable for holding the optimal Hamiltonan Cycle*/
	private static int[] path;
	/**private data variable for holding the value of distance of the optimal path*/
	static double best_dist = Integer.MAX_VALUE;
	static double best_value = Integer.MIN_VALUE;
	/**instance of travel_cost*/
	private Travel_Cost tc;
	private int surveyTime = 30;
	private ArrayList<Integer> optimal_route = null;
	private Scanner s;
	private int workTimeLimit = 36;
	private ArrayList<String> rpRoute = new ArrayList<String>();
	private ArrayList<String> finalrpRoute = new ArrayList<String>();
	public int lastVisitNo = 0;
	
	Optimal(String addressDataFile, String address, String value, int n){
		
		try{	
                    String dname= "C:\\Users\\Rads\\Documents\\NetBeansProjects\\capstone\\src\\java\\"+address;
               	File f = new File(dname);
			s = new Scanner(f); 
			//s.useDelimiter(",");   //Use the normal expression and exclude data we imagine they are not "WORDS"
		}
		catch ( FileNotFoundException e) {}
		
		tc = new Travel_Cost(addressDataFile, value, n);
		
		while(s.hasNextLine()){
			String[] curr = s.nextLine().split(",");
			al.add(new Node(curr[1], Integer.valueOf(curr[2])).setNo(Integer.valueOf(curr[0])));			
		}
		
		//al.add(new Node(al.get(0).getStreet(),0).setNo(0));
	}
	
	 /** This method is to retreive the crime data specified by the user input, 
	  *  and copy them into a new array. Also, this method will call the method to fill the distance metrix for each pair of the vertices.
	  *  After the above procedures are completed. Arrays will be opened to start to explore every possible Hamiltonan Cycle.
	  *  Precondition: two integer number is already input by the user.
	  *  Postcondition: The distance Metrix will be calculated and filled. Each possible Hamiltonan Cycle existing in the given set of vertices will be explored.
	  *  The cycle with minimum distance will be returend.
	  *  Best Case: theta(n^2)
	  *  Worst Case: theta(n^2)
	 */
	public void CalcCycle(int time){
		
		/**private data variable for working as temp to hold the most temporarily optimal Hamiltonan Cycle*/
		ArrayList<Integer> record = new ArrayList<Integer>();
		record.add(0);
		TSP(record, time);
		//path[path.length-1]=path[0]; //copy the first vertices into the end of the array as the termination
	}
	
	 /** This method is to explore each possible Hamiltonan Cycle by brute force approach recursively until the method hits the base case.
	  *  Precondition: a counting variable n, a double type distance and the total number of vertices should be given
	  *  Postcondition: The brute force approach will be performed. The optimal cycle with minimum distance value shall be returned. 
	  *  Best Case: theta(n^2)
	  *  Worst Case: theta(n^2)
	 */
	public void TSP(ArrayList<Integer> record, int current){
		
		if(record.size() == al.size()){
			
			
			double distance= 0;
			int time = current;
			
			//double value = al.get(record.get(0)).getValue();
			
			int duration=0;
			for(int i=0; i < record.size()-1; i++){
				if(time > workTimeLimit) break;
				duration = tc.getTravel_Cost(record.get(i) , record.get(i+1) , time);
				time = time + (duration+ surveyTime) / 10;
				distance += duration + surveyTime;

			}
			duration = tc.getTravel_Cost(record.get(record.size() - 1) , 0 , time);
			distance += duration;
			/*
			for(int i = 0; i < record.size(); i++){
				value = al.get(record.get(i)).getValue();
			}
			*/
			
			if(distance < best_dist){
				best_dist = distance;
				optimal_route = new ArrayList<Integer>(record);
				finalrpRoute = new ArrayList<String>(rpRoute);
			}
			return;
			
		}
		
		for(int i = 1 ; i < al.size(); i++){
			if(record.contains(al.get(i).getNo())){
				continue;
			}
			record.add(al.get(i).getNo());
			TSP(record, current);
			record.remove(record.size()-1);
		}
	}
	
	 /** This method is to help represent the content of an array by a string.
	  *  Precondition: The optimal path has been determined
	  *  Postcondition: A string representation of the optimal path will be returned.
	  *  Best Case: theta(n)
	  *  Worst Case: theta(n)
	 */
	public ArrayList<String> getNextAddress(){
            
            ArrayList<String> result= new ArrayList<String>();

            String ResultFinal="";
            String NextLocation="";
              /////
           // System.out.println("list of addresses");
                String cycle="";
		int time = 0;
		int dis=0;
		for(int i=0;i<optimal_route.size()-1;i++){
                    
			//System.out.println(optimal_route.get(i) + " - " + optimal_route.get(i+1) +" "+ tc.getTravel_Cost(optimal_route.get(i), optimal_route.get(i+1), time));
			
                        ///new code
                        String tempAddress="";
                        for(Node n1: al){
                        if(n1.getNo() == optimal_route.get(i)){
				tempAddress=n1.getStreet();
                                 ResultFinal=ResultFinal+"Address "+i+":"+tempAddress+"\n";
			}
                }
                      
                     
                       
			
                      
                        
                        //new code ends
                        int duration = tc.getTravel_Cost(optimal_route.get(i), optimal_route.get(i+1), time);
			//System.out.println(duration + " " +surveyTime + " "+ time);
			dis += duration + surveyTime;
			time = time + (duration+ surveyTime) / 10;
			//System.out.println(dis);
			//cycle+=optimal_route.get(i)+": "+al.get(optimal_route.get(i)).getStreet()+"\n";
                /////
                        
                }  //System.out.println(ResultFinal);
                        
		lastVisitNo = optimal_route.get(1);
		
		for(Node n: al){
			if(n.getNo() == lastVisitNo){
				NextLocation= n.getStreet();
                                
			}
                }
		
                result.add(NextLocation);
                result.add(ResultFinal);
                
                
              
                
                
                
                
		return result;
                
	}
	
	public void finishSurvey(){
		
		for(Node n: al){
			
			if(n.getNo() == lastVisitNo){
				al.remove(n);
				return;
			}
		}
	}
	
	public String getBack(){
		
		return al.get(0).getStreet();
		
	}
}

