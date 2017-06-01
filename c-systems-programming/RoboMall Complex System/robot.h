// Created by Jeff Sirocki and Joseph Kaiser
// CS2303 - A02

// Header File

#include <iostream>
#include <string>
using namespace std;

// Prevent Multiple inclusion of the class
#ifndef ROBOT_H
#define ROBOT_H

// Class for ROBOT
class Robot {

 public:
  Robot( void );

  int Id;
  int status;
  int location[ 3 ];
  int numStore;
  int current;
  int stores[ 20 ][ 4 ]; // ( x, y, z, k-items );
  int startTime;
  int finishTime;
  int finished;
  int sort; 
}; // end of Robot class

#endif
