// Jeff Sirocki
// Program 3

#include <stdio.h>
#include <stdlib.h>
#include "prog3.h"


int simulate( ListNodePtr *sPtr, int time_splice ) {

  static int cpu_time;
  static int io_time;
  static int rr_time;

  QueueNodePtr cpuHeadPtr = NULL;
  QueueNodePtr cpuTailPtr = NULL;

  QueueNodePtr ioHeadPtr = NULL;
  QueueNodePtr ioTailPtr = NULL;

  // Makes CPU QUEUE
  make_cpu_queue( *sPtr, &cpuHeadPtr, &cpuTailPtr );

  // PRINTS CPU Queue
  print_queue( cpuHeadPtr );


  // Set CPU start time
  cpu_time = (*sPtr)->event.arrival_time;

  if( DEBUG ) {
    printf( "DEBUG: %d \n", cpu_time );
  }

  // While cpu queue not empty run RR Scheduling
  while ( cpuHeadPtr != NULL ) {

    /*
    // RR Scheduling
    // Exit Case
    if ( cpuHeadPtr.event.cpu_remain < time_splice ) {
      cpu_time = cpu_time + cpuHeadPtr->event.cpu_remain;
      cpuHeadPtr->event.cpu_comp = cpu_time;
      enqueue( &ioHeadPtr, &ioTailPtr, cpuHeadPtr->event );
    } else {
    // do time splice
    */

    // FCFS
    if ( cpu_time < cpuHeadPtr->event.arrival_time ) {
      cpu_time++;
      printf( "Added time - %d\n", cpu_time );
    } else {

      // Update cpu_time, add to io Queue
      cpu_time = cpu_time + cpuHeadPtr->event.cpu_remain;
      cpuHeadPtr->event.cpu_comp = cpu_time;
      enqueue( &ioHeadPtr, &ioTailPtr, cpuHeadPtr->event );
      dequeue( &cpuHeadPtr, &cpuTailPtr );
    }
  }

  // PRINTS CPU Queue
  print_queue( ioHeadPtr );

  // Set IO start time
  io_time = ioHeadPtr->event.cpu_comp;

  while (ioHeadPtr != NULL ) {

    // FCFS
    // Delete Event List Items
    // Increment Time
    // Math
    // Set IO completions

    // FCFS
    if ( io_time > ioHeadPtr->event.cpu_comp ) {
      io_time++;
      printf( "Added IO time - %d\n", io_time );
    } else {

      // Update io_time, delete items from eventlist
      io_time = ioHeadPtr->event.io_time;
      ioHeadPtr->event.io_comp = io_time;
      delete( sPtr, ioHeadPtr->event );
      dequeue( &ioHeadPtr, &ioTailPtr );
    }
  }

  return 0;
}

// Puts events from eventlist into cpu queue
int make_cpu_queue( ListNodePtr currentPtr, QueueNodePtr *headPtr, QueueNodePtr *tailPtr ) {

  if( isEmpty( currentPtr ) ) {
    printf( "List is empty.\n" );
  } else {

    while( currentPtr != NULL ) {
      enqueue( headPtr, tailPtr, currentPtr->event );
      currentPtr = currentPtr->nextPtr;
    }
  }

  // End of make cpu queue
  return 0;
}


// This is where the Magic happens
