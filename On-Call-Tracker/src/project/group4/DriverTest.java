package project.group4;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class DriverTest {

	public static void main(String[] args) throws ParseException, IOException {
		
		
		/* **************************************************
		 * Read Information
		 ****************************************************/
		AbsenceWorkerReader reader = new AbsenceWorkerReader();
		
		ArrayList<ArrayList<String>> termScheduleData = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> supplyListData = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> absenceTrackerData = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> tallyCountData = new ArrayList<ArrayList<String>>();
		
		try 
		{
			termScheduleData = AbsenceWorkerReader.readTermSchedule();
			//reader.printData(termScheduleData);
						
			supplyListData = AbsenceWorkerReader.readSupplyList();
			//reader.printData(supplyListData);
			
			absenceTrackerData = AbsenceWorkerReader.readAbsenceTracker("2018-03-16");
			//reader.printData(absenceTrackerData);
			
			tallyCountData = TallyWorkbookReader.readTallyCount("2018-03-19");
			//reader.printData(tallyCountData);
			
		}
		
		catch(IOException e) 
		{
			System.out.println(e.getMessage());
		}
		
		/* **************************************************
		 * Step 1 Create ArrayList of Teachers's
		 ****************************************************/
		ArrayList<OnCallTeacher> teacherList = new ArrayList<OnCallTeacher>();
		
		//Extract Data from TermSchedule to create List
		for(int row = 0; row < termScheduleData.size(); row++){
			
			String id = termScheduleData.get(row).get(0);
		    String name = termScheduleData.get(row).get(1);
		    String p1 = termScheduleData.get(row).get(2);
		    String p2 = termScheduleData.get(row).get(3);
		    String p3a = termScheduleData.get(row).get(4);
		    String p3b = termScheduleData.get(row).get(5);
		    String p4 = termScheduleData.get(row).get(6);
		    Schedule sched = new Schedule(p1,p2,p3a,p3b,p4);
		    OnCallTeacher obj1 = new OnCallTeacher(id,name,sched);
		    teacherList.add(obj1);
		}
		
		//for(int i = 0; i < teacherList.size(); i++){
		//	System.out.println(teacherList.get(i).toString());
		//}
		
		/* **************************************************
		 * Step 2 Create ArrayList of Supply's
		 ****************************************************/
		ArrayList<Teacher> supplyList = new ArrayList<Teacher>();
		
			for(int row = 0; row < supplyListData.size(); row++){
				String id = supplyListData.get(row).get(0);
				String name = supplyListData.get(row).get(1);
				SupplyTeacher obj1 = new SupplyTeacher(id,name);
				supplyList.add(obj1);
		}
		
		//for(int i = 0; i < supplyList.size(); i++){
		//	System.out.println(supplyList.get(i).toString());
		//}
		
		/* **************************************************
		 * Step 3 Add AbsenceTracker Schedule to Teacher
		 ****************************************************/
		
		//Match name with teacher and assign a Absentee Schedule
		
		for(int row = 0; row < absenceTrackerData.size(); row++){
			String name = absenceTrackerData.get(row).get(0);
			String p1 = absenceTrackerData.get(row).get(1);
			String p2 = absenceTrackerData.get(row).get(2);
			String p3a = absenceTrackerData.get(row).get(3);
			String p3b = absenceTrackerData.get(row).get(4);
			String p4 = absenceTrackerData.get(row).get(5);
			
			AbsenceTracker tracker = new AbsenceTracker(p1,p2,p3a,p3b,p4);		
			//If there is an absence, absence could be happening later in the week
			
			if(tracker.checkAbsencePresent()){
				//find teacher match
				for(int i = 0; i< teacherList.size(); i++)
				{
					if(teacherList.get(i).getName().equals(name) ){
						teacherList.get(i).setToAbsent();
						teacherList.get(i).submitAbsenceSchedule(tracker);
					}
				}
				
			 }
		
		}	
		/* **************************************************
		 * Step 4 Add Tally to each Teacher
		 ****************************************************/
		for(int row = 0; row < tallyCountData.size()-5; row++){
			String name = tallyCountData.get(row).get(0);
			String p1 = tallyCountData.get(row).get(1);
			String p2 = tallyCountData.get(row).get(2);
			String p3a = tallyCountData.get(row).get(3);
			String p3b = tallyCountData.get(row).get(4);
			String p4 = tallyCountData.get(row).get(5);
			String weeklyTally = tallyCountData.get(row).get(6);
			String monthTally = tallyCountData.get(row).get(7);
			String TermTally = tallyCountData.get(row).get(8);
			//System.out.println(TermTally);
			AssignmentTracker assign = new AssignmentTracker(p1,p2,p3a,p3b,p4);
			int i = 0;
			boolean match = false;
			while( i< teacherList.size() && !match)
			{
				if(teacherList.get(i).getName().equals(name) ){
					
					//attach assignement schedule Teacher object
					teacherList.get(i).attachAssignmentTrackerToTeacherObject(assign);
					
					//Set the tallies to teacher object
					teacherList.get(i).setWeeklyTally(weeklyTally);
					teacherList.get(i).setMonthlyTally(monthTally);
					teacherList.get(i).setTermTally(TermTally);
				
					match = true;
				}
				i++;
			}
			
			
		}
		/*******************************************************************
		 * BUG - NEED TO TRACE WHERE IT'S BREAKING BUG
		 ******************************************************************/
		for(int i = 0; i < teacherList.size(); i++){
			System.out.println(teacherList.get(i).toString());
		}
			
		/*******************************************************************
		 * SAVE WRITE METHODS
		 ******************************************************************/
		AbsenceWorkerReader.writeToAbsenceTracker(teacherList, "2018-03-16");
		
	
		
		
		/*******************************************************************
		 * Inside of method for View 1
		 ******************************************************************/
		//Period-Absentee-Covered_by
		ArrayList<String> rowData = new ArrayList<String>();
		for(int i = 0; i < teacherList.size(); i++){
			if(teacherList.get(i).getAbsentStatus())
			{
				OnCallTeacher teacher = teacherList.get(i);
				AbsenceTracker obj1 = teacher.getSubmittedAbsenceSchedule();
				int j = 0;
				while(j<5){
					String value = obj1.getPeriodValueAtIndex(j);
					if(!(value.equals("0.0")) && !(value.equals("X")) )
					{
						System.out.println("value " + value);
						
						//Is it a supply teacher or on-Call
						//int num = Integer.parseInt(value);
						String coveredBy ="";
						//if(num > 30)
						//{
						//Search Supply Teacher
							
							for(int k = 0; k < supplyList.size();k++)
							{
								if(supplyList.get(k).getID().equals(value))
								{
									coveredBy = supplyList.get(k).getName();
								}
								
							}
							
						//}else{
						//Search On-Call Teacher
						//	for(int f = 0; f < teacherList.size();f++){
						//		if(teacherList.get(f).getID().equals(value)){
						//			coveredBy = teacherList.get(f).getName();
						//		}
								
						//	}
						
					System.out.println("Period: " + j + " Absent " + teacherList.get(i).getName() + "CoveredBy: " + coveredBy);
					}
					j++;
				}
				
				
			}
			
		}
		
		
		/*******************************************************************
		 * Inside of method for View 2
		 ******************************************************************/
		
		
		
		
		/*******************************************************************
		 * Inside of method for View 3
		 ******************************************************************/
		
		
		
	}

}
