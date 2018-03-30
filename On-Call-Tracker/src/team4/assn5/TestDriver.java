package team4.assn5;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class TestDriver {

	public static void main (String args[]) throws IOException, ParseException{
		File file = new File("TallyWorkbook.xls");
		TallyWorkbookReader reader = new TallyWorkbookReader(file, "Monday", "2018-03-16");
		
		DataProccess data = new DataProccess(reader);
		ArrayList<TallyCount> tally = new ArrayList<TallyCount>();
		tally = data.createTallyWorkbook();
		
		for(int i = 0; i < tally.size(); i++){
			System.out.println(tally.get(i).getTeacher() + " " + tally.get(i).getWeeklyCount() + " " +
					tally.get(i).getMonthlyCount() + " " + tally.get(i).getTermCount());
		
		}
		
		//Everyone gets assignment of 1
		for(int i = 0; i < tally.size(); i++){
			tally.get(i).setTalleys("2", "2", "2");
		}
		
		TallyWorkbookWriter output = new TallyWorkbookWriter(file, "Monday", "2018-03-16");
		output.writeToTallyCoutner(tally);
		
	}
	
}
