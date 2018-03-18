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

 
  public boolean checkAbsencePresent()
  {
	  boolean absentPresent = false;
	  for (int i = 0; i < absences.size(); i++)
		  if(!absences.get(i).equals(0.0))
			  absentPresent = true;
	  return absentPresent;
  }
  
  
  
  public String toString(){
	  String result = " ";
	  for(int i = 0; i < 5; i++)
		  result += absences.get(i) + " "; 
	  
	  return result;
  }
}
