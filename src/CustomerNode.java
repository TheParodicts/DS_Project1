/**
 * An adaptation of previously discussed Doubly Linked List Nodes.
 * @author Brian Rodriguez Badillo
 *
 * @param <E>
 */
public class CustomerNode<E> {
	private Customer customer;
	private CustomerNode<E> next;
	private CustomerNode<E> prev;
	
	/**
	 * Creates new empty CustomerNode.
	 */
	public CustomerNode() {
		customer = null;
		next=null;
		prev = null;
	}
	
	/**
	 * Creates a new CustomerNode with a Customer Object.
	 * @param c - Customer object to be added to the new CustomerNode.
	 */
	public CustomerNode(Customer c) {
		customer = c;
		next=null;
		prev = null;
	}

	/**
	 * @return - the CustomerNode's Customer Object
	 */
	public Customer getElement() {
		return customer;
	}

	/**
	 * Sets the CustomerNode's Customer object to the desired input.
	 * @param data - Customer to be placed inside CustomerNode.
	 */
	public void setElement(Customer data) {
		customer = data;
		
	}

	/**
	 * 
	 * @return - Next CustomerNode
	 */
	public CustomerNode<E> getNext() {
		return next;
	}
	
	/**
	 * 
	 * @return - Previous CustomerNode
	 */
	public CustomerNode<E> getPrev() {
		return prev;
	}

	/**
	 * Sets the next CustomerNode for the current Node
	 * @param next - CustomerNode to set as next.
	 */
	public void setNext(CustomerNode<E> next) {
		this.next=next;
	}
	
	/**
	 * Sets the previous CustomerNode for the current Node
	 * @param prev - CustomerNode to set as previous.
	 */
	public void setPrev(CustomerNode<E> prev) {
		this.prev=prev;
		
	}
		
		
}