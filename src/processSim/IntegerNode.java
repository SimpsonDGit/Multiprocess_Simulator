package processSim;

public class IntegerNode
{
	private static int index = 0;
	private int Key;
	private IntegerData Data;
	private IntegerNode NextNode;

	public IntegerNode()
	{
		setKey(0);
		setData(null);
		setNextNode(null);
	}

	public IntegerNode(int value) 
	{
		Key = index;
		index++;
		Data = new IntegerData(value);
		NextNode = null;
	}

	public IntegerNode(IntegerData data) 
	{
		Key = index;
		index++;
		setData(data);
		NextNode = null;
	}
	
	//Mutators
	public void setKey(int key)
	{
		Key = key;
	}
	
	public void setData(IntegerData data)
	{
		Data = data;
	}
	
	public void setNextNode(IntegerNode nextNode) 
	{
		NextNode = nextNode;
	}
	
	//Accessors
	public int getKey()
	{
		return Key;
	}

	public IntegerData getData()
	{
		return Data;
	}

	public IntegerNode getNextNode() 
	{
		return NextNode;
	}

	public String Display() 
	{
		return "\tKey: "+ Key + "\t\t Value: " + Data.getValue();
	}
}
