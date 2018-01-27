import java.math.BigDecimal;

/**
 * This class allows the creation of objects which can perform the Mat Pat Simulation project as
 * described in ICOM 4035.
 * It contains a startSim method and various sorting methods.
 * @author Brian Rodriguez Badillo
 *
 */
public class Simulation {
	int time;
	float profit;
	int customersServed;
	DLL inList;
	DLL processingList;
	
	/**
	 * Creates a new Simulation object.
	 * This can be used to run simulations, sort Doubly Linked Lists
	 * according to the desired approach, and return the results of the
	 * Pat and Mat simulations.
	 */
	public Simulation(){
		time=0;
		profit=0;
		customersServed=0;
		inList = new DLL();
		processingList = new DLL();
	}
	
	/**
	 * Begins the simulation with the current inList.
	 * If inList is empty, the simulation
	 * will return 0,0.
	 * @return float[] - returns an array of size 2 of floats. the [0] index
	 * contains the customers served, and the [1] index, the profit.
	 */
	public float[] startSim(){
		//Passes the current sorted list to the simulation.
		processingList=inList;
		
		//Because nothing happens at time 0, start at time 1.
		time =1;
		//Customer currently being processed.
		CustomerNode<Customer> customerBeingServed=null;
		//Used for keeping track of where we are in the line.
		CustomerNode<Customer> customerLine;
		
		//Run the simulation until all customers have been served or left.
		while(!processingList.isEmpty()){
			//Pick the customer to be processed
			
			customerBeingServed=processingList.first;
			//Pick the first person in line who is actually in the restaurant.
			while (customerBeingServed.getElement().getArrivalTime()>time){
				customerBeingServed = customerBeingServed.getNext();
				if (customerBeingServed == null) break;
				}
			//If the customer is impatient, remove them from the line and check the next person.
			while(customerBeingServed!=null && customerBeingServed.getElement().getPatience() + customerBeingServed.getElement().getArrivalTime() < time){
				customerLine = customerBeingServed.getNext();
					processingList.remove(customerBeingServed);
					customerBeingServed=customerLine;
					while (customerBeingServed != null && customerBeingServed.getElement().getArrivalTime()>time){
						customerBeingServed = customerBeingServed.getNext();
						if (customerBeingServed == null) break;
						}
					if(customerBeingServed==null)break;
				}
			
			if( processingList.isEmpty()) break;
			//If nobody is being attended, but there are still people, advance time and try again.
			if(customerBeingServed==null) time++;
			//Serve the person, charge them, and repeat the loop.
			else{
				time = time + customerBeingServed.getElement().getOrderTime();
				profit+= customerBeingServed.getElement().getPaid();
				processingList.remove(customerBeingServed);
				customersServed++;
			}
		}
		
		//Return stats.
		float[] stats = {customersServed, profit};
		time=customersServed=0;
		profit=0;
		return stats;
	}
	
	
	/**
	 * Sorts the input Doubly Linked List by First Come First Served criteria.
	 * Sets this sorted list as the Simulation Object's inList
	 * @param in - Doubly Linked List (DLL) to be sorted.
	 */
	public void patApproach(DLL in){
		//Reset the current inList and time.
		inList.reset();
		time=0;
		//While the lists are not the same size.
		while(in.getSize()>inList.getSize()){
			CustomerNode<Customer> temp = in.first;
			while (temp !=null){
				//If the customer has arrived at current time, add them to the list.
				if(temp.getElement().getArrivalTime()==time){
					inList.addNode(temp);
				}
				temp = temp.getNext();
			}
			time++;
		}
		time=0;
	}
	
	/**
	 * Sorts the input Doubly Linked List by Last Come First Served criteria.
	 * Sets this sorted list as the Simulation Object's inList
	 * @param in - Doubly Linked List (DLL) to be sorted.
	 */
	public void matApproach(DLL in){
		//Sort the list by Pat's approach, then reverse it.
		patApproach(in);
		inList = inList.reverse(inList);
	}
	
	/**
	 * Sorts the input Doubly Linked List by Highest-Profit-First criteria.
	 * Sets this sorted list as the Simulation Object's inList
	 * @param in - Doubly Linked List (DLL) to be sorted.
	 */
	public void maxApproach(DLL in){
		//Reset inList.
		inList.reset();
		//temp = node to sort into list.
		CustomerNode<Customer> temp = in.first;
		//check = iterator node used to compare against for sorting purposes.
		CustomerNode<Customer> check;
		//While lists are not the same size.
		while(in.getSize()!=inList.getSize()){
			//while there are still customers to add.
			while (temp !=null){
				//If the line is empty, add the first one in.
				if(inList.isEmpty()){
					inList.addNode(temp);
					temp = temp.getNext();
				}
				//Else, begin sorting.
				else{
					//reset sorting node's position.
					check = inList.first;
					//While the node is not null.
					while( temp!=null){
						//If the node to be added has a higher profit than the current customer, add them before this person.
						if(temp.getElement().getPaid() > check.getElement().getPaid()){
							inList.addNodeBefore(check, temp);
							temp = temp.getNext();
							check = inList.first;
						}
						//If that's the end of the list, add them to the end of it.
						else if (check.getNext()==null){
							inList.addNode(temp);
							temp = temp.getNext();
							check = inList.first;
						}
						//Else, check against the next person in line.
						else check = check.getNext();
					}
					
				}
				
			}
		}
	}
	
	/**
	 * Sorts the input Doubly Linked List by Shortest-Order time-First criteria.
	 * Sets this sorted list as the Simulation Object's inList
	 * @param in - Doubly Linked List (DLL) to be sorted.
	 */
	public void pacApproach(DLL in){
		//Reset the inList and establish nodes.
		inList.reset();
		CustomerNode<Customer> temp = in.first;
		CustomerNode<Customer> check;
		
		while(in.getSize()!=inList.getSize()){
			while (temp !=null){
				//Add the first node by FIFO
				if(inList.isEmpty()){
					inList.addNode(temp);
					temp = temp.getNext();
				}
				//begin sorting.
				else{
					check = inList.first;
					while( temp!=null){
						//If the node to add's order is faster than the current node's, add them before this one.
						if(check != null && temp.getElement().getOrderTime() < check.getElement().getOrderTime()){
							inList.addNodeBefore(check, temp);
							temp = temp.getNext();
							check = inList.first;
						}
						//If it's the end of the line, add them to the back.
						else if (check== inList.last){
							inList.addNode(temp);
							temp = temp.getNext();
							check = inList.first;
						}
						//Else, check against the next person.
						else check = check.getNext();
					}
					
				}
				
			}
		}
	}
	
	/**
	 * Rounds up the floats to the desired decimal place. Used
	 * for consistency in outputting prices.
	 * @param d- float to round
	 * @param decimalPlace - number of decimal places to round to.
	 * @return- returns a BigDecimal with value of the input float rounded to the desired decimal places.
	 */
	public BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
        return bd;
    }
}
