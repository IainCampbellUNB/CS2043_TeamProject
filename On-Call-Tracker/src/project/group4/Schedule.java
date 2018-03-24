package project.group4;

import java.util.ArrayList;
import java.util.Scanner;

public class Schedule
{
  private String spare;
  private int spareIndex;
  private ArrayList<String> schedule;
  private boolean skillset;
  private Scanner scan;
  
  public Schedule(String p1, String p2, String p3a, String p3b, String p4)
  {
    skillset = false;
    schedule = new ArrayList<String>(6);
    schedule.add(p1);
    schedule.add(p2);
    schedule.add(p3a);
    schedule.add(p3b);
    schedule.add(p4);
    spareIndex = determineSparePeriodByIndex();
    spare = convertIndexToPeriod(spareIndex);
  }
  
  
  public Schedule(String p1, String p2, String p3a, String p3b, String p4, String skill)
  {
    skillset = true;
    schedule = new ArrayList<String>(6);
    schedule.add(p1);
    schedule.add(p2);
    schedule.add(p3a);
    schedule.add(p3b);
    schedule.add(p4);
    schedule.add(skill);
    spareIndex = determineSparePeriodByIndex();
    spare = convertIndexToPeriod(spareIndex);
  }

  public String getSpareByString()
  {
	  return spare;
  }
  
  
  public boolean isLunchPeriod(int index)
  {  
	  return schedule.get(index).equals("Lunch");
  }
  
  public boolean isSparePeriod(int index)
  {  
	  return schedule.get(index).equals("Spare");
  }
  
  public String getSubject(int index)
  {
	  String subject  = "";
	  scan = new Scanner(schedule.get(index));
	  scan.useDelimiter("/");
	  subject = scan.next();
	  
	  return subject;
  }
  
  public String getRoomNumber(int index)
  {
	  scan = new Scanner(schedule.get(index));
	  scan.useDelimiter("/");
	  scan.next();
	  return scan.next();
  }
  
  
  
  public int determineSparePeriodByIndex()
  {
	  int period = 0;
	  for(int index = 0; index < 5; index++)
	  {
		  if(schedule.get(index).equals("Spare"))
		  {
			  period = index;
		  } 
	  }
		  
	  return period;
  }
  
  public static String convertIndexToPeriod(int index)
  { 
	  switch (index) 
	  {
      	case 0:
      		return "P1";
      	case 1:
      		return "P2";
      	case 2:
      		return "P3A";
      	case 3:
      		return "P3B";
      	default:
      		return "P4";
	  }   
  }
  
  public ArrayList<String> getSchedule()
  {
    return this.schedule;
  }
  
  public String toString()
  {
	  String result = "";
	  for(int i = 0; i < 5; i++)
	  {
		  result += schedule.get(i) + " "; 
	  }
	  if(skillset)
	  {
		  result += "Skill: " + schedule.get(5);
	  }
	  return result;
  }
}
