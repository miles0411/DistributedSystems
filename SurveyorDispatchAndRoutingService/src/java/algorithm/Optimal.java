/**
 * @category Capstone Project - US Census Bureau
 * @date Sep 21, 2014
 * @note Find the optimal path for a given set of address based on the calculated travel cost
 */
package algorithm; 
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**This class is to define several methods and initialize the loaded data with a customized constrcutor.
 * After the crime data is given, each possibility of path will be travsed to compare their distance. 
 * A path will finally be produced with the minimym distance.
 * Note: No need to consider Big Theta 
 */
public class Optimal {
	
	/**private data variable for holding given crime data based on user's input*/
	private static ArrayList<Node> al = new ArrayList<Node>();
	/**private data variable for holding the optimal Hamiltonan Cycle*/
	/**private data variable for holding the value of distance of the optimal path*/
	private static double best_dist = Integer.MAX_VALUE;
	private static double most_visted = Integer.MAX_VALUE;
	static double best_value = Integer.MIN_VALUE;
	/**instance of travel_cost*/
	private Travel_Cost tc;
	private int surveyTime = 30;
	private ArrayList<Integer> optimal_route = null;
	private Scanner s;
	private int workTimeLimit = 36;
	private int lastVisitNo = 0;
	private String lastVisitAdd = "";
	@SuppressWarnings("deprecation")
	private Time defaultStartTime = new Time(15,0,0);
	private double alpha = 0.5;
	private double min_Missed_Value = Double.MAX_VALUE;
	private String surverorHomeAddress ="";
	
