/*	
	Author : Laxman Sai Karthik Mahidhar Gopalabhatla
	Email id : lgopalab@uncc.edu
*/

import java.util.*;
import java.io.*;
import java.lang.*;
public class isort
{ 
    
     static ArrayList<Float> InsertionSort(ArrayList<Float> InputList)
     {
		float key = 0; //value to compare
		int j = 0; //index of other value
		
		//loop through the given input list
		for(int i = 1; i < InputList.size(); i++){
			key = InputList.get(i); //get value at index
			j = i-1; //get index of the previous value
			
			//compare value at index j to key
			while(j >= 0 && InputList.get(j) > key){
				InputList.set(j+1, InputList.get(j));
				j--; //moving j to previous index
			}
			InputList.set(j+1, key); //set with new value
		}
		
		//Returning the whole sorted list
		return InputList;
     }
	
     
	 
	 public static void main(String[] args) throws Exception
     {
		if(args.length == 0)
		{
			System.out.println("Enter a File name");
			System.exit(0);
		}
		else
		{
			String filename = args[0]; // getting the filename from commandline arguments
			ArrayList<Float> Inputlist = new ArrayList<Float>(); //ArrayList for input
			ArrayList<Float> OutputList = new ArrayList<Float>();//ArrayList for Output
			
			String Path = System.getProperty("user.dir") + "/" + filename;//Getting file path here
			BufferedReader inputStream = null;			
			inputStream = new BufferedReader(new FileReader(Path));//Building an input stream with file contents
			
			String line = null;//Used for line by line processing
			String[] NumbersInLine = null;//used for getting numbers from each line			
				
			try
			{					
				// The following loop iterate line by line if in case of a multiline input.
				while ((line = inputStream.readLine()) != null) 
				{
					// This line checks if a line starts with semicolon and if found semicolon is removed.
					if(line.substring(0, 1).equals(";"))
					{
						line = line.substring(1);
						
					}
					
					//This line removes all the spaces in the current read line
					line = line.replaceAll("\\s","");
					
					//This below code splits the read line into an array at the specified delimiters
					NumbersInLine = line.split(";");
					
					//Store the Input Number into a ArrayList
					for(int i=0; i<NumbersInLine.length;i++)
					{
						Inputlist.add(Float.parseFloat(NumbersInLine[i]));
					}		
					
				}
				
			}
			finally
			{
				if (inputStream != null)
					inputStream.close();
			}
				
			
			
			//Calling the insertion sort algorithm by passing the Integer ArrayList and saving the result in OutputList
			OutputList = InsertionSort(Inputlist);
			String output = ""; //This variable is used in saving the output to file
			
			
			//creating a string with the numbers in OutputList
			for (Float s : OutputList)
			{
				output += s + ";";
			}
			
			//removing trailing semicolon
			output = output.substring(0,output.length()-1);
			
			//Printing Output
			System.out.println(output);
			
			//Creating new file
			File outputFile = new File ("output.txt");
			FileWriter fileWriter = new FileWriter (outputFile);
			PrintWriter printWriter = new PrintWriter (fileWriter);	
			
			//writing the output string to output.txt
			printWriter.print(output);
			
			//PrintWriter close
			printWriter.close();
			
		}     
	}
}	