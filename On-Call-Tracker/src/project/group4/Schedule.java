package project.group4;

import java.util.ArrayList;
import java.util.Scanner;

public class Schedule{

  private String spare;
  private int spareIndex;
  private ArrayList<String> schedule;
  private String skill;

  private Scanner scan;
  
  
 
  public Schedule(String p1, String p2, String p3a, String p3b, String p4)
  {

    schedule = new ArrayList<String>(6);
    schedule.add(p1);
    schedule.add(p2);
    schedule.add(p3a);
    schedule.add(p3b);
    schedule.add(p4);
    spareIndex = determineSparePeriodByIndex();
    spare = convertIndexToPeriod(spareIndex);
    skill = determineSkill();
  }
  

  public String getSpareByString(){
	  return spare;
  }
  
  
  public String getSkill(){
	  return this.skill;
  }
  
  public boolean isLunchPeriod(int index){
	  boolean value = false;
	  if(schedule.get(index).equals("Lunch")){
		  value = true;
	  } 
	  return value;
  }
  
  
  public boolean isSparePeriod(int index){
	  boolean value = false;
	  if(schedule.get(index).equals("Spare")){
		  value = true;
	  } 
	  return value;
  }
  
  //NOTE** if the teacher has more than one possibility of a skill
  //		for example they don't teach the same subject twice
  //		then for now it just uses the first subject
  private String determineSkill(){
	  
	  //Transforms the schedule arrayList into an array
	  String[] arraySched = new String[5];
	  
	  for(int i = 0; i < 5; i++) {
		  arraySched[i] = getSubject(i);
	  }

	  
	  //some int values to help determine the skill
	  int biggestIndex = 0;
	  int biggestCount = 0;
	  int count = 0;
	  
	  //two for loops used to determine the skill
	  for(int indexOne = 0; indexOne < 5; indexOne++) {
		  
		 for(int indexTwo = indexOne + 1; indexTwo < 5; indexTwo++) {
			 
			 if(arraySched[indexOne].equals(arraySched[indexTwo])) {
				 count++;		 
			 }
		 }
		 if(count > biggestCount) {
			 biggestCount = count;
			 biggestIndex = indexOne;
		 }
		 
		 count = 0;
	  }
	  
	return arraySched[biggestIndex];
	  
  }
  
  public String getSubject(int index){
	  String subject  = "";
	  scan = new Scanner(schedule.get(index));
	  scan.useDelimiter("/");
	  subject = scan.next();
	  
	  return subject;
	  
  }
  
  public String getRoomNumber(int index){
	  String roomNumber = "";
	  scan = new Scanner(schedule.get(index));
	  scan.useDelimiter("/");
	  scan.next();
	  roomNumber = scan.next();
	  return roomNumber;
  }
  
  
  
  public int determineSparePeriodByIndex(){
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
	  
	  result += "Skill: " + skill;
	  
	  return result;
  }
}
