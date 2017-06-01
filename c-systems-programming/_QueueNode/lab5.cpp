// Jeff Sirocki
// CS2303 - A02

#include <iostream>
#include <string>
#include "lab5.h"
using namespace std;

// Main Function
int main ( int argc, char * argv[] ) {

  //int time = 0; // Simulation Time
  int i; // counters

  int numStores = atoi(argv[1]); // number of stores for shopper 1
  int time_splice = atoi(argv[2]); // time splice for RR scheduling

  Shop S;
  int xpos;
  int ypos;
  int service;
  
  queueNode head;
  queueNode tail;
  queueNode temp;

  // Input Script File and Create Queue
  for ( i = 0; i < numStores; i++ ) {

    // Prompt
    cout << "Enter the xpos ypos and service time of the shop" << endl;
    cin >> xpos >> ypos >> service;

    // Set Shop
    S.xpos = xpos;
    S.ypos = ypos;
    S.service = service;

    cout << " Store: " << S.xpos << " " << S.ypos << " " << S.service << endl;

    // Enqueue - Doesn't work - SEGFAULTS
    // head.enqueue( S );

    cout << "Head: " << head.shop.xpos << " " << head.shop.ypos << " " <<  head.shop.service << " " << endl;
    cout << "Tail: " << tail.shop.xpos << " " << tail.shop.ypos << " " <<  tail.shop.service << " " << endl;

  }

  /*
  // Simulate the Queue
  while( ( head.shop ) != NULL ) {
    
    if ( head.shop.service > time_splice ) {
      
      // OUTPUT
      cout << " Shopper 1 arrives at store s at time " << time << endl;

      // Dequeue and process shop
      temp = head.dequeue( &head, &tail );
      S = temp.shop;
      S.service = S.service - time_splice;
      time = time + time_splice;
      
      // Enqueue shop
      head.enqueue( &head, &tail, S );

      cout << "After using z time slices at store s, shopper 1 leaves store s at time " << time << endl;

    } else {
      
      // Dequeue and process shop
      temp = head.dequeue( &head, &tail );
      S = temp.shop;
      S.service = 0;
      time = time + time_splice;
      
      // OUTPUT
      cout << "Shopper 1 exits the RoboMall at time " << time << endl;
      }
  }
  */

}
