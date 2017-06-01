// Jeff Sirocki
// Program 3

#include <stdio.h>
#include <stdlib.h>
#include "prog3.h"

// Queue Functions:
//    isEmpty, enqueue, dequeue, print_queue

// If queue is empty
int queueIsEmpty( TrafficQueueNodePtr headPtr ) {
  return headPtr == NULL;
}

// Adds an thread to the queue tail
void enqueue_car( TrafficQueueNodePtr *headPtr, TrafficQueueNodePtr *tailPtr, pthread_t T, int tid ) {

	//Output
	printf("\tENQUEUE: Car %d in traffic line.\n", tid);

  TrafficQueueNodePtr newPtr; // pointer to new node

  newPtr = malloc( sizeof( TrafficQueueNode ) );

  if( newPtr != NULL ) { // space available

    // Set node for insert
    newPtr->thread = T;
	newPtr->id = tid;
    newPtr->nextPtr = NULL; // no link

    // if empty, insert node at head
    if( queueIsEmpty( *headPtr ) ) {
      *headPtr = newPtr;

    } else {
      ( *tailPtr )->nextPtr = newPtr;
    }

    *tailPtr = newPtr;
  } else {
    printf( "thread not inserted. No memory available.\n" );
  }
}

// Deletes an thread from the queue
pthread_t dequeue_car( TrafficQueueNodePtr *headPtr, TrafficQueueNodePtr *tailPtr ) {
  pthread_t T; // thread
  TrafficQueueNodePtr tempPtr; // temp node

  printf("\tDEQUEUE: Thread %d from line: %u\n", (*headPtr)->id, (*headPtr)->thread);
  
  T = ( *headPtr )->thread;
  tempPtr = *headPtr;
  *headPtr = ( *headPtr )->nextPtr;

  // if queue is empty
  if( *headPtr == NULL ) {
    *tailPtr = NULL;
  }

  free( tempPtr );
  return T;
}
