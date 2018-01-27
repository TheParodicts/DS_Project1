import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Main from which the program will run and outputs will be written.
 * @author Brian Rodriguez Badillo
 *
 */
public class Main {

	public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		Input input = new Input();
		Simulation sim = new Simulation();
		int maxServed=0;
		float maxProfit=0;
		String inText="input.txt";
		Scanner sc = new Scanner(System.in);
		
		
		input.readTextFile(inText);
		if(input.csvFiles[0]==null){
			do{
				System.out.println("input.txt file not found. \nIf you are using cmd or powershell, please paste your 'input.txt' file and csv files to this project's 'src' folder;\n"
						+ "If you are running through the IDE, paste them in the project folder.\n"
						+ "Alternatively, input the complete path for the input file (with forward slashes) now: ");
				inText = sc.next();
				input.readTextFile(inText);
			}while (input.csvFiles[0]==null);
		}
		
		sc.close();
		int index;
		float [] stats;
		
		//Run the simulations
		for(String s: input.csvFiles){
			if(s!=null){
				maxProfit=maxServed=0;
				//Set the current list of customers for the simulation.
				DLL baseList = input.readCSV(s);
				
				if(baseList.isEmpty()) break;
				
				//Calculate maximum customers.
				maxServed = baseList.getSize();
				CustomerNode<Customer> c = baseList.first;
				
				//calculate maxProfit
				do{
					maxProfit+= c.getElement().getPaid();
					c=c.getNext();
				}while (c!=null);
				
				//Creates the output file.
				index=s.lastIndexOf('.');
				PrintWriter writer = new PrintWriter(s.substring(0, index)+".out", "UTF-8");
			
				//Print max stats.
				System.out.println("Maximum profit possible: $"+sim.round(maxProfit, 2) +
						"\nMaximum number of customers served possible: "+maxServed);
				
				//Write those results to the output file.
				writer.println("Maximum profit possible: $"+sim.round(maxProfit, 2));
				writer.println("Maximum number of customers served possible: "+maxServed);
				
				
				//Start Pat Approach Sim (FIFO)
				sim.patApproach(baseList);
				stats = sim.startSim();
				System.out.println("Pat’s approach profit: $"+sim.round(stats[1], 2)+
						"\nPat’s approach number of disappointed customers: "+sim.round((maxServed-stats[0]), 0));
				
				//Write the Pat Approach results to the output file.
				writer.println("Pat’s approach profit: $"+sim.round(stats[1], 2));
				writer.println("Pat’s approach number of disappointed customers: "+sim.round((maxServed-stats[0]), 0));
				
				//Start Mat Approach Sim. (LIFO)
				sim.matApproach(baseList);
				stats = sim.startSim();
				System.out.println("Mat’s approach profit: $"+sim.round(stats[1], 2)+
							"\nMat’s approach number of disappointed customers: "+sim.round((maxServed-stats[0]), 0));
				
				//Write the Mat Approach to the output File.
				writer.println("Mat’s approach profit: $"+sim.round(stats[1], 2));
				writer.println("Mat’s approach number of disappointed customers: "+sim.round((maxServed-stats[0]), 0));
				
				//Start Max Approach Sim.(Max profit first)
				sim.maxApproach(baseList);
				stats = sim.startSim();
				System.out.println("Max’s approach profit: $"+sim.round(stats[1], 2)+
							"\nMax’s approach number of disappointed customers: "+sim.round((maxServed-stats[0]), 0));
				
				//Write the Max approach results to the output file.
				writer.println("Max’s approach profit: $"+sim.round(stats[1], 2));
				writer.println("Max’s approach number of disappointed customers: "+sim.round((maxServed-stats[0]), 0));
				 
				//Start Pac Approach Sim. (Shortest order time first)
				sim.pacApproach(baseList);
				stats = sim.startSim();
				System.out.println("Pac’s approach profit: $"+sim.round(stats[1], 2)+
							"\nPac’s approach number of disappointed customers: "+sim.round((maxServed-stats[0]), 0) +"\n\n");
				
				//Write the Pac Approach results to the output file.
				writer.println("Pac’s approach profit: $"+sim.round(stats[1], 2));
				writer.println("Pac’s approach number of disappointed customers: "+sim.round((maxServed-stats[0]), 0));
				 
				//Close the writer for current output file.
				writer.close();
				}
			}
	}
}