	Optimal(String addressDataFile, String address, String section, int n){
		
		try{	
			File f = new File("C:\\Users\\Rads\\Documents\\NetBeansProjects\\capstone\\src\\java\\algorithm\\"+address);
			s = new Scanner(f); 
		}
		catch ( FileNotFoundException e) {}
		
		tc = new Travel_Cost(addressDataFile, section, n);
		
		while(s.hasNextLine()){
			String[] curr = s.nextLine().split(",");
			al.add(new Node(curr[1], Integer.valueOf(curr[2])).setNo(Integer.valueOf(curr[0])));			
		}
		lastVisitAdd = al.get(0).getStreet();
		 surverorHomeAddress = al.get(0).getStreet();
		//lastVisitAdd = al.get(0).getNo() + " " +al.get(0).getStreet();
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
	public void CalcCycle(String s){
		
		String[] currentTime = s.split(":");
		@SuppressWarnings("deprecation")
		Time t = new Time(Integer.valueOf(currentTime[0]), Integer.valueOf(currentTime[1]), 0);
		long diffFromBegin = t.getTime() - defaultStartTime.getTime();
		int minute = (int) TimeUnit.MILLISECONDS.toMinutes(diffFromBegin);
		int time = minute / 10;
		best_dist = Integer.MAX_VALUE;
		most_visted = Integer.MIN_VALUE;
		ArrayList<Integer> record = new ArrayList<Integer>();
		record.add(lastVisitNo);
		TSP(record, time);
		
	}
	
	public void earlyOff(String s){

		String[] earlyOff = s.split(":");

		@SuppressWarnings("deprecation")
		Time t = new Time(Integer.valueOf(earlyOff[0]), Integer.valueOf(earlyOff[1]), 0);
		
		long diff = t.getTime() - defaultStartTime.getTime();
		int minute2 = (int) TimeUnit.MILLISECONDS.toMinutes(diff);
		workTimeLimit = minute2 / 10;

	}
	
	
	 /** This method is to explore each possible Hamiltonan Cycle by brute force approach recursively until the method hits the base case.
	  *  Precondition: a counting variable n, a double type distance and the total number of vertices should be given
	  *  Postcondition: The brute force approach will be performed. The optimal cycle with minimum distance value shall be returned. 
	  *  Best Case: theta(n^2)
	  *  Worst Case: theta(n^2)
	 * @throws InterruptedException 
	 */
	public void TSP(ArrayList<Integer> record, int current){
		
		if(record.size() == al.size()){
			

			ArrayList<Integer> permutation = new ArrayList<Integer>(record);
			permutation.add(0);
			
			double distance= 0;
			int time = current;
			int duration=0;
			int numberWithinLimit = 0;
			for(int i=0; i < permutation.size() - 1; i++){
				
				//Can't finish all suver taking locations..finish as more as possible before the off-time
				if(time > workTimeLimit) {
					
					int missed_Values = 0;
					
					for(int v = numberWithinLimit; v < permutation.size() - 1; v++){
						for(Node n : al){
							if(n.getNo() == permutation.get(v)){
								missed_Values += n.getValue();
							}
						}
					}
					
					double minRemaining = alpha * distance + (1-alpha) + missed_Values;
					
					if(min_Missed_Value >= minRemaining){
					//if(numberWithinLimit >= most_visted & distance < best_dist){
						//most_visted = numberWithinLimit; 
						//best_dist = distance;
						optimal_route = new ArrayList<Integer>(permutation.subList(0, numberWithinLimit));	
						optimal_route.add(0);
						return;
					}
				}
				
				duration = tc.getTravel_Cost(permutation.get(i) , permutation.get(i+1) , time);
				time = time + (duration+ surveyTime) / 10;
				distance += duration + surveyTime;
				numberWithinLimit = i;
			}
			
			if(distance < best_dist){
				best_dist = distance;
				optimal_route = new ArrayList<Integer>(permutation);
				
				/*
				for(Integer n : optimal_route){
					System.out.print(n + " ");
				}
				System.out.println(best_dist+" ");
				*/
				
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
	public String[] getCurrentAndNextAddress(){
		
		String[] addressPair = new String[3];
		addressPair[0] = lastVisitAdd;
		String path ="";
		try{ 
			int a = lastVisitNo;
			lastVisitNo = optimal_route.get(1);
			int b = lastVisitNo;
			 path = getPath(a, b);
			
			for(Node n: al){
				
				if(n.getNo() == lastVisitNo){
					addressPair[1] = n.getStreet();
					//addressPair[1] = n.getNo() +" " + n.getStreet();
					lastVisitAdd = addressPair[1];
				}
			}
		}
		catch(Exception e){
			addressPair[1] = al.get(0).getStreet();
			//addressPair[1] = al.get(0).getNo() + " " + al.get(0).getStreet();
			addressPair[2] =	"{" +
					"\\\"type\\\": \\\"Feature\\\","+
					"\\\"geometry\\\": " +
					 "{"+
							"\\\"type\\\": \\\"MultiPoint\\\","+
							"\\\"coordinates\\\":" + path +
						"},"+
					"\\\"properties\\\": " +
					 	"{"+
							"\\\"address1\\\":\\\"" + addressPair[0]+"\\\"," +
							"\\\"address2\\\":\\\"" + addressPair[1] +"\\\""+
						"}"+
					"}"; 
			return addressPair;
		}
		addressPair[2] =	"{" +
					"\\\"type\\\": \\\"Feature\\\","+
					"\\\"geometry\\\": " +
					 "{"+
							"\\\"type\\\": \\\"MultiPoint\\\","+
							"\\\"coordinates\\\":" + path +
						"},"+
					"\\\"properties\\\": " +
					 	"{"+
							"\\\"address1\\\":\\\"" + addressPair[0]+"\\\"," +
							"\\\"address2\\\":\\\"" + addressPair[1] +"\\\""+
						"}"+
					"}"; 
			return addressPair;
	}
	
	public void finishSurvey(){
		
		for(Node n: al){
			
			if(n.getNo() == lastVisitNo){
				al.remove(n);

				return;
			}
		}
	}
	
	@SuppressWarnings("resource")
	public String getPath(int a, int b){
		
		Scanner s = null;
		try {
			s = new Scanner(new File("C:\\Users\\Rads\\Documents\\NetBeansProjects\\capstone\\src\\java\\algorithm\\pairwise_path"));
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		}
		
		while(s.hasNextLine()){
			
			String curr = s.nextLine();
			if(curr.split(":")[0].equals(a+","+b)){
				return curr.split(":")[1];
			}
		}
		return "";
	}
	
	public void setWeight(double alpha){
		
		this.alpha = alpha;
	}
	
	public boolean isSurveyorHomeAddress(String s){
		
		if(s.equals(surverorHomeAddress)){
			return true;
		}
		
		return false;
		
	}
}
