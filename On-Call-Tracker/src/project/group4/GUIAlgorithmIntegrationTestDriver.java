package project.group4;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class GUIAlgorithmIntegrationTestDriver {

	public static void main(String args[]) throws IOException, ParseException{
	
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String date = "2018-03-13";
		Scanner sc = new Scanner(date);
		sc.useDelimiter("-");
		String year = sc.next();
		int iyear = Integer.parseInt(year);
		
		String month = sc.next();
		int imonth = Integer.parseInt(month);
		String day = sc.next();
		int iday = Integer.parseInt(day);
		Calendar c = Calendar.getInstance();
		c.set(iyear,imonth-1,iday);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
	
		if(dayOfWeek == 2){
			String forSheetLookUp = date;
		}
		String dateToPass = sdf.format(c.getTime());
		System.out.println(dateToPass);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.add(Calendar.DAY_OF_WEEK, -dayOfWeek+Calendar.MONDAY);
		System.out.println(dayOfWeek);
		//Sunday is 1.
		dateToPass = s.format(c.getTime());
		System.out.println(dateToPass);
		
		
		
		
		/*File absenceFile = new File("AbsenceWorkBook.xls");
		File tallyFile = new File("TallyWorkbook.xls");
		AbsenceWorkbookReader AWreader = new AbsenceWorkbookReader(absenceFile,"Monday", "2018-03-16");
		TallyWorkbookReader TWreader = new TallyWorkbookReader(tallyFile, "Monday", "2018-03-16");
		
		
		DataProccess data = new DataProccess(AWreader,TWreader);
		ArrayList<OnCallTeacher> teacherList = new ArrayList<OnCallTeacher>();
		ArrayList<Teacher> supplyList = new ArrayList<Teacher>();
		teacherList = data.createTeacherTermSchedule();
		supplyList = data.createSupplyList();
		
		//Print TeacherList
		
		for(int i = 0; i < teacherList.size(); i++){
			System.out.println(teacherList.get(i).toString());
		}
		
		for(int i = 0; i < supplyList.size(); i++){
			System.out.println(supplyList.get(i).toString());
		}
		
	
		AssignmentAlgorithm test = new AssignmentAlgorithm(teacherList);
		
		
		
		test.assignOnCallTeacher();
		for(int i = 0; i < teacherList.size(); i++){
			System.out.println(teacherList.get(i).toString());
		}
	//test.printData();
		
		
		 * This is to test the GenerateViews
		 
		
		Vector<Vector<String>> coverageViewData = new Vector<Vector<String>>();
		coverageViewData = GenerateView.generateCoverageView(teacherList, supplyList);
		GenerateView.printData(coverageViewData);
		
		Vector<Vector<String>> tallyViewData = new Vector<Vector<String>>();
		tallyViewData = GenerateView.generateCountView(teacherList);
		GenerateView.printData(tallyViewData);
		
		Vector<Vector<String>> availabilityViewData = new Vector<Vector<String>>();
		
		availabilityViewData = GenerateView.generateAvailabilityView(teacherList);
		GenerateView.printData(availabilityViewData);

		
		
		ArrayList<OnCallTeacher> potentials = GenerateView.findPotentialNextInLines(teacherList, 2);
		for(int i = 0; i < potentials.size(); i++){
			System.out.println(potentials.get(i).toString());
		}
		String name = GenerateView.determineWhoIsNext(teacherList, 2);
		System.out.println(name);
		
		*/
		
	}
	
	
	
	
}
