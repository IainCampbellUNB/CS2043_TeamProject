package project.group4;


public class OnCallTeacher extends Teacher
{
	private Schedule dailySchedule;
	private String weeklyTallyCount;
	private String monthlyTallyCount;
	private String termTallyCount;
	private AbsenceTracker submittedAbsence;
	private boolean absent;
	//private AssignmentTracker assignments;
	//set up a method to set period of spare
	private int spareIndex;
	private boolean assigned;
	private String skill;
		
	public OnCallTeacher(String NAME, String ID, Schedule dailySchedule) 
	{
		super(NAME,ID);
		this.dailySchedule = dailySchedule;
		this.absent = false;
		this.assigned = false;
		spareIndex = -1;
	}
	
	//They are being assigned so no longer available for assignments.
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
		return spareIndex = this.dailySchedule.determineSparePeriodByIndex();
	}
		
	public String getSparePeriodByValue()
	{
		return this.dailySchedule.getSpareByString();
	}
		
	public void setToAbsent()
	{
		absent = true;
	}
		
	//AttachAbsentScheduleToTeacher
	public void submitAbsenceSchedule(AbsenceTracker submittedAbsence)
	{
		if(this.absent = true)
		{
			this.submittedAbsence = submittedAbsence;
		}
	}
	
	public AbsenceTracker getSubmittedAbsenceSchedule()
	{
		return this.submittedAbsence;
	}
		
	public boolean getAbsentStatus()
	{
		return absent;
	}
		
	public Schedule getSchedule() 
	{
		return dailySchedule;
	}
		
	public void setWeeklyTally(String count)
	{
		 weeklyTallyCount = count;
	}
		
	public void setMonthlyTally(String count) 
	{
		 monthlyTallyCount = count;
	}
		
	public void setTermTally(String count) 
	{
		termTallyCount = count;
	}
	
	public void incrementWeeklyTally()
	{
		double i = Double.parseDouble(weeklyTallyCount);
		i++;
		setWeeklyTally(String.valueOf(i));
	}
		
	public void incrementMonthlyTally()
	{		
		double i = Double.parseDouble(monthlyTallyCount);
		i++;
		setMonthlyTally(String.valueOf(i));
	}
		
	public void incrementTermTally()
	{		
		double i = Double.parseDouble(termTallyCount);
		i++;
		setTermTally(String.valueOf(i));
	}
		
	public String getWeeklyTally() 
	{
		return weeklyTallyCount;
	}
		
	public String getMonthlyTally() 
	{
		return monthlyTallyCount;
	}
		
	public String getTermTally() 
	{
		return termTallyCount;
	}
		
	public boolean hasReachedweeklyMax()
	{
		boolean maxReached = false;
		if(weeklyTallyCount.equals("2.0"))
		{
			maxReached = true;
		}
		return maxReached;
	}
		
	public boolean hasReachedMonthlyMax()
	{
		boolean maxReached = false;
		if(monthlyTallyCount.equals("4.0"))
		{
			maxReached = true;
		}
		return maxReached;
	}
		
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
