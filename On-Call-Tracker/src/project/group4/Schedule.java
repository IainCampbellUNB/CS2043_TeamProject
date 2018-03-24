package project.group4;

import java.util.ArrayList;
import java.util.Scanner;

public class Schedule
{
  private String p1;
  private String p2;
  private String p3a;
  private String p3b;
  private String p4;
  private String spare;
  private int spareIndex;
  private ArrayList<String> schedule;
  private String skill;
  private boolean skillset;
  private Scanner scan;
  
  
 
  public Schedule(String p1In, String p2In, String p3aIn, String p3bIn, String p4In)
  {
    this.p1 = p1In;
    this.p2 = p2In;
    this.p3a = p3aIn;
    this.p3b = p3bIn;
    this.p4 = p4In;
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
  
  
  public Schedule(String p1In, String p2In, String p3aIn, String p3bIn, String p4In,String skill)
  {
    this.p1 = p1In;
    this.p2 = p2In;
    this.p3a = p3aIn;
    this.p3b = p3bIn;
    this.p4 = p4In;
    this.skill = skill;
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
	  boolean value = false;
	  if(schedule.get(index).equals("Lunch"))
	  {
		  value = true;
	  }
	  
	  return value;
  }
  
  
  public boolean isSparePeriod(int index)
  {
	  boolean value = false;
	  if(schedule.get(index).equals("Spare"))
	  {
		  value = true;
	  }
	  
	  return value;
  }
  
  
  public String determineSkill()
  {
	  String skill = "";
	  
	  
	  return skill;
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
	  String roomNumber = "";
	  scan = new Scanner(schedule.get(index));
	  scan.useDelimiter("/");
	  scan.next();
	  roomNumber = scan.next();
	  return roomNumber;
  }
  
  
  
  public int determineSparePeriodByIndex()
  {
	  int period = 0;
	  for(int index = 0; index < 5; index++){
		  if(schedule.get(index).equals("Spare")){
			  period = index;
		  } 
	  }
		  
	  return period;
  }
  
 
  public String convertIndexToPeriod(int index)
  {
	 String ind = String.valueOf(index);
	 String answer = "";
	  switch (ind) {
      	case "0":
    	  answer  = "P1";
          break;
      	case "1":
    	  answer = "P2";
          break;
      	case "2":
    	  answer = "P3A";
          break;
      	case "3":
    	  answer = "P3B";
          break;
      	case "4":
    	  answer = "P4";
          break;
	  }   
	  return answer;
  }
  

  public ArrayList<String> getSchedule()
  {
    return this.schedule;
  }
  
  public String toString(){
	  String result = "";
	  for(int i = 0; i < 5; i++){
		  result += schedule.get(i) + " "; 
	  }
	  if(skillset){
		  result += "Skill: " + schedule.get(5);
	  }
	  return result;
  }
}
