package project.group4;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

public class GUIAlgorithmIntegrationTestDriver {

	public static void main(String args[]) throws IOException, ParseException
	{
		//new GUI();
		
		
		Schedule sched = new Schedule("BIOL/RM102", "TECH/RM103","TRUE/RM104","LUNCH/RM103","BIOL/RM102");
		
		String sub = sched.getRoomNumber(0);
		System.out.println(sub);
		String rm = sched.getSubject(0);
		System.out.println(rm);
		File absenceFile = new File("AbsenceWorkBook.xls");
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
		
	}
	
	
	
	
}