// Jeff Sirocki
// Program 5

#include <stdio.h>
#include <iostream>
#include <string>
#include "eventlist.h"
using namespace std;

// Robot Eventlist Constructor
EventList::EventList( void ) {
  robot = Robot();
  nextPtr = NULL;
} 

void EventList::add_event( EventList* *list, Robot R ){
  int value;

  // value is the quantity of the item in the delivery
  value = R.sort;                          
  EventList* currentPtr;                         
  EventList* tmpPtr;
  EventList* newPtr;               
    
  // allocates memory
  newPtr = new EventList();              
  if( newPtr != NULL ) {
    newPtr->robot = R;
    newPtr->nextPtr = NULL;
    tmpPtr = NULL;
    currentPtr = *list;

    while( currentPtr != NULL && value > currentPtr->robot.sort ) {
      tmpPtr = currentPtr;                                  
      currentPtr = currentPtr->nextPtr;         
    }
    
    if( tmpPtr == NULL ) {
      newPtr->nextPtr = *list;
      
      *list = newPtr;
    } else {
      tmpPtr->nextPtr = newPtr;             
      newPtr->nextPtr = currentPtr;
    }
  }
}

// Returns pointer to NEXT EVENT to simulate and deletes event from event list
EventList* EventList::get_nextevent( EventList* *sPtr ) {

  EventList* firstPtr;

  firstPtr = *sPtr;
  *sPtr = (*sPtr)->nextPtr;

  // cout << " Got next Event: " << firstPtr->robot.Id << " -- Sort: " << firstPtr->robot.sort << endl; 
  return firstPtr;
}

// Prints inventory
void EventList::print_list( EventList* currentPtr ) {
  int i;

  while( currentPtr != NULL ) {
    cout << "      " << currentPtr->robot.Id << " -- (" 
	 << currentPtr->robot.location[0] << ", " 
	 << currentPtr->robot.location[1] << ", " 
	 << currentPtr->robot.location[2] << ")" 
	 << " -- Sort: " << currentPtr->robot.sort << endl;
    for( i = 0; i < currentPtr->robot.numStore; i++ ) {
      printf( "Store: %d %d %d -- %d Items \n",
	      currentPtr->robot.stores[i][0],
	      currentPtr->robot.stores[i][1],
	      currentPtr->robot.stores[i][2],
	      currentPtr->robot.stores[i][3] );
    }
    currentPtr = currentPtr->nextPtr;
  }
}
