// Jeff Sirocki
// Program 3
// Header File

// Contains : structs, prototypes, global variables

#include <stdio.h>
#include <stdlib.h>

#define DEBUG 0

int time; // global simulation time

typedef struct eventTag {
  int type;                  // 0 ( Arrivals ), 1 ( CPU ), 2 ( I/O ), 3 ( Summary )
  int process_id;            // unique id
  int arrival_time;          // arrival time to simulation
  int cpu_time;              // required cpu time
  int io_time;               // required io time
  int cpu_remain;            // remaining cpu time
  int cpu_comp;              // simulation time that event finished at cpu
  int io_comp;               // simulation time that event finished at io
  int idle;                  // total idle time
  int sort;                  // value to insert event by
} Event;

typedef struct ListNodeTag {
  Event event;
  struct ListNodeTag *nextPtr;
} ListNode;

typedef ListNode *ListNodePtr;

typedef struct QueueNodeTag {
  Event event;
  struct QueueNodeTag *nextPtr;
} QueueNode;

typedef QueueNode *QueueNodePtr;

// PROTOTYPES -- (Helper Functions)

// SIMULATE
int simulate( ListNodePtr *sPtr, int numLines, int timeSplice );
void process_event( Event E, QueueNodePtr *headPtr, QueueNodePtr *tailPtr );
Event process_cpu( QueueNodePtr *headPtr, QueueNodePtr *tailPtr, Event event, int time_splice );
Event process_io( QueueNodePtr *headPtr, QueueNodePtr *tailPtr, Event event );

// LINKED LIST
int isEmpty( ListNodePtr sPtr );
void add_event( ListNodePtr *nPtr, Event event, int sort );
Event delete( ListNodePtr *nPtr, Event event );
ListNodePtr get_nextevent( ListNodePtr *sPtr );

// QUEUES
int qIsEmpty( QueueNodePtr headPtr );
void enqueue( QueueNodePtr *headPtr, QueueNodePtr *tailPtr, Event E );
Event dequeue( QueueNodePtr *headPtr, QueueNodePtr *tailPtr );

// PRINT
void print_eventlist( ListNodePtr nPtr );
void print_queue( QueueNodePtr currentPtr );
void print_summary( int summary[][3], int rows, int columns, int n, int total);
