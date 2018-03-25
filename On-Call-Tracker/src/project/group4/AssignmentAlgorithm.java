


package project.group4;

import java.util.ArrayList;

public class AssignmentAlgorithm {
	
	

	final private ArrayList<OnCallTeacher> teacher;
	private ArrayList<ArrayList<OnCallTeacher>> allTeachers;
	
	
	public AssignmentAlgorithm(ArrayList<OnCallTeacher> teacherList){
			allTeachers = new ArrayList<ArrayList <OnCallTeacher>>();
			this.teacher = teacherList;
			findAvailablePeriod();
			sortByTallies();
	}
	public void printData(){
		System.out.println("Hello");
		System.out.println("Size of allTeachesr" + allTeachers.size());
		for(int i = 0; i < allTeachers.size(); i++){
			for(int j = 0; j < allTeachers.get(i).size(); j++){
			System.out.print("e" + allTeachers.get(i).get(j));
			}
			System.out.println("");
		}
	}
	private void findAvailablePeriod(){
		System.out.println("Working");
		ArrayList<OnCallTeacher> holder = new ArrayList<OnCallTeacher>();
		for(int period = 0; period < 5; period++){
			holder = new ArrayList<OnCallTeacher>();
			for(int i = 0; i < teacher.size(); i++){
				int spareIndex = teacher.get(i).getSparePeriodByIndex();
				if(spareIndex == period){
					if(!teacher.get(i).getAbsentStatus() &&!(teacher.get(i).hasReachedMonthlyMax()) && !(teacher.get(i).hasReachedweeklyMax())){
						System.out.println("Adding");
						holder.add(teacher.get(i));
					}
				}
			}
			allTeachers.add(holder);
		}
		
	}
	
	private  void sortByTallies(){
		ArrayList<OnCallTeacher> holder;
		for(int period = 0; period < allTeachers.size(); period++){
			holder = new ArrayList<OnCallTeacher>();
			holder = allTeachers.get(period);
			for(int i = 0; i < holder.size()-1; i++){
				int min_idx = i;
			    for (int j = i+1; j < holder.size(); j++)
			    {
			    	if (compareTallysBetweenTeachers(holder.get(j), holder.get(min_idx)))
			    	{
			    		 min_idx = j;
			    	}
			
			            OnCallTeacher temp = allTeachers.get(period).get(min_idx);
			            allTeachers.get(period).set(min_idx,allTeachers.get(period).get(i));
			            allTeachers.get(period).set(i,temp);
			    }
			}
		}
		
	
	}
	
	public void assignOnCallTeacher(){
		ArrayList<AbsenceTracker> absencesList = new ArrayList<AbsenceTracker>();
		
		for(int i = 0; i < teacher.size(); i++){
			if(teacher.get(i).getAbsentStatus()){
				absencesList.add(teacher.get(i).getSubmittedAbsenceSchedule());
			}
		}
 		
		ArrayList<OnCallTeacher> holder;
		//System.out.println("Teacher" + allTeachers.size());
		for(int period = 0; period < 5; period++){
			holder = new ArrayList<OnCallTeacher>();
			holder = allTeachers.get(period);
			for(int i = 0; i < absencesList.size(); i++){
				String value = absencesList.get(i).getPeriodValueAtIndex(period);
				//If the value equals 
				if(value.equals("X")){
					
					
					if(holder.size() != 0){
						
						String ID = holder.get(0).getID();
						String name = holder.get(0).getName();
						String teacherToAssignValues = absencesList.get(i).getTeacherName();
						//System.out.println(teacherToAssignValues + " " + period);
						findTeacherAndSetValues(teacherToAssignValues,ID,name,period);
						holder.remove(0);
					}
				}
			}	
		}
	
	}

	
	
	private void findTeacherAndSetValues(String teacherToAssignValues, String ID, String name, int index)
	{
		for(int i = 0; i < teacher.size(); i++)
		{
			//System.out.println(teacher.get(i).getName() + "?=" + name);
			if(teacher.get(i).getName().equals(teacherToAssignValues))
			{
				if(teacher.get(i).getSchedule().isLunchPeriod(index)){
					teacher.get(i).getSubmittedAbsenceSchedule().setValueByIndex(index, "LU");
				}
				else if(teacher.get(i).getSchedule().isSparePeriod(index)){
					teacher.get(i).getSubmittedAbsenceSchedule().setValueByIndex(index, "SP");
				}else{
					teacher.get(i).hasBeenAssigned();
					teacher.get(i).getSubmittedAbsenceSchedule().setValueByIndex(index, ID);
				}
				
				
			}
		}
	}
	
	

	
		
	public void printData(ArrayList<ArrayList<String>> allData)
	{
		for(int i = 0; i < allData.size(); i++){
			for(int j = 0; j < allData.get(i).size(); j++){
			System.out.print(" " + allData.get(i).get(j));
			}
			System.out.println("");
		}
	}
	
	
	//Comparison method for who has the lowest of everything
	private boolean compareTallysBetweenTeachers(OnCallTeacher first,  OnCallTeacher second)
	{
		boolean flag = false;
		String firstWT = first.getWeeklyTally();
		String firstMT = first.getMonthlyTally();
		String secondWT = second.getWeeklyTally();
		String secondMT = second.getMonthlyTally();
		String firstTL = first.getTermTally();
		String secondTL = first.getTermTally();
		
		double FWT = Double.parseDouble(firstWT);
		double FMT = Double.parseDouble(firstMT);
		double SWT = Double.parseDouble(secondWT);
		double SMT = Double.parseDouble(secondMT);
		double FTL = Double.parseDouble(firstTL);
		double STL = Double.parseDouble(secondTL);
		
		if(FWT == SWT)
		{
			if(FMT < SMT)
			{
				flag = true;
			}
			if(FMT == SMT)
			{
				if(FTL < STL)
				{
					flag = true;
				}
			}
		}
		
		if(FWT < SWT)
		{
			if(FMT < SMT)
			{
				flag = true;
			}
			if(FMT == SMT)
			{
				if(FTL < STL)
				{
					flag = true;
				}
			}
		}
	
		return flag;
	}

}
