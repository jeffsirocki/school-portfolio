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
#define NUM_MOTORCADE 3

#define NORTH 0
#define WEST 1
#define SOUTH 2
#define EAST 3
#define NW 0
#define SW 1
#define SE 2
#define NE 3

// Declarations

// Threads
pthread_t car_threads[NUM_CARS];
pthread_t emergency_threads[NUM_EMERGENCY];
pthread_t motorcade_threads[NUM_MOTORCADE];

sem_t quad[4];
sem_t deadlock;
sem_t emergency;
sem_t max;

void * run_car(Car *sptr){
	//set the variables
	int thread_id = (int) sptr->id;
	int type = (int) sptr->type;
	int source = (int) sptr->source;
	int destination = (int) sptr->destination;
	int position;
	int count = 0;
	int maxValue;
	
	//if you emergency vehicle, sleep for x time, then set the flag to indicate you are coming
	
	if(type == 2){
		usleep(30000000);
		
		//this will lock out the cars from moving.
		sem_wait(&emergency);
		
		//this will wait until the existing cars have vacated the intersection
		sem_getvalue(&max, &maxValue);
		while(maxValue != 4){
			sem_getvalue(&max, &maxValue);
		}
		
		if(source == 0 && destination == 1 || source == 1 && destination == 2 || 
		   source == 2 && destination == 3 || source == 3 && destination == 0){
			printf("thread %d - Emergency Vehicle started (Right Turn %dto%d)\n", thread_id, source, destination);
			usleep(1000000);
			printf("thread %d - Emergency Vehicle finished (Right Turn %dto%d)\n", thread_id, source, destination);
			printf("EMERGENCY VEHICLE HAS LEFT THE INTERSECTION.\n");
		}
		
		if(source == 0 && destination == 2 || source == 1 && destination == 3 ||
	       source == 2 && destination == 0 || source == 3 && destination == 1){
			printf("thread %d - Emergency Vehicle moved (Straight %dto%d)\n", thread_id, source, destination);
			usleep(1000000);
			printf("thread %d - Emergency Vehicle moved (Straight %dto%d)\n", thread_id, source, destination);
			printf("EMERGENCY VEHICLE HAS LEFT THE INTERSECTION.\n");
		}
		
		if(source == 0 && destination == 3 || source == 1 && destination == 0 ||
		   source == 2 && destination == 1 || source == 3 && destination == 2){
			printf("thread %d - Emergency Vehicle started (Left Turn %dto%d)\n", thread_id, source, destination);
			usleep(1000000);
			printf("thread %d - Emergency Vehicle finished (Left Turn %dto%d)\n", thread_id, source, destination);
			printf("EMERGENCY VEHICLE HAS LEFT THE INTERSECTION.\n");
		}
		
		sem_post(&emergency);
	} else {

	// 3 cases...
	
	// 1st case: Right hand turn
	//this case will try to get the semaphore lock on the quadrant in front of it...
	//when it gets it, it will print it has entered with all its info
	// gives up the lock and then prints exit and all its info
	
	count++;
	
	//check for emergency here
	
	if(source == 0 && destination == 1 || source == 1 && destination == 2 || 
	   source == 2 && destination == 3 || source == 3 && destination == 0){
		
		if(source == 0){
			sem_wait(&quad[0]);
			sem_wait(&max);
			
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

	if(source == 0 && destination == 2 || source == 1 && destination == 3 ||
	   source == 2 && destination == 0 || source == 3 && destination == 1){
		   
		if(source == 0){
			sem_wait(&deadlock);
			sem_wait(&quad[0]);
			sem_wait(&max);
			
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

	if(source == 0 && destination == 3 || source == 1 && destination == 0 ||
	   source == 2 && destination == 1 || source == 3 && destination == 2){
		   
		if(source == 0){
			sem_wait(&deadlock);
			sem_wait(&quad[0]);
			sem_wait(&max);
			
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
	
	//everytime a motorcade is created, we will have the next 2 cars created be type motorcade with 
	//the same source and destination.
	}
}

int main() {
	
	// Declarations
	int i;
	Car car[20];
	
	printf( "\nInitializing Traffic Simulator... \n" );
	printf( "---------------------------------- \n\n");
	
	// Semaphore Initiation
	sem_init(&deadlock, 0, 3);
	sem_init(&emergency, 0, 1);
	sem_init(&max, 0, 4);
	for( i = 0; i < 4; i++ ){
		sem_init(&quad[i], 0, 1);
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
	
	printf( "\n\nTraffic Simulator Initialization Complete. Running...	\n" );
	printf( "---------------------------------- \n\n");
		
	// CLEAN UP
	for(i = 0; i < NUM_CARS; i++){
		if (pthread_join(car_threads[i], NULL)) { 
			printf("Error joining thread #%d!\n", i);
		} 
	}
	
	printf( "\n\nTraffic Simulator Complete.\n" );
	printf( "---------------------------------- \n\n");

	/* for(i = 0; i < NUM_EMERGENCY; i++){
		printf( "\tJOINING: #%d\n", i );
		if (pthread_join(car_threads[i], NULL)) { 
			printf("Error joining thread #%d!\n", i);
		} 
	} 

	for(i = 0; i < NUM_MOTORCADE; i++){
		printf( "\tJOINING: #%d\n", i );
		if (pthread_join(car_threads[i], NULL)) { 
			printf("Error joining thread #%d!\n", i);
		} 
	}  */
	
	return 0;
	
}
	