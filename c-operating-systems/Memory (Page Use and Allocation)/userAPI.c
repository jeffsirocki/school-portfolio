// Tyler Bennett and Jeff Sirocki
// CS 3013 - Project 4
// Due: 02-27-15

// Implementing Virtual Memory
// Checkpoint Due: 02-23-2015

#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>
#include "prog4.h"

// Global Page Table
Page pageTable[1000] = {{0}};

int evictLRU(int index, int type){
	
	//flag..Mem full
	int counter = 0;
	double accum = 0;
	int indexPage;
	int j;
	int oldIndex = -1;
	struct timeval check;
	double dcheck, dpage, dtotal;
	gettimeofday(&check, NULL);
	dcheck = (check.tv_sec*1000.0) + (check.tv_usec/1000.0);
	
	for(j = 0; j < 1000; j++){
		if(pageTable[j].type == type && pageTable[j].locked == 0){
			counter++;
			dpage = (pageTable[j].time.tv_sec*1000) + (pageTable[j].time.tv_usec/1000);
			dtotal = dcheck - dpage;
			//printf("Page[%d] dtotal = %lf\n", j, dtotal);
			if(dtotal > accum){
				accum = dtotal;
				indexPage = j;
			}
		}
	}
	
	//printf("The value of the accum = %lf, type %d and Evict %d\n", accum, type, indexPage);
	
	if(type == 1){
		usleep(250000);
	
		//sem_wait(&pgTable[j]);
		sem_wait(&s);
		SSD[index] = RAM[pageTable[indexPage].index];
		sem_post(&s);
		
		oldIndex = pageTable[indexPage].index;
		pageTable[indexPage].index = index;
		pageTable[indexPage].type += 1;
		//sem_post(&pgTable[j]);
		
		if (DEBUG) printf(" => Swap Out RAM[%d] to SSD[%d]", oldIndex, index);
		
		return oldIndex;
		
	} else {
		usleep(2500000);
	
		//sem_wait(&pgTable[j]);
		sem_wait(&h);
		HDD[index] = SSD[pageTable[indexPage].index];
		sem_post(&h);
		
		oldIndex = pageTable[indexPage].index;
		pageTable[indexPage].index = index;
		pageTable[indexPage].type += 1;
		//sem_post(&pgTable[j]);
		
		if (DEBUG) printf(" => Swap Out SDD[%d] to HDD[%d]", oldIndex, index);	
		
		return oldIndex;
	}
	
	if(counter == 0){
		printf("Cannot evict. RAM is completely locked.\n");
		return -1;
	}
}

// Eviction Algorithm (First unlocked page is swapped)
int evict(int index, int type){

    //printf("Type = %d\n"); 
	 
	int oldIndex = -1;
	int j;

	for(j = 0; j < 1000; j++){
		sem_wait(&pgTable[j]);
		if(pageTable[j].locked == 0 && pageTable[j].allocated == 1 && pageTable[j].type == type){
			if(type == 1){
				usleep(250000);
				
				sem_wait(&s);
				SSD[index] = RAM[pageTable[j].index];
				sem_post(&s);
				
				oldIndex = pageTable[j].index;
				pageTable[j].index = index;
				pageTable[j].type += 1;
				sem_post(&pgTable[j]);
				
				if (DEBUG) printf(" => Swap Out RAM[%d] to SSD[%d]", oldIndex, index);
				
				return oldIndex;
			} else {
				usleep(2500000);
			
				sem_wait(&h);
				HDD[index] = SSD[pageTable[j].index];
				sem_post(&h);
				
				oldIndex = pageTable[j].index;
				pageTable[j].index = index;
				pageTable[j].type += 1;
				sem_post(&pgTable[j]);
				if (DEBUG) printf(" => Swap Out SDD[%d] to HDD[%d]", oldIndex, index);	
				
				return oldIndex;
			}
		}
		sem_post(&pgTable[j]);
	}

	return oldIndex;
}

// helper that finds the number of pages in the page table that is of the
// given memory type and is allocated. (Use this number later to tell if there
// will be any spots open in that given type of memory) 
int typeCounter(int type){
	int counter = 0;
	int j;
	
	for(j = 0; j < 1000; j++){
		if(pageTable[j].type == type && pageTable[j].allocated == 1){
			counter++;
		}
	}

	return counter;
}

// helper that finds the first non allocated spot within the given type of memory
// type 1 = RAM, type 2 = SSD, type 3 = HDD
// this function is only used when it is known that there is spots open in a place 
// in memory
int findIndex(int type){
	int k;
	if(type == 1){
		//RAM
		for(k = 0; k < 25; k++){
			if(RAM_allocation[k] == 0){
				RAM_allocation[k] = 1;
				return k;
			}
		}
	}

	if(type == 2){
		// SSD
		for(k = 0; k < 100; k++){
			if(SSD_allocation[k] == 0){
				SSD_allocation[k] = 1;
				return k;
			}
		}
	}

	if(type == 3){
		// HDD
		for(k = 0; k < 1000; k++){
			if(HDD_allocation[k] == 0){
				HDD_allocation[k] = 1;
				return k;
			}
		}
	}
}

