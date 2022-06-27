package processSim;

public class ProcessQueue 
{
	private static Node Front;
	private static Node Back;
	static int Count;

	public ProcessQueue() 
	{
		Front = null;
		Back = null;
		Count = 0;
	}

	public ProcessQueue(Node back) 
	{
		Back = back;
		Front = back;
		Count = 0;
	}

	//Mutators
	public static void setFront(Node head) 
	{
		Front = head;
	}
	
	public static void setBack(Node back) 
	{
		Back = back;
	}
	
	public static void setCount(int count) 
	{
		Count = count;
	}
	
	//Accessors
	public static Node getFront() 
	{
		return Front;
	}
	
	public static Node getBack() 
	{
		return Back;
	}

	public static int getCount() 
	{
		return Count;
	}

	//Add process to Queue 
	public static void Enqueue(ProcessInfo data) 
	{
		Node temp = new Node(data);
		if (Front == null) 
		{
			Front = temp;
		} 
		else 
		{
			if (Back == null) 
			{
				Back = temp;
				Front.setNextNode(Back);
			} 
			else 
			{
				Node prevBack = Back;
				Node newBack = temp;
				prevBack.setNextNode(newBack);
				Back = newBack;
			}
		}
		Count++;
	}
	
	//Remove Process from Queue after its execution
	public static ProcessInfo Dequeue() 
	{
		Node nodeToReturn = Front;
        if (Front == null) 
        {
            return null;
        } 
        else 
        {
            if (Back == null) 
            {
                Front = null;
            } 
            else 
            {
                Front = Front.getNextNode();
            }
            Count--;
        }
        return nodeToReturn.getData();
	}

	public static void displayList()
	{
		if (Front == null) 
		{
			System.out.println("The Queue has no Nodes...");
		}
		else
		{
			Node temp = Front;
			
			System.out.print("\nPID\t Base Address\t\tTask\t\tPriority \t    Create Time  \t   Sleep Time");//\t\t\t\t\tStart Time \t\t\t\tEnd Time \t\t\tAttempts \t\t\tSleep Time \n");	
			System.out.print("\n===\t ============\t\t====\t\t======== \t    ===========   \t   ==========\n");
			while (temp != null) 
			{
				String output = temp.getData().Display();
				temp = temp.getNextNode();
				System.out.println(output);
			}
		}
	}
}
