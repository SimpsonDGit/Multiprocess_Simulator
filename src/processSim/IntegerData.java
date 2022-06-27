package processSim;

public class IntegerData 
{
	private int Value;

	public IntegerData()
	{
		setValue(0);
	}

	public IntegerData(int value)
	{
		Value = value;
	}
	
	public void setValue(int value)
	{
		Value = value;
	}
	
	public int getValue()
	{
		return Value;
	}

	public void display()
	{
		System.out.print("Value: " + Value + "\n");
	}
}
