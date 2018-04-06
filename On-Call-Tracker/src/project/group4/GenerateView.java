package project.group4;

import java.util.ArrayList;
import java.util.Vector;

public class GenerateView 
{

	public static Vector<Vector<String>> generateCoverageView(ArrayList<OnCallTeacher> teacherList, ArrayList<Teacher> supplyList)
	{
		Vector<Vector<String>> allData = new Vector<Vector<String>>();
		Vector<String> perRowData; 
		Schedule schedule = new Schedule("p1", "p2", "p3a", "p3b", "p4");
		
		for(int i = 0; i < teacherList.size(); i++)
		{
			perRowData = new Vector<String>();
			if(teacherList.get(i).getAbsentStatus())
			{
				OnCallTeacher teacher = teacherList.get(i);
				AbsenceTracker obj1 = teacher.getSubmittedAbsenceSchedule();
				int periodIndex = 0;
				String coveredBy ="";
				
				while(periodIndex <5)
				{
					perRowData = new Vector<String>();
					String value = obj1.getPeriodValueAtIndex(periodIndex);
					int spare = teacher.getSparePeriodByIndex();
					int lunch = teacher.getLunchPeriodByIndex();
					
					if(spare != periodIndex && lunch != periodIndex)
					{
						if(!(value.equals("0.0")) && !(value.equals("X")) && !(value.equals("SP")) && !(value.equals("LU")))
						{
							if(value.charAt(0) == 'A')
							{
								for(int k = 0; k < teacherList.size();k++)
								{
									if(teacherList.get(k).getID().equals(value))
									{
										coveredBy = teacherList.get(k).getName();
									}	
								}
							}
							else 
							{
								for(int k = 0; k < supplyList.size();k++)
								{
									if(supplyList.get(k).getID().equals(value))
									{
										coveredBy = supplyList.get(k).getName();
									}	
								}
							}
							
							if(value.equalsIgnoreCase("NaN"))
							{
								coveredBy = "No one was available";
							}
							
							perRowData.add(schedule.convertIndexToPeriod(periodIndex));
							perRowData.add(teacherList.get(i).getName());
							String roomNum = teacherList.get(i).getSchedule().getRoomNumber(periodIndex);
							perRowData.add(coveredBy);
							perRowData.add(roomNum);	
							allData.add(perRowData);
						}
					}

					periodIndex++;
				}
			}
		}
		
		allData = sortByNames(allData, 1);
		return allData;
	}

	public static Vector<Vector<String>> generateCountView(ArrayList<OnCallTeacher> teacherList)
	{
		Vector<Vector<String>> allData = new Vector<Vector<String>>();
		Vector<String> perRowData = new Vector<String>();
		
		for(int i = 0; i < teacherList.size(); i++){
			
			perRowData = new Vector<String>();
		
			String name = teacherList.get(i).getName();
			String spare = teacherList.get(i).getSparePeriodByValue();
			String week = teacherList.get(i).getWeeklyTally();
			String month = teacherList.get(i).getMonthlyTally();
			String total = teacherList.get(i).getTermTally();
			
			perRowData.add(name);
			perRowData.add(spare);
			perRowData.add(week);
			perRowData.add(month);
			perRowData.add(total);
			allData.add(perRowData);
		}
		
		allData = sortByNames(allData, 0);
		return allData;
	}
	
	
	private static Vector<Vector<String>> sortByNames(Vector<Vector<String>> allData, int colToSort) 
	{
		for(int i = 0; i < allData.size() - 1; i++)
		{
			int indexOfMin = i;
			for(int j = i+1; j < allData.size(); j++)
			{
				if(allData.get(indexOfMin).get(colToSort).compareTo(allData.get(j).get(colToSort)) > 0 )
				{
					indexOfMin = j;
				}
			}
			Vector<String> temp = allData.get(indexOfMin);
			allData.set(indexOfMin, allData.get(i));
			allData.set(i, temp);
		}
		return allData;
	}

