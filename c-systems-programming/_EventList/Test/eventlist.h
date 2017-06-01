// Jeff Sirocki
// Lab3

#define DEBUG 0

typedef struct eventTag {
  int process_id;
  int arrival_time;
  int cpu_time;
  int io_time;
} Event;

typedef struct ListNodeTag {
  Event event;
  struct ListNodeTag *nextPtr;
} ListNode;

typedef ListNode *ListNodePtr;
