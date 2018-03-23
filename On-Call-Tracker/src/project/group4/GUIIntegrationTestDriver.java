package project.group4;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

public class GUIIntegrationTestDriver {

	public static void main(String args[]) throws IOException, ParseException
	{
		File absenceFile = new File("Test.xls");
		File tallyFile = new File("TallyTest.xls");
		AbsenceWorkerReader AWreader = new AbsenceWorkerReader(absenceFile,"Monday", "2018-03-16");
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
		
		
		
		Vector<Vector<String>> coverageViewData = new Vector<Vector<String>>();
		coverageViewData = GenerateView.generateCoverageView(teacherList, supplyList);
		GenerateView.printData(coverageViewData);
		
		int num = AbsenceTracker.getNumberOfAbsences();
		System.out.println("Num" + num);
	}
	
	
	
	
}
