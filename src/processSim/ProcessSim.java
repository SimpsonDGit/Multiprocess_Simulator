package processSim;

import java.util.Random;
//import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ProcessSim 
{	
	Random rint = new Random();
	
	//Creating shared list of integer elements
	IntegerData[] intElements = new IntegerData[20];
	
	//default constructor
	public ProcessSim()
	{}
	
	public void callSim(PrintWriter slpw)
	{
		final int listItemMAX = 15;
				
		//Adding random numbers less than 99 to the array
		for (int i = 0; i < listItemMAX; i++)
		{
			intElements[i] = new IntegerData();
			intElements[i].setValue((rint.nextInt(99))+2);
			IntegerList.addInt(intElements[i], 0);
		}
				
		//Write list of integers to file
		IntegerNode tempHead = IntegerList.getHead();
		if (tempHead == null)
		{
			System.out.println ("Node Head is empty");
		}
		else
		{
			System.out.println("Writing integer nodes to file... ");
			while(tempHead != null)
			{
				slpw.println(tempHead.Display());
				tempHead = tempHead.getNextNode();
			}
			slpw.println("\n");
			slpw.println("\t    CURRENT TOTAL: " + IntegerList.getTotal());
						
			System.out.print("Complete\n");
		}
	}
	
	public void processList(int processNo, PrintWriter aspw)
	{
		final int proccessCount = processNo;
			
		aspw.println("\nPID\t  Base Address\t          Task\t\tPriority \t\t       Create Time \t\t       Sleep Time\t\t            Start Time \t\t            End Time \t\t Attempts");	
		aspw.println("\n----\t---------------------\t          --------\t\t -------- \t\t  ------------------------- \t        --------------\t\t   -------------------------\t    -------------------------\t----------- ");
		Node[] pi = new Node[proccessCount];
		
		//Assigning Tasks to Processes
		for (int counter = 0; counter < proccessCount; counter++)
		{
			pi[counter] = new Node();
			pi[counter].getData().setPID(counter+1);
			String bAdd = String.valueOf(pi[counter].getData());
			bAdd = bAdd.replace("cit3002.ProcessInfo", "");
			pi[counter].getData().setBaseAddress(bAdd);
			pi[counter].getData().setTask((rint.nextInt(5)+1));
			pi[counter].getData().setOperation(pi[counter].getData().getTask());
			int taskNo = pi[counter].getData().getTask();
			pi[counter].getData().setPriority(taskNo);
			pi[counter].getData().setCreateTime(LocalDateTime.now());
			pi[counter].getData().setSleeptime((rint.nextInt(5) + 1));
			
			ProcessQueue.Enqueue(pi[counter].getData());
		}
		
		ProcessQueue.displayList();
		intListDisplay();	
		
	}		
				
	//Display current items in Integer List
	public void intListDisplay()
	{		
		System.out.println("\nINTEGERS IN LIST: "); 
		IntegerList.Display();
	}	
	
	//Effecting processes on Shared List of Integers
	public void schedulingP(PrintWriter aspw)
	{
		Node frontNode = new Node();
		Node nextNode = new Node();
		Node prevNode = new Node();
		
		while (true) 
        {			
			int totalQ = ProcessQueue.getCount();
            if (totalQ != 0) 
            {                	                	
            	for (int left = 0; left < totalQ; left++)
            	{
            		frontNode = ProcessQueue.getFront();
            		nextNode = ProcessQueue.getFront().getNextNode();
            		
            		
            		if (nextNode == null)
                	{
                		ProcessQueue.getFront().getData().createThread(aspw);
                		break;
                	}
            		//Preemptive FIFO Scheduling
                	if (frontNode.getData().getPriority() < nextNode.getData().getPriority() || frontNode.getData().getPriority() == nextNode.getData().getPriority())
                	{                    		
                		ProcessQueue.getFront().getData().createThread(aspw);
                		//ProcessQueue.displayList();
                	}
                	
                	//Nonpreemptive Priority Scheduling
                	else if (frontNode.getData().getPriority() > nextNode.getData().getPriority() )
                	{
                		prevNode = frontNode;
                		ProcessQueue.setFront(nextNode);
                		nextNode = ProcessQueue.getFront().getNextNode();
                		ProcessQueue.getFront().setNextNode(prevNode);
                		prevNode.setNextNode(nextNode);

                		ProcessQueue.getFront().getData().createThread(aspw);
                	}
            	}                   
            }
            else if (totalQ == 0) 
            {
            	intListDisplay(); 
                break;
            }
        }
                
	}
	
	public void afterResults(PrintWriter slpw)
	{
		//Write list of integers to file
		slpw.println("\n");
		slpw.println("\t     After Process Results");
		slpw.println("\t====================\n");
		IntegerNode tempHead = IntegerList.getHead();
		if (tempHead == null)
		{
			System.out.println ("Node Head is empty");
		}
		else
		{
			System.out.println("Writing integer nodes to file... ");
			while(tempHead != null)
			{
				slpw.println(tempHead.Display());
				tempHead = tempHead.getNextNode();
			}
			slpw.println("\n");
			slpw.println("\t   CURRENT TOTAL: " + IntegerList.getTotal());
			System.out.print("Complete\n");
			slpw.close();
		}
	}
		       					
}
	


