# CS2043_Team Project Deliverable 4 

Repository for the CS2043 team project.

## Description

This is a On-Call Tracker software system built for CS2043 Software Engineering project at the University of New Brunswick.
The objective of  the project is to design and build a software tool which will help a current school assign on-call teachers to classes that need coverage. 

## System Considerations & Limitations:

1.  Excel files for input and output must be older version **(i.e. xls)**. 

2.  A user of the system must have the right dependencies files to read and write input (see dependencies files section below).

3. Input files must be entered in the right order. AbsenceWorkbook followed by TallyWorkbook. :octocat: 

4. Selected dates must already exist in the data excel files. The excel files sheets are labelled by the date of the monday of every week.

5. For the system to work, the excel files must stay as they are. They are designed for our specific system. It is encouraged that empty fields be non-empty. If a tally is 0, there should be a 0 in that cell. If a teacher is present and not missing a period, a 0 indicates that the teacher is present. The system does catch for empty fields. However, further testing needs to be done to make sure nothing breaks!

## Testing Files

|    **INPUT**        | **OUTPUT**              |
|---------------------|-------------------------|
| AbsenceWorkbook.xls | AbsenceWorkbook.xls  |
| TallyWorkbook.xls   | TallyWorkbook.xls  |

**Data is overwritten when test are run. It is encourage for testers to create duplicates before testing in order to have back up of original test data.**


## Dependencies files

### Apache Poi files
- poi-3.17.jar
- poi-examples-3.17.jar
- poi-excellent-3.17.jar
- poi-ooxml-3.17.jar
- poi-ooxml-schemas-3.17.jar
- poi-scratchpad-3-17.jar

### Common-collections
- Common-collections-4-4.1.jar

## Instructions


## Cool features

1. Max Tallies can be preset before each assignment. 
2. Views from previous days can be viewed if already run in the system
3. Easy to use GUI!

## Authors

- **Members of Team 4!!!**
- Craig Beaman  **Role: Tech Lead**
- Iain Campbell  **Role: Project Manager**
- Gabbie Dupuis   **Role: Documentation Lead**
- Catherine Higgins @chninja  **Role: Progress Facilator**

