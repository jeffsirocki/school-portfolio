// Tyler Bennett and Jeff Sirocki
// CS 3013 - Project 3
// Due: 02-17-15

// Problem 1: SecuriOS Cluster
// Checkpoint Due: 02-11-2015

#define _BSD_SOURCE
#include <pthread.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <semaphore.h> 
#include <time.h>
#include "prog3.h"

#define DEBUG 0
#define TEST 0

#define TRUE 1
#define FALSE 0

#define NUM_JOBS 20
#define TOP_SECRET_COUNT 6
#define SECRET_COUNT 6
#define UNCLASSIFIED_COUNT 8
 
//Declarations

// Queue
QueueNodePtr jobsHeadPtr = NULL;
QueueNodePtr jobsTailPtr = NULL;

// Threads
pthread_t job_threads[NUM_JOBS];

pthread_t topsecretThreads[TOP_SECRET_COUNT];
pthread_t secretThreads[SECRET_COUNT];
pthread_t unclassifiedThreads[UNCLASSIFIED_COUNT];

// Mutexes 
pthread_mutex_t cluster = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t queue = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t empty = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t full = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t turn1 = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t turn2 = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t top_secret = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t secret = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t uc = PTHREAD_MUTEX_INITIALIZER;

// Globals
int cluster_state;
int ts_count;
int s_count;
int uc_count;

