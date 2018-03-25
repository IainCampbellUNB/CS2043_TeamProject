


package project.group4;

import java.util.ArrayList;

public class AssignmentAlgorithm {
	
	
	private ArrayList<OnCallTeacher> p1;
	private ArrayList<OnCallTeacher> p2;
	private ArrayList<OnCallTeacher> p3a;
	private ArrayList<OnCallTeacher> p3b;
	private ArrayList<OnCallTeacher> p4;
	final private ArrayList<OnCallTeacher> teacher;
	private ArrayList<ArrayList<OnCallTeacher>> allAvailableTeachers;
	
	
	public AssignmentAlgorithm(ArrayList<OnCallTeacher> teacherList)
	{
			allAvailableTeachers = new ArrayList<ArrayList <OnCallTeacher>();
			allAvailableTeachers(p1)
			p1 = new ArrayList<OnCallTeacher>();
			p2 = new ArrayList<OnCallTeacher>();
			p3a = new ArrayList<OnCallTeacher>();
		    p3b = new ArrayList<OnCallTeacher>();
		    p4 = new ArrayList<OnCallTeacher>();
		    
		//assignOnCallTeacher(teacherList);
		this.teacher = teacherList;
		
		//set the values for p1 to p4 ArrayList
		findAvailablePeriod();
		//Sort each p1 to p4
		sortByTallies();
	}
	
	
	public String printTestAvailable()
	{
		String results="";
		for(int i = 0; i < p1.size();i++)
		{
			results += "P1 Teachers "+  p1.get(i).getName() + " \n";
		}
		
		for(int i = 0; i < p2.size();i++)
		{
			results += "P2 Teachers " + p2.get(i).getName() + " \n ";
		}
		
		for(int i = 0; i < p3a.size();i++)
		{
			results += "P3A Teachers " + p3a.get(i).getName() + " \n";
		}
		
		for(int i = 0; i < p3b.size();i++)
		{
			results += "P3b Teachers " + p3b.get(i).getName() + " \n";
		}
		
		for(int i = 0; i < p4.size();i++)
		{
			results += "P4 Teachers " + p4.get(i).getName() + " \n";
		}
		
		return results;
	}
	
	public void assignOnCallTeacher()
	{
		
		
		ArrayList<AbsenceTracker> absencesList = new ArrayList<AbsenceTracker>();
		
		for(int i = 0; i < teacher.size(); i++)
		{
			if(teacher.get(i).getAbsentStatus())
			{
				absencesList.add(teacher.get(i).getSubmittedAbsenceSchedule());
			}
		}
 		
		
	
		
		for(int i = 0; i < absencesList.size(); i++)
		{
			String value = absencesList.get(i).getPeriodValueAtIndex(0);
			//If the value equals 
			if(value.equals("X"))
			{
				
				if(p1.size() != 0)
				{
					String ID = p1.get(0).getID();
					String name = p1.get(0).getName();
					System.out.println("Name" + name);
					String teacherToAssignValues = absencesList.get(i).getTeacherName();
					findTeacherAndSetValues(teacherToAssignValues,ID,name,0);
					p1.remove(0);
				}
				
				
			}
		}	
		for(int i = 0; i < absencesList.size(); i++)
		{
			String value = absencesList.get(i).getPeriodValueAtIndex(1);
			//If the value equals 
			if(value.equals("X"))
			{
				
				if(p2.size() != 0)
				{
					String ID = p2.get(0).getID();
					String name = p2.get(0).getName();
					System.out.println("Name" + name);
					String teacherToAssignValues = absencesList.get(i).getTeacherName();
					findTeacherAndSetValues(teacherToAssignValues,ID,name,1);
					p2.remove(0);
				}
				
				
			}
		}	
		
		for(int i = 0; i < absencesList.size(); i++)
		{
			String value = absencesList.get(i).getPeriodValueAtIndex(2);
			//If the value equals 
			if(value.equals("X"))
			{
				
				if(p3a.size() != 0)
				{
					String ID = p3a.get(0).getID();
					String name = p3a.get(0).getName();
					String teacherToAssignValues = absencesList.get(i).getTeacherName();
					findTeacherAndSetValues(teacherToAssignValues,ID,name,2);
					p3a.remove(0);
				}
			}
		}	
		
		for(int i = 0; i < absencesList.size(); i++)
		{
			String value = absencesList.get(i).getPeriodValueAtIndex(3);
			//If the value equals 
			if(value.equals("X"))
			{
				if(p3b.size() != 0)
				{
					String ID = p3b.get(0).getID();
					String name = p3b.get(0).getName();
					String teacherToAssignValues = absencesList.get(i).getTeacherName();
					findTeacherAndSetValues(teacherToAssignValues,ID,name,3);
					p4.remove(0);
				}
			}
		}	
		
		for(int i = 0; i < absencesList.size(); i++)
		{
			String value = absencesList.get(i).getPeriodValueAtIndex(4);
			//If the value equals 
			if(value.equals("X"))
			{
				if(p3b.size() != 0)
				{
					String ID = p3b.get(0).getID();
					String name = p3b.get(0).getName();
					String teacherToAssignValues = absencesList.get(i).getTeacherName();
					findTeacherAndSetValues(teacherToAssignValues, ID,name,4);
					p3b.remove(0);
				}
			}
		}
	}

	
	
