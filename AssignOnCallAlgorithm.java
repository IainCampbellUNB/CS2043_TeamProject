import java.util.ArrayList;

public static class AssignOnCallAlgorithm
{
  ArrayList<OnCallTeacher> listOfTeachers;

  public void assignOnCalls()
  {
    /*
    Put the absentee's absences in an array
    Iterate through absences
      if it's NOT an X
        assign supply with matching ID
      else
        Iterate trough all teachers
          if teacher's free period = absentee's absent period
            Add teacher to potential on-caller ArrayList
    Iterate through ArrayList
      if weekly on-call tally > limit OR monthly on-call tally > limit
        Remove teacher from ArrayList
    if remaining teachers = 1
      Assign them as on-caller
    else
      Assign random teacher from ArrayList as on-caller
    */
  }
}
