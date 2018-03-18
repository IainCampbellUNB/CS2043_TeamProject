package project.group4;


public class OnCallTeacher extends Teacher
{
		private Schedule dailySchedule;
		private String weeklyTallyCount;
		private String monthlyTallyCount;
		private String termTallyCount;
		private AbsenceTracker submittedAbsence;
		private boolean absent;
		private AssignmentTracker assignments;
		//set up a method to set period of spare
		
		
		public OnCallTeacher(String NAME, String ID, Schedule dailySchedule) {
			super(NAME,ID);
			this.dailySchedule = dailySchedule;
			this.absent = false;
		}
		
		public void setToAbsent(){
			absent = true;
		}
		
		public void submitAbsenceSchedule(AbsenceTracker submittedAbsence){
			if(this.absent = true)
			{
				this.submittedAbsence = submittedAbsence;
			}
		}
		
		
		public void attachAssignmentTrackerToTeacherObject(AssignmentTracker assignments){
			this.assignments = assignments;
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
			/******************************************************
			 * BUG WITH THIS CAUSE NULL (what is commented out)
			 ******************************************************/
			//result += "\nASSIGNMENTS: " + assignments.toString();
			result += "\nCOUNTS: WeeklyTally: " + weeklyTallyCount + " ";
			result += "MonltyTally: " + monthlyTallyCount + " ";
			result += "TermTally: " + termTallyCount +" \n";
			return result;
		}
}
