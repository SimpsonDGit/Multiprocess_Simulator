package processSim;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Driver
{
	public static void main(String [] args) 
	{
		@SuppressWarnings("resource")
		Scanner user_in = new Scanner(System.in);
		int processNo = 0;
		
		final int processMIN = 10;
		final int processMAX = 30;
		
		System.out.println("\n\t\tMULTIPROCESSOR SIMULATION");
		System.out.println("\t\t==========================\n");
		
		try
		{				
			do
			{
				//Prompt user to specify the number of processes they would like the simulation to perform
				System.out.print("Enter the number of processes you would like to use (between 10 and 30): ");
				processNo = user_in.nextInt();
	
				if (processNo < processMIN || processNo > processMAX)
				{
					System.out.print("Please enter a value between 10 and 30...\n\n");
					processNo = 0;
				}
				else
				{
					System.out.println("Processes: " + processNo);
					
				}
			}while(processNo == 0);
		}
		catch(InputMismatchException e)
		{
			System.out.println("Please enter an integer value");
			//x = 0;
		}
		
		try 
		{
			//Create text file reports
			FileWriter slfw = new FileWriter("Integer Shared List.txt", true);
			FileWriter asfw = new FileWriter("Process Report.txt", true);
			
			//Create File Report Headings with direct output streams
			PrintWriter slpw = new PrintWriter(slfw);
			slpw.println("\t\tList Values");
			slpw.println("\t\t========\n");
			PrintWriter aspw = new PrintWriter(asfw);
			aspw.println("\n");
			aspw.println("\t\t\t\t\t\t\t\tPROCESS  QUEUE");
			aspw.println("\t\t\t\t\t\t\t\t==============");
			aspw.println("\n");
			
			ProcessSim simulation = new ProcessSim();
			
			//Begin simulation
			simulation.callSim(slpw);
			simulation.processList(processNo, aspw);
			simulation.schedulingP(aspw);
			aspw.close();
			simulation.afterResults(slpw);
		}
		catch (IOException e)
		{
			System.out.println("File creation error");
	        e.printStackTrace();
		}
							
	}
				
}

