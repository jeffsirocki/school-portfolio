// Jeff Sirocki
// Lab3

#include <stdio.h>
#include <stdlib.h>
#include "eventlist.h"


// Linked List Functions:
//   add_event, get_nextevent, print_eventlist, isEmpty, delete


// ADDS an event to the event list
void add_event( ListNodePtr *nPtr, Event event ) {
  int value;
  value = event.arrival_time; // value to insert by

  ListNodePtr newPtr; // pointer to new node
  ListNodePtr previousPtr; // pointer to previous node in list
  ListNodePtr currentPtr; // pointer to current node in list

  // Create node
  newPtr = malloc( sizeof( ListNode ) ); // create node

  // Check for Memory
  if( newPtr != NULL ) {

    // Set node for insert
    newPtr->event = event;
    newPtr->nextPtr = NULL; // no link

    // Set starting position
    previousPtr = NULL;
    currentPtr = *nPtr;

    // loop to find correct location
    while( currentPtr != NULL && value > currentPtr->event.arrival_time ) {
      previousPtr = currentPtr;
      currentPtr = currentPtr->nextPtr;
    }

    // If first node, else other node
    if( previousPtr == NULL ) {
      newPtr->nextPtr = *nPtr;
      *nPtr = newPtr;

      // Print Add
      printf( "  Event { %d %d %d %d } was INSERTED into the list. \n\n",
              event.process_id,
              event.arrival_time,
              event.cpu_time,
              event.io_time );

    } else {
      previousPtr->nextPtr = newPtr;
      newPtr->nextPtr = currentPtr;

      // Print Add
      printf( "  Event { %d %d %d %d } was INSERTED into the list. \n\n",
              event.process_id,
              event.arrival_time,
              event.cpu_time,
              event.io_time );
    }
  } else {
    printf( "Node not inserted. No memory available.\n" ); // Print error for no memory
  }
}



// Returns pointer to NEXT EVENT to simulate and deletes event from event list
ListNodePtr get_nextevent( ListNodePtr *sPtr ) {

  ListNodePtr firstPtr; // pointer to new node

  //Set First Event pointer and increment starting pointer to "delete" the first event
  firstPtr = *sPtr;
  *sPtr = (*sPtr)->nextPtr;

  // Print action of Event
  printf( "  Event { %d %d %d %d } is being simulated and was REMOVED from the list. \n\n",
          firstPtr->event.process_id,
          firstPtr->event.arrival_time,
          firstPtr->event.cpu_time,
          firstPtr->event.io_time );

  return firstPtr;
}

//Is Empty
int isEmpty( ListNodePtr sPtr ) {
  return sPtr == NULL;
}

// Print Event List
void print_eventlist( ListNodePtr currentPtr ) {
  if ( isEmpty( currentPtr ) ) {
    puts( "Event List is empty.\n" );
  } else {

    printf( "\n\t*********Event List***********\n\n" );
    printf( "       Process Id   Arrival Time   CPU Time   IO Time\n" );
    printf( "-----------------------------------------------------\n" );

    // while not the end of the list
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

// Delete an event
Event delete( ListNodePtr *nPtr, Event event ) {
  int value;

  ListNodePtr tempPtr; // pointer to new node
  ListNodePtr previousPtr; // pointer to previous node in list
  ListNodePtr currentPtr; // pointer to current node in list


  value = event.arrival_time; // set value to delete

  // delete first node
  if ( value == ( *nPtr )->event.arrival_time ) {
    tempPtr = *nPtr;
    *nPtr = (*nPtr)->nextPtr;
    free( tempPtr ); // free the node
    return event;
  } else {
    previousPtr = *nPtr;
    currentPtr = ( *nPtr )->nextPtr;

    // loop to find correct event
    while( currentPtr != NULL && currentPtr->event.arrival_time != value ) {
      previousPtr = currentPtr;
      currentPtr = currentPtr->nextPtr;
    }

    // delete currentPtr
    if (currentPtr != NULL ) {
      tempPtr = currentPtr;
      previousPtr->nextPtr = currentPtr->nextPtr;
      free( tempPtr );
      return event;
    }
  }
  // End of Delete
  return event;
}