	private void findTeacherAndSetValues(String teacherToAssignValues, String ID, String name, int index)
	{
		for(int i = 0; i < teacher.size(); i++)
		{
			//System.out.println(teacher.get(i).getName() + "?=" + name);
			if(teacher.get(i).getName().equals(teacherToAssignValues))
			{
				System.out.println("Yes");
				teacher.get(i).hasBeenAssigned();
				teacher.get(i).getSubmittedAbsenceSchedule().setValueByIndex(index, ID);
			}
		}
	}
	
	

	private void findAvailablePeriod()
	{
		
		
		for(int i = 0; i < teacher.size(); i++)
		{
			int spareIndex = teacher.get(i).getSparePeriodByIndex();
			//System.out.println("Teacher All" + teacher.get(i).getName());
			if(spareIndex == 0)
			{
				//System.out.println("SpareIndex: " + spareIndex + "Teacher Nmae: " + teacher.get(i).getName());
				//System.out.println("Teacher AbsenceStatus" + teacher.get(i).getAbsentStatus());
				//System.out.println("Teacher mx" + teacher.get(i).hasReachedMonthlyMax());
				
				if(!teacher.get(i).getAbsentStatus() &&
						!(teacher.get(i).hasReachedMonthlyMax()) && !(teacher.get(i).hasReachedweeklyMax()))
				{
					//System.out.println("ADD" + teacher.get(i).getName());
					this.p1.add(teacher.get(i));
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
	
	//Comparison method for who has the lowest of everything
	private boolean compareTallysBetweenTeachers(OnCallTeacher first,  OnCallTeacher second)
	{
		boolean flag = false;
		String firstWT = first.getWeeklyTally();
		String firstMT = first.getMonthlyTally();
		String secondWT = second.getWeeklyTally();
		String secondMT = second.getMonthlyTally();
		String firstTL = first.getTermTally();
		String secondTL = first.getTermTally();
		
		double FWT = Double.parseDouble(firstWT);
		double FMT = Double.parseDouble(firstMT);
		double SWT = Double.parseDouble(secondWT);
		double SMT = Double.parseDouble(secondMT);
		double FTL = Double.parseDouble(firstTL);
		double STL = Double.parseDouble(secondTL);
		
		if(FWT == SWT)
		{
			if(FMT < SMT)
			{
				flag = true;
			}
			if(FMT == SMT)
			{
				if(FTL < STL)
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
				if(FTL < STL)
				{
					flag = true;
				}
			}
		}
	
		return flag;
	}

}
