// Jeff Sirocki
// Lab4

#include <iostream>
#include "lab4.h"

int main( int argc, char * argv[] ) {

  int i; //counter
  int students = atoi(argv[1]);
  
  Node root = NULL;
  Student s;

  int student_id;
  float g1, g2, g3; // Three grades and average

  for ( i = 0; i < students; i++ ) {
    printf( " Enter a student id and three grades \n" );
    scanf( "%d%f%f%f", &student_id, &g1, &g2, &g3 );

    // Initialize student
    s.set_student( student_id, g1, g2, g3 );

    insert( &root, s );
  }

  print_tree( root );

}