	public static Vector<Vector<String>> generateAvailabilityView(ArrayList<OnCallTeacher> teacherList){
		
		
		Vector<Vector<String>> allData = new Vector<Vector<String>>();
		Vector<String> perRowData = new Vector<String>();
		Schedule schedule = new Schedule("p1", "p2", "p3a", "p3b", "p4");
		
		int weeklyCount = 0;
		int monthlyCount = 0;

		for(int periodIndex = 0; periodIndex < 5; periodIndex++)
		{
			perRowData = new Vector<String>();
			String nameNext = determineWhoIsNext(teacherList,periodIndex);
			
			for(int i = 0; i < teacherList.size(); i++)
			{
				OnCallTeacher  teacher = teacherList.get(i);
				int spare = teacher.getSparePeriodByIndex();
				
				if(spare == periodIndex)
				{
					if(!teacherList.get(i).hasReachedweeklyMax() && !teacherList.get(i).hasReachedMonthlyMax())
					{
							weeklyCount++;
					}
					if(!teacherList.get(i).hasReachedMonthlyMax())
					{
						monthlyCount++;
					}
				}
			}
			
			perRowData.add(schedule.convertIndexToPeriod(periodIndex));	
			perRowData.add(String.valueOf(weeklyCount));
			perRowData.add(String.valueOf(monthlyCount));
			perRowData.add(nameNext);
			weeklyCount = 0;
			monthlyCount = 0;
			allData.add(perRowData);
		}
		return allData;
	}
	
	public static String determineWhoIsNext(ArrayList<OnCallTeacher> teacher, int periodIndex)
	{
		String name = "No one available";
		ArrayList<OnCallTeacher> potentials = new ArrayList<OnCallTeacher>();
		
		potentials = findPotentialNextInLines(teacher, periodIndex);
		sortByTallies(potentials);
		
		if(!potentials.isEmpty())
		{
			for(OnCallTeacher onCaller: potentials)
			{
				if(onCaller.getAbsentStatus())
				{
					continue;
				}
				name = onCaller.getName();
			}
		}
		return name;
	}
	
	public static ArrayList<OnCallTeacher> findPotentialNextInLines(ArrayList<OnCallTeacher> teacher, int periodIndex)
	{
		ArrayList<OnCallTeacher> potentials = new ArrayList<OnCallTeacher>();
		
		for(int i = 0; i < teacher.size(); i++)
		{
			int spare = teacher.get(i).getSparePeriodByIndex();

			if(!teacher.get(i).hasReachedMonthlyMax() && !teacher.get(i).hasReachedweeklyMax() && spare == periodIndex)
			{
				potentials.add(teacher.get(i));
			}
		}
		return potentials;
	}
	
	
	public static void printData(Vector<Vector<String>> allData)
	{
		for(int i = 0; i < allData.size(); i++)
		{
			for(int j = 0; j < allData.get(i).size(); j++)
			{
				System.out.print(" " + allData.get(i).get(j));
			}
			System.out.println("");
		}
	}
	
	private static  void sortByTallies(ArrayList<OnCallTeacher> potentials)
	{	
		for(int i = 0; i < potentials.size()-1; i++)
		{
			int min_idx = i;
		    for (int j = i+1; j < potentials.size(); j++)
		    {
		    	if (compareTallysBetweenTeachers(potentials.get(min_idx), potentials.get(j)))
		    	{
		    		 min_idx = j;
		    	}
		    		OnCallTeacher temp = potentials.get(min_idx);
		            potentials.set(min_idx,potentials.get(i));
		            potentials.set(i,temp);
		    }
		}
	}
	
	private static boolean compareTallysBetweenTeachers(OnCallTeacher first,  OnCallTeacher second)
	{
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
				return true;
			}
			if(FMT == SMT)
			{
				if(FTL < STL)
				{
					return true;
				}
			}
		}
		
		if(FWT < SWT)
		{
			if(FMT < SMT)
			{
				return true;
			}
			if(FMT == SMT)
			{
				if(FTL < STL)
				{
					return true;
				}
			}
		}
	
		return false;
	}

}
