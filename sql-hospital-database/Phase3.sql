-- Jeff Sirocki Jason Mckinney
-- Phase 3 - Hospital Database
-- Due: December 13th

--
-- This script creates the Hospital database.
--
-- Make sure tables you are trying to create are not
-- already in the database. One way to accomplish this
-- is to run the students_drop script. Otherwise Oracle 
-- will report an error.
--


-- Drop All Tables

DROP TABLE Employee CASCADE CONSTRAINTS;
DROP TABLE EquipmentType CASCADE CONSTRAINTS;
DROP TABLE Equipment CASCADE CONSTRAINTS;
DROP TABLE Room CASCADE CONSTRAINTS;
DROP TABLE RoomService CASCADE CONSTRAINTS;
DROP TABLE RoomAccess CASCADE CONSTRAINTS;
DROP TABLE Patient CASCADE CONSTRAINTS;
DROP TABLE Doctor CASCADE CONSTRAINTS;
DROP TABLE Admission CASCADE CONSTRAINTS;
DROP TABLE Examine CASCADE CONSTRAINTS;
DROP TABLE StayIn CASCADE CONSTRAINTS;

/* ******** PHASE 2 - Part 1 ( Create Database ) ********** */

-- The database you will create must include:
--  o All tables with descriptive names and fields.
--  o For the fields, choose the appropriate data types
--  o Add the appropriate constraints, which must include: 
--    *Primary Keys and Unique  
--    *Foreign Key  
--    *Not Null constraints
--    *Domain constraints


CREATE TABLE Employee
(
	ID int PRIMARY KEY,
	FName VARCHAR(10) NOT NULL,
	LName VARCHAR(10) NOT NULL,
	Salary int NOT NULL,
	jobTitle VARCHAR(12) NOT NULL,
	OfficeNum int NOT NULL,
	empRank VARCHAR(20) NOT NULL,
	supervisorID int
);

CREATE TABLE EquipmentType
(
	ID int PRIMARY KEY,
	Description VARCHAR(20) NOT NULL,
	model VARCHAR(20) NOT NULL,
	instructons VARCHAR(20) NOT NULL
);

CREATE TABLE Equipment
(
	Serial# VARCHAR(8) PRIMARY KEY,
	TypeID int NOT NULL REFERENCES EquipmentType,
	PurchaseYear int NOT NULL,
	LastInspection VARCHAR(7) NOT NULL,
	roomNum int NOT NULL
);

CREATE TABLE Room
(
	Num int PRIMARY KEY,
	occupiedFlag CHAR(1) NOT NULL
);

CREATE TABLE RoomService
(
	roomNum int NOT NULL REFERENCES Room,
	service VARCHAR(10) NOT NULL,
	PRIMARY KEY(roomNum, service)
);

CREATE TABLE RoomAccess
(
	roomNum int NOT NULL REFERENCES Room,
	EmpID int NOT NULL REFERENCES Employee,
	PRIMARY KEY(roomNum, EmpID)
);

CREATE TABLE Patient
(
	SSN VARCHAR(10) PRIMARY KEY,
	FirstName VARCHAR(10) NOT NULL,
	LastName VARCHAR(10) NOT NULL,
	Address VARCHAR(20) NOT NULL,
	TelNum VARCHAR(12) NOT NULL
);

CREATE TABLE Doctor
(
	ID int PRIMARY KEY,
	gender VARCHAR(6) NOT NULL,
	specialty VARCHAR(10) NOT NULL,
	LastName VARCHAR(10) NOT NULL,
	FirstName VARCHAR(10) NOT NULL
);

CREATE TABLE Admission
(
	Num int PRIMARY KEY,
	AdmissionDate VARCHAR(8) NOT NULL,
	LeaveDate VARCHAR(8),
	TotalPayment DECIMAL,
	InsurancePayment DECIMAL,
	Patient_SSN VARCHAR(10) NOT NULL REFERENCES Patient,
	FutureVisit VARCHAR(8)
);

