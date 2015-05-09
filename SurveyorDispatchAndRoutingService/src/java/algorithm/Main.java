/**
 * @category Capstone Project - US Census Bureau
 * @date Sep 21, 2014
 * @note Main Routine
 */
package algorithm;
import java.util.Scanner;


public class Main {
	
	private static Scanner s = new Scanner(System.in);
	private static int size  = 10;
	public static void main(String[] args){
	
		System.out.println("Program Started.");
		System.out.println("Next-day Survey Taking addresses are loaded....\n");
		System.out.println("Current Time set as 15:00");
		System.out.println("Work End Time set as 21:00");
		Optimal opt = new Optimal("pairwise_travel_time.csv", "address_list.txt", "section.csv", size);
		opt.CalcCycle("15:00");

		for(String add : opt.getCurrentAndNextAddress()){
			System.out.println(add +" ");
		}
	
		while(s.hasNextLine()){
			boolean flag = false;
			String[] input = s.nextLine().split(" ");
			
			if(input[0].equals("1")){
				
				opt.finishSurvey();
				opt.CalcCycle(input[1]);
				for(String add : opt.getCurrentAndNextAddress()){
					System.out.println(add +" ");
					
					if(flag){
						System.exit(0);
					}
					
					if(opt.isSurveyorHomeAddress(add)){
						flag = true;
					}
				}
			}
			else if(input[0].equals("2")){
				
				opt.earlyOff(input[1]);
				System.out.println("Earlier Off Work Time Set!");
			
			}
			else{
				System.exit(0);;
			}
		}
	}
}