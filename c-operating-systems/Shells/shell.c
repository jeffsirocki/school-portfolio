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

// Main Function for Shells
int main( int argc, char **argv) {

	/* Declaration of Variables */

	// Counters
	int i, j; 

	// Shell Variable Declaration
   	pid_t pid;
   	int child;
   	int who = RUSAGE_CHILDREN;
   	struct timeval commandS, commandF;
   	struct rusage ru;
   	double commandStart, commandFinish, commandElapsed, userCPU, systemCPU;

	// Read Line
	char *inputLine;
	char *command = NULL;
	size_t length;
	int ampbool;
	char *token;
	char *arguments[32];

	/* Declaration End */

	// Running Shell
	while(1){
		inputLine = NULL;
		command = NULL;
		length = 0;
     
		// Read Line
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

		// Exit Command ( exit )
		if(strcmp("exit", command) == 0){
			return 0;

		// Change Directory( cd dir )
		} else if(strcmp("cd", command) == 0){
			chdir(arguments[1]);

		
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
		}
	} // While ( Running Shell )

	// End of Main
	return 0;
}