// Reserves new memory location in RAM
vAddr allocateNewInt(){
	
	int numRam, numSsd;
	int i;
	int p;

	// find the number of pages of RAM that have been allocated
	

	// Loop through Page Table
	for(i = 0; i < 1000; i++){
	
		sem_wait(&pgTable[i]);
		// Check for the first spot that isn't allocated
		if(pageTable[i].allocated == 0){
			//printf(" PAGE %d: ", i);
			
			sem_wait(&rtype_counter);
			numRam = typeCounter(1);
			
			// if there are spots left in the RAM
			//printf(" NUM_RAM: %d", numRam);
			if(numRam < 25){
			
				// Put into RAM
				pageTable[i].allocated = 1;
				sem_post(&rtype_counter);
				pageTable[i].locked = 1;
				struct timeval tv;
				gettimeofday(&tv, NULL);
				pageTable[i].time = tv;
				sem_wait(&find);
				pageTable[i].index = findIndex(1);
				sem_post(&find);
				pageTable[i].type = 1;
				sem_post(&pgTable[i]);
				
				return i;
				
			// SSD
			} else {
			sem_post(&rtype_counter);
			sem_wait(&stype_counter);
			numSsd = typeCounter(2);
				
				//printf(" NUM_SSD: %d ", numSsd);
				//if free SSD space
				if(numSsd < 100){

					//printf( " SSD: page i %d", i);
					

					pageTable[i].allocated = 1;
					pageTable[i].locked = 1;
					pageTable[i].type = 2;
					sem_post(&stype_counter);
					sem_post(&pgTable[i]);

					
					//Evict from RAM to SSD
					sem_wait(&find);
					int x = findIndex(2);
					// printf(" Evict %d ", x);
					int v = evictLRU(x, 1);
					sem_post(&find);
					
					// fail safe if everything is locked in RAM
					if( v == -1){
						return v;
					}

					// Put into RAM
					sem_wait(&pgTable[i]);
					//printf(" User released PageTableLock %d ", i);
					struct timeval tv;
					gettimeofday(&tv, NULL);
					pageTable[i].time = tv; 
					pageTable[i].type = 1;
					pageTable[i].index = v;
					sem_post(&pgTable[i]);
					
					
					return i;

				} else {
			
					sem_post(&stype_counter);
					pageTable[i].allocated = 1;
					pageTable[i].locked = 1;
					sem_post(&pgTable[i]);
					
					//printf(" EVICT HDD ");
					
					// Evict page from SSD and then RAM
					sem_wait(&find);
					p = findIndex(3);
					int q = evictLRU(p, 2);
					if( q == -1){
						return q;
					}
					int r = evictLRU(q, 1);
					if( r == -1){
						return r;
					}
					sem_post(&find);

					// Put into RAM
					sem_wait(&pgTable[i]);
					struct timeval tv;
					gettimeofday(&tv, NULL);
					pageTable[i].time = tv; 
					pageTable[i].index = r;
					pageTable[i].type = 1;
					sem_post(&pgTable[i]);

					return i;
				}

			}
			 
		}
		
		sem_post(&pgTable[i]);
	}
	
	// return -1 if no memory is available in the lower layers for the things
	// in RAM to be pushed into
	printf("ERROR: Memory is full.\n");
	return -1;
}

// Accesses int memory in RAM
int *accessIntPtr(vAddr address){

	sem_wait(&zlock);
	printf(" Access %d", z);
	z++;
	sem_post(&zlock);
	
	//type RAM
	sem_wait(&lock[address]);
	if(pageTable[address].type == 1){
		struct timeval tv;
		gettimeofday(&tv, NULL);
		pageTable[address].time = tv;
		return &RAM[pageTable[address].index];
	}

	//type SSD
	if (pageTable[address].type == 2){
		int s = evictLRU(pageTable[address].index, 1);
		if(s == -1){
		 return NULL;
		}
		struct timeval tv;
		gettimeofday(&tv, NULL);
		pageTable[address].time = tv;
		return &RAM[pageTable[address].index];
	}

	// type HDD
	if (pageTable[address].type == 3){
		int e = evictLRU(pageTable[address].index, 2);
		if(e = -1){
			return NULL;
		}
		int r = evictLRU(e, 1);
		if(r = -1){
			return NULL;
		}
		struct timeval tv;
		gettimeofday(&tv, NULL);
		pageTable[address].time = tv;
		return &RAM[pageTable[address].index];

	}
}

// Unlocks page when user is done
void unlockMemory(vAddr address){

	// unlock the given address in the page table, and then invalidate pointers to the memory
	sem_wait(&pgTable[address]);
	pageTable[address].locked = 0;
	sem_post(&pgTable[address]);
	sem_post(&lock[address]);
	printf( " => RAM[%d] Unlocked\n", pageTable[address].index);
}

// Unallocates memory when user is done
void freeMemory(vAddr address){
	// goes through the page table and unallocates the memory page, as well as any copies
	// of it in lower layers of memory..?
	sem_wait(&pgTable[address]);
	pageTable[address].allocated = 0;
	
	// indicate that there is nothing left in the RAM spot
	if(pageTable[address].type == 1){
		RAM_allocation[pageTable[address].index] = 0;
	}
	if(pageTable[address].type == 2){
		SSD_allocation[pageTable[address].index] = 0;
	}
	if(pageTable[address].type == 3){
		HDD_allocation[pageTable[address].index] = 0;
	}
	sem_post(&pgTable[address]);
}
