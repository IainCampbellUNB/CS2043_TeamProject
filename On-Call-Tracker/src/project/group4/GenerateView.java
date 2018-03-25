package project.group4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class GenerateView {

	public static Vector<Vector<String>> generateCoverageView(ArrayList<OnCallTeacher> teacherList, ArrayList<SupplyTeacher> supplyList)
	{
		Vector<Vector<String>> allData = new Vector<Vector<String>>();
		Vector<String> perRowData = new Vector<String>(); 
		
		for(OnCallTeacher onCaller: teacherList)
		{
			if(onCaller.getAbsentStatus())
			{
				AbsenceTracker obj1 = onCaller.getSubmittedAbsenceSchedule();
				
				int periodIndex = 0;

				String coveredBy ="";
				while(periodIndex <5){
					//Add a check


					perRowData = new Vector<String>();
					String value = obj1.getPeriodValueAtIndex(periodIndex);
					
					if(!(value.equals("0.0")) && !(value.equals("X")))
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
						}else
						{
							for(int k = 0; k < supplyList.size();k++)
							{
								if(supplyList.get(k).getID().equals(value))
								{
									coveredBy = supplyList.get(k).getName();
								}	
							}
						}
					
					perRowData.add(Schedule.convertIndexToPeriod(periodIndex));
					perRowData.add(onCaller.getName());
					String roomNum = onCaller.getSchedule().getRoomNumber(periodIndex);
					perRowData.add(coveredBy);
					perRowData.add(roomNum);
					
					allData.add(perRowData);
					}
					periodIndex++;
				}
			}
		}
		return allData;
	}

	
	public static Vector<Vector<String>> generateCountView(ArrayList<OnCallTeacher> teacherList)
	{
		Vector<Vector<String>> allData = new Vector<Vector<String>>();
		Vector<String> perRowData = new Vector<String>();
		
		for(int i = 0; i < teacherList.size(); i++)
		{
			perRowData = new Vector<String>();
			
			perRowData.add(teacherList.get(i).getName());
			perRowData.add(teacherList.get(i).getSparePeriodByValue());
			perRowData.add(teacherList.get(i).getWeeklyTally() + "");
			perRowData.add(teacherList.get(i).getMonthlyTally() + "");
			perRowData.add(teacherList.get(i).getTermTally() + "");
			allData.add(perRowData);
		}
		
		return allData;
	}
	
	
	public static Vector<Vector<String>> generateAvailabilityView(ArrayList<OnCallTeacher> teacherList)
	{
		Vector<Vector<String>> allData = new Vector<Vector<String>>();
		Vector<String> perRowData = new Vector<String>();
		int weeklyCount = 0;
		int monthlyCount = 0;
	
		for(int periodIndex = 0; periodIndex < 5; periodIndex++)
		{
			perRowData = new Vector<String>();
			String nameNext = determineWhoIsNext(teacherList,periodIndex);
			
			for(OnCallTeacher onCaller: teacherList)
			{
				int spare = onCaller.getSparePeriodByIndex();
				
				if(spare == periodIndex)
				{
					if(!onCaller.hasReachedweeklyMax() && !onCaller.hasReachedMonthlyMax())
					{
						weeklyCount++;
					}
					
					if(!onCaller.hasReachedMonthlyMax())
					{
						monthlyCount++;
					}
				}
			}
			
			perRowData.add(Schedule.convertIndexToPeriod(periodIndex));
			perRowData.add(String.valueOf(weeklyCount));
			perRowData.add(String.valueOf(monthlyCount));
			weeklyCount = 0;
			monthlyCount = 0;
			perRowData.add(nameNext);
			allData.add(perRowData);
		}
		
		return allData;
	}
	
	public static String determineWhoIsNext(ArrayList<OnCallTeacher> teacherList, int periodIndex)
	{
		ArrayList<OnCallTeacher> potentials = new ArrayList<OnCallTeacher>();

		//double lowest = findTheLowestTally(teacher, periodIndex);
		//potentials = findPotentialNextInLines(teacher, lowest, periodIndex);
		//Collections.shuffle(potentials);
		int lowestTallyCount = findTheLowestTally(teacherList, periodIndex);
		potentials = findPotentialNextInLines(teacherList, lowestTallyCount, periodIndex);
		//Collections.shuffle(potentials);

		if(!potentials.isEmpty())
		{
			return potentials.get(0).getName();
		}else 
		{
			return  "No one available";
		}
	}
	
	public static ArrayList<OnCallTeacher> findPotentialNextInLines(ArrayList<OnCallTeacher> teacherList, int lowestTallyCount, int periodIndex)
	{
		ArrayList<OnCallTeacher> potentials = new ArrayList<OnCallTeacher>();
		
		for(OnCallTeacher OnCaller: teacherList){
			
			int spare = OnCaller.getSparePeriodByIndex();
			
			if(spare == periodIndex && !OnCaller.hasReachedMonthlyMax() && OnCaller.getWeeklyTally() == lowestTallyCount)
			{
				potentials.add(OnCaller);
			}
		}
		
		return potentials;
	}
	
	public static int findTheLowestTally(ArrayList<OnCallTeacher> teacherList, int periodIndex)
	{
		int lowestTallyCount = 10000;
	
		for(OnCallTeacher onCaller: teacherList)
		{
			int spare = onCaller.getSparePeriodByIndex();
			
			if(spare == periodIndex)
			{
				int thisTeachersCount = onCaller.getWeeklyTally();
				
				if(lowestTallyCount > thisTeachersCount)
				{
					lowestTallyCount = thisTeachersCount;
				}
			}
			
		}
		
		return lowestTallyCount;
	}
	
}
