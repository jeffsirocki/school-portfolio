// Jeff Sirocki
// Program 3

#include <stdio.h>
#include <stdlib.h>
#include "prog3.h"

// Queue Functions:
//    isEmpty, enqueue, dequeue, print_queue

// If queue is empty
int qIsEmpty( QueueNodePtr headPtr ) {
  return headPtr == NULL;
}

// Adds an event to the queue tail
void enqueue( QueueNodePtr *headPtr, QueueNodePtr *tailPtr, Event E ) {

  QueueNodePtr newPtr; // pointer to new node

  newPtr = malloc( sizeof( QueueNode ) );
  
  if( newPtr != NULL ) { // space available
    
    // Set node for insert
    newPtr->event = E;
    newPtr->nextPtr = NULL; // no link

    // if empty, insert node at head
    if( qIsEmpty( *headPtr ) ) {
      *headPtr = newPtr;

      if ( DEBUG ) {
        // Print Enqueue
        printf( "  Event { %d %d %d %d } was ENQUEUED. \n",
                E.process_id,
                E.arrival_time,
                E.cpu_time,
                E.io_time );
      }

    } else {
      ( *tailPtr )->nextPtr = newPtr;
    
      if ( DEBUG ) {
        // Print Enqueue
        printf( "  Event { %d %d %d %d } was ENQUEUED. \n",
                E.process_id,
                E.arrival_time,
                E.cpu_time,
                E.io_time );
      }

    }

    *tailPtr = newPtr;
  } else {
    printf( "Event not inserted. No memory available.\n" );
  }
}

// Deletes an event from the queue
Event dequeue( QueueNodePtr *headPtr, QueueNodePtr *tailPtr ) {
  Event E; // event 
  QueueNodePtr tempPtr; // temp node

  E = ( *headPtr )->event;
  tempPtr = *headPtr;
  *headPtr = ( *headPtr )->nextPtr;

  if ( DEBUG ) {
    // Print action of Event
    printf( "  Event { %d %d %d %d } was DEQUEUED. \n",
            E.process_id,
            E.arrival_time,
            E.cpu_time,
            E.io_time );
  }

  // if queue is empty
  if( *headPtr == NULL ) {
    *tailPtr = NULL;
  }

  free( tempPtr );
  return E;
}

// print the queue
void print_queue( QueueNodePtr currentPtr ) {
  // if queue is empty
  if( currentPtr == NULL ) {
    printf( "Queue is empty.\n" );
  } else {

    printf( "\n\t********* QUEUE ***********\n\n" );
    printf( "       Process Id   Arrival Time   CPU Time   IO Time\n" );
    printf( "-----------------------------------------------------\n" );
   
    // while not end of queue
    while ( currentPtr != NULL ) {
      printf( "Event:  %7d      %5d         %5d     %5d\n",
              currentPtr->event.process_id ,
              currentPtr->event.arrival_time,
              currentPtr->event.cpu_time,
              currentPtr->event.io_time );
      currentPtr = currentPtr->nextPtr;
    }

    printf( "\n" );
  }
}