CREATE TABLE Examine
(
	Doctor_ID int REFERENCES Doctor,
	AdmissionNum int REFERENCES Admission,
	Doc_comment VARCHAR(10),
	PRIMARY KEY(Doctor_ID, AdmissionNum)
);

CREATE TABLE StayIn
(
	AdmissionNum int REFERENCES Admission,
	RoomNum int REFERENCES Room,
	startDate DATE,
	endDate DATE,
	PRIMARY KEY(AdmissionNum, RoomNum, startDate)
);

/* End of PHASE 2 - Part 1 */

/* ************ PHASE2 - Part 3 (Populate Database) ************** */

-- Insert records in the database such that:
--    1. All the constraints that you defined in Part 1 will be obeyed
--    2. Each of the above queries must return some records in their results
--    3. Insert information for at least:
--      a. 10 Patients
--      b. 10 Doctors
--      c. 10 Rooms, at least 3 of these rooms have 2 or more services
--      d. 3 Equipment types
--      e. 3 Equipment units of each type
--      f. At least 5 patients have 2 or more admissions (visits)
--      g. 10 regular employees, 4 division managers, and 2 general managers


INSERT INTO Patient
    VALUES ('111223333', 'Abe', 'Apple', 'Atown', '1999999999')
;

INSERT INTO Patient
    VALUES ('211111111', 'Bob', 'Banks', 'Btown', '2999999999')
;

INSERT INTO Patient
    VALUES ('311111111', 'Carl', 'Crab', 'Ctown', '3999999999')
;

INSERT INTO Patient
    VALUES ('411111111', 'Dan', 'Dryers', 'Dtown', '4999999999')
;

INSERT INTO Patient
    VALUES ('511111111', 'Earl', 'Earth', 'Etown', '5999999999')
;

INSERT INTO Patient
    VALUES ('611111111', 'Frank', 'Fallow', 'Ftown', '6999999999')
;

INSERT INTO Patient
    VALUES ('711111111', 'Gabe', 'Gronk', 'Gtown', '7999999999')
;

INSERT INTO Patient
    VALUES ('811111111', 'Hank', 'Hallow', 'Htown', '8999999999')
;

INSERT INTO Patient
    VALUES ('911111111', 'Itte', 'Inker', 'Itown', '9999999999')
;

INSERT INTO Patient
    VALUES ('101111111', 'Jim', 'Jankins', 'Jtown', '1099999999')
;


/* *** Break *** */


INSERT INTO Doctor
    VALUES ('001', 'Male', 'Heart', 'Art', 'Artiste')
;

INSERT INTO Doctor
    VALUES ('002', 'Male', 'Heart', 'Bill', 'Babtiste')
;

INSERT INTO Doctor
    VALUES ('003', 'Female', 'Heart', 'Cathy', 'Cranks')
;

INSERT INTO Doctor
    VALUES ('004', 'Male', 'Heart', 'Dean', 'Diver')
;

INSERT INTO Doctor
    VALUES ('005', 'Male', 'Heart', 'Eric', 'Ethernet')
;

INSERT INTO Doctor
    VALUES ('006', 'Male', 'Brain', 'Fin', 'Flyer')
;

INSERT INTO Doctor
    VALUES ('007', 'Male', 'Brain', 'Gram', 'Griffin')
;

INSERT INTO Doctor
    VALUES ('008', 'Male', 'Brain', 'Hunter', 'Herrow')
;

INSERT INTO Doctor
    VALUES ('009', 'Female', 'Brain', 'Ingrid', 'Ippers')
;

INSERT INTO Doctor
    VALUES ('010', 'Male', 'Brain', 'John', 'Jungle')
;


/* *** Break *** */


INSERT INTO Room
    VALUES ('001', '0')
;

INSERT INTO Room
    VALUES ('002', '0')
;

INSERT INTO Room
    VALUES ('003', '0')
;

INSERT INTO Room
    VALUES ('004', '0')
;

INSERT INTO Room
    VALUES ('005', '0')
;

INSERT INTO Room
    VALUES ('006', '0')
