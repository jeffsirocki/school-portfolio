// Jeff Sirocki
// Program5

#include <iostream>
#include <stdio.h>
#include "robot.h"
#include "robomall.h"


// Prototypes - (Helper Functions)
Robot move( Robot R, int direction );

// Sets Destination
// Calls Move robot
// Updates Robot
// Returns Robot
Robot process_move( Robot R ) {

  int i, j; // counters
  
  // Declarations
  Robot robot;
  int loc[ 3 ];
  int locId;
  int dest[ 3 ];
  int destId;
  int wasSet = 0; // Was direction set
  int toHere; // Direction
  
  // Initiate LOCATION
  loc[ 0 ] = R.location[ 0 ];
  loc[ 1 ] = R.location[ 1 ];
  loc[ 2 ] = R.location[ 2 ];
  locId = ( buildingLayout[ loc[0] ][ loc[1] ][ loc[2] ].Id );

  // Initiate DESTINATION
  dest[ 0 ] = R.stores[R.current][0];
  dest[ 1 ] = R.stores[R.current][1];
  dest[ 2 ] = R.stores[R.current][2];
  destId = ( buildingLayout[ dest[0] ][ dest[1] ][ dest[2] ].Id );

  // Set Direction
  // Shortest Path LOGIC
  if ( buildingLayout[ loc[0] ][ loc[1] ][ loc[2] ].num == 1 ) {
    toHere = buildingLayout[ loc[0] ][ loc[1] ][ loc[2] ].moves[ 0 ];
  } else {

    // check through first two moves( moves[ 0 ], moves[ 1 ] else last move
    for( i = 0; i < 2; i++ ) {
      for( j = 0; j < 4; j++ ) {
	if ( buildingLayout[ loc[0] ][ loc[1] ][ loc[2] ].logic[ i ][ j ] == buildingLayout[ dest[0] ][ dest[1] \
													][ dest[2] ].Id ) {

	  // Set Direction for next move
	  toHere =  buildingLayout[ loc[0] ][ loc[1] ][ loc[2] ].moves[ i ];
	  wasSet = 1;
	}
      }
    }

    // If not in Logic take main route
    if ( wasSet == 0 ) {
      if ( buildingLayout[ loc[0] ][ loc[1] ][ loc[2] ].num == 2 ) {

	// For all weird nodes
	switch ( buildingLayout[ loc[0] ][ loc[1] ][ loc[2] ].Id ) {
	case (800): case (904): case (912): case (8): case (808): case (409): case (1209):
	case (10904): case (10816): case (11608): case (10409): case (11209): case (10912):
	  toHere =  buildingLayout[ loc[0] ][ loc[1] ][ loc[2] ].moves[ 1 ];
	  wasSet = 1;
	  break;

	default:
	  toHere =  buildingLayout[ loc[0] ][ loc[1] ][ loc[2] ].moves[ 0 ];
	  wasSet = 1;
	  break;
	}

      } else {
	toHere =  buildingLayout[ loc[0] ][ loc[1] ][ loc[2] ].moves[ 2 ];
	wasSet = 1;
      }
    }
  }

  // Move
  robot = move( R, toHere );

  // Return processed robot
  return robot;
}
