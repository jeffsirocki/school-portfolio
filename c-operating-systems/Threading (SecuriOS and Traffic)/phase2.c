// Tyler Bennett and Jeff Sirocki
// CS 3013 - Project 3
// Due: 02-17-15

// Problem 2: SecuriOS Cluster

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
#define TEST 1

#define TRUE 1
#define FALSE 0

#define NUM_CARS 20
#define NUM_EMERGENCY 2
#define NUM_MOTORCADE 2

#define NORTH 0
#define WEST 1
#define SOUTH 2
#define EAST 3
#define NW 0
#define SW 1
#define SE 2
#define NE 3

void * run_car(Car *whatever);
// Declarations

// Queue
TrafficQueueNodePtr NorthHeadPtr = NULL;
TrafficQueueNodePtr NorthTailPtr = NULL;

TrafficQueueNodePtr SouthHeadPtr = NULL;
TrafficQueueNodePtr SouthTailPtr = NULL;

TrafficQueueNodePtr WestHeadPtr = NULL;
TrafficQueueNodePtr WestTailPtr = NULL;

TrafficQueueNodePtr EastHeadPtr = NULL;
TrafficQueueNodePtr EastTailPtr = NULL;

// Threads
pthread_t car_threads[NUM_CARS];
pthread_t emergency_threads[NUM_EMERGENCY];
pthread_t motorcade_threads[NUM_MOTORCADE];

// Semaphores
sem_t quad[4]; // Traffic locks
sem_t queue[4]; // Locks for queues
sem_t deadlock;
sem_t emergency;
sem_t max;

