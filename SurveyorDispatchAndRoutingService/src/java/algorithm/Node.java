/**
 * @category Capstone Project - US Census Bureau
 * @date Sep 21, 2014
 * @note Node class to represent each address as vertx on the graph
 */

/**This class is designed to hold the value defined by a node*/
package algorithm;

public class Node {
	
	/**member varaible to hold the data of a node*/
	private String street;
	private boolean visited;
	/**member varaible to hold the data of a node*/
	private int no;
	private int value;
	
	/**Default constructor*/
	Node(String address, int value){

		this.street = address;
		this.value = value;

	}
	
	/**This is to set the order of a node in a linkedlist
	 * Precondition: The parameter is given
	 * Postcondition: The member variable is set to be equal to the given value
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public Node setNo(int i){
	
		no = i;
		return this;
	}
	
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public int getNo(){
		
		return no;
	}
	
	
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public String getStreet(){
		
		return street;
	}
	
	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	

	/**This is to get the value of a member variable
	 * Precondition: N/A
	 * Postcondition: The value is returned
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public boolean getVisited(){
		
		return visited;
	}
	
	/**This is to set the boolean value in a node to indicate it's visited
	 * Precondition: N/A
	 * Postcondition: The value is set to be true
	 * Best case: theta(1)
	 * Worst case: theta(1)
	 */
	public void setVisited(){
		
		this.visited=true;
	}
	
	public int getValue(){
		
		return value;
	}
}
