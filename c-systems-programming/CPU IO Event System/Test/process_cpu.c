// Jeff Sirocki
// Program 3

#include <stdio.h>
#include <stdlib.h>
#include "prog3.h"


// Adds event to the cpu queue, 
// Runs queue with RR scheduling, 
// Update event and time,
// Returns the event to be reinserted into eventlist
Event process_cpu ( QueueNodePtr *headPtr, QueueNodePtr *tailPtr, Event event, int time_splice ) {
  
  Event E;

  // Enqueue the event in cpu queue
  enqueue( headPtr, tailPtr, event );

  // Queue is always IDLE

  // Dequeue the event to process time-splice
  E = dequeue( headPtr, tailPtr);
  
  // Log Arrival type events
  if( E.type == 0 ) {
    printf( " CPU: Event { %d %d %d %d } Arrived at Time %d at the CPU scheduler. \n\n",
	    E.process_id,
	    E.arrival_time,
	    E.cpu_time,
	    E.io_time,
	    time );
  }


  // Checks if CPU will finish in time splice
  if ( E.cpu_remain > time_splice ) {
  
    // CPU Event does NOT finish in time-splice

    // Update Time and Event
    E.type = 1; // CPU type
    E.cpu_remain = E.cpu_remain - time_splice;
    E.idle = E.idle + ( time - E.sort );
    time = time + time_splice;
    E.sort = time;

  } else {

    // CPU Event finishes mid time-splice

    // Update Time and Event
    E.type = 2; // IO type
    E.cpu_comp = time + E.cpu_remain;
    E.cpu_remain = 0;
    E.idle = E.idle + ( time - E.sort );
    time = time + time_splice;
    E.sort = E.cpu_comp; // Sorted by cpu completion time

  }

  // Return processed cpu event
  return E;
}
