package project.group4;

import java.util.ArrayList;

public class GenerateView {

	
	
	public static ArrayList<ArrayList<String>> generateCoverageView(ArrayList<OnCallTeacher> teacherList, ArrayList<Teacher> supplyList)
	{
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData; 
		Schedule schedule = new Schedule("p1", "p2", "p3a", "p3b", "p4");
		for(int i = 0; i < teacherList.size(); i++){
			perRowData = new ArrayList<String>();
			//Only search those who have their absences marked
			if(teacherList.get(i).getAbsentStatus()){
				
				OnCallTeacher teacher = teacherList.get(i);
				
				//Get teacher absent schedule
				AbsenceTracker obj1 = teacher.getSubmittedAbsenceSchedule();
				
				int periodIndex = 0;
				String coveredBy ="";
				while(periodIndex <5){
					perRowData = new ArrayList<String>();
					String value = obj1.getPeriodValueAtIndex(periodIndex);
					
					//Check for slots with assigned code
					if(!(value.equals("0.0")) && !(value.equals("X"))){
						
						//Check for matches
						//If OnCall check teacherList
						if(value.charAt(0) == 'A'){
							for(int k = 0; k < teacherList.size();k++){
								if(teacherList.get(k).getID().equals(value)){
									coveredBy = teacherList.get(k).getName();
								}	
							}
						}
						//Check supplyList
						else{
							
							for(int k = 0; k < supplyList.size();k++){
								if(supplyList.get(k).getID().equals(value)){
									coveredBy = supplyList.get(k).getName();
								}	
							}
						}
					
			
					//System.out.println("Period: " + periodIndex + " Absent " + teacherList.get(i).getName() + "CoveredBy: " + coveredBy);
					
					perRowData.add(schedule.convertIndexToPeriod(periodIndex));
					perRowData.add(teacherList.get(i).getName());
					perRowData.add(coveredBy);
					allData.add(perRowData);
					}
					periodIndex++;
					
				}
			}
		}
		return allData;
	}
	
	
	public static ArrayList<ArrayList<String>> generateCountView(ArrayList<OnCallTeacher> teacherList)
	{
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		
		for(int i = 0; i < teacherList.size(); i++){
			perRowData = new ArrayList<String>();
			
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
		
		return allData;
	}
	
	
	public static ArrayList<ArrayList<String>> generateAvailabilityView(ArrayList<OnCallTeacher> teacherList)
	{
		
		
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		int weeklyCount = 0;
		int monthlyCount = 0;
		Schedule schedule = new Schedule("p1", "p2", "p3a", "p3b", "p4");
		/******************************************************************
		 * BUG    Some of the objects have null values for their tally. 
		 ******************************************************************/
		
		/******************************************************************
		 * TO-DO    Figure how to figure out who's next.
		 ******************************************************************/
		
		
		for(int periodIndex = 0; periodIndex < 5; periodIndex++)
		{
			//Issue here some teachers have null values in their count
			//throwing a nullException pointer
			for(int i = 0; i < 10; i++){
				perRowData = new ArrayList<String>();
				OnCallTeacher  teacher = teacherList.get(i);
				teacher.getSparePeriodByIndex();
				
					if(!teacherList.get(i).hasReachedweeklyMax()){
						if(!(teacherList.get(i).getHasBeenAssigned())){
							weeklyCount++;
						}
						monthlyCount++;
					}
			
			}
			perRowData.add(schedule.convertIndexToPeriod(periodIndex));
			
			perRowData.add(String.valueOf(weeklyCount));
			perRowData.add(String.valueOf(monthlyCount));
			weeklyCount = 0;
			monthlyCount = 0;
			allData.add(perRowData);
		}
		
		return allData;
	}
	
	public static void printData(ArrayList<ArrayList<String>> allData)
	{
		for(int i = 0; i < allData.size(); i++){
			for(int j = 0; j < allData.get(i).size(); j++){
			System.out.print(" " + allData.get(i).get(j));
			}
			System.out.println("");
		}
	}
	
	
	
}
