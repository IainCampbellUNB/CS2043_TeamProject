package project.group4;
	
public abstract class Teacher
{
	private final String NAME;
	private final int ID;
	
	public Teacher(String NAME, int ID)
	{
		this.NAME = NAME;
		this.ID = ID;
	}
	
	public String getName() 
	{
		return NAME;
	}
	
	public int getID() 
	{
		return ID;
	}
	public String toString()
	{
		String result = NAME + " " + ID;
		
		return result;
		
	}
}