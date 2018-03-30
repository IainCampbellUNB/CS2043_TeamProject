package team4.assn5;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import project.group4.AbsenceTracker;
import project.group4.AbsenceWorkbookReader;
import project.group4.OnCallTeacher;
import project.group4.Schedule;
import project.group4.SupplyTeacher;
import project.group4.Teacher;

public class DataProccess {

	private TallyWorkbookReader tallyreader;
		
	public DataProccess(TallyWorkbookReader tallyreader){
		this.tallyreader = tallyreader;
	}
	
	public ArrayList<TallyCount> createTallyWorkbook() throws IOException, ParseException{
		ArrayList<ArrayList<String>> tallyCountData = new ArrayList<ArrayList<String>>();
		tallyCountData = tallyreader.readTallyCount();
		ArrayList<TallyCount> alldata = new ArrayList<TallyCount>();
		for(int row = 0; row < tallyCountData.size(); row++){
			String name = tallyCountData.get(row).get(0);
			String weeklyTally = tallyCountData.get(row).get(1);
			String monthTally = tallyCountData.get(row).get(2);
			String TermTally = tallyCountData.get(row).get(3);
			
			TallyCount obj = new TallyCount(weeklyTally, monthTally, TermTally, name);
			alldata.add(obj);
		}
		return alldata;
	}
	

}