// Run SecuriOS System
void * run_job(Info *sptr){
	
	// Thread information
	pthread_t T;
	int thread_id = (int) sptr->id;
	int sec_type = (int) sptr->sec_type;
	int count = 0;
	int flag;
	
	// Run Forever
	while(TRUE){
	
		count++;
		// 1.1 Check TS count
		pthread_mutex_lock(&queue);
		pthread_mutex_lock(&top_secret);
		if(ts_count >= 3){
			
			if(TEST) printf("\n------------------------\n");
			if(TEST) printf("TOP SECRET - REORDERING\n");
			if(TEST) printf("------------------------\n\n");

			reorder_queue( &jobsHeadPtr, &jobsTailPtr );
			
		}
		pthread_mutex_unlock(&top_secret);
		pthread_mutex_unlock(&queue);
		
		// 1.2 Check Head of Queue
		flag = 1;
		while(flag) {
			pthread_mutex_lock(&queue);
			if( jobsHeadPtr != NULL && jobsHeadPtr->id == thread_id ) {
				
				if(sec_type == 1) {
					pthread_mutex_lock(&top_secret);
					ts_count--;
					pthread_mutex_unlock(&top_secret);
				}
				
				flag = 0;
			}
			pthread_mutex_unlock(&queue);
		}
		
		// 1.3 Check If (TS) (S) (UC) 
		
		// TURN 1 - Synchronization
		pthread_mutex_lock(&turn1);
		if(sec_type == 1 || sec_type == 2){
			pthread_mutex_lock(&secret); 
			s_count++;
			if(s_count == 1){
				pthread_mutex_lock(&empty);
			}
		pthread_mutex_unlock(&secret);
		}
		
		if(sec_type == 3){
			pthread_mutex_lock(&uc);
			uc_count++;
			if(uc_count == 1){
				pthread_mutex_lock(&empty);
			}
			pthread_mutex_unlock(&uc);
		}
		
		pthread_mutex_unlock(&turn1);
		// TURN 1
	
		/* ----------------------------------------------------------
								1.4 WORK START
		------------------------------------------------------------*/
        //print, "execute", then print again
		
		pthread_mutex_lock(&cluster);
		if(DEBUG) printf("\tLOCK: cluster\n");
		if (cluster_state == 1 ) {
		
			// Add to Cluster
			cluster_state = 2;
			pthread_mutex_unlock(&cluster);
			if(DEBUG) printf("\tUNLOCK: cluster\n");
			
			T = dequeue( &jobsHeadPtr, &jobsTailPtr );
			
			// Job Running
			printf("Thread %d - SecuriOS (%d) Job #%d started (LEFT CLUSTER)\n", thread_id, sec_type, count);
			usleep(rand() % 1750000 + 250000);
			printf("Thread %d - SecuriOS (%d) Job #%d finished (LEFT CLUSTER)\n\n", thread_id, sec_type, count);
			// Job Finished
			
			// Restore Cluster
			pthread_mutex_lock(&cluster);
			if(DEBUG) printf("\tLOCK: cluster\n");
			if (cluster_state == 2 ) {
				cluster_state = 1;
			} else {
				cluster_state = 3;
				pthread_mutex_unlock(&full);
				if(DEBUG) printf("\tUNLOCK: full\n");
			}
			pthread_mutex_unlock(&cluster);
			if(DEBUG) printf("\tUNLOCK: cluster\n");
			
			// Go to Turn 2
			
		} else if ( cluster_state == 2 ) {
			
			// Add to Cluster
			pthread_mutex_lock(&full);
			if(DEBUG) printf("\tLOCK: full\n");
			cluster_state = 4;
			pthread_mutex_unlock(&cluster);
			if(DEBUG) printf("\tUNLOCK: cluster\n");
			
			T = dequeue( &jobsHeadPtr, &jobsTailPtr );
			
			// Job Running
			printf("Thread %d - SecuriOS (%d) Job #%d started (RIGHT CLUSTER)\n", thread_id, sec_type, count);
			usleep(rand() % 1750000 + 250000);
			printf("Thread %d - SecuriOS (%d) Job #%d finished (RIGHT CLUSTER)\n\n", thread_id, sec_type, count);
			// Job Finished
			
			// Restore Cluster
			pthread_mutex_lock(&cluster);
			if(DEBUG) printf("\tLOCK: cluster\n");
			if (cluster_state == 3 ) {
				cluster_state = 1;
			} else {
				cluster_state = 2;
				pthread_mutex_unlock(&full);
				if(DEBUG) printf("\tUNLOCK: full\n");
			}
			pthread_mutex_unlock(&cluster);
			if(DEBUG) printf("\tUNLOCK: cluster\n");
			
			// Go to Turn 2
			
		} else if ( cluster_state == 3 ) {
		
			// Add to Cluster
			pthread_mutex_lock(&full);
			if(DEBUG) printf("\tLOCK: full\n");
			cluster_state = 4;
			pthread_mutex_unlock(&cluster);
			if(DEBUG) printf("\tUNLOCK: cluster\n");
			
			T = dequeue( &jobsHeadPtr, &jobsTailPtr );
			
			// Job Running
			printf("Thread %d - SecuriOS (%d) Job #%d started (LEFT CLUSTER)\n", thread_id, sec_type, count);
			usleep(rand() % 1750000 + 250000);
			printf("Thread %d - SecuriOS (%d) Job #%d finished (LEFT CLUSTER)\n\n", thread_id, sec_type, count);
			// Job Finished
			
			// Restore Cluster
			pthread_mutex_lock(&cluster);
			if(DEBUG) printf("\tLOCK: cluster\n");
			if (cluster_state == 2 ) {
				cluster_state = 1;
			} else {
				cluster_state = 3;
				pthread_mutex_unlock(&full);
				if(DEBUG) printf("\tUNLOCK: full\n");
			}
			pthread_mutex_unlock(&cluster);
			if(DEBUG) printf("\tUNLOCK: cluster\n");
			
			// Go to Turn 2
			
		} else {
		
			pthread_mutex_unlock(&cluster);
			if(DEBUG) printf("\tUNLOCK: cluster\n");
			
			// Cluster State 4
			pthread_mutex_lock(&full);
			if(DEBUG) printf("\tLOCK: full (STATE 4)\n\n");
			
			pthread_mutex_lock(&cluster);
			if(DEBUG) printf("\tLOCK: cluster\n");
				
				
				/* ----------------------------------------------------------
									  1.5 Cluster State 4
				------------------------------------------------------------*/
				
			if (cluster_state == 1 ) {
			
				// Add to Cluster
				cluster_state = 2;
				pthread_mutex_unlock(&full);
				if(DEBUG) printf("\tUNLOCK: full (STATE 4)\n");
				pthread_mutex_unlock(&cluster);
				if(DEBUG) printf("\tUNLOCK: cluster\n");
				
				T = dequeue( &jobsHeadPtr, &jobsTailPtr );
				
				// Job Running
				printf("Thread %d - SecuriOS (%d) Job #%d started (LEFT CLUSTER)\n", thread_id, sec_type, count);
				usleep(rand() % 1750000 + 250000);
				printf("Thread %d - SecuriOS (%d) Job #%d finished (LEFT CLUSTER)\n\n", thread_id, sec_type, count);
				// Job Finished
				
				// Restore Cluster
				pthread_mutex_lock(&cluster);
				if(DEBUG) printf("\tLOCK: cluster\n");
				if (cluster_state == 2 ) {
					cluster_state = 1;
				} else {
					cluster_state = 3;
					pthread_mutex_unlock(&full);
					if(DEBUG) printf("\tUNLOCK: full (STATE 4)\n\n");
				}
				pthread_mutex_unlock(&cluster);
				if(DEBUG) printf("\tUNLOCK: cluster\n");
				
				// Go to Turn 2
			
			} else if ( cluster_state == 2 ) {
			
				// Add to Cluster
				cluster_state = 4;
				pthread_mutex_unlock(&cluster);
				if(DEBUG) printf("\tUNLOCK: cluster\n");
				
				T = dequeue( &jobsHeadPtr, &jobsTailPtr );
				
				// Job Running
				printf("Thread %d - SecuriOS (%d) Job #%d started (RIGHT CLUSTER)\n", thread_id, sec_type, count);
				usleep(rand() % 1750000 + 250000);
				printf("Thread %d - SecuriOS (%d) Job #%d finished (RIGHT CLUSTER)\n\n", thread_id, sec_type, count);
				// Job Finished
				
				// Restore Cluster
				pthread_mutex_lock(&cluster);
				if(DEBUG) printf("\tLOCK: cluster\n");
				if (cluster_state == 3 ) {
					cluster_state = 1;
				} else {
					cluster_state = 2;
					pthread_mutex_unlock(&full);
					if(DEBUG) printf("\tUNLOCK: full (STATE 4)\n");
				}
				pthread_mutex_unlock(&cluster);
				if(DEBUG) printf("\tUNLOCK: cluster\n");
				
				// Go to Turn 2
			
			} else { // cluster_state 3
			
				// Add to Cluster
				cluster_state = 4;
				pthread_mutex_unlock(&cluster);
				if(DEBUG) printf("\tUNLOCK: cluster\n");
				
				T = dequeue( &jobsHeadPtr, &jobsTailPtr );
				
				// Job Running
				printf("Thread %d - SecuriOS (%d) Job #%d started (LEFT CLUSTER)\n", thread_id, sec_type, count);
				usleep(rand() % 1750000 + 250000);
				printf("Thread %d - SecuriOS (%d) Job #%d finished (LEFT CLUSTER)\n\n", thread_id, sec_type, count);
				// Job Finished
				
				// Restore Cluster
				pthread_mutex_lock(&cluster);
				if(DEBUG) printf("\tLOCK: cluster\n");
				if (cluster_state == 2 ) {
					cluster_state = 1;
				} else {
					cluster_state = 3;
					pthread_mutex_unlock(&full);
					if(DEBUG) printf("\tUNLOCK: full (STATE 4)\n");
				}
				pthread_mutex_unlock(&cluster);
				if(DEBUG) printf("\tUNLOCK: cluster\n");
				
				// Go to Turn 2
			}
				/* ----------------------------------------------------------
									  State 4
				------------------------------------------------------------*/
		
		}
		/* ----------------------------------------------------------
								WORK END
		------------------------------------------------------------*/
		
		// 1.6 TURN 2 - Restoration
		pthread_mutex_lock(&turn2);	
		if(sec_type == 1 || sec_type == 2){
			pthread_mutex_lock(&secret);
			s_count--;
			if(s_count == 0){
				pthread_mutex_unlock(&empty);
			}
			pthread_mutex_unlock(&secret);
		}
	
		if(sec_type == 3){
			pthread_mutex_lock(&uc);
			uc_count--;
			if(uc_count == 0){
				pthread_mutex_unlock(&empty);
			}
			pthread_mutex_unlock(&uc);
		}
		pthread_mutex_unlock(&turn2);
		// TURN 2
		
		// 1.7 Random Delay
		if (sec_type == 1 ) {
			sleep(12); // 12 seconds
		} else {
			usleep(rand() % 500000 + 500000); // .5 to 1 seconds
		}
		
		// 1.8 Enqueue
		pthread_mutex_lock(&queue);
		enqueue( &jobsHeadPtr, &jobsTailPtr, T, thread_id, sec_type);
		pthread_mutex_unlock(&queue);
		
		if (0) {
			print_queue(jobsHeadPtr);
		}
		
		// 1.9 Top Secret 
		
		if(sec_type  == 1){
			pthread_mutex_lock(&top_secret);
			ts_count++;
			if(DEBUG) printf( "Top Secret Count: %d\n\n", ts_count );
			pthread_mutex_unlock(&top_secret);
		}
		
	}
}

