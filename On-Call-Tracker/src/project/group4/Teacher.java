package project.group4;
	
public abstract class Teacher
{
	private final String ID;
	private final String NAME;
	
	public Teacher(String ID, String NAME)
	{
		this.NAME = NAME;
		this.ID = ID;
	}
	
	public String getName()
	{
		return NAME;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public String toString()
	{
		return "ID: " +  ID + " NAME: " + NAME;
	}
}
