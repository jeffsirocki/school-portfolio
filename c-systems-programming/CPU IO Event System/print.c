// Jeff Sirocki
// Program 3

#include <stdio.h>
#include <stdlib.h>
#include "prog3.h"

void print_summary( int summary[][3], int rows, int columns, int n, int total ) {
  
  int i; // counter

  static int total_time = 0;
  static int total_idle = 0;
  static float avg_idle;
  static float avg_time;


  printf( "\n\n****** SUMMARY *******\n\n" );
  printf( "  id  |  idle | total \n" );
  printf( "----------------------\n" );

  for( i = 0; i < n; i++ ) {

    // Accumalate totals
    total_idle = total_idle + summary[ i ][ 1 ];
    total_time = total_time + summary[ i ][ 2 ];
    
    // Prints SUMMARY
    printf( "%5d | %5d | %5d \n", 
	    summary[ i ][ 0 ],
	    summary[ i ][ 1 ],
	    summary[ i ][ 2 ] );
  }
  
  // Set Performance Metrics
  avg_idle = total_idle / (float)n;
  avg_time = total_time / (float)n;

  // Print performance Metrics

  printf( "\n\n*** Performance Metrics ***\n" );
  printf( "-----------------------------\n" );
  printf( "   Total Time: %7d seconds \n", total );
  printf( " Average Time: %7.2f seconds \n", avg_time );
  printf( " Average Idle: %7.2f seconds \n\n\n", avg_idle );
  
}
