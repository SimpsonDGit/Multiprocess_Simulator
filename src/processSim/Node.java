package processSim;

public class Node 
{
	private ProcessInfo Data;
	private Node NextNode;

	public Node() 
	{
		Data = new ProcessInfo();
		NextNode = null;
	}

	public Node(ProcessInfo data) 
	{
		Data = data;
		NextNode = null;
	}

	
	//Mutators
	public void setData(ProcessInfo data) {
		Data = data;
	}
	
	public void setNextNode(Node nextNode) 
	{
		NextNode = nextNode;
	}
	
	//Accessors
	public ProcessInfo getData() 
	{
		return Data;
	}
	
	public Node getNextNode() 
	{
		return NextNode;
	}

	
}
