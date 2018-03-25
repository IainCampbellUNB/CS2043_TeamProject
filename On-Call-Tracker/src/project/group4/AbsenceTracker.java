package project.group4;

import java.util.ArrayList;

public class AbsenceTracker
{
  ArrayList<String> absences;
  public static int absenceCounter = 0;
  private String teacherName;

  public AbsenceTracker(String p1, String p2, String p3a, String p3b, String p4)
  {
    absences = new ArrayList<String>();
    absences.add(p1);
    absences.add(p2);
    absences.add(p3a);
    absences.add(p3b);
    absences.add(p4);
  }

 public void setValueByIndex(int index, String value)
 {
	 absences.set(index,value);
 }
  
 public static int getNumberOfAbsences()
 {
	 return AbsenceTracker.absenceCounter;
 }
 
 public void attachTeacherName(String name)
 {
	 this.teacherName = name;
 }
 
 public String getTeacherName()
 {
	 return this.teacherName;
 }
  
  public boolean checkAbsencePresent()
  {
	  boolean absentPresent = false;
	  for (int i = 0; i < absences.size(); i++)
	  {
		  if(!(absences.get(i).equals("0.0")))
		  {
			  absentPresent = true;
		  }
	  }
	  return absentPresent;
  }
  
  public String getPeriodValueAtIndex(int index)
  {
	  return absences.get(index);
  }
  
  public String toString(){
	  String result = " ";
	  for(int i = 0; i < 5; i++)
		  result += absences.get(i) + " "; 
	  
	  return result;
  }
}
