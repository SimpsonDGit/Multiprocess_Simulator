package processSim;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ProcessInfo implements Runnable
{
	Scanner user_in = new Scanner(System.in);
	private int PID, task, priority, attempts, sleeptime;
	String baseAddress, operation;
	private LocalDateTime createTime, startTime, endTime;
	DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyy HH:mm:ss");
	
	public ProcessInfo ()
	{
		setPID(0);
		setTask(0);
		setBaseAddress("");
		setPriority(0);
		setAttempts(0);
		setSleeptime(0);
		setCreateTime(null);
		setStartTime(null);
		setEndTime(null);
	}


	public int getPID() {
		return PID;
	}


	public void setPID(int pID) {
		PID = pID;
	}


	public int getTask() {
		return task;
	}


	public void setTask(int task) {
		this.task = task;
	}
	

	public String getBaseAddress() {
		return baseAddress;
	}


	public void setBaseAddress(String baseAddress) {
		this.baseAddress = baseAddress;
	}
	
	public int getPriority() {
		return priority;
	}

	//Determine the priority of a Process based on its Task
	public void setPriority(int pNumber) 
	{
		switch(pNumber)
		{
		case 1:
			this.priority = 1;
			break;
		case 2:
			this.priority = 2;
			break;
		case 3:
			this.priority = 3;
			break;
		case 4:
			this.priority = 4;
			break;
		case 5:
			this.priority = 5;
			break;
		}
	}


	public int getAttempts() {
		return attempts;
	}


	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}


	public int getSleeptime() {
		return sleeptime;
	}


	public void setSleeptime(int sleeptime) {
		this.sleeptime = sleeptime;
	}


	public LocalDateTime getCreateTime() {
		return createTime;
	}
	
	public void setOperation(int t)
	{
		switch(task)
		{
			case 1:
				this.operation = "Insert";
				break;
			case 2:
				this.operation = "Remove";
				break;
			case 3:
				this.operation = "Sort List";
				break;
			case 4:
				this.operation = "Search";
				break;
			case 5:
				this.operation = "Count";
				break;
		}
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}


	public LocalDateTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}


	public LocalDateTime getEndTime() {
		return endTime;
	}


	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	//Return a string displaying the intial attributes of the Process
	public String Display()
	{
		String op = operation.replace(" List", "");
		return PID + "\t   "+ baseAddress + "\t        "+ op + "\t\t   " + priority + "\t\t " + format.format(createTime) + "\t\t" + sleeptime;
	}
	
	//Return a string containing all attributes of the Process
	public String finalDisplay()
	{
		return PID + "\t "+ baseAddress + "\t        "+ operation + "\t\t    " + priority + "\t\t " + format.format(createTime) + "\t\t" + sleeptime + "\t\t   " + format.format(startTime) + "\t   " + format.format(endTime) + "\t        " + attempts;   
	}
	
	//Create thread to manipulate concurrency control
	Thread thread = null;
	public void createThread(PrintWriter aspw) 
	{
        if (thread == null) {
            thread = new Thread(this);
            startTime = LocalDateTime.now();
            thread.start();
            try 
            {
                thread.join();
            } 
            catch (InterruptedException ex) 
            {
                ex.printStackTrace();
            }
            endTime = LocalDateTime.now();

            String message = finalDisplay();
            
            //Writing processes results to file and removing them from the Queue
            aspw.println(message);
            ProcessQueue.Dequeue();
        }
    }
	

	//Operations to perform when thread.start() is called 
	@Override
	public void run() 
	{
		 int value = 0, key;attempts ++;

	            switch(task)
	            {	            	
	            	case 1:
	            		System.out.print("\nEnter an integer to add to the list: " );
	            		value = user_in.nextInt();
	            		
		                IntegerData id = new IntegerData();
		                IntegerList.addInt(id,value);

		                System.out.printf("Value %d added to list", value);
		                System.out.print("\n");
		                break;
		                
	            	case 2:
	            		System.out.println("\nRecord Removed: " + IntegerList.removeInt());
		                break;
		                
	            	case 3:
	                    IntegerList.sortList();
		                System.out.println("\nData Sorted");
		                break;

	            	case 4:
	            		System.out.print("\nEnter key to find value: ");
		                key = user_in.nextInt();
		                
		                if (IntegerList.searchInt(key) > -1) 
		                {
		                    System.out.printf("Value at %d is %d", key, IntegerList.searchInt(key));
		                    System.out.print("\n");
		                } 
		                else 
		                {
		                    System.out.println("\nNo value was assigned to the given key...");
		                }
		            	break;     			                
	            	case 5: 
			               System.out.println("\nSum of current integers in the list: " + IntegerList.sumInts());
			               break;
	            }
	            	            
	      }
			
}