;

INSERT INTO Room
    VALUES ('007', '0')
;

INSERT INTO Room
    VALUES ('008', '0')
;

INSERT INTO Room
    VALUES ('009', '1')
;

INSERT INTO Room
    VALUES ('010', '1')
;

/* *** Break *** */


INSERT INTO Employee
    VALUES ('00001', 'Ariel', 'Animal', '10000', 'Nurse', '00' , 'Regular Employee', '00101')
;

INSERT INTO Employee
    VALUES ('00002', 'Beyonce', 'Barns', '10000', 'Nurse', '01' , 'Regular Employee', '00101')
;

INSERT INTO Employee
    VALUES ('00003', 'Carly', 'Crayons', '10000', 'Nurse', '02', 'Regular Employee', '00101')
;

INSERT INTO Employee
    VALUES ('00004', 'Diane', 'Dillow', '10000', 'Nurse', '03', 'Regular Employee', '00101')
;

INSERT INTO Employee
    VALUES ('00005', 'Erica', 'Empire', '10000', 'Nurse', '04', 'Regular Employee', '00101')
;

INSERT INTO Employee
    VALUES ('00006', 'Fanny', 'Fire', '10000', 'Nurse', '05', 'Regular Employee', '00202')
;

INSERT INTO Employee
    VALUES ('00007', 'Grammy', 'Gorge', '10000', 'Nurse', '06', 'Regular Employee', '00202')
;

INSERT INTO Employee
    VALUES ('00008', 'Hilda', 'Hammy', '10000', 'Nurse', '07', 'Regular Employee', '00202')
;

INSERT INTO Employee
    VALUES ('00009', 'Iris', 'Imps', '10000', 'Nurse', '08', 'Regular Employee', '00303')
;

INSERT INTO Employee
    VALUES ('00010', 'Jill', 'Jello', '10000', 'Nurse', '09', 'Regular Employee', '00404')
;


/* *** Break *** */


INSERT INTO Employee
    VALUES ('00101', 'Minny', 'Mills', '50000', 'Div Nurse', '10', 'Division Manager', '10101')
;

INSERT INTO Employee
    VALUES ('00202', 'Niamh', 'Night', '50000', 'Div Nurse', '11', 'Division Manager', '20202')
;

INSERT INTO Employee
    VALUES ('00303', 'Olga', 'Orange', '50000', 'Div Nurse', '12', 'Division Manager', '20202')
;

INSERT INTO Employee
    VALUES ('00404', 'Penny', 'Peoples', '50000', 'Div Nurse', '13', 'Division Manager', '20202')
;

INSERT INTO Employee
    VALUES ('10101', 'Sarah', 'Smith', '100000', 'Gen Manager', '14', 'General Manager', null)
;

INSERT INTO Employee
    VALUES ('20202', 'Tara', 'Tilda', '100000', 'Gen Manager', '15', 'General Manager', null)
;

/* *** Break *** */

INSERT INTO RoomService
    VALUES ('001', 'Heart')
;

INSERT INTO RoomService
    VALUES ('001', 'Brain')
;

INSERT INTO RoomService
    VALUES ('002', 'Heart')
;

INSERT INTO RoomService
    VALUES ('002', 'Brain')
;

INSERT INTO RoomService
    VALUES ('009', 'Heart')
;

INSERT INTO RoomService
    VALUES ('009', 'Brain')
;


INSERT INTO RoomAccess
    VALUES ('001', '00001')
;

INSERT INTO RoomAccess
    VALUES ('002', '00001')
;


INSERT INTO EquipmentType
    VALUES ('01', 'Heart Poker', 'm1', 'Be Careful')
;

INSERT INTO EquipmentType
    VALUES ('02', 'Heart Poker', 'm2', 'Be Careful')
;

INSERT INTO EquipmentType
    VALUES ('03', 'Brain Poker', 'm1', 'Be Extra Careful')
;


/* *** Break *** */


INSERT INTO Equipment
    VALUES ('A01-02X', '01', '2000', '01-2010', '001')
