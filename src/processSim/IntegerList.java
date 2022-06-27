package processSim;

public class IntegerList 
{
	private static IntegerNode Head;
	private static int Total;
	
	public IntegerList()
	{
		setHead(null);
		setTotal(0);
	}

	
	//Mutators
	public static void setHead(IntegerNode head) {
		Head = head;
	}
	public static void setTotal(int total) {
		Total = total;
	}

	//Accessors
	public static IntegerNode getHead() {
		return Head;
	}
	public static int getTotal() {
		return Total;
	}
	
	//Display call for shared list of integers
	public static void Display() 
	{
		if (Head != null) 
		{
			IntegerNode temp = Head;
			while (temp != null) 
			{
				System.out.print(temp.getData().getValue() + "  ");
				temp = temp.getNextNode();
			}
			System.out.println("\nInteger Total: "+ Total +"\n");
		} 
		else 
		{
			System.out.println("Integer List is empty...");
		}
	}
	
	//Add an integer to the shared list
	public synchronized static void addInt(IntegerData data, int inputVal)
	{
		if (inputVal == 0)
		{
			IntegerNode temp = new IntegerNode(data);
			if (temp != null)
			{
				if (Head == null)
				{
					Head = temp;
				}
				else
				{
					temp.setNextNode(Head);
					Head = temp;
				}
				Total++;
			}
		}
		else
		{
			IntegerNode temp = new IntegerNode(inputVal);
			if (temp != null)
			{
				if (Head == null)
				{
					Head = temp;
				}
				else
				{
					temp.setNextNode(Head);
					Head = temp;
				}
				Total++;
			}
		}
		
	}
	
	//Remove an integer from the shared list
	public synchronized static int removeInt()
	{
		int prevNode = 0;
		if (Head != null) 
		{
			prevNode = Head.getData().getValue();
			Head = Head.getNextNode();
			Total--;
		} 
		else 
		{
			System.out.println("Integer list has no values.../n");
		}
		return prevNode;
	}
	
	//Sort the list of integers	
	public synchronized static void sortList()
	{
		if (Head != null)
		{
			boolean sorted = false;

			do 
			{
				IntegerNode tempHead = Head;
				IntegerNode prevNode = null;
				IntegerNode nextNode = Head.getNextNode();
				
				while (nextNode != null) 
				{
					if (tempHead.getData().getValue() > nextNode.getData().getValue()) 
					{
						

						if (prevNode != null) 
						{
							IntegerNode sig = nextNode.getNextNode();

							prevNode.setNextNode(nextNode);
							nextNode.setNextNode(tempHead);
							tempHead.setNextNode(sig);
						} 
						else 
						{
							IntegerNode sig = nextNode.getNextNode();

							Head = nextNode;
							nextNode.setNextNode(tempHead);
							tempHead.setNextNode(sig);
						}

						prevNode = nextNode;
						nextNode = tempHead.getNextNode();
					} 
					else 
					{
						prevNode = tempHead;
						tempHead = nextNode;
						nextNode = nextNode.getNextNode();
					}
				}
				sorted = true;
			} while (sorted == false);
		}
		else
		{
			System.out.println("No values present to be sorted...");
		}
	}
	
	//Retrieve a value by using a unique key
	public synchronized static int searchInt(int key)
	{
		int value = -1;
		if (Head != null) 
		{
			IntegerNode temp = Head;

			while (temp != null) 
			{
				if (temp.getKey() == key) 
				{
					value = temp.getData().getValue();
					break;
				} 
				else 
				{
					temp = temp.getNextNode();
				}
			}
		}
		return value;
	}

	//Count the total number of integers present in the list
	public synchronized static int sumInts() 
	{
		int total = 0;
		if (Head != null) 
		{
			IntegerNode temp = Head;

			while (temp != null) 
			{
				total += temp.getData().getValue();
				temp = temp.getNextNode();
			}
		} 
		else 
		{
			System.out.println("Integer list has no nodes...");
			return -1;
		}
		return total;
	}
	
}
