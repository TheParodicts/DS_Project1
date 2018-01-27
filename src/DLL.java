
/**
 * An implementation of Doubly Linked Lists for the Mat Pat project.
 * @author Brian Rodriguez Badillo
 *
 */
public class DLL {
	private int size;
	public CustomerNode<Customer> first;
	public CustomerNode<Customer> last;
	
	/**
	 * Creates a new Doubly Linked List of size 0, that holds CustomerNodes<Customer>.
	 */
	public DLL(){
		size=0;
		first=new CustomerNode<Customer>();
		last=new CustomerNode<Customer>();
		first.setNext(last);
		last.setPrev(first);
	}
	
	/**
	 * 
	 * @return - list size
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * Adds a CustomerNode after the desired CustomerNode in the list.
	 * @param target - CustomerNode before which a new node is to be added.
	 * @param c - new CustomerNode to be added.
	 */
	public void addNodeBefore(CustomerNode<Customer> target, CustomerNode<Customer> c){
		size++;
		CustomerNode<Customer> temp = new CustomerNode<Customer>(new Customer(c.getElement().getArrivalTime(), 
				c.getElement().getID(), c.getElement().getOrderTime(), 
				c.getElement().getPaid(), c.getElement().getPatience()));
		if(target == first){
			temp.setNext(target);
			target.setPrev(temp);
			first = temp;
			
			return;
		}
		temp.setNext(target);
		temp.setPrev(target.getPrev());
		target.setPrev(temp);
		temp.getPrev().setNext(temp);
	}
	
	/**
	 * Adds a new CustomerNode to the end of the list.
	 * @param c - CustomerNode to be added.
	 */
	public void addNode(CustomerNode<Customer> c){
		CustomerNode<Customer> temp = new CustomerNode<Customer>(new Customer(c.getElement().getArrivalTime(), 
																	c.getElement().getID(), c.getElement().getOrderTime(), 
																	c.getElement().getPaid(), c.getElement().getPatience()));
		if(this.isEmpty()){
			first=last=temp;
			size++;
			return;
		}
		last.setNext(temp);
		temp.setPrev(last);
		last=temp;
		size++;
	}
	
	/**
	 * Reverses the order of the DLL passed in.
	 * @param in - DLL to be reversed
	 * @return - Reversed DLL.
	 */
	public DLL reverse(DLL in){
		DLL temp = new DLL();
		CustomerNode<Customer> c = in.last;
		while(c!=null){
			temp.addNode(new CustomerNode<Customer>(new Customer(c.getElement().getArrivalTime(), 
					c.getElement().getID(), c.getElement().getOrderTime(), 
					c.getElement().getPaid(), c.getElement().getPatience())));
			c = c.getPrev();
		}
		return temp;
	}
	
	/**
	 * 
	 * @return - if list is empty (boolean)
	 */
	public boolean isEmpty(){
		return size<=0;
	}
	

	
/**
 * Removes the desired CustomerNode from the list.
 * @param n - CustomerNode to be removed from list.
 * @return
 */
	public CustomerNode<Customer> remove(CustomerNode<Customer> n) throws NullPointerException{
		if(n == null) 
			return null;
		
		 if(n == first){
			first=first.getNext();
			if(first!=null)
				first.setPrev(null);
			size--;
			return n;
		}
			
		else if (n == last){
			last=last.getPrev();
			if(last!=null)
				last.setNext(null);
			size--;
			return n;
		}
		
		else{
			//if(n.getPrev()!=null)
				n.getPrev().setNext(n.getNext());
		//	if(n.getNext()!=null)
				n.getNext().setPrev(n.getPrev());
			size--;
			return n;
		}
	}

	/**
	 * Empties the list.
	 */
	public void reset(){
		while (!this.isEmpty()){
			remove(first);
			remove(last);
		}
		first = null;
		last=null;
	}
}
