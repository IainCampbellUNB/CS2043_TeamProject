package project.group4;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

public class GUIIntegrationTestDriver {

	public static void main(String args[]) throws IOException, ParseException
	{
		//new GUI();
		
		
		Schedule sched = new Schedule("BIOL/RM102", "TECH/RM103","TRUE/RM104","LUNCH/RM103","BIOL/RM102");
		
		String sub = sched.getRoomNumber(0);
		System.out.println(sub);
		String rm = sched.getSubject(0);
		System.out.println(rm);
		File absenceFile = new File("Test.xls");
		File tallyFile = new File("TallyTest.xls");
		AbsenceWorkbookReader AWreader = new AbsenceWorkbookReader(absenceFile,"Monday", "2018-03-16");
		TallyWorkbookReader TWreader = new TallyWorkbookReader(tallyFile, "Monday", "2018-03-19");
		
		
		DataProccess data = new DataProccess(AWreader,TWreader);
		ArrayList<OnCallTeacher> teacherList = new ArrayList<OnCallTeacher>();
		ArrayList<Teacher> supplyList = new ArrayList<Teacher>();
		teacherList = data.createTeacherTermSchedule();
		supplyList = data.createSupplyList();
		
		//Print TeacherList
		
		for(int i = 0; i < teacherList.size(); i++)
		{
			System.out.println(teacherList.get(i).toString());
		}
		
		for(int i = 0; i < supplyList.size(); i++)
		{
			System.out.println(supplyList.get(i).toString());
		}
		

		
		/*
		Vector<Vector<String>> coverageViewData = new Vector<Vector<String>>();
		coverageViewData = GenerateView.generateCoverageView(teacherList, supplyList);
		GenerateView.printData(coverageViewData);
		
		Vector<Vector<String>> tallyViewData = new Vector<Vector<String>>();
		tallyViewData = GenerateView.generateCountView(teacherList);
		GenerateView.printData(tallyViewData);
		
		Vector<Vector<String>> availabilityViewData = new Vector<Vector<String>>();
		
		availabilityViewData = GenerateView.generateAvailabilityView(teacherList);
		GenerateView.printData(availabilityViewData);
		
		double lowest = GenerateView.findTheLowestTally(teacherList, 2);
		System.out.println(lowest);
		ArrayList<OnCallTeacher> potentials = GenerateView.findPotentialNextInLines(teacherList, lowest, 2);
		for(int i = 0; i < potentials.size(); i++)
		{
			System.out.println(potentials.get(i).toString());
		}
		String name = GenerateView.determineWhoIsNext(teacherList, 2);
		System.out.println(name);
		
		*/
		
	}
	
	
	
	
}
