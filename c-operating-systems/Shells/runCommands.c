// Jeff Sirocki and Tyler Bennett
// CS 3013 - Project 1
// Due: 01-26-2015

// Phase 1
// Checkpoint Due: 01-21-2015

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/time.h>
#include <sys/resource.h>

/* Output
 * - Wall Clock
 * - CPU Time (User + System)
 * - Process Preemption
 * - Process gave Up
 * - Page faults
 * - Page faults that used unreclaimed
*/

// Main Function for Shells
int main( int argc, char **argv) {

   // Shell Variable Declaration
   pid_t pid;
   int child;
   int who = RUSAGE_CHILDREN;
   struct timeval commandS, commandF;
   struct rusage ru;
   double commandStart, commandFinish, commandElapsed, userCPU, systemCPU;

   // Create a child process
   pid = fork();
   if (pid < 0) { // Error, pid = -1
	printf( "Error: fork()\n" );
   } else if (pid == 0) { // Child Process
	if (execvp(argv[1], argv+1) < 0) {
	   printf( "Error: execvp, %s is not a valid command.\n", argv[1]); // Error
	}
   } else { // Parent Process
   
    // Child process Start
    gettimeofday(&commandS, NULL);
	commandStart = (commandS.tv_sec*1000.0) + (commandS.tv_usec/1000.0);
	wait(&child);
	gettimeofday(&commandF, NULL);
	commandFinish = (commandF.tv_sec*1000.0) + (commandF.tv_usec/1000.0);
	// Child process End
	
	// Get Usage statistics
	getrusage(who, &ru);
	
	// Convert timeval to milliseconds
	userCPU = (ru.ru_utime.tv_sec*1000.0) + (ru.ru_utime.tv_usec/1000.0);
	systemCPU = (ru.ru_stime.tv_sec*1000.0) + (ru.ru_stime.tv_usec/1000.0);

	// Wall Clock Time
	commandElapsed = commandFinish - commandStart;

	// OUTPUT
	printf("-----------------------------------");
	printf("\nCHILD PROCESS EXECUTION STATISTICS:\n");

	printf("\nWALL CLOCK TIME:");

	printf("\nThe command '%s' took %lf milliseconds to finish.\n",
                            argv[1], commandElapsed);

    printf("\nTOTAL SYSTEM CPU TIME USED: %lf milliseconds\n", systemCPU);

    printf("TOTAL USER CPU TIME USED: %lf milliseconds\n", userCPU);

    printf("\nNUMBER OF TIMES PROCESS ENDED INVOLUNTARILY: %ld\n", ru.ru_nivcsw);

    printf("\nNUMBER OF TIMES PROCESS GAVE UP CPU VOLUNTARILY: %ld\n", ru.ru_nvcsw);

    printf("\nNUMBER OF PAGE FAULTS: %ld\n", ru.ru_majflt);

    printf("\nNUMBER OF PAGE FAULTS THAT COULD BE SATISFIED\nUSING UNRECLAIMED PAGES: %ld \n\n", ru.ru_minflt);
   }
   
   // End of Phase 1
   return 0;
}