;

INSERT INTO Equipment
    VALUES ('11112', '01', '2000', '01-2010', '001')
;

INSERT INTO Equipment
    VALUES ('11113', '01', '2000', '01-2010', '001')
;

INSERT INTO Equipment
    VALUES ('22221', '02', '2000', '02-2010', '002')
;

INSERT INTO Equipment
    VALUES ('22222', '02', '2010', '03-2010', '002')
;

INSERT INTO Equipment
    VALUES ('22223', '02', '2010', '05-2010', '002')
;

INSERT INTO Equipment
    VALUES ('22224', '02', '2011', '05-2010', '002')
;

INSERT INTO Equipment
    VALUES ('33330', '03', '2010', '01-2012', '009')
;

INSERT INTO Equipment
    VALUES ('33331', '03', '2011', '01-2012', '009')
;

INSERT INTO Equipment
    VALUES ('33332', '03', '2000', '05-2010', '009')
;

INSERT INTO Equipment
    VALUES ('33333', '03', '2000', '03-2010', '009')
;

INSERT INTO Equipment
    VALUES ('33334', '03', '2000', '03-2010', '009')
;


/* *** Break *** */

-- Admissions (15)

INSERT INTO Admission
    VALUES ('0001', '20120101', '20120101', '1000', '500', '111223333', '20130101')
;

INSERT INTO Admission
    VALUES ('0002', '20120102', '20120102', '1000', '500', '211111111', '20130102')
;

INSERT INTO Admission
    VALUES ('0003', '20120103', '20120103', '1000', '500', '311111111', '20130103')
;

INSERT INTO Admission
    VALUES ('0004', '20120104', '20120104', '1000', '500', '411111111', '20130104')
;

INSERT INTO Admission
    VALUES ('0005', '20120105', '20120105', '1000', '500', '511111111', '20130105')
;

INSERT INTO Admission
    VALUES ('0006', '20120606', '20120606', '1000', '500', '611111111', '20130606')
;

INSERT INTO Admission
    VALUES ('0007', '20130606', '20130606', '5000', '1000', '611111111', '20140606')
;

INSERT INTO Admission
    VALUES ('0008', '20130101', null, '1000', '500', '111223333', null)
;

INSERT INTO Admission
    VALUES ('0009', '20130707', '20130707', '5000', '1000', '711111111', '20140707')
;

INSERT INTO Admission
    VALUES ('0010', '20120808', '20120808', '1000', '500', '811111111', '20130808')
;

INSERT INTO Admission
    VALUES ('0011', '20130808', '20130808', '5000', '1000', '811111111', '20140808')
;

INSERT INTO Admission
    VALUES ('0012', '20120909', '20120909', '1000', '500', '911111111', '20130909')
;

INSERT INTO Admission
    VALUES ('0013', '20130909', '20130909', '5000', '1000', '911111111', '20140909')
;

INSERT INTO Admission
    VALUES ('0014', '20121010', '20121010', '1000', '500', '101111111', '20131010')
;

INSERT INTO Admission
    VALUES ('0015', '20131010', '20131010', '5000', '1000', '101111111', '20141010')
;

INSERT INTO Admission
    VALUES ('0016', '20131010', '20131010', '5000', '1000', '111223333', '20141010')
;

/* *** BREAK *** */

INSERT INTO Examine
    VALUES ('001', '0001', 'Bad')
;

INSERT INTO Examine
    VALUES ('001', '0016', 'Good')
;

INSERT INTO Examine
    VALUES ('001', '0008', 'Good')
;

INSERT INTO Examine
    VALUES ('001', '0007', 'Good')
;


/* End of Population*/

/* ************** PHASE3 - Part 2 - Query Database ***************** */

-- Query 1
--     (Select the hospital rooms (the room number) that are currently occupied.) 
SELECT Num FROM Room WHERE occupiedFlag = 1;

