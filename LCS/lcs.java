/*	

*/

import java.util.*;
import java.io.*;

public class lcs
{ 

	public static String DNAString(int length) 
	{
		Random random = new Random();
		StringBuilder rand_dna = new StringBuilder(length);

		for (int i = 0; i < length; i++) 
		{
			rand_dna.append("ACGT".charAt(random.nextInt(4)));
		}

		return rand_dna.toString();
	}
	
	public static String computeLcs(String m, String n) 
	{
		int[][] stringLengths = new int[m.length()+1][n.length()+1];	 
	 
		for (int i = 0; i < m.length(); i++)
		{
			for (int j = 0; j < n.length(); j++)
			{
				if (m.charAt(i) == n.charAt(j))
				{
					stringLengths[i+1][j+1] = stringLengths[i][j] + 1;
				}					
				else
				{
					stringLengths[i+1][j+1] = Math.max(stringLengths[i+1][j], stringLengths[i][j+1]);
				}									
			}				
		}			
	 
		StringBuffer sbuffer = new StringBuffer();
		for (int x = m.length(), y = n.length();x != 0 && y != 0; ) 
		{
			if (stringLengths[x][y] == stringLengths[x-1][y])
			{
				x--;
			}				
			else if (stringLengths[x][y] == stringLengths[x][y-1])
			{
				y--;
			}				
			else 
			{
				assert m.charAt(x-1) == n.charAt(y-1);
				sbuffer.append(m.charAt(x-1));
				x--;
				y--;
			}
		} 
		return sbuffer.reverse().toString();
	}
	
    public static void main(String[] args) throws Exception
    {	
		Random random = new Random();
		ArrayList<String> dnaStrings = new ArrayList<String>();
		ArrayList<String> lcsStrings = new ArrayList<String>();
		while(true)
		{		
			BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter a command");
			String str = inputBuffer.readLine();
			String[] stringElements = null;
			stringElements = str.split(" ");
			
			if(stringElements.length==1)
			{
				if(stringElements[0].equals("quit"))
				{
					System.exit(0);
				}
				else if (stringElements[0].equals("new"))
				{
					int min = 6;
					int max = 20;
					
					lcsStrings.clear();
					
					String dnaString1 = DNAString(random.nextInt((max - min) + 1) + min);
					String dnaString2 = DNAString(random.nextInt((max - min) + 1) + min);
					
					dnaStrings.add(dnaString1);
					dnaStrings.add(dnaString2);
					
					System.out.println("Strands generated successfully:");
					System.out.println(dnaString1);
					System.out.println(dnaString2);			
					System.out.println();			
					
				}
				else if (stringElements[0].equals("lcs")||stringElements[0].equals("LCS"))
				{
					lcsStrings.clear();
					
					if(dnaStrings.size() == 0)
					{
						System.out.println("No DNA strings stored.\n");
					}
					else
					{
						for (int i = 0; i < dnaStrings.size(); i+=2) 
						{
							String dnaString1 = dnaStrings.get(i);
							String dnaString2 = dnaStrings.get(i+1);
							String LCS = computeLcs(dnaString1,dnaString2);
							lcsStrings.add(LCS);
						}
						System.out.println("longest common subsequences (LCS) of all stored pair strands computed successfully.\n");
					}					
				}
				else if (stringElements[0].equals("print"))
				{
					if(dnaStrings.size()==0)
					{
						System.out.println("No DNA strings stored.\n");
					}
					else if(lcsStrings.size()==0)
					{
						System.out.println("LCS not computed.\n");
					}
					else
					{
						for (int i = 0; i < lcsStrings.size(); i++) 
						{
							
							System.out.println("The DNA strands:");
							System.out.println("\t"+dnaStrings.get(2*i));
							System.out.println("\t"+dnaStrings.get(2*i+1));
							System.out.println("LCS is " +lcsStrings.get(i));
							System.out.println("LCS length is "+lcsStrings.get(i).length());						
							System.out.println("______________________________");
						}						
					}					
				}
				else
				{
					System.out.println("Illegal command \n");					
				}				
			}
			else
			{			
				if(stringElements[0].equals("lcs")||stringElements[0].equals("LCS"))
				{
					lcsStrings.clear();
					
					int linecount = 0;
					
					String fileName = stringElements[1]; 				
					String path = System.getProperty("user.dir") + "/" + fileName;		
					BufferedReader inputStream = new BufferedReader(new FileReader(path));

					String line = null;
					
					while ((line = inputStream.readLine()) != null) 
					{
						dnaStrings.add(line);
						linecount++;
					}
					
					if(linecount == 0)
					{
						System.out.println("Input file empty! \n");
						continue;
					}
					
					if(dnaStrings.size()%2 == 0)
					{
						System.out.println("Strands stored successfully.");
					}
					else
					{
						dnaStrings.remove(dnaStrings.size()-1);
						System.out.println("Odd number of Strands detected. Discarded the last strand.");
						System.out.println("Remaining strands stored successfully.\n");
					}
				}
				else
				{
					System.out.println("Illegal Command \n");
				}								
			}			
		}		
	}
}	