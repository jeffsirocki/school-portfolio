// Tyler Bennett and Jeff Sirocki
// CS 3013 - Project 3
// Due: 02-17-15

// Problem 1: SecuriOS Cluster
// Checkpoint Due: 2/11/15

#define _GNU_SOURCE 

#include <pthread.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <stdlib.h>

#define NUM_JOBS 20

pthread_t jobs[NUM_JOBS];
int ts_count, secret_count, u_count;

//pointers to queue
	
pthread_mutex_t empty;
pthread_mutex_init(&empty, NULL);
pthread_mutex_t turn;
pthread_mutex_init(&turn, NULL);
pthread_mutex_t secret;
pthread_mutex_init(&secret, NULL);
pthread_mutex_t uc; 
pthread_mutex_init(&uc, NULL);
pthread_mutex_t queue;
pthread_mutex_init(&queue, NULL);
pthread_mutex_t ts_num;
pthread_mutex_init(&ts_num, NULL);


struct Info{
	int thread_id;
	int sec_type;
	pthread_mutex_t lock;
}

//this function will take the highest two ts jobs and put them 
//at the top of the queue to be run next
void TS_finder(){
	
	return;
}

//function that the threads will run will be put here ... must return void *
void * run_job(void *struct_ptr){
	
	int thread_id = (int) struct_ptr->thread_id;
	int sec_type = (int) struct_ptr->sec_type;
	//enqueue
	
	while(1){
		
		pthread_mutex_lock(&ts_num);
		if(ts_count == 3){
			pthread_mutex_lock(&queue);
			TS_finder();
		}
		pthread_mutex_unlock(&ts_num);
		
		
		//isTopOfQueue? If not, block until you are
		// if so, dequeue and wait on turn.
		
		pthread_mutex_lock(&turn);
		if(*thread_id == 1 || *thread_id == 2){
			pthread_mutex_lock(&secret); 
			secret_count++;
			if(secret_count == 1){
				pthread_mutex_lock(&empty);
			}
		pthread_mutex_unlock(&secret);
		}
		
		if(*thread_id == 3)){
			pthread_mutex_lock(&uc);
			u_count++;
			if(u_count == 1){
				pthread_mutex_lock(&empty);
			}
			pthread_mutex_unlock(&uc);
		}
	
		pthread_mutex_unlock(&turn);
	
	
        //print, "execute", then print again

			
		if(*thread_id == 1 || *thread_id == 2){
			pthread_mutex_lock(&secret)
			secret_count--;
			if(secret_count == 0){
				pthread_mutex_unlock(&empty);
			}
			pthread_mutex_unlock(&secret);
		}
	
		if(*thread_id == 3){
			pthread_mutex_lock(&uc)
			u_count--;
			if(u_countcount == 0){
				pthread_mutex_unlock(&empty);
			}
			pthread_mutex_unlock(&uc);
		}
		
		//how to sleep for random amount of time
		sleep(rand());
		pthread_mutex_lock(&ts_num);
		if(*thread_id  == 1){
			ts_count++
		}
		pthread_mutex_unlock(&ts_num);
		
		enqueue();
	}
	return;
}

int main(){
	
	int j;
	
	for(j = 0; j < NUM_JOBS; j++){
		//create struct
		struct Info *new_thread = malloc(sizeof(struct Info));
		
		//populate
		new_thread->thread_id = j;
		if(j < 7){
			new_thread->sec_type = 1;
		} else if(j > 7 && j < 13){
			new_thread->sec_type = 2;
		} else {
			new_thread->sec_type = 3;
		}

		//populate it with thread number (based on j) and security mode (how will we do this?)
		//as well as the head and tail ptr of the queue
		//the argument passed will have to be a pointer to the struct
		if(pthread_create(&jobs[j], NULL, run_job, &new_thread)){
			printf("ERROR: Unable to create thread.\n");
			return 1;
		}
	}
	
	
	
	// queue stuff
	
	//I think we need to have a for loop here that joins the threads for some reason unknown to me
	
	//so we need to create a queue (Jeff did it) and then populate the queue with threads. 
	//Maybe make some sort of counter that keeps track of the number of TS threads in the queue.
	// If that number reaches three, 2 first TS jobs are moved to the top of the queue to be run right away.
	
	
	//MY NAME IS JEFF cluster code stuff, his speciality 
	// need to print the thread number, what security mode it is running in, and which half of the cluster 
	// it is gonna use/used.
	//job will usleep(rand(.25 - 2 seconds)) during the 2 prints.
	
	//now that the work has been done, sleep for a random amount of time and then enqueue itself,
	// if it is a TS job obtain the mutex lock on numTS and then increment the value.
	
 return 0;
}




