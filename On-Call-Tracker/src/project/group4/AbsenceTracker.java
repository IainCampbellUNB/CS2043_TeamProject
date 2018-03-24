package project.group4;

import java.util.ArrayList;

public class AbsenceTracker
{
  private String p1;
  private String p2;
  private String p3a;
  private String p3b;
  private String p4;
  ArrayList<String> absences;
  public static int absenceCounter = 0;
  private String teacherName;
  
  
  
  public AbsenceTracker( String p1In, String p2In, String p3aIn, String p3bIn, String p4In)
  {
    
	this.p1 = p1In;
    this.p2 = p2In;
    this.p3a = p3aIn;
    this.p3b = p3bIn;
    this.p4 = p4In;
    absences = new ArrayList<String>();
    absences.add(p1);
    absences.add(p2);
    absences.add(p3a);
    absences.add(p3b);
    absences.add(p4);
   
    
  }

 public void setValueByIndex(int index, String value)
 {
	 absences.set(index,"value");
 }
  
  
  
  
 public static int getNumberOfAbsences()
 {
	 return AbsenceTracker.absenceCounter;
 }
 
 public void attachTeacherName(String name){
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
		  if(!(absences.get(i).equals("0.0"))){
			  
			  absentPresent = true;
	  		 //return true;
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
