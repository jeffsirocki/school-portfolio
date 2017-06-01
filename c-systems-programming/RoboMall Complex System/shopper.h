//written by Joseph Kaiser

#include <iostream>
#include <string>
#include "tree.h"
using namespace std;

// Prevent duplicate headers
#ifndef SHOPPER_H
#define SHOPPER_H

// Class for Shopper
class Shopper {
  
 public:
  Shopper( void );          // Constructor
  // void run_app();           //prototype for the function which runs the app
        

  int id;                   //identifaction value for each shopper
  int status;
  int location[ 3 ];                    //x, y, and z positions of the shopper's location
  int linePos;              //the shopper's position in line if it is at a store 
  ListNode *shoplist;       //pointer to the first node of the shopper's shopping list
  int startTime;            //the time the shopper arrives in the simulation
  int finishTime;           //the time the shopper leaves the simulation
  int sort;                 //the time at which the shopper is set to be processed again within the event list
}; // end of Shopper class

#endif
  

  
