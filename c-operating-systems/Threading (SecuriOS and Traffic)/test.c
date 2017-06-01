// Tyler Bennett and Jeff Sirocki
// CS 3013 - Project 3
// Due: 02-17-15

// Problem 1: SecuriOS Cluster
// Checkpoint Due: 02-11-2015

#define _BSD_SOURCE
#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <semaphore.h> 
#include <time.h>
#include "phase1.h"

#define DEBUG 1

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

void * hello(Info *sptr){

	pthread_t T;
	int thread_id = (int) sptr->id;
	int sec_type = (int) sptr->sec_type;
	int count = 0;
	
	while(TRUE) {
		// CONDITION VARIABLES
		// 1.2 Check if head of queue
		pthread_mutex_lock(&queue);
		//T = dequeue( &jobsHeadPtr, &jobsTailPtr );
		
		pthread_mutex_unlock(&queue);
		
		count++;
		printf("Thread %d - SecuriOS (%d) Job #%d started\n", thread_id, sec_type, count);
		usleep(rand() % 1750000 + 250000);
		printf("Thread %d - SecuriOS (%d) Job #%d finished\n", thread_id, sec_type, count);
		
		pthread_mutex_lock(&queue);
		//enqueue( &jobsHeadPtr, &jobsTailPtr, T );
		pthread_mutex_unlock(&queue);
	}
	pthread_exit(NULL);
}

int main(){

	// Declaration
	int i, delay;
	Info info[20];
	ts_count = 0;
	s_count = 0;
	uc_count = 0;
	cluster_state = 1;

	// Mutex Initiation
	pthread_mutex_init(&cluster, NULL);
	pthread_mutex_init(&queue, NULL);
	pthread_mutex_init(&empty, NULL);
	pthread_mutex_init(&turn1, NULL);
	pthread_mutex_init(&turn2, NULL);
	pthread_mutex_init(&top_secret, NULL);
	pthread_mutex_init(&secret, NULL);
	pthread_mutex_init(&uc, NULL);
	
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
		if(pthread_create(&job_threads[i], NULL, *hello, &info[i] ) ){
			printf("ERROR: Unable to create thread.\n");
			return 1;
		}

		/* // Create Thread
		if(pthread_create(&job_threads[i], NULL, run_job, &info[i])){
			printf("ERROR: Unable to create thread.\n");
			return 1;
		} */
		
		// Enqueue Random Delay
		pthread_mutex_lock(&queue);
		delay = rand() % 250000 + 250000;
		usleep(delay); // Delay .25 - .5 seconds
		enqueue( &jobsHeadPtr, &jobsTailPtr, &job_threads[i] );
		pthread_mutex_unlock(&queue);
		
		printf("Added Job #%d to Queue after Delay %dusec \n", i, delay); 
	}
	
	printf( "\n\nSecuriOS Cluster Initialization Complete. Running...	\n" );
	printf( "---------------------------------- \n\n");
	
	sleep(2);
	
	exit(0);
}
