// Jeff Sirocki
// Lab4

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

int main( int argc, char * argv[] ) {

  int i; //counter
  int students = atoi(argv[1]);

  NodePtr rootPtr = NULL; // Tree Pointer
  Student s;

  int student_id;
  float g1, g2, g3; // Three grades and average

  for ( i = 0; i < students; i++ ) {
    printf( " Enter a student id and three grades \n" );
    scanf( "%d%f%f%f", &student_id, &g1, &g2, &g3 );

    // Initialize student
    s.id = student_id;
    s.g1 = g1;
    s.g2 = g2;
    s.g3 = g3;
    s.average = ( g1 + g2 + g3 ) / 3;

    insert( &rootPtr, s );
  }

  printf( "\n\t\t******* TREE ********\n\n" );
  printf( "              ID | Grade 1 | Grade 2 | Grade 3 | Average\n" );
  printf( "            --------------------------------------------\n" );


  print_tree( rootPtr );

}
