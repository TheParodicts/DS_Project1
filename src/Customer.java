/**
 * Describes a Customer Object with the required criteria for the Mat Pat Project.
 * @author Brian Rodriguez Badillo
 *
 */
public class Customer {
private int arrivalTime;
private int id;
private int orderTime;
private float orderCost;
private int patience;

/**
 * Creates a new Customer Object.
 * @param inTime - Customer's arrival time.
 * @param id - Customer's ID number.
 * @param orderTime - Time it takes to prepare the customer's order.
 * @param cost - Cost of customer's order.
 * @param patience - How much time the customer is willing to wait after they arrive at the restaurant.
 */
public Customer(int inTime, int id, int orderTime, float cost, int patience){
	arrivalTime = inTime;
	this.id = id;
	this.orderTime = orderTime;
	orderCost = cost;
	this.patience = patience;
}


/**
 * 
 * @return - Cost of Order
 */
public float getPaid(){
	return orderCost;
}

/**
 * 
 * @return - Time it takes to prepare their order.
 */
public int getOrderTime(){
	return orderTime;
}

/**
 * 
 * @return - How long the customer is willing to wait after they arrive.
 */
public int getPatience(){
	return patience;
}

/**
 * 
 * @return - Customer's ID Number
 */
public int getID(){
	return id;
}

/**
 * 
 * @return - Time when the Customer arrives at the restaurant.
 */
public int getArrivalTime(){
	return arrivalTime;
}
}
