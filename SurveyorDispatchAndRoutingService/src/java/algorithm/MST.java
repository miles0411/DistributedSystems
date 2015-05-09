/**
 * @category Capstone Project - US Census Bureau
 * @date Sep 21, 2014
 * @note Find the greedy path for a given set of address based on the calculated travel cost
 */
package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**This class is to define several methods and initialize the loaded data with a customized constrcutor.
 * After the travel cost data is given, a minimum spanning tree will be constructed, and a preorder travesal will be performed.
 * A path will finally be produced with the distance calculated for that path.
 */
public class MST {
	
	/**private data variable for holding given crime data based on user's input*/
	private static LinkedList<Node> al = new LinkedList<Node>();
	
	/**private data variable for holding the distance value of the produced path*/
	private double [][] distanceMatrix;
	
	/**private data variable for holding on-going traversed vertices.*/
	private static LinkedList<Node>[] graphArray;
	
	/**private data variable for the ideal path based on Prim's algorithm*/
	private static LinkedList<Node> preOrder = new LinkedList<Node>();
	
	/**instance of travel_cost*/
	private Travel_Cost tc;
	
	/**private data variable for holding the distance value of the produced path*/
	private static double Distance = 0;
	private int surveyTime = 2; //10 mins
	private Scanner s;
	
	MST(String addressDataFile, String address, String value, int n){
		
		try{	
			File f = new File(address);
			s = new Scanner(f); 
			//s.useDelimiter(",");   //Use the normal expression and exclude data we imagine they are not "WORDS"
		}
		catch ( FileNotFoundException e) {}
		
		tc = new Travel_Cost(addressDataFile, value, n);
		
		while(s.hasNextLine()){
			String[] curr = s.nextLine().split(",");
			al.add(new Node(curr[1], Integer.valueOf(curr[2])).setNo(Integer.valueOf(curr[0])));			
		}
	}
	
	 /** This method is to construct the minimum tree based on MST-Prim's algorithm
	  *  Precondition: A set of vertices is given by an array. Those vertices have been built by a node class
	  *  Postcondition: A minimum node tree will be produced.
	  *  Best Case: theta(n^2)
	  *  Worst Case: theta(n^2)
	 */
	public void spanningTree(){
		graphArray = new LinkedList[al.size()];
		for(int i=0;i<al.size();i++){
			graphArray[i]= new LinkedList();
		}
		boolean [] visit = new boolean[al.size()]; 
		visit[0]=true;
		double minDistance = -1;
		int minVertax = -1;
		int correlativeVertax = -1;
    
		int time= 0;
		for(int i=0;i<al.size()-1;i++){
			for(int j=0;j<al.size();j++){
				if(!visit[j]){
					for(int k=0;k<al.size();k++){
						if(visit[k]){
							//System.out.print(k +" - "+j+": ");
							//System.out.println(getTravelCost(k,j,time));
							if(minDistance>getTravelCost(k,j,time)||minDistance==-1){
								minDistance=getTravelCost(k,j,time);
								minVertax=j;
								correlativeVertax=k;
							}
							time = time + surveyTime;
						}
					}
				}
			}
			
		graphArray[correlativeVertax].addFirst(al.get(minVertax));
		visit[minVertax]=true;
		minDistance=-1;
		minVertax=-1;
		correlativeVertax = -1;
		time=0;
		}
	}
	
	 /** This method is to retreive the crime data specified by the user input, 
	  *  anc copy them into a new array. Also, this method will call the method to fill the distance metrix for each pair of the vertices.
	  *  Precondition: two integer number is already input by the user.
	  *  Postcondition: The distance Metrix will be calculated and filled. A minimum spanning tree will be constructed. A traversal will be performed preorderly 
	  *  on the minimum spanning tree.
	  *  Best Case: theta(n^2)
	  *  Worst Case: theta(n^2)
	 */
	
	public double getTravelCost(int a, int b, int time){
		
		return tc.getTravel_Cost(a, b, time);

	}
	
	
	 /** This method is to travese a tree preorderly. The node will be visited given a root and construct a path that visit each vertex only once.
	  *  And return the Hamiltonan Cycle.
	  *  Precondition: A minimum spanning has been built for performing travesal.
	  *  Postcondition: The Hamiltonan Cycle will be recorded and returned
	  *  Best Case: theta(n)
	  *  Worst Case: theta(n)
	 */
	public void traverse(int n){
		
		LinkedList<Node> temp = new LinkedList<Node>();
		preOrder.add(al.getFirst());
		for(int i=0;i<graphArray.length;i++){
			temp.addFirst(al.get(i));
			if(!temp.isEmpty()){
				Node curr = temp.removeFirst();
				while(!graphArray[curr.getNo()].isEmpty()){
					temp.add(graphArray[curr.getNo()].removeFirst());
					while(!temp.isEmpty()){
						Node innerCurr = temp.removeFirst();
						if(!graphArray[innerCurr.getNo()].isEmpty()){
							temp.add(graphArray[innerCurr.getNo()].removeFirst());
						}
						preOrder.addLast(innerCurr);
					}	
				}
			
			}
		}
	}
	
	public String getNextAddress(){
		
		String address="\n";
		for(int i = 0 ; i < preOrder.size() ; i++){
			address += preOrder.get(i).getNo()+": "+preOrder.get(i).getStreet()+"\n";
		}
		
		return address;
	
	}
	
	public void doneWithAddress(){
		
		preOrder.removeFirst();
		
	}
	
	public double getDistance(){
		
		int time=0;
		for(int i = 0; i < preOrder.size() - 1; i++){
			//System.out.println((preOrder.get(i).getNo() +" - " + preOrder.get(i+1).getNo()));
			Distance += getTravelCost(preOrder.get(i).getNo(), preOrder.get(i+1).getNo(),time);
			time = time + surveyTime;
		}
		return Distance;
	}
}

