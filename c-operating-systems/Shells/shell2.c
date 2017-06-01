// Jeff Sirocki and Tyler Bennett
// CS 3013 - Project 1
// Due: 01-26-2015

// Final Project Due: 01-27-2015

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <string.h>
#include <sys/wait.h>

/* Data
 * - Wall Clock
 * - CPU Time (User + System)
 * - Process Preemption
 * - Process gave Up
 * - Page faults
 * - Page faults that used unreclaimed
*/

// Structure to store background process information
typedef struct bgroundTag{
	pid_t pid;
	struct timeval start;
	char *cmd;
	int flag; //is finished if 1 
	int valid; // 0 invalid, 1 valid
} bground;

// Main Function for Shells
int main( int argc, char **argv) {
	
	/* Declaration of Variables */

	// Counters
	int i, j; 

	// Shell Variable Declaration
   	pid_t pid;
	pid_t bpid;
   	int child;
   	int who = RUSAGE_CHILDREN;
   	struct timeval commandS, commandF, bgS, bgF;
   	struct rusage ru, ru2;
   	double commandStart, commandFinish, commandElapsed, userCPU, systemCPU;

	// Read Line
	char *inputLine;
	char *command = NULL;
	size_t length;
	int ampbool;
	char *token;
	char *arguments[32];

	//Initialize background array
	bground bg[25];
	for(j = 0; j < 25; j++){
		bg[j].valid = 0;
	}

	/* Declaration End */

	// Running Shell
	while(1){
		inputLine = NULL;
		command = NULL;
		length = 0;
		ampbool = 1;
		
		// Read LINE
		while( command == NULL ) {
			printf("==>");	
			getline(&inputLine, &length, stdin);

			i = 1;
			token = strtok(inputLine, " \n");
			command = token;
			arguments[0] = command;
		}
		
		// Parse Input
		while(token != '\0'){
			token = strtok(NULL, " \n");
			arguments[i] = token;
			i++;
		}
		
		// Identify Background Process (ampbool = 0)
		ampbool = strcmp("&", arguments[i-2]);
		if(ampbool == 0){
			arguments[i-2] = NULL;
			gettimeofday(&bgS, NULL);	
		}		

		//Exit: Checks for Background Processes (j > 0)
		j = 0;
		for( i = 0; i < 25; i++ ) {
			if( bg[i].valid != 0 && bg[i].flag == 0 ) {
				j++;
			}
		}
		
		//Exit Logic: Array is (Null, Done, Waiting)
		if(strcmp("exit", command) == 0){    
			if( bg[0].valid == 0 ) {
				return 0;
			} else if ( j = 0 ){
				// All done
				return 0;
			} else {
				j = 1;
				while(j){
					for(i = 0; i < 25; i++){
						if(bg[i].valid == 1 && bg[i].flag == 0){
							wait4(bg[i].pid, NULL, WEXITED, &ru2);
							gettimeofday(&bgF, NULL);
							commandStart = (bg[i].start.tv_sec*1000.0) + (bg[i].start.tv_usec/1000.0);
							commandFinish = (bgF.tv_sec*1000.0) + (bgF.tv_usec/1000.0);
						
							// Convert timeval to milliseconds
							userCPU = (ru2.ru_utime.tv_sec*1000.0) + (ru2.ru_utime.tv_usec/1000.0);
							systemCPU = (ru2.ru_stime.tv_sec*1000.0) + (ru2.ru_stime.tv_usec/1000.0);
	
							// Wall Clock Time
							commandElapsed = commandFinish - commandStart;
	
							// OUTPUT
							printf("-----------------------------------");
							printf("\nCHILD PROCESS EXECUTION STATISTICS:\n");
							printf("\nWALL CLOCK TIME:");
							printf("\nThe background job '%s' took %f milliseconds to finish.\n", bg[i].cmd, commandElapsed);
							printf("\nTOTAL SYSTEM CPU TIME USED: %f milliseconds\n", systemCPU);
							printf("TOTAL USER CPU TIME USED: %f milliseconds\n", userCPU);
							printf("\nNUMBER OF TIMES PROCESS ENDED INVOLUNTARILY: %ld\n", ru2.ru_nivcsw);
							printf("\nNUMBER OF TIMES PROCESS GAVE UP CPU VOLUNTARILY: %ld\n", ru2.ru_nvcsw);
							printf("\nNUMBER OF PAGE FAULTS: %ld\n", ru2.ru_majflt);
							printf("\nNUMBER OF PAGE FAULTS THAT COULD BE SATISFIED\nUSING UNRECLAIMED PAGES: %ld \n\n", ru2.ru_minflt);
							// Print END
							printf("Here is the print info ^^ of background job: %d.\n", bg[i].pid);
						}
					}
					j=0; // Exit While
				}

				// All Jobs Complete
				return 0;
			}
		// Change Directory( cd dir )
		} else if(strcmp("cd", command) == 0){
			chdir(arguments[1]);
		
		// Jobs Command (list all background processes)
		} else if(strcmp("jobs", command) == 0){
			//loop through our array and print backgrounded jobs, if any.
			printf(" Jobs: (id) cmd \n");
			printf("----------------\n");
			for( i = 0; i < 25; i++ ){
				if( bg[i].valid == 1 && bg[i].flag == 0 ){
					printf( "      (%d) %s \n", bg[i].pid, bg[i].cmd );
				}
			}  
		} else {
   
			// Create a child process
			pid = fork();
			printf("pid = %d\n", pid);

			// Error: pid = -1
			if (pid < 0) {
				printf( "Error: fork()\n" );

			// Child Process
			} else if (pid == 0) {
				if (execvp(command, arguments) < 0) {
				printf("Here is what command's value is:%s.\n", command);
				printf( "Error: execvp, %s is not a valid command.\n", command); // Error
				free(inputLine);
				return 0;
				}			
			
			// Parent Process
			} else {					
	
				/* Add Background Process( ampbool == 0 ) */
				if( ampbool == 0 ){

					// Create Process
					bground newbg;
					newbg.cmd = command;
					newbg.pid = pid;
					newbg.start = bgS;
					newbg.flag = 0;
					newbg.valid = 1;

					i = 0;
					j = 1;
					// Insert process into array
					while(j) {
						if( bg[i].valid == 0 || bg[i].flag == 1 ) {
							bg[i] = newbg;
							j = 0;
						} else {
							i++;
						}
					}

					// OUTPUT
					printf( "[%d] %d\n", i, pid);
				
				/* Foreground Process */
				} else {
					
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
					printf("WALL CLOCK TIME:");
					printf("The command '%s' took %f milliseconds to finish.\n", command, commandElapsed);
					printf("TOTAL SYSTEM CPU TIME USED: %f milliseconds\n", systemCPU);
					printf("TOTAL USER CPU TIME USED: %f milliseconds\n", userCPU);
					printf("NUMBER OF TIMES PROCESS ENDED INVOLUNTARILY: %ld\n", ru.ru_nivcsw);
					printf("NUMBER OF TIMES PROCESS GAVE UP CPU VOLUNTARILY: %ld\n", ru.ru_nvcsw);
					printf("NUMBER OF PAGE FAULTS: %ld\n", ru.ru_majflt);
					printf("NUMBER OF PAGE FAULTS THAT COULD BE SATISFIED\nUSING UNRECLAIMED PAGES: %ld \n\n", ru.ru_minflt);
	
					free(inputLine);
				}


				
				/* Background Process */
				// Call to Wait3 ( Find Ready background processes )
				while((bpid = wait3( &who, WNOHANG, &ru2 )) > 0) {
					for(i = 0; i < 25; i++){
						printf("here is the value of bg[%d]: %d\n", i, bg[i].pid);
						if(bpid == bg[i].pid){
							bg[i].flag = 1;

							// Print START all Stats
							printf(" Background Job: %d\n", bg[i].pid);
							printf("------------------------");

							// Child process Start
							commandStart = (bg[i].start.tv_sec*1000.0) + (bg[i].start.tv_usec/1000.0);
							gettimeofday(&bgF, NULL);
							commandFinish = (bgF.tv_sec*1000.0) + (bgF.tv_usec/1000.0);
							// Child process End
	
							// Get Usage statistics
							//getrusage(who, &ru2);
	
							// Convert timeval to milliseconds
							userCPU = (ru2.ru_utime.tv_sec*1000.0) + (ru2.ru_utime.tv_usec/1000.0);
							systemCPU = (ru2.ru_stime.tv_sec*1000.0) + (ru2.ru_stime.tv_usec/1000.0);

							// Wall Clock Time
							commandElapsed = commandFinish - commandStart;

							// OUTPUT
							printf("-----------------------------------");
							printf("\nCHILD PROCESS EXECUTION STATISTICS:\n");
							printf("WALL CLOCK TIME:");
							printf("The command '%s' took %f milliseconds to finish.\n", command, commandElapsed);
							printf("TOTAL SYSTEM CPU TIME USED: %f milliseconds\n", systemCPU);
							printf("TOTAL USER CPU TIME USED: %f milliseconds\n", userCPU);
							printf("NUMBER OF TIMES PROCESS ENDED INVOLUNTARILY: %ld\n", ru2.ru_nivcsw);
							printf("NUMBER OF TIMES PROCESS GAVE UP CPU VOLUNTARILY: %ld\n", ru2.ru_nvcsw);
							printf("NUMBER OF PAGE FAULTS: %ld\n", ru2.ru_majflt);
							printf("NUMBER OF PAGE FAULTS THAT COULD BE SATISFIED\nUSING UNRECLAIMED PAGES: %ld \n\n", ru2.ru_minflt);
							// Print END

						}
					}
				} // While( wait3 )
			}
		}
   } // While(running shell)

   // End of Main
   return 0;
}
