// Jeff Sirocki
// Program 3

#include <stdio.h>
#include <stdlib.h>
#include "prog3.h"


int simulate( ListNodePtr *sPtr, int numLines, int time_splice ) {

  Event E;
  int total;
  static int counter = 0;
  int summary[ 20 ][ 3 ];
  ListNodePtr tempPtr = NULL;

  QueueNodePtr cpuHeadPtr = NULL;
  QueueNodePtr cpuTailPtr = NULL;

  QueueNodePtr ioHeadPtr = NULL;
  QueueNodePtr ioTailPtr = NULL;

  

// This is where the Magic happens

  printf( "Go to Simulate \n" );

  while ( *sPtr != NULL ) {

    // RUN event if its sort time is equal or less than simulation time
    if( (*sPtr)->event.sort <= time ) {


      // Log Arrival type events
      if( (*sPtr)->event.type == 0 ) {
	
	// PRINTS arrival event at simulation
	E = (*sPtr)->event;
	printf( "System: Event { %d %d %d %d } Arrived at Time %d to the simulation. \n\n",
		E.process_id,
		E.arrival_time,
		E.cpu_time,
		E.io_time,
		E.arrival_time );
      }

      switch ( (*sPtr)->event.type ) {

	// Arrival Type or CPU Type
      case 0: case 1:
	if ( DEBUG ) {
          printf( "Simulation: Case 1 \n" );
        }
	// Process CPU event -- if Type 1

	// Get next event
	tempPtr = get_nextevent( sPtr );
	E = tempPtr->event;
	free (tempPtr);
	
	// Process CPU event and set TIME
	E = process_cpu( &cpuHeadPtr, &cpuTailPtr, E, time_splice );

	// Add processed event back
	add_event( sPtr, E, E.sort );

	// End of CPU case
	break;


	// IO Type
      case 2:
	if ( DEBUG ) {
          printf( "Simulation: Case 2 \n" );
        }
	// Process IO Event -- if Type 2

	// Get next event
        tempPtr = get_nextevent( sPtr );
        E = tempPtr->event;
        free (tempPtr);

	// Process IO event and set TIME
        E = process_io( &ioHeadPtr, &ioTailPtr, E );

        // Add processed event back
        add_event( sPtr, E, E.sort );

	// End of IO case
	break;


	// IO Complete Type
      case 3:
	if ( DEBUG ) {
	  printf( "Simulation: Case 3 \n" );
	}
	// Process IO Complete -- if Type 3
	
	// Get next event
        tempPtr = get_nextevent( sPtr );
        E = tempPtr->event;
        free (tempPtr);
	
	// Log event COMPLETION
	printf( "System: Event { %d %d %d %d } has been COMPLETED at Time %d. \n\n",
	        E.process_id,
	        E.arrival_time,
	        E.cpu_time,
	        E.io_time,
	        time );
	
	// Set Summary values
	summary[ counter ][ 0 ] = E.process_id;
	summary[ counter ][ 1 ] = E.idle;
	summary[ counter ][ 2 ] = E.io_comp - E.arrival_time;
	total = E.io_comp;
	counter++;

	// End of Summary case
	break;
      }
      // End of Switch

    } else {
      if ( DEBUG ) {
        printf( "Simulation: Increment time ++ \n" );
      }

      // Do nothing and increment time
      time++;
    }
  }

  // PRINTS Performance Metrics
  print_summary( summary, 20, 3, numLines, total );

  // End of Simulation
  return 0;
}
      
    
    
