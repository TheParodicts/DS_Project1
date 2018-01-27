import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * Allows the acquisition of data from the required text files for the Mat Pat Project.
 * Uses FileReader, BufferedReader.
 * @author Brian Rodriguez Badillo
 *
 */
public class Input {
	public String[] csvFiles = new String[100];
	public Customer[] customerArray = new Customer[100];
	public String inputPath;
	
	
	/**
	 * Constructor.
	 */
	public Input(){
		return;
	}
	
	/**
	 * Opens the file with fileName inside the project folder and saves
	 * the csv filenames in the csvFiles array.
	 * @param fileName - name of input.txt to read inside project folder.
	 */
	public void readTextFile(String fileName){
		String line = null;
		
		 try {
	            FileReader fileReader = new FileReader(fileName);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int i=-1;
	            while((line = bufferedReader.readLine()) != null) {
	                i++;
	                csvFiles[i] = line;
	                if(csvFiles.length-15 <i){
	                	String[] temp = new String[csvFiles.length*2];
	                	int j=0;
	                	for(String s: csvFiles){
	                		temp[j] = s;
	                		j++;
	                	}
	                	csvFiles=temp;
	                }
	                
	            }   

	            bufferedReader.close();
	            
	            System.out.println(fileName+" successfully read.");
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                fileName + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + fileName + "'");                  
	        }
		 inputPath = fileName;
	}
	
	/**
	 * Opens and reads the csv file with fileName in project folder
	 * and returns a DLL which contains the customer Line to be used
	 * in simulation.
	 * @param fileName - name of csv file to open in project folder.
	 * @return - DLL with all customers in csv file.
	 */
	public DLL readCSV(String fileName){
		DLL tempList = new DLL();
		 BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        String[] customer= new String[5];
	        if (inputPath.contains("/")){
	        	int index= inputPath.lastIndexOf("/");
	        	fileName = inputPath.substring(0, index+1)+fileName;
	        }

	        try {
	            br = new BufferedReader(new FileReader(fileName));
	            while ((line = br.readLine()) != null) {
	              // use comma as separator
	              customer = line.split(cvsSplitBy);
	              //Remove all signs from the currency item so it can be parsed to float.
	              customer[3]= customer[3].replaceAll("(?<=\\d),(?=\\d)|\\$", "");	 
	              //Add current customer to tempList
	              tempList.addNode(new CustomerNode<Customer>(new Customer(Integer.parseInt(customer[0]), 
		            		  Integer.parseInt(customer[1]), Integer.parseInt(customer[2]),
		            		  Float.parseFloat(customer[3]), Integer.parseInt(customer[4]))));
	       	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return tempList;
	}
}
