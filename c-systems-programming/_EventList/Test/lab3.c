// Jeff Sirocki
// Lab3

#include <stdio.h>
#include <stdlib.h>
#include "eventlist.h"

// Prototypes for assignment
void add_event( ListNodePtr *nPtr, Event event );
ListNodePtr get_nextevent( ListNodePtr *sPtr );
void print_eventlist( ListNodePtr nPtr );

// Prototypes -- (Helper Functions)
int create_node( void );
int print_node( ListNodePtr cPtr );
int print_event( Event E );
int isEmpty( ListNodePtr sPtr );
Event delete( ListNodePtr *nPtr, Event event );

// Main Function
int main ( int argc, char * argv[] ) {
  int i; // counter
  
  int numLines = atoi(argv[1]);
  int p_id;
  int a_time;
  int cpu_time;
  int io_time;
  Event E;

  ListNodePtr startPtr = NULL; // Event List Start
  ListNodePtr tempPtr = NULL; // Temp to free event with get_nextevent

  // Scan in numLines from input file and ADD event
  for( i = 0; i < numLines; i++ ) {
    printf( "Enter in a process id, arrival time, cpu time, and io time\n" );
    scanf( "%d%d%d%d", &p_id, &a_time, &cpu_time, &io_time );

    E.process_id = p_id;
    E.arrival_time = a_time;
    E.cpu_time = cpu_time;
    E.io_time = io_time;
    
    // add scanned event to event list
    add_event( &startPtr, E );
  }
  
  // Print final Event List
  print_eventlist( startPtr );

  // For all nodes, get first event for simulation in FIFO order and delete from event list
  for( i = 0; i < numLines; i++ ) {
    tempPtr = get_nextevent( &startPtr );
    free(tempPtr);
  }

  // End of main
  return 0;
}

