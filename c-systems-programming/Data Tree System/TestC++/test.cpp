// Jeff Sirocki
// Test

#include <iostream>
#include "test.h"
using namespace std;

// Main
int main() {
  int i; // counter
  
  int num1; 
  int num2;
  int ray[5][5];
  int sum;

  Gradebook myGradeBook;

  cout << "Enter first ID: "; // prompt
  cin >> num1;
  cout << "Enter second ID: "; // prompt
  cin >> num2;

  sum = num1 + num2;
  cout << "Sum is " << sum << endl;

  myGradeBook.displayMessage();
  myGradeBook.displayMessage();

  for( i = 0; i < 5; i++ ) {
    cin >> ray[ i ][ 0 ] >> ray[ i ][ 1 ] >> ray[ i ][ 2 ] >> ray[ i ][ 3 ] >>  ray[ i ][ 4 ];
  }

  for( i = 0; i < 5; i++ ) {
    cout << ray[ i ][ 0 ] << " " << ray[ i ][ 2 ] << ray[ i ][ 4 ] << endl;
  }

  cout << "End of Test" << endl;
}
