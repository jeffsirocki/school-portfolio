Jeffrey Sirocki and Joseph Kaiser
CS2303 - A02
LATE - 10/16/2014 

                  *** Program 5 ***
                  ----------------- 
Mission : The goal of this program was to simulate robots delivering packages in Robomall as well as to simulate
	  shoppers in Robomall getting items they desire from a shopping list.

The Program is split into two parts:
    Stage1: Robots ( Complete )
    Stage2: Shoppers ( NOT Complete )

In Stage1, multiple robots move through the mall delivering items to stores in real time. These items are
   stored in a binary search tree that the shoppers interact with in Stage 2. After a robot has made all
   its deliveries it leaves the mall. Summary Information is then Printed.

   Output: Arrivals - (simulation, stores, and elevator) 
	   Departures - (simulation, stores, and elevator)
	   Summary - (Robot Id, start time, finish time)
	   Inventory - (Prints Tree)

In Stage2, multiple shoppers run an app to generate a shopping list. The shoppers visit stores both 
   the first floor( FCFS ) and second floor ( RR ) and but items that are then removed from the inventory tree.
   After a shopper has completed its shopping list it leaves the mall. Summar Information is then Printed.

   Output: Initiation - ( Shopper Fields are printed )
	   Item List - ( The Master list of all Items to be bought by shoppers )
           Summary - (Robot Id, start time, finish time)

 
*** NOTE ***
This program is INOCOMPLETE.

Stage 1 is complete and fully functional.

Stage 2 is not complete and parts are commented out because of errors( i.e. seg faults )
  Errors or Missing( Only Stage 2 ): 
	  - shopper.cpp ( run_app function )
	  - listnode.cpp ( prog5.cpp: listPtr[9] gave errors )
	  - shopper_eventlist.cpp ( None )
	  - shopper_simulate.cpp ( Cannot simulate movement without eventlist )
	  

jas_jtk_prog5.tar includes:
     
 Jeff S - prog5.cpp              Joe K - shopper.h
          tree.h                       - shopper.cpp
          robomall.h		       - tree.h
          robot.h		       - listnode.cpp
          eventlist.h		       - prog5a.out
	  item.cpp		       - prog5 (executable)
	  robot.cpp		       - prog5.dat
	  treenode.cpp		       - README.txt
	  eventlist.cpp
	  simulate.cpp
	  process_move.cpp
	  move.cpp
	  process_store.cpp
	  building_layout.cpp
	  Makefile
	  prog5a.out
	  prog5 (executable)
	  prog5.dat
	  README.txt

To Compile program, enter make in the command line
   $ make
To run the program, run the executable with the following command line (50 is the time_slice)
   $ ./prog5 50 < prog5.dat
