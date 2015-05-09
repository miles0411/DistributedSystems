/**
 * @category Capstone Project - US Census Bureau
 * @date Sep 21, 2014
 * @note Will be loaded with a csv file with travel information
 */
package algorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Travel_Cost {
	
	private ArrayList<Integer>[][] p2p;
	private ArrayList<String>[][] p2pEdge;
	private Scanner s; 
	
	Travel_Cost(String filename, String routeSheet, int size){
		
		p2p = new ArrayList[size][size];
		p2pEdge = new ArrayList[size][size];
		
		try{        
                    
			File f = new File("C:\\Users\\Rads\\Documents\\NetBeansProjects\\capstone\\src\\java\\algorithm\\"+filename);
			s = new Scanner(f); 
			s.useDelimiter(",");   //Use the normal expression and exclude data we imagine they are not "WORDS"
		}
		catch ( FileNotFoundException e) {}
		
		while(s.hasNextLine()){
			ArrayList<Integer> al = new ArrayList<Integer>(); 
			
			String[] curLine = s.nextLine().split(",");
			
			for(int i = 2; i < curLine.length; i++){
				al.add(Integer.valueOf(curLine[i]));	
			}
			p2p[Integer.valueOf(curLine[0])][Integer.valueOf(curLine[1])] = new ArrayList<Integer>(al);
			p2p[Integer.valueOf(curLine[1])][Integer.valueOf(curLine[0])] = new ArrayList<Integer>(al);
		}
		try{	
			File f = new File(routeSheet);
			s = new Scanner(f); 
			s.useDelimiter(",");   //Use the normal expression and exclude data we imagine they are not "WORDS"
		}
		catch ( FileNotFoundException e) {}
		while(s.hasNextLine()){
			ArrayList<String> all = new ArrayList<String>(); 
			
			String[] curLine = s.nextLine().split(",");
			
			for(int i = 2; i < curLine.length; i++){
				all.add((curLine[i]));	
			}
			p2pEdge[Integer.valueOf(curLine[0])][Integer.valueOf(curLine[1])] = new ArrayList<>(all);
			p2pEdge[Integer.valueOf(curLine[1])][Integer.valueOf(curLine[0])] = new ArrayList<>(all);
		}
		
		
	}
	
	public int getTravel_Cost(int a, int b, int time){
		
		return p2p[a][b].get(time);

	}
	
	public String getTravel_Edge(int a, int b, int time){
		
		return "[0-N]";

	}

}
