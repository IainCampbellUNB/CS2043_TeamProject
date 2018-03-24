package project.group4;

import java.util.ArrayList;

public class AssignmentAlogorithm {
	
	
	private ArrayList<OnCallTeacher> p1;
	private ArrayList<OnCallTeacher> p2;
	private ArrayList<OnCallTeacher> p3a;
	private ArrayList<OnCallTeacher> p3b;
	private ArrayList<OnCallTeacher> p4;
	final private ArrayList<OnCallTeacher> teacher;
	
	
	public AssignmentAlogorithm(ArrayList<OnCallTeacher> teacherList)
	{
		
		ArrayList<OnCallTeacher> p1 = new ArrayList<OnCallTeacher>();
		ArrayList<OnCallTeacher> p2 = new ArrayList<OnCallTeacher>();
		ArrayList<OnCallTeacher> p3a = new ArrayList<OnCallTeacher>();
		ArrayList<OnCallTeacher> p3b = new ArrayList<OnCallTeacher>();
		ArrayList<OnCallTeacher> p4 = new ArrayList<OnCallTeacher>();
		//assignOnCallTeacher(teacherList);
		this.teacher = teacherList;
		findAvailablePeriod();
		sortByTallies();
	}
	
	
	public void assignOnCallTeacher(ArrayList<OnCallTeacher> teacherList)
	{
		
		
		ArrayList<AbsenceTracker> absencesList = new ArrayList<AbsenceTracker>();
		
		for(int i = 0; i < teacherList.size(); i++)
		{
			if(teacherList.get(i).getAbsentStatus())
			{
				absencesList.add(teacherList.get(i).getSubmittedAbsenceSchedule());
			}
		}
 		
		
			for(int i = 0; i < absencesList.size(); i++)
			{
				String value = absencesList.get(i).getPeriodValueAtIndex(0);
				//If the value equals 
				if(value.equals("X"))
				{
					
				}
			}
		
		
	
		
	}
	
	
	
