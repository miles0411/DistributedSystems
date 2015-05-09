/**
 * @category Capstone Project - US Census Bureau
 * @date Sep 21, 2014
 * @note Main Routine
 */

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;


public class Main {
	
	private static Scanner s = new Scanner(System.in);
	private static int size  = 10;
	public static void main(String[] args){
	
		System.out.println("Program Started.");
		System.out.println("Next-day Survey Taking addresses are loaded....\n");
		Optimal opt = new Optimal("travel.csv", "address.csv", "section.csv", size);
		opt.CalcCycle(0);
		//opt.finishSurvey();
                
		System.out.println("The next location to be visited: \n"+opt.getNextAddress().get(0));
                System.out.println(opt.getNextAddress().get(1));
		size--;
                
                 System.out.println("time taken at location"+opt.lastVisitNo);
		while(s.hasNextLine()){
			
			opt.finishSurvey();
                       System.out.println("time taken at location"+opt.lastVisitNo);
			int time = s.nextInt();
			time = time/10;
			opt.CalcCycle(time);
			//System.out.println(opt.getNextAddress());
			System.out.println("The next location to be visited: \n"+opt.getNextAddress().get(0));
                        System.out.println(opt.getNextAddress().get(1));
                        
                        size--;
			if(size == 1){
				System.out.println("");
				System.out.println("Today's suver locations are finished!");
				System.out.println("Return to survey taker's address:" + "\n"+ opt.getBack());
				System.exit(0);
			}
		}
	}
}