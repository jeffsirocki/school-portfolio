 // Tyler Bennett and Jeff Sirocki
// CS 3013 - Project 3
// Due: 02-17-15

// PHASE 1

typedef struct ClusterHalfTag {    
	pthread_t thread;
	int free;
	int level;
} ClusterHalf;

typedef struct ClusterTag {    
	ClusterHalf left;
	ClusterHalf right;
} Cluster;

typedef struct QueueNodeTag {
  pthread_t *thread;
  int id;
  int type;
  struct QueueNodeTag *nextPtr;
} QueueNode;

typedef struct InfoTag {    
	int id;    
	int sec_type;    
	pthread_mutex_t lock;
} Info;

typedef QueueNode *QueueNodePtr;

// QUEUES
int qIsEmpty( QueueNodePtr headPtr );
void enqueue( QueueNodePtr *headPtr, QueueNodePtr *tailPtr, pthread_t T, int tid, int sec_type );
int reorder_queue( QueueNodePtr *headPtr, QueueNodePtr *tailPtr );
void print_queue( QueueNodePtr currentPtr );
pthread_t dequeue( QueueNodePtr *headPtr, QueueNodePtr *tailPtr );
// ----------------------
// PHASE 2

typedef struct TrafficQueueNodeTag {
  pthread_t *thread;
  int id;
  struct TrafficQueueNodeTag *nextPtr;
} TrafficQueueNode;

typedef TrafficQueueNode *TrafficQueueNodePtr;

typedef struct CarTag {
	pthread_t thread;
	int id;
	int type;
	int position;
	int source;
	int destination;
} Car;

// QUEUES
int queueIsEmpty( TrafficQueueNodePtr headPtr );
void enqueue_car( TrafficQueueNodePtr *headPtr, TrafficQueueNodePtr *tailPtr, pthread_t T, int tid );
pthread_t dequeue_car( TrafficQueueNodePtr *headPtr, TrafficQueueNodePtr *tailPtr );


