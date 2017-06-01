// Created by Jeff Sirocki
// CS2303 - A02

// Header File: Robot Event List 

#ifndef EVENTLIST_H
#define EVENTLIST_H

#include "robot.h"

// Event List for Robots
class EventList {
  
 public:
  EventList( void ); // Default constructor

  void add_event( EventList* *list, Robot R );
  EventList* get_nextevent( EventList* *sPtr );
  void print_list( EventList* currentPtr );

  Robot robot;
  EventList *nextPtr;
}; // end of EventList class

#endif
