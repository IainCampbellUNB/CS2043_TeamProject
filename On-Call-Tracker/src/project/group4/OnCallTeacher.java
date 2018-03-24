package project.group4;


public class OnCallTeacher extends Teacher
{
	private Schedule dailySchedule;
	private int weeklyTallyCount;
	private int monthlyTallyCount;
	private int termTallyCount;
	private AbsenceTracker submittedAbsence;
	private boolean absent;
	private boolean assigned;
	private int maxWeeklyTallies = 2;
	private int maxMonthlyTallies = 4;
	
	
	public OnCallTeacher(String NAME, String ID, Schedule dailySchedule) {
		super(NAME,ID);
		this.dailySchedule = dailySchedule;
		this.absent = false;
		this.assigned = false;
	}

	public void hasBeenAssigned()
	{
		this.assigned = true;
	}
	
	public boolean getHasBeenAssigned()
	{
		return this.assigned;
	}
	
	public int getSparePeriodByIndex()
	{
		return this.dailySchedule.determineSparePeriodByIndex();
	}
	
	public String getSparePeriodByValue()
	{
		return this.dailySchedule.getSpareByString();
	}
	
	public void setToAbsent()
	{
		absent = true;
	}

	public void submitAbsenceSchedule(AbsenceTracker submittedAbsence)
	{
		if(absent = true)
		{
			this.submittedAbsence = submittedAbsence;
		}
	}

	public AbsenceTracker getSubmittedAbsenceSchedule()
	{
		return this.submittedAbsence;
	}
	
	public boolean getAbsentStatus(){
		return absent;
	}
	
	public Schedule getSchedule() 
	{
		return dailySchedule;
	}
	
	public void setWeeklyTally(int count) 
	{
		 weeklyTallyCount = count;
	}
	
	public void setMonthlyTally(int count) 
	{
		 monthlyTallyCount = count;
	}
	
	public void setTermTally(int count) 
	{
		 termTallyCount = count;
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
	
	public boolean hasReachedweeklyMax()
	{
		return weeklyTallyCount == maxWeeklyTallies;
	}
	
	public boolean hasReachedMonthlyMax()
	{
		return monthlyTallyCount == maxMonthlyTallies;
	}
	
	/*public void increaseWeeklyTally(String weeklyTallyCount) 
	{
		this.weeklyTallyCount++;
	}
	
	public void increaseMonthlyTally(String monthlyTallyCount) 
	{
		this.monthlyTallyCount++;
	}
	
	public void increaseTermTally(String termTallyCount) 
	{
		this.termTallyCount++;
	}*/

	public String toString()
	{
		String result= super.toString();
		result += "\nTERM SCHEDULE: "+ dailySchedule.toString();
		
		if(absent)
		{
			result += "\nABSENT" + submittedAbsence.toString();
		}
		
		result += "\nCOUNTS: WeeklyTally: " + weeklyTallyCount + " ";
		result += "MonltyTally: " + monthlyTallyCount + " ";
		result += "TermTally: " + termTallyCount +" \n";
		return result;
	}
}