int main(){

	// Declaration
	int i;
	Info info[20];
	ts_count = 0;
	s_count = 0;
	uc_count = 0;
	cluster_state = 1;

	// Mutex Initiation
	pthread_mutex_init(&cluster, NULL);
	pthread_mutex_init(&queue, NULL);
	pthread_mutex_init(&empty, NULL);
	pthread_mutex_init(&full, NULL);
	pthread_mutex_init(&turn1, NULL);
	pthread_mutex_init(&turn2, NULL);
	pthread_mutex_init(&top_secret, NULL);
	pthread_mutex_init(&secret, NULL);
	pthread_mutex_init(&uc, NULL);
	
	// OUTPUT
	printf( "\nInitializing SecuriOS Cluster... \n" );
	printf( "---------------------------------- \n\n");
	
	// Create Jobs and Populate Queue
	for(i = 0; i < NUM_JOBS; i++){
		//populate
		info[i].id = i;
		if(i < 6){
			info[i].sec_type = 1; // Top Secret 6
		} else if(i >= 6 && i < 12){
			info[i].sec_type = 2; // Secret 6
		} else {
			info[i].sec_type = 3; // Unclassified 8
		}
		
		// Create Thread
		if(pthread_create(&job_threads[i], NULL, run_job, &info[i] ) ){
			printf("ERROR: Unable to create thread.\n");
			return 1;
		}
		
		// Enqueue Random Delay
		pthread_mutex_lock(&queue);
		usleep(rand() % 250000 + 250000);
		enqueue( &jobsHeadPtr, &jobsTailPtr, job_threads[i], i, info[i].sec_type );
		
		// Increment Top Secret
		if(info[i].sec_type  == 1){
			pthread_mutex_lock(&top_secret);
			ts_count++;
			pthread_mutex_unlock(&top_secret);
			if (DEBUG) printf( "Top Secret Count: %d\n\n", ts_count );
		}
		pthread_mutex_unlock(&queue);
		
		printf("Added Job #%d to Queue \n", i); 
	}
	
	// OUTPUT
	printf( "\n\nSecuriOS Cluster Initialization Complete. Running...	\n" );
	printf( "---------------------------------- \n\n");
	
	// sleep(100);
	
	// CLEAN UP
	for(i = 0; i < NUM_JOBS; i++){
		printf( "\tJOINING: #%d\n", i );
		if (pthread_join(job_threads[i], NULL)) { 
			printf("Error joining thread #%d!\n", i);
		} 
	} 
	
	pthread_mutex_destroy(&uc);
	
	return 0;
}




