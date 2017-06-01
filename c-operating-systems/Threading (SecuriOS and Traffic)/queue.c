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

// Adds an thread to the queue tail
void enqueue( QueueNodePtr *headPtr, QueueNodePtr *tailPtr, pthread_t T, int tid, int sec_type ) {

  QueueNodePtr newPtr; // pointer to new node

  newPtr = malloc( sizeof( QueueNode ) );

  if( newPtr != NULL ) { // space available

    // Set node for insert
    newPtr->thread = T;
	newPtr->id = tid;
	newPtr->type = sec_type;
    newPtr->nextPtr = NULL; // no link

    // if empty, insert node at head
    if( qIsEmpty( *headPtr ) ) {
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
pthread_t dequeue( QueueNodePtr *headPtr, QueueNodePtr *tailPtr ) {
  pthread_t T; // thread
  QueueNodePtr tempPtr; // temp node

  printf("DEQUEUE: Thread #%d: %u\n", (*headPtr)->id, (*headPtr)->thread);
  
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

// Reorders queue so that first two nodes are Top Secret
int reorder_queue( QueueNodePtr *headPtr, QueueNodePtr *tailPtr ) {
	
	// Place Keeper
	int count = 0;
	QueueNodePtr currentPtr = *headPtr;
	QueueNodePtr previousPtr = NULL;
	
	// Reorder two Ptrs
	QueueNodePtr movePtr1;
	QueueNodePtr movePtr2;
	
	printf("\tREORDER: Thread #%d: %u\n", (*headPtr)->id, (*headPtr)->thread);
	
	while ( currentPtr != NULL && count < 2 ) {
		if ( currentPtr->type == 1 ) {
			
			if ( previousPtr == NULL ) {
					count++;
			} else if ( count == 0 ) {
				
				// Move Ptr 2
				movePtr2 = currentPtr;
				previousPtr->nextPtr = currentPtr->nextPtr;
				movePtr2->nextPtr = *headPtr;
				*headPtr = movePtr2;
				
				printf("\tREORDER: Thread #%d MOVED.\n", (*headPtr)->id, (*headPtr)->thread);
				
				// Increase
				count++;
			} else {
			
				// Move Ptr 1
				movePtr1 = currentPtr;
				previousPtr->nextPtr = currentPtr->nextPtr;
				movePtr1->nextPtr = *headPtr;
				*headPtr = movePtr1;
	
				printf("\tREORDER: Thread #%d MOVED.\n", (*headPtr)->id, (*headPtr)->thread);
	
				// Increase
				count++;
			}
		}
		
		// Move to next Node
		previousPtr = currentPtr;
		currentPtr = currentPtr->nextPtr;
		
	} // end while
	
	return 0;
}
		
// print the queue
void print_queue( QueueNodePtr currentPtr ) {
  // if queue is empty
  if( currentPtr == NULL ) {
    printf( "Queue is empty.\n" );
  } else {

    printf( "\n\t********* QUEUE ***********\n\n" );
    printf( "-----------------------------------------------------\n" );

    // while not end of queue
    while ( currentPtr != NULL ) {
      printf( "Thread:  %u      %d\n",
              currentPtr->thread ,
              currentPtr->id );
      currentPtr = currentPtr->nextPtr;
    }

    printf( "\n" );
  }
}



