// Jeff Sirocki
// CS2303 - A02

#include <iostream>
#include <string>
#include "tree.h"
#include <stdio.h>
#include "robomall.h"
#include "robot.h"
#include "eventlist.h"

#include "shopper.h"

using namespace std;

// Globals
Node buildingLayout[ 17 ][ 17 ][ 2 ];

// Prototypes - (Helper Functions)
int build_layout();
int simulate( EventList* *sPtr, int numRobots );

// Main Function
int main( int argc, char * argv[] ) {

  int i, j = 0, k = 0, c = 0; // counter

  // CommandLine
  int time_slice = atoi(argv[1]); // time slice for RR scheduling

  // Intitialize Building Layout
  build_layout();

  // STAGE 1 -- Robot Declarations
  Robot robots[ 9 ]; // Make 9 Robots
  Robot r;

  int numRobots;
  int s[9]; // Array to hold number of stores for robot i
  int sum = 0;

  // Event List
  EventList start;
  EventList* startPtr = NULL; // Event List Start

  // Inventory TREE
  TreeNode root;
  Item I;

  int xpos, ypos, zpos; // Store coordinates
  int k_items; // number of unique items on line of input
  string ID;
  int count;

  // STAGE 2 -- Shopper Declarations
  Shopper shoppers[ 9 ]; // Make 9 Shoppers
  int numShoppers;

  int arr_time;
  int j_items;
  string id;
  int j_count;

  // Linked List
  ListNode list;
  ListNode* listPtr; // Linked List start
  // ListNode* listPtr[9];

  //              PROGRAM START

  //*********** STAGE 1 - Robots ************* 
  
  // Input Robot numbers
  cout << "Enter number of robots:" << endl;
  cin >> numRobots;

  // Input Robot number of stores
  cout << "Enter the number of stores the corresponding robots (R1, R2, ... , Rn) must visit." << endl;
  cout << "Use this template: s1 s2 s3 ... sn" << endl;
  for ( i = 0; i < numRobots; i++ ) {
    cin >> s[i];
    sum = sum + s[i];
    //cout << "Store" << i << " Sum = " << sum << endl;
  }
  //cout << endl;

  // Input Robot INVENTORY
  // While input is not termination case
  while( xpos != 99999 ) {
    
    if ( c == s[k] ) {
      robots[k].numStore = c;
    }

    j++;
    //cout << " J: " << j << endl;
    if ( j > sum ) {
      cout << "Stage 1 Input End - lines:  " << j - 1 << endl;
      break;
    }
    // Prompt
    cout << "Enter store( x, y, z ) k-itmes Item-1 count-1 ... Item-n count-n" << endl;

    // End case for { 99999 }
    cin >> xpos;
    if ( xpos == 99999 ) {
      cout << "Sentinel Reached: " << xpos << endl;
      // end while
      break;
    }
    // input rest
    c++;
    cin >> ypos >> zpos >> k_items;

    if ( c > s[k] ){
      
      // Increment and Reset counters
      k++;
      c = 1;
    }

    // cout << " K: " << k << " C: " << c << endl;

    // Set up robots
    robots[k].stores[c-1][0] = xpos;
    robots[k].stores[c-1][1] = ypos;
    robots[k].stores[c-1][2] = zpos;
    robots[k].stores[c-1][3] = k_items;


    for( i = 0; i < k_items; i++ ) {
      cin >> ID >> count;

      // Set Item
      I.ID = ID;
      I.count = count;
      I.xpos = xpos;
      I.ypos = ypos;
      I.zpos = zpos;


      // Insert into tree
      root.insert( I );
    }
  }

  
  cout << "***** Robots *****" << endl << endl;

  // Create Robot and Add robots to Event List
  for( i = 0; i < numRobots; i++ ) {

    // Initialize Robots
    robots[ i ].Id = i;
    robots[ i ].status = 0; // Arrival Status
    robots[ i ].current = 0;
    robots[ i ].startTime = i*25;
    robots[ i ].finishTime = 0;
    robots[ i ].finished = 0;
    robots[ i ].location[ 0 ] = 8;
    robots[ i ].location[ 1 ] = 16;
    robots[ i ].location[ 2 ] = 0;
    robots[ i ].sort = i*25; // Arrival Time

    cout << "   Robot Id: " << robots[ i ].Id << endl;
    cout << "   numStore: " << robots[ i ].numStore << endl;
    cout << "   Current: " << robots[ i ].current << endl;
    cout << "   Start Time: " << robots[ i ].startTime << endl;
    cout << "   Finish Time: " << robots[ i ].finishTime << endl;
    cout << "   Finished: " << robots[ i ].finished << endl;
    cout << "   Location { " 
	 << robots[ i ].location[0] << ", " 
	 << robots[ i ].location[1] << ", " 
	 << robots[ i ].location[2] << endl;

    cout << "   Sort: " << robots[ i ].sort << " " << endl << endl << endl;

    // Add robots to event list
    start.add_event( &startPtr, robots[i] );
  }

  // Print Event list
  // start.print_list( startPtr );

  // Simulate Robots
  simulate( &startPtr, numRobots );

  cout << " Got to Print Tree " << endl << endl;
  cout << " *********** PRINTING TREE ***************** " << endl << endl;  

  // Print Binary Tree
  root.print_tree( );

  

  //************ STAGE 2 - Shoppers ***************
  cout << "*********** STAGE 2 - Shoppers *************" << endl << endl;
  // Input Shopper numbers
  cout << "Enter number of shoppers:" << endl;
  cin >> numShoppers;

  j = 0;
  while( arr_time != 99999 ) {

    j++;
    if( j > numShoppers ) {
      cout << "End of STAGE 2 Input" << endl;
      break;
    }

    // Prompt
    cout << "Enter:  arrival_time j_itmes  Item-1 count-1 ... Item-n count-n" << endl;

    // End case for { 99999 }
    cin >> arr_time;
    if ( arr_time == 99999 ) {
      cout << "Sentinel Reached: " << arr_time << endl;
      // end while
      break;
    }

    // input rest
    c++;
    cin >> j_items;

    shoppers[j-1].startTime = arr_time;
    shoppers[j-1].sort = arr_time;

    for( i = 0; i < j_items; i++ ) {
      cin >> id >> j_count;

      // Set Item
      I.ID = id;
      I.count = j_count;
      I.xpos = 8;
      I.ypos = 16;
      I.zpos = 0;

      // Insert into List
      //list.addItem( &shoppers[j-1].shoplist, I );
      list.addItem( &listPtr, I );
      cout << "i == " << i << endl;
    }

    // Print List
    cout << "******** Shopping List *********" << endl;
    list.printList( listPtr );
    //list.printList( shoppers[j-1].shoplist );

  }

  // Initialize Shoppers
  cout << endl << "***** Shoppers *****" << endl << endl;

  // Set Shopper Event List
  for( i = 0; i < numShoppers; i++ ) {

    // Initialize Shoppers
    shoppers[ i ].id = i;
    shoppers[ i ].status = 0; // Arrival Status
    shoppers[ i ].finishTime = 0;
    shoppers[ i ].location[ 0 ] = 8;
    shoppers[ i ].location[ 1 ] = 16;
    shoppers[ i ].location[ 2 ] = 0;

    cout << "   Shopper Id: " << shoppers[ i ].id << endl;
    cout << "   sort: " << shoppers[ i ].sort << endl;
    cout << "   Start Time: " << shoppers[ i ].startTime << endl;
    cout << "   Finish Time: " << shoppers[ i ].finishTime << endl;
    cout << "   Location { "
         << shoppers[ i ].location[0] << ", "
         << shoppers[ i ].location[1] << ", "
         << shoppers[ i ].location[2] << endl << endl;
  
    // ADD to event List
    // start.add_event( shoppers[ i ] );
  
  }

  // Simulate Shoppers
  // simulate ( &startPtr, numShoppers );

  // Print STAGE 2 Summary
  printf( "\n\t *********** STAGE 2 - SUMMARY ***********\n" );
  printf( "\t Shopper     Start Time Finish Time\n");
  printf( "\t---------------------------------\n" );

  for( i = 0; i < numShoppers; i++ ) {

    printf("\t  %d         %5d           %5d\n",
           shoppers[i].id + 1,
           shoppers[i].startTime,
           shoppers[i].finishTime );
  }

  cout << endl << endl;

  // End of PROGRAM 5
  return time_slice;
}
