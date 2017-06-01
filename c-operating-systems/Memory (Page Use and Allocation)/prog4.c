// Tyler Bennett and Jeff Sirocki
// CS 3013 - Project 4
// Due: 02-27-15

// Implementing Virtual Memory
// Checkpoint Due: 02-23-2015

#define _BSD_SOURCE
#include <pthread.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <semaphore.h> 
#include <time.h>
#include "prog4.h"

// Threads
pthread_t user_threads[NUM_USER];

// User functions that the threads will run (TESTING PURPOSES)

// MEMORY MAXIMIZER ( SLOW )
void * run_user1(User *uptr){
	pthread_t U = uptr->thread;
	int uid = (int) uptr->id;
	int page;
	int job_num = 0;
	
	// LOOP until hit Max Page count
	while( TRUE && index_count < MAX) {
		
		// Increment Count
		sem_wait(&count);
		page = index_count;
		index_count++;
		sem_post(&count);
		job_num++;
		printf("USER %d on Job %d working on Page %d: ", uid, job_num, page);
		
		// User Functions
		indexes[page] = allocateNewInt(); 
		int *value = accessIntPtr(indexes[page]); 
		
		*value = (page * 3);
		
		unlockMemory(indexes[page]); 
	}
}

// MEMORY CACHER ( FAST )
void * run_user2(User *uptr){
	pthread_t U = uptr->thread;
	int uid = (int) uptr->id;
	int page;
	int job_num = 0;

	//printf("USER: \n");
	
	while( TRUE && index_count < MAX) {
		
		// Increment Count
		sem_wait(&count);
		index_count++;
		sem_post(&count);
		// Assign Page
		page = (rand() % 24);
		job_num++;
		printf("USER %d on Job %d working on Page %d: ", uid, job_num, page);
		if( index_count < 25 ) {
			indexes[page] = allocateNewInt(); 
		}
		
		// User Functions
		int *value = accessIntPtr(indexes[page]); 
		
		*value = (page * 3);
		
		unlockMemory(indexes[page]); 
		usleep(25000);
	}
}

// MEMORY TESTER ( Medium )
/* void * run_user3(User *uptr){
	
	// Declarations
	pthread_t U = uptr->thread;
	int uid = (int) uptr->id;
	int page, l;
	int job_num = 0;
	
	while( TRUE && index_count < MAX) {
		
		// Increment Count
		sem_wait(&count);
		page = index_count;
		index_count++;
		sem_post(&count);
		job_num++;
		
		// Allocate New Int
		if( index_count > 25 ) {
			
			page = (rand() % 50); // Number 0 - 24
			printf("USER %d on Job %d working on Page %d: ", uid, job_num, page);
			if ( page > 24 ) {
				indexes[page] = allocateNewInt(); 
			}
			
		} else if( index_count > 125 ) {
			
			page = (rand() % 125); // Number 25 to 125
			printf("USER %d on Job %d working on Page %d: ", uid, job_num, page);
		} else {
			indexes[page] = allocateNewInt(); 
		}
		
		// User Functions
		int *value = accessIntPtr(indexes[page]); 
		
		*value = (page * 3);
		
		unlockMemory(indexes[page]); 
		usleep(250);
	}
} */

// Main Function: simulates user-end
int main(){

	// Delcarations
	int i;
	User user[NUM_USER];
	struct timeval start;
	struct timeval end;
	double dstart, dend, dtotal;
	
	

	// Initializations
	index_count = 0;
	
	// Semaphores
	sem_init(&r, 0, 1);
	sem_init(&s, 0, 1);
	sem_init(&h, 0, 1);
	sem_init(&rtype_counter, 0, 1);
	sem_init(&stype_counter, 0, 1);
	sem_init(&zlock, 0, 1);
	sem_init(&count, 0, 1);
	sem_init(&find, 0, 1);
	for( i = 0; i < 1000; i++) {
		sem_init(&pgTable[i], 0, 1);
		sem_init(&lock[i], 0, 1);
	}
	
	// Set start of simulation
	gettimeofday(&start, NULL);
    dstart = (start.tv_sec*1000.0) + (start.tv_usec/1000.0);
	
	// Create Threads
	for( i = 0; i < NUM_USER; i++) {
		user[i].id = i;
		user[i].thread = user_threads[i];
		if(pthread_create(&user_threads[i], NULL, run_user1, &user[i])){
			printf("ERROR: Unable to create thread.\n");
			return 1;
		}
	}

	// Join Threads
	for( i = 0; i < NUM_USER; i++) {
		if (pthread_join(user_threads[i], NULL)) { 
			printf("Error joining thread #%d!\n", 1);
		} 
	}
	
	// Free Memory
	for( i = 0; i < MAX; i++ ) {
		if ( indexes[i] != NULL ) {
			freeMemory(indexes[i]);
		}
	}
		
	// Set end of simulation
	gettimeofday(&end, NULL);
    dend = (end.tv_sec*1000.0) + (end.tv_usec/1000.0);
		
	// OUTPUT: Timing metrics
	dtotal = (dend - dstart)/1000.0;
	printf("Total Simulation took %lf seconds\n\n", dtotal);	
	
	return 0;
}