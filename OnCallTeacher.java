package project.group4;

public class OnCallTeacher extends Teacher
{
	private Schedule dailySchedule;
	private int weeklyTallyCount = 0;
	private int monthlyTallyCount = 0;
	private int termTallyCount = 0;
	
	public OnCallTeacher(String NAME, int ID, Schedule dailySchedule) 
	{
		super(NAME,ID);
		this.dailySchedule = dailySchedule;
	}
	
	public Schedule getSchedule() 
	{
		return dailySchedule;
	}
	
	public int getWeeklyTally() 
	{
		return weeklyTallyCount;
	}
	
	public int getMonthlyTally() 
	{
		return monthlyTallyCount;
	}
	
	public int getTermTally() 
	{
		return termTallyCount;
	}
	
	public void increaseWeeklyTally(int weeklyTallyCount) 
	{
		this.weeklyTallyCount++;
	}
	
	public void increaseMonthlyTally(int monthlyTallyCount) 
	{
		this.monthlyTallyCount++;
	}
	
	public void increaseTermTally(int termTallyCount) 
	{
		this.termTallyCount++;
	}
}
