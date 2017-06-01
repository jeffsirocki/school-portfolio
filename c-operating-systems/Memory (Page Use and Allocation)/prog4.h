// Tyler Bennett and Jeff Sirocki
// CS 3013 - Project 4
// Due: 02-27-15

// Implementing Virtual Memory
// Checkpoint Due: 02-23-2015

#include <semaphore.h>

#define DEBUG 1
#define TRUE 1
#define NUM_USER 10
#define MAX 150

// Memory Hierarchy
int RAM[25];
int SSD[100];
int HDD[1000];

//basically these new arrays are full of 0/1, 
// 0 indicates that nothing is currently in that location,
// 1 indicates something is there
int RAM_allocation[25];
int SSD_allocation[100];
int HDD_allocation[1000];

typedef unsigned short vAddr;

vAddr indexes[1000]; 
int index_count;
int z;

// Semaphores
sem_t pgTable[1000];
sem_t lock[1000];
sem_t r;
sem_t s;
sem_t h;
sem_t zlock;
sem_t rtype_counter;
sem_t stype_counter;
sem_t find;
sem_t count;

// Page Table
typedef struct PageTag { 
	int locked; // 0 if page unlocked, 1 if locked
	int allocated; // 0 if unallocated, 1 if allocated
	struct timeval time; // Info on when it was last accessed
	int type; // 1 RAM, 2 SSD, 3 HDD
	int index; // index into the allocation arrays 0<x<1000
} Page;

typedef struct UserTag {
	pthread_t thread;
	int id;
} User;


// Function Prototypes
vAddr allocateNewInt();
int *accessIntPtr(vAddr address);
void unlockMemory(vAddr address);
void freeMemory(vAddr address);