	private void findAvailablePeriod()
	{
		
		
		for(int i = 0; i < teacher.size(); i++)
		{
			int spareIndex = teacher.get(i).getSparePeriodByIndex();
			if(spareIndex == 0)
			{
				if(!teacher.get(i).getAbsentStatus() && !teacher.get(i).hasReachedMonthlyMax() && !teacher.get(i).hasReachedweeklyMax())
				{
					p1.add(teacher.get(i));
				}
			}
		}
		
		for(int i = 0; i < teacher.size(); i++)
		{
			int spareIndex = teacher.get(i).getSparePeriodByIndex();
			if(spareIndex == 1)
			{
				if(!teacher.get(i).getAbsentStatus() && !teacher.get(i).hasReachedMonthlyMax() && !teacher.get(i).hasReachedweeklyMax())
				{
					p2.add(teacher.get(i));
				}
			}
		}
		
		for(int i = 0; i < teacher.size(); i++)
		{
			int spareIndex = teacher.get(i).getSparePeriodByIndex();
			if(spareIndex == 2)
			{
				if(!teacher.get(i).getAbsentStatus() && !teacher.get(i).hasReachedMonthlyMax() && !teacher.get(i).hasReachedweeklyMax())
				{
					p3a.add(teacher.get(i));
				}
			}
		}
		
		for(int i = 0; i < teacher.size(); i++)
		{
			int spareIndex = teacher.get(i).getSparePeriodByIndex();
			if(spareIndex == 3)
			{
				if(!teacher.get(i).getAbsentStatus() && !teacher.get(i).hasReachedMonthlyMax() && !teacher.get(i).hasReachedweeklyMax())
				{
					p3b.add(teacher.get(i));
				}
			}
		}
		
		for(int i = 0; i < teacher.size(); i++)
		{
			int spareIndex = teacher.get(i).getSparePeriodByIndex();
			if(spareIndex == 4)
			{
				if(!teacher.get(i).getAbsentStatus() && !teacher.get(i).hasReachedMonthlyMax() && !teacher.get(i).hasReachedweeklyMax())
				{
					p4.add(teacher.get(i));
				}
			}
		}
	
		
	}
	
	
	private  void sortByTallies()
	{
		
	
		for(int i = 0; i < p1.size()-1; i++)
		{
			int min_idx = i;
		    for (int j = i+1; j < p1.size(); j++)
		    {
		    	if (compareTallysBetweenTeachers(p1.get(j), p1.get(min_idx)))
		    	{
		    		 min_idx = j;
		    	}
		
		            OnCallTeacher temp = p1.get(min_idx);
		            p1.set(min_idx,p1.get(i));
		            p1.set(i,temp);
		    }
		}
		
		for(int i = 0; i < p2.size()-1; i++)
		{
			int min_idx = i;
		    for (int j = i+1; j < p2.size(); j++)
		    {
		    	if (compareTallysBetweenTeachers(p2.get(j), p2.get(min_idx)))
		    	{
		    		 min_idx = j;
		    	}
		
		            OnCallTeacher temp = p2.get(min_idx);
		            p2.set(min_idx,p2.get(i));
		            p2.set(i,temp);
		    }
		}
		
		for(int i = 0; i < p3a.size()-1; i++)
		{
			int min_idx = i;
		    for (int j = i+1; j < p3a.size(); j++)
		    {
		    	if (compareTallysBetweenTeachers(p3a.get(j), p3a.get(min_idx)))
		    	{
		    		 min_idx = j;
		    	}
		
		            OnCallTeacher temp = p3a.get(min_idx);
		            p3a.set(min_idx,p3a.get(i));
		            p3a.set(i,temp);
		    }
		}
		
		for(int i = 0; i < p3b.size()-1; i++)
		{
			int min_idx = i;
		    for (int j = i+1; j < p3b.size(); j++)
		    {
		    	if (compareTallysBetweenTeachers(p3b.get(j), p3b.get(min_idx)))
		    	{
		    		 min_idx = j;
		    	}
		
		            OnCallTeacher temp = p3b.get(min_idx);
		            p3b.set(min_idx,p3b.get(i));
		            p3b.set(i,temp);
		    }
		}
		
		
		for(int i = 0; i < p4.size()-1; i++)
		{
			int min_idx = i;
		    for (int j = i+1; j < p4.size(); j++)
		    {
		    	if (compareTallysBetweenTeachers(p4.get(j), p4.get(min_idx)))
		    	{
		    		 min_idx = j;
		    	}
		
		            OnCallTeacher temp = p4.get(min_idx);
		            p4.set(min_idx,p4.get(i));
		            p4.set(i,temp);
		    }
		}
		
		
		
		
		
		
	}
	
	private boolean compareTallysBetweenTeachers(OnCallTeacher first,  OnCallTeacher second)
	{
		boolean flag = false;
		String firstWT = first.getWeeklyTally();
		String firstMT = first.getMonthlyTally();
		String secondWT = second.getWeeklyTally();
		String secondMT = second.getMonthlyTally();
		String firstTL = first.getTermTally();
		String secondTL = first.getTermTally();
		
		int FWT = Integer.parseInt(firstWT);
		int FMT = Integer.parseInt(firstMT);
		int SWT = Integer.parseInt(secondWT);
		int SMT = Integer.parseInt(secondMT);
		int FTL = Integer.parseInt(firstTL);
		int STL = Integer.parseInt(secondTL);
		
		if(FWT == SWT)
		{
			if(FMT < SMT)
			{
				flag = true;
			}
			if(FMT == SMT)
			{
				if(FTL <= STL)
				{
					flag = true;
				}
			}
		}
		
		if(FWT < SWT)
		{
			if(FMT < SMT)
			{
				flag = true;
			}
			if(FMT == SMT)
			{
				if(FTL <= STL)
				{
					flag = true;
				}
			}
		}
		
		
		
		return flag;
	}
	
	
	
}