void * run_car(Car *sptr){
	//set the variables
	pthread_t C = sptr->thread;
	int thread_id = (int) sptr->id;
	int type = (int) sptr->type;
	int source = (int) sptr->source;
	int destination = (int) sptr->destination;
	int position;
	int count = 0;
	int flag = 0;
	int maxValue;
	int dead;
	int emergencyValue;
	
	// Increment Thread Car count
	count++;
	

	if(type == 3){
		sleep(5);
		
		//this will lock out the cars from moving.
		sem_wait(&emergency);
		printf("\nMOTORCADE GET OUT THE INTERSECTION\n\n");
		//this will wait until the existing cars have vacated the intersection
		sem_getvalue(&max, &maxValue);
		while(maxValue != 4){
			sem_getvalue(&max, &maxValue);
		}

		int numMoto = (rand() % 5) + 1;
		int i;

		for(i = 0; i < numMoto; i++){

		if((source == 0 && destination == 1) || (source == 1 && destination == 2) || 
		   (source == 2 && destination == 3) || (source == 3 && destination == 0)){
			printf("\n\n--------------------------------------------\n");
			printf("MOTORCADE VEHICLE %d HAS ENTERED THE INTERSECTION.\n\n", i+1);
			printf("Thread %d - Motorcade Vehicle started (Right Turn %dto%d)\n", thread_id, source, destination);
			usleep(1000000);
			printf("Thread %d - Motorcade Vehicle finished (Right Turn %dto%d)\n\n", thread_id, source, destination);
			printf("MOTORCADE VEHICLE %d HAS LEFT THE INTERSECTION.\n", i+1);
			printf("--------------------------------------------\n\n");
		}
		
		if((source == 0 && destination == 2) || (source == 1 && destination == 3) ||
	       (source == 2 && destination == 0) || (source == 3 && destination == 1)){
			printf("\n\n--------------------------------------------\n");
			printf("MOTORCADE VEHICLE %d HAS ENTERED THE INTERSECTION.\n\n", i+1);
			printf("thread %d - Motorcade Vehicle moved (Straight %dto%d)\n", thread_id, source, destination);
			usleep(1000000);
			printf("thread %d - Motorcade Vehicle moved (Straight %dto%d)\n\n", thread_id, source, destination);
			printf("MOTORCADE VEHICLE %d HAS LEFT THE INTERSECTION.\n", i+1);
			printf("--------------------------------------------\n\n");
		}
		
		if((source == 0 && destination == 3) || (source == 1 && destination == 0) ||
		   (source == 2 && destination == 1) || (source == 3 && destination == 2)){
			printf("\n\n--------------------------------------------\n");
			printf("MOTORCADE VEHICLE %d HAS ENTERED THE INTERSECTION.\n\n", i+1);
			printf("thread %d - Motorcade Vehicle started (Left Turn %dto%d)\n", thread_id, source, destination);
			usleep(1000000);
			printf("thread %d - Motorcade Vehicle finished (Left Turn %dto%d)\n\n", thread_id, source, destination);
			printf("MOTORCADE VEHICLE %d HAS LEFT THE INTERSECTION.\n", i+1);
			printf("--------------------------------------------\n\n");
		}
		}
		sem_post(&emergency);
		
	}
	// EMERGENCY Case
	if(type == 2){
		sleep(5);
		
		//this will lock out the cars from moving.
		sem_wait(&emergency);
		printf("\n\nEMERGENCY GET OUT THE INTERSECTION\n\n");
		//this will wait until the existing cars have vacated the intersection
		sem_getvalue(&max, &maxValue);
		while(maxValue != 4){
			sem_getvalue(&max, &maxValue);
		}
		
		if((source == 0 && destination == 1) || (source == 1 && destination == 2) || 
		   (source == 2 && destination == 3) || (source == 3 && destination == 0)){
			printf("\n\n--------------------------------------------\n");
			printf("EMERGENCY VEHICLE HAS ENTERED THE INTERSECTION.\n\n");
			printf("Thread %d - Emergency Vehicle started (Right Turn %dto%d)\n", thread_id, source, destination);
			usleep(1000000);
			printf("Thread %d - Emergency Vehicle finished (Right Turn %dto%d)\n\n", thread_id, source, destination);
			printf("EMERGENCY VEHICLE HAS LEFT THE INTERSECTION.\n");
			printf("--------------------------------------------\n\n");
		}
		
		if((source == 0 && destination == 2) || (source == 1 && destination == 3) ||
	       (source == 2 && destination == 0) || (source == 3 && destination == 1)){
			printf("\n\n--------------------------------------------\n");
			printf("EMERGENCY VEHICLE HAS ENTERED THE INTERSECTION.\n\n");
			printf("thread %d - Emergency Vehicle moved (Straight %dto%d)\n", thread_id, source, destination);
			usleep(1000000);
			printf("thread %d - Emergency Vehicle moved (Straight %dto%d)\n\n", thread_id, source, destination);
			printf("EMERGENCY VEHICLE HAS LEFT THE INTERSECTION.\n");
			printf("--------------------------------------------\n\n");
		}
		
		if((source == 0 && destination == 3) || (source == 1 && destination == 0) ||
		   (source == 2 && destination == 1) || (source == 3 && destination == 2)){
			printf("\n\n--------------------------------------------\n");
			printf("EMERGENCY VEHICLE HAS ENTERED THE INTERSECTION.\n\n");
			printf("thread %d - Emergency Vehicle started (Left Turn %dto%d)\n", thread_id, source, destination);
			usleep(1000000);
			printf("thread %d - Emergency Vehicle finished (Left Turn %dto%d)\n\n", thread_id, source, destination);
			printf("EMERGENCY VEHICLE HAS LEFT THE INTERSECTION.\n");
			printf("--------------------------------------------\n\n");
		}
		
		sem_post(&emergency);
		
	} else {
		/* sem_getvalue(&emergency, &emergencyValue);
		printf(" Emergency value = %d\n", emergencyValue);
		
		sem_getvalue(&deadlock, &dead);
		printf(" DEADlock		value = %d\n", dead); */
	
		// ENQUEUE
		if ( source == 0 ) {
			enqueue_car(&NorthHeadPtr, &NorthTailPtr, C, thread_id);
		} else if (source == 1 ) {
			enqueue_car(&WestHeadPtr, &WestTailPtr, C, thread_id);
		} else if (source == 2) {
			enqueue_car(&SouthHeadPtr, &SouthTailPtr, C, thread_id);
		} else {
			enqueue_car(&EastHeadPtr, &EastTailPtr, C, thread_id);
		}
		
		// DEQUEUE
		flag = 1;
		if ( source == 0 ) {
			while(flag) {
			sem_getvalue(&emergency, &emergencyValue);
				if( NorthHeadPtr != NULL && NorthHeadPtr->id == thread_id && emergencyValue != 0) {
					flag = 0;
				}
			}
		} else if (source == 1 ) {
			while(flag) {
			sem_getvalue(&emergency, &emergencyValue);
				if( WestHeadPtr != NULL && WestHeadPtr->id == thread_id && emergencyValue != 0 ) {
					flag = 0;
				}
			}
		} else if (source == 2) {
			while(flag) {
			sem_getvalue(&emergency, &emergencyValue);
				if( SouthHeadPtr != NULL && SouthHeadPtr->id == thread_id && emergencyValue != 0) {
					flag = 0;
				}
			}
		} else {
			while(flag) {
			sem_getvalue(&emergency, &emergencyValue);
				if( EastHeadPtr != NULL && EastHeadPtr->id == thread_id && emergencyValue != 0) {
					flag = 0;
				}
			}
		}
		
		// THREE CASES:
		
		// 1st case: Right hand turn
		//this case will try to get the semaphore lock on the quadrant in front of it...
		//when it gets it, it will print it has entered with all its info
		// gives up the lock and then prints exit and all its info
		
		// RIGHT TURN
		if((source == 0 && destination == 1) || (source == 1 && destination == 2) || 
		   (source == 2 && destination == 3) || (source == 3 && destination == 0)){
		
			if(source == 0){
				sem_wait(&quad[0]);
				sem_wait(&max);
				
				// DEQUEUE
				sem_wait(&queue[NORTH]);
				dequeue_car(&NorthHeadPtr, &NorthTailPtr);
				sem_post(&queue[NORTH]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Right Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				printf("Thread %d - Type (%d) Car #%d finished (Right Turn %dto%d)\n", thread_id, type, count, source, destination);
				// Car Finished
				
				sem_post(&quad[0]);
				sem_post(&max);
			} else if(source == 1){
				sem_wait(&quad[1]);
				sem_wait(&max);
				
				//DEQUEUE
				sem_wait(&queue[WEST]);
				dequeue_car(&WestHeadPtr, &WestTailPtr);
				sem_post(&queue[WEST]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Right Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				printf("Thread %d - Type (%d) Car #%d finished (Right Turn %dto%d)\n", thread_id, type, count, source, destination);
				// Car Finished
				
				sem_post(&quad[1]);
				sem_post(&max);
			} else if(source == 2){
				sem_wait(&quad[2]);
				sem_wait(&max);
				
				// DEQUEUE
				sem_wait(&queue[SOUTH]);
				dequeue_car(&SouthHeadPtr, &SouthTailPtr);
				sem_post(&queue[SOUTH]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Right Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				printf("Thread %d - Type (%d) Car #%d finished (Right Turn %dto%d)\n", thread_id, type, count, source, destination);
				// Car Finished
				
				sem_post(&quad[2]);
				sem_post(&max);
			} else {
				sem_wait(&quad[3]);
				sem_wait(&max);
				
				// DEQUEUE
				sem_wait(&queue[EAST]);
				dequeue_car(&EastHeadPtr, &EastTailPtr);
				sem_post(&queue[EAST]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Right Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				printf("Thread %d - Type (%d) Car #%d finished (Right Turn %dto%d)\n", thread_id, type, count, source, destination);
				// Car Finished
				
				sem_post(&quad[3]);
				sem_post(&max);
			}
		}
	   
		//  2nd case: Straight
		//wait on deadlock semaphore... once it can obtain the semaphore, wait on block 
		// directly in front of it, obtains it, prints that it is in the intersection
		// then it will wait on the lock of the quad in front of it ... once it gets the 2nd,
		// it can let go of the 1st lock, and then print it is exiting
		// let go of the second lock, then post the deadlock semaphore 

		if((source == 0 && destination == 2) || (source == 1 && destination == 3) ||
	       (source == 2 && destination == 0) || (source == 3 && destination == 1)){
			   
			if(source == 0){
				sem_wait(&deadlock);
				sem_wait(&quad[0]);
				sem_wait(&max);
				
				// DEQUEUE
				sem_wait(&queue[NORTH]);
				dequeue_car(&NorthHeadPtr, &NorthTailPtr);
				sem_post(&queue[NORTH]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Straight %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				
				// Move
				sem_wait(&quad[1]);
				printf("Thread %d - Type (%d) Car #%d moved (Straight %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[0]);
				
				// Car Finished
				printf("Thread %d - Type (%d) Car #%d finished (Straight %dto%d)\n", thread_id, type, count, source, destination);
				sem_post(&quad[1]);
				sem_post(&deadlock);
				sem_post(&max);
				
			} else if(source == 1){
				sem_wait(&deadlock);
				sem_wait(&quad[1]);
				sem_wait(&max);
				
				//DEQUEUE
				sem_wait(&queue[WEST]);
				dequeue_car(&WestHeadPtr, &WestTailPtr);
				sem_post(&queue[WEST]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Straight %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				
				// Move
				sem_wait(&quad[2]);
				printf("Thread %d - Type (%d) Car #%d moved (Straight %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[1]);
				
				// Car Finished
				printf("Thread %d - Type (%d) Car #%d finished (Straight %dto%d)\n", thread_id, type, count, source, destination);
				sem_post(&quad[2]);
				sem_post(&deadlock);
				sem_post(&max);
				
			} else if(source == 2){
				sem_wait(&deadlock);
				sem_wait(&quad[2]);
				sem_wait(&max);
				
				// DEQUEUE
				sem_wait(&queue[SOUTH]);
				dequeue_car(&SouthHeadPtr, &SouthTailPtr);
				sem_post(&queue[SOUTH]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Straight %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				
				// Move
				sem_wait(&quad[3]);
				printf("Thread %d - Type (%d) Car #%d moved (Straight %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[2]);
				
				// Car Finished
				printf("Thread %d - Type (%d) Car #%d finished (Straight %dto%d)\n", thread_id, type, count, source, destination);
				sem_post(&quad[3]);
				sem_post(&deadlock);
				sem_post(&max);
				
			} else {
				sem_wait(&deadlock);
				sem_wait(&quad[3]);
				sem_wait(&max);
				
				// DEQUEUE
				sem_wait(&queue[EAST]);
				dequeue_car(&EastHeadPtr, &EastTailPtr);
				sem_post(&queue[EAST]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Straight %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				
				// Move
				sem_wait(&quad[0]);
				printf("Thread %d - Type (%d) Car #%d moved (Straight %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[3]);
				
				// Car Finished
				printf("Thread %d - Type (%d) Car #%d finished (Straight %dto%d)\n", thread_id, type, count, source, destination);
				sem_post(&quad[0]);
				sem_post(&deadlock);
				sem_post(&max);
			}
		}	
		
		//3rd case: Left turn
		//wait on deadlock semaphore... once it can obtain the semaphore, wait on block 
		// directly in front of it, obtains it, prints that it is in the intersection
		// then it will wait on the lock of the quad in front of it ... once it gets the 2nd,
		// it can let go of the 1st lock. Then, wait on the block to the left of the 2nd block...
		// when it is obtained, let go of the second lock, then print, let go of the third lock
		// post the deadlock semaphore

		if((source == 0 && destination == 3) || (source == 1 && destination == 0) ||
		   (source == 2 && destination == 1) || (source == 3 && destination == 2)){
			   
			if(source == 0){
				sem_wait(&deadlock);
				sem_wait(&quad[0]);
				sem_wait(&max);
				
				// DEQUEUE
				sem_wait(&queue[NORTH]);
				dequeue_car(&NorthHeadPtr, &NorthTailPtr);
				sem_post(&queue[NORTH]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				
				// Move
				sem_wait(&quad[1]);
				printf("Thread %d - Type (%d) Car #%d moved (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[0]);
				sem_wait(&quad[2]);
				printf("Thread %d - Type (%d) Car #%d moved (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[1]);
				
				// Car Finished
				printf("Thread %d - Type (%d) Car #%d finished (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				sem_post(&quad[2]);
				sem_post(&deadlock);
				sem_post(&max);
			} else if(source == 1){
				sem_wait(&deadlock);
				sem_wait(&quad[1]);
				sem_wait(&max);
				
				//DEQUEUE
				sem_wait(&queue[WEST]);
				dequeue_car(&WestHeadPtr, &WestTailPtr);
				sem_post(&queue[WEST]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				
				// Move
				sem_wait(&quad[2]);
				printf("Thread %d - Type (%d) Car #%d moved (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[1]);
				sem_wait(&quad[3]);
				printf("Thread %d - Type (%d) Car #%d moved (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[2]);
				
				// Car Finished
				printf("Thread %d - Type (%d) Car #%d finished (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				sem_post(&quad[3]);
				sem_post(&deadlock);
				sem_post(&max);
			} else if(source == 2){
				sem_wait(&deadlock);
				sem_wait(&quad[2]);
				sem_wait(&max);
				
				// DEQUEUE
				sem_wait(&queue[SOUTH]);
				dequeue_car(&SouthHeadPtr, &SouthTailPtr);
				sem_post(&queue[SOUTH]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				
				// Move
				sem_wait(&quad[3]);
				printf("Thread %d - Type (%d) Car #%d moved (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[2]);
				sem_wait(&quad[0]);
				printf("Thread %d - Type (%d) Car #%d moved (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[3]);
				
				// Car Finished
				printf("Thread %d - Type (%d) Car #%d finished (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				sem_post(&quad[0]);
				sem_post(&deadlock);
				sem_post(&max);
			} else {
				sem_wait(&deadlock);
				sem_wait(&quad[3]);
				sem_wait(&max);
				
				// DEQUEUE
				sem_wait(&queue[EAST]);
				dequeue_car(&EastHeadPtr, &EastTailPtr);
				sem_post(&queue[EAST]);
				
				// Car Start
				printf("Thread %d - Type (%d) Car #%d started (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				
				// Move
				sem_wait(&quad[0]);
				printf("Thread %d - Type (%d) Car #%d moved (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[3]);
				sem_wait(&quad[1]);
				printf("Thread %d - Type (%d) Car #%d moved (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				usleep(1000000);
				sem_post(&quad[0]);
				
				// Car Finished
				printf("Thread %d - Type (%d) Car #%d finished (Left Turn %dto%d)\n", thread_id, type, count, source, destination);
				sem_post(&quad[1]);
				sem_post(&deadlock);
				sem_post(&max);
			}
		}		
	}
	
	//everytime a motorcade is created, we will have the next 2 cars created be type motorcade with 
	//the same source and destination.
}

int main() {
	
	// Declarations
	int i;
	Car car[NUM_CARS];
	Car emerg[NUM_EMERGENCY];
	Car moto[NUM_MOTORCADE];
	
	printf( "\nInitializing Traffic Simulator... \n" );
	printf( "---------------------------------- \n\n");
	
	// Semaphore Initiation
	sem_init(&emergency, 0, 1);
	sem_init(&deadlock, 0, 3);
	sem_init(&max, 0, 4);
	for( i = 0; i < 4; i++ ){
		sem_init(&quad[i], 0, 1);
		sem_init(&queue[i], 0, 1);
	}
	
	for( i = 0; i < NUM_CARS; i++ ) {
		
		// Initiate Car Struct
		car[i].id = i;
		car[i].type = 1; // 
		car[i].thread = car_threads[i];
		
		// Set source and destination
		car[i].source = rand() % 4;
		do {
			car[i].destination = rand() % 4;
		} while( car[i].source == car[i].destination );
			
		// Run Cars
		if (pthread_create(&car_threads[i], NULL, run_car, &car[i] )) {
			printf("Error creating car #%d!\n", i);
			exit(1);
		}
	}
	
	sleep(5);
	
	for( i = 0; i < NUM_EMERGENCY; i++ ) {
		
		// Initiate Car Struct
		emerg[i].id = i;
		emerg[i].type = 2; // 
		emerg[i].thread = emergency_threads[i];
		
		// Set source and destination
		emerg[i].source = rand() % 4;
		do {
			emerg[i].destination = rand() % 4;
		} while( emerg[i].source == emerg[i].destination );
			
		// Run Cars
		if (pthread_create(&emergency_threads[i], NULL, run_car, &emerg[i] )) {
			printf("Error creating car #%d!\n", i);
			exit(1);
		}
	}

	for( i = 0; i < NUM_MOTORCADE; i++ ) {
		
		// Initiate Car Struct
		moto[i].id = i;
		moto[i].type = 3; // 
		moto[i].thread = motorcade_threads[i];
		
		// Set source and destination
		moto[i].source = rand() % 4;
		do {
			moto[i].destination = rand() % 4;
		} while( moto[i].source == moto[i].destination );
			
		// Run Cars
		if (pthread_create(&motorcade_threads[i], NULL, run_car, &moto[i] )) {
			printf("Error creating car #%d!\n", i);
			exit(1);
		}
	}
	
	printf( "\n\nTraffic Simulator Initialization Complete. Running...	\n" );
	printf( "---------------------------------- \n\n");
		
	// CLEAN UP
	for(i = 0; i < NUM_CARS; i++){
		if (pthread_join(car_threads[i], NULL)) { 
			printf("Error joining thread #%d!\n", i);
		} 
	}

	 for(i = 0; i < NUM_EMERGENCY; i++){
		printf( "\tJOINING: #%d\n", i );
		if (pthread_join(emergency_threads[i], NULL)) { 
			printf("Error joining thread #%d!\n", i);
		} 
	} 

	for(i = 0; i < NUM_MOTORCADE; i++){
		printf( "\tJOINING: #%d\n", i );
		if (pthread_join(motorcade_threads[i], NULL)) { 
			printf("Error joining thread #%d!\n", i);
		} 
	}  

	printf( "\n\nTraffic Simulator Complete.\n" );
	printf( "---------------------------------- \n\n");
	
	return 0;	
}

	