package project.group4;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class DriverTest {

	public static void main(String[] args) throws ParseException {
		
		
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
		
		for(int i = 0; i < teacherList.size(); i++){
			System.out.println(teacherList.get(i).toString());
		}
		
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
		for(int row = 0; row < tallyCountData.size(); row++){
			String name = tallyCountData.get(row).get(0);
			String p1 = tallyCountData.get(row).get(1);
			String p2 = tallyCountData.get(row).get(2);
			String p3a = tallyCountData.get(row).get(3);
			String p3b = tallyCountData.get(row).get(4);
			String p4 = tallyCountData.get(row).get(5);
			String weeklyTally = tallyCountData.get(row).get(6);
			String monthTally = tallyCountData.get(row).get(7);
			String TermTally = tallyCountData.get(row).get(8);
			
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
		for(int i = 0; i < teacherList.size(); i++){
			System.out.println(teacherList.get(i).toString());
		}
			
			
			
	
		
		
		
	}

}