-- Query 2
--     (For a given division manager (say, ID = 10), 
--	select all regular employees that are supervised by
--	this manager. Display the employees ID, names, and salary.)
SELECT ID, FName, LName, Salary FROM Employee WHERE supervisorID = 00101;

-- Query 3
--	(For each patient, report the sum of amounts paid by
--	 the insurance company for that patient, i.e., report 
--	 the patients SSN, and the sum of insurance payments over all visits.
SELECT Patient_SSN, SUM(InsurancePayment) FROM Admission GROUP BY Patient_SSN;

-- Query 4
--	(Report the number of visits done for each patient,
--	 i.e., for each patient, report the patient SSN, first 
--	 and last names, and the count of visits done by this patient.
SELECT SSN, FirstName, LastName, Count
FROM Patient P JOIN (SELECT Patient_SSN, COUNT(*) Count
			FROM Admission GROUP BY Patient_SSN) Q
ON P.SSN = Q.Patient_SSN;

-- Query 5
--	(Report the room number that has an equipment unit 
--	 with serial A01-02Xnumber.) 
SELECT roomNum FROM Equipment WHERE serial# = 'A01-02X';

-- Query 6
--	(Report the employee who has access to the largest 
--	 number of rooms. We need theemployee ID, and the 
--	 number of rooms (s)he can access.)
SELECT EmpID, Count
    FROM (SELECT EmpID, COUNT(*) Count
        FROM RoomAccess GROUP BY EmpID)
    WHERE Count = (SELECT MAX(Count)
            FROM (SELECT EmpID, COUNT(*) Count
                    FROM RoomAccess GROUP BY EmpID));

-- Query 7
--	(Report the number of regular employees, 
--	 division managers, and general managers in the hospital.)
SELECT EmpRank, Count(*) FROM Employee GROUP BY EmpRank ORDER BY Count(*);

-- Query 8
--	(For patients who have scheduled future visit, 
--	 report that patient (SSN, and first and
--	 last names) and the visit date. Do not report 
--	 patients who do not have scheduled visit.)
SELECT SSN, FirstName, LastName
FROM Patient P JOIN (SELECT distinct Patient_SSN
			FROM Admission Where FutureVisit is not null) Q
ON P.SSN = Q.Patient_SSN;

-- Query 9
--	(For each equipment type that has more than 3 units,
--	 report the equipment type ID, model, and the number 
--	 of units this type has.) 
SELECT TypeID, Count
        FROM (SELECT TypeID, Count(*) Count
                FROM Equipment Group By TypeID)
        WHERE Count > 3;

-- Query 10
--	(Report the date of the coming future visit 
--	 for patient with SSN = 111-22-3333.)
SELECT MAX(FutureVisit) FROM (SELECT FutureVisit
				FROM Admission
				WHERE Patient_SSN = 111223333);

-- Query 11
--	(For patient with SSN = 111-22-3333, report the 
--	doctors (only ID) who have examined this patient more than 2 times.)
SELECT distinct Doctor_ID
FROM Examine E JOIN (SELECT Num FROM Admission WHERE Patient_SSN = 111223333) Q
ON E.AdmissionNum = Q.Num;

-- Query 12
--	(Report the equipment types (only the ID) for which 
--	 the hospital has purchased equipments (units) in 
--	 both 2010 and 2011. Do not report duplication.)
SELECT distinct E1.TypeID
From Equipment E1, Equipment E2
WHERE E1.TypeID = E2.TypeID
AND 
( (E1.PurchaseYear = 2010 AND E2.PurchaseYear = 2011)
OR
(E1.PurchaseYear = 2011 AND E2.PurchaseYear = 2010) );

/* **************************** END OF PHASE 2 ****************************** */



/* ********** PHASE 3 ***************** */

/* ***  Part 1 ( 50pts )  *** */


/* ***  Part 2 ( 70pts )  *** */

-- Trigger 1
--	(Any room in the hospital cannot offer more than three services.)

-- Insert and Update Case
Create Trigger RoomMaxService
After Insert Or Update On RoomService
For Each Row
Declare
   countService int;
Begin
   Select COUNT(*) into countService
   From RoomService
   Group By roomNum;

   IF countService > 3 Then
	RAISE_APPLICATION_ERROR(-20004, 'Cannot insert or update record.');
   End IF;
End;

-- Trigger 2
-- 	(The insurance payment should be calculated automatically as 70%
--	  of the total payment. If the total payment changes then the 
--	  insurance amount should also change.)
Create Trigger 70%Insurance
Before Insert Or Update On Admission
For Each Row
Begin
   :new.InsurancePayment := :new.TotalPayment*0.70;
End;


-- Trigger 3 and 4
--	(Ensure that regular employees (with rank 0) must have their 
--	 supervisors as division managers (with rank 1). Also each regular
--	 employee must have a supervisor at all times.)

--	(Similarly, division managers (with rank 1) must have their 
--	 supervisors as general managers (with rank 2). Division managers
--	 must have supervisors at all times.)

-- Notes:
--      Change Ranks to numbers
--      Check Ranks to be 0, 1, or 2
--      Check Regs and Divs have managers
Create Trigger EmpTrig
After Insert Or Update On Employee
For Each Row
Declare
   supervisorRank int;
Begin
   Select empRank into supervisorRank
   From Employee E
   Where E.id = :new.supervisorID;


   IF (:new.empRank = 0) Then
      IF (supervisorRank != 1 or :new.supervisorID is null) Then
	 RAISE_APPLICATION_ERROR(-20004, 'rank or supervisor field not correct');
      End IF;
   End IF;

   IF (:new.empRank = 1) Then
      IF (supervisorRank != 2 or :new.supervisorID is null) Then
         RAISE_APPLICATION_ERROR(-20004, 'rank or supervisor field not correct');
      End IF;
   End IF;

End;


-- Trigger 5
--	(When a patient is admitted to ICU room on date D, the 
--	 futureVisitDate should be automatically set to 3 months
--	 after that date, i.e., D + 3 months. The futureVisitDate
--	 may be manually changed later, but when the ICU admission
--	 happens, the date should be set to default as mentioned above.)

-- Notes:
--	If admitted to ICU then update futureVisitDate
Create Trigger ICUFollowUp
After Insert On Admission
For Each Row
Declare
   roomNumber int;
   ICUService string;
Begin
   Select roomNum into roomNumber
   From StayIn S
   Where :new.Num = S.AdmissionNum;

   Select service into ICUService
   From RoomService R
   Where roomNumber = R.roomNum;

   IF ( ICUService = 'ICU' ) Then
      :new.futureVisit = DATEADD(MONTH, 3, :new.AdmissionDate);
   End IF;
End;

-- Trigger 6
--	(If an equipment of type 'MRI', then the purchase year must be not null 
--	 and after 2005.)
Create Trigger MRITrig
After Update or Insert on Equipment
For Each Row
Declare
   Desc String;
Begin
   Select Description into Desc
   From EquipmentType
   Where E.ID = :new.TypeID;

   IF ( Desc = 'MRI' ) Then
      IF (:new.PurchaseYear < 2006 or :new.PurchaseYear in null) Then
	  RAISE_APPLICATION_ERROR(-20004, 'Purchase year invalid for 'MRI'');
      End If;
   End If;
End;


-- Notes:
--	Select :new.type = MRI where purchase year null or year < 2005
--	The (Error 20004)

-- Trigger 7
--	(When a patient is admitted to the hospital, i.e., a new record
--	 is inserted into the Admission table; the system should print out
--	 the names of the doctors who previously examined this patient (if any).)
Create Trigger DoctorOutput
After Insert On Admission
For Each Row
Declare
   DocID int;
Begin

   -- Print These Doctors
   Select DocID
   From Examine E
   Where E.AdmissionNum in 
	(Select A.AdmissionNum
	 From Admission A
	 Where :new.Patient_SSN = A.Patient_SSN);
   
-- Notes: 
--	Check previous Doctors that match patient and print them.

/* ***  Part 3 ( 50pts - Java )  *** */

-- End of Program
