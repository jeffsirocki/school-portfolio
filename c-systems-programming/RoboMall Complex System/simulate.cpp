// Jeff Sirocki
// Program 3

#include <iostream>
#include <stdio.h>
#include "eventlist.h"
#include "robot.h"

// prototypes --(helper functions)
Robot process_move( Robot R );
Robot process_store( Robot R );
Robot process_exit( Robot R );

int simulate( EventList* *sPtr, int numRobots ) {
  
  int debug = 0;

  // Declarations
  Robot R;
  int time = 0;
  int i;
  static int counter = 0;
  int summary[ 10 ][ 3 ];
  EventList start;
  EventList* tempPtr = NULL;


  // This is where the Magic happens

  // While Loop
  while ( *sPtr != NULL ) {

    // RUN event if its sort time is equal or less than simulation time
    if( (*sPtr)->robot.sort <= time ) {
      
      // Log Arrival type events
      if( (*sPtr)->robot.status == 0 ) {

        // PRINTS arrival event at simulation
        R = (*sPtr)->robot;
        printf( "\nSystem: Robot { %d %d %d %d } Arrived at Time %d to the simulation. \n\n",
                R.Id,
                R.location[0],
                R.location[1],
                R.numStore,
                R.sort );

	(*sPtr)->robot.status = 1; // Move Status
      }

  //   Switch for Robot status types ( Arrival, Move, Store, Exit )
      
      switch ( (*sPtr)->robot.status ) {
	

	// Status: Move

      case 1:
	if (debug) {
	  printf( "Simulation: Case 1 \n" );
        }

	// Get next event
	tempPtr = start.get_nextevent( sPtr );
	R = tempPtr->robot;
	delete (tempPtr);

	// Process Move
	R = process_move( R );

	// Add processed robot back
	start.add_event( sPtr, R );

	// End of MOVE case
	break;


  //   Status: Store

      case 2:
	if (debug) {
          printf( "Simulation: Case 2 \n" );
        }

	// Get next event
        tempPtr = start.get_nextevent( sPtr );
        R = tempPtr->robot;
        delete (tempPtr);

        // Process Store
        R = process_store( R );
	
        // Add processed robot back
        start.add_event( sPtr, R );

        // End of STORE case
        break;


  //   Status: Exit
    
      case 3:
        if (debug) {
          printf( "\nSIMULATION: Case 3 \n" );
        }
	
        // Get next event
        tempPtr = start.get_nextevent( sPtr );
        R = tempPtr->robot;
        delete (tempPtr);

        // Process Exit
        R = process_move( R );
	
        // Add processed robot back
        start.add_event( sPtr, R );
	
        // End of EXIT case
        break;

      case 4:
	if (debug) {
          printf( "\n\n\nSIMULATION: Case 4 \n" );
        }

        // Get next event
        tempPtr = start.get_nextevent( sPtr );
        R = tempPtr->robot;
        delete (tempPtr);

	// OUTPUT
	printf( "\nSystem: Robot %d has left the simulation at Time %d. \n\n",
		R.Id + 1,
		R.sort );

	// Set Summary Values
	summary[ counter ][ 0 ] = R.Id + 1;
	summary[ counter ][ 1 ] = R.startTime;
	summary[ counter ][ 2 ] = R.finishTime;
	counter++;

	// END of Summary case
	break;
      }
      // End of Switch

  //   Increment Time
    
    } else {
      if ( debug ) {
        printf( "Simulation: Increment time ++ \n" );
      }
      
      // Do nothing and increment time
      time++;
    }
  }    

  // Print Summary
  printf( "\n\n\t *********** STAGE 1 - SUMMARY ***********\n" );
  printf( "\t Robot     Start Time Finish Time\n");
  printf( "\t---------------------------------\n" );
  for( i = 0; i < numRobots; i++ ) {
    printf("\t  %d         %5d           %5d\n",
	   summary[i][0],
	   summary[i][1],
	   summary[i][2] );
  }

  // End of Simulation
  return 0;
}
  
