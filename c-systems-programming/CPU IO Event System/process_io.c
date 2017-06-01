// Jeff Sirocki
// Program 3

#include <stdio.h>
#include <stdlib.h>
#include "prog3.h"

// Adds event to the IO queue,
// Runs queue with FCFS scheduling,
// Update event and time,
// Returns the event to be reinserted into eventlist
Event process_io( QueueNodePtr *headPtr, QueueNodePtr *tailPtr, Event event ) {
  
  static int io_time = 0;
  Event E;

  // Enqueue the event in cpu queue
  enqueue( headPtr, tailPtr, event );

  // Queue is always IDLE

  // Dequeue the event to process time-splice
  E = dequeue( headPtr, tailPtr);

  // Update IO time or event idle
  if ( io_time > E.cpu_comp ) {
    E.idle = E.idle + ( io_time - E.cpu_comp );
  } else {
    io_time = E.cpu_comp;
  }

  // Log CPU type event arrivals
  if( E.type == 2 ) {
    printf( "  I/O: Event { %d %d %d %d } Arrived at Time %d at the IO scheduler. \n\n",
            E.process_id,
            E.arrival_time,
            E.cpu_time,
            E.io_time,
            io_time );
  }

  // IO Event finishes
  E.type = 3;
  io_time = io_time + E.io_time;
  E.io_comp = io_time;
  E.sort = E.io_comp;

  // Return processed IO event
  return E;

}
