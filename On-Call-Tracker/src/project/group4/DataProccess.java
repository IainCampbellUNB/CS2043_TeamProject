package project.group4;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class DataProccess {

	
	private ArrayList<OnCallTeacher> teacherList;
	ArrayList<Teacher> supplyList;
	AbsenceWorkbookReader reader;
	TallyWorkbookReader tallyreader;
	
	
	public DataProccess(AbsenceWorkbookReader reader, TallyWorkbookReader tallyreader){
		this.teacherList = new ArrayList<OnCallTeacher>();
		this.supplyList = new ArrayList<Teacher>();
		this.reader = reader;
		this.tallyreader = tallyreader;
		
	}
	
	
	public ArrayList<OnCallTeacher> createTeacherTermSchedule() throws IOException, ParseException
	{

		ArrayList<ArrayList<String>> termScheduleData = new ArrayList<ArrayList<String>>();
		termScheduleData = reader.readTermSchedule();
		
		for(int row = 0; row < termScheduleData.size(); row++){
			
			String id = termScheduleData.get(row).get(0);
		    String name = termScheduleData.get(row).get(1);
		    String p1 = termScheduleData.get(row).get(2);
		    String p2 = termScheduleData.get(row).get(3);
		    String p3a = termScheduleData.get(row).get(4);
		    String p3b = termScheduleData.get(row).get(5);
		    String p4 = termScheduleData.get(row).get(6);
		    Schedule sched;
		    if(reader.getSkillsFilled()){
		    	String skills = termScheduleData.get(row).get(7);
		    	sched = new Schedule(p1,p2,p3a,p3b,p4,skills);
		    }else{
		    	sched = new Schedule(p1,p2,p3a,p3b,p4);
		    }
		   
		    OnCallTeacher obj1 = new OnCallTeacher(id,name,sched);
		   
		    teacherList.add(obj1);
		
	}
		assignAbsences();
		assignTallys();
		
		return teacherList;
	
	}
	
	public ArrayList<Teacher> createSupplyList() throws IOException, ParseException{
		ArrayList<ArrayList<String>> supplyListData = new ArrayList<ArrayList<String>>();
		supplyListData = reader.readSupplyList();
		for(int row = 0; row < supplyListData.size(); row++){
			String id = supplyListData.get(row).get(0);
			String name = supplyListData.get(row).get(1);
			SupplyTeacher obj1 = new SupplyTeacher(id,name);
			supplyList.add(obj1);
		}
	
		
		return supplyList;
	}
	
	private void assignAbsences() throws IOException, ParseException{
		ArrayList<ArrayList<String>> absenceTrackerData = new ArrayList<ArrayList<String>>();
		absenceTrackerData = reader.readAbsenceTracker();
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

	}
	
	private void assignTallys() throws IOException, ParseException{
		ArrayList<ArrayList<String>> tallyCountData = new ArrayList<ArrayList<String>>();
		tallyCountData = tallyreader.readTallyCount();
		
		for(int row = 0; row < tallyCountData.size(); row++){
			String name = tallyCountData.get(row).get(0);
			String weeklyTally = tallyCountData.get(row).get(1);
			String monthTally = tallyCountData.get(row).get(2);
			String TermTally = tallyCountData.get(row).get(3);
			
			int i = 0;
			boolean match = false;
			while( i< teacherList.size() && !match)
			{
				if(teacherList.get(i).getName().equals(name) ){
					
					teacherList.get(i).setWeeklyTally(weeklyTally);
					teacherList.get(i).setMonthlyTally(monthTally);
					teacherList.get(i).setTermTally(TermTally);
				
					match = true;
				}
				i++;
			}
		}
	}
	
	
	
	
	
	
	
}
