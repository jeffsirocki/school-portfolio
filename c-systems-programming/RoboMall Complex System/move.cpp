// Jeff Sirocki - jasirocki
// Program 2 - CS2303 - A02

#include <iostream>
#include <stdio.h>
#include "robomall.h"
#include "robot.h"

// Prototypes -- (Helper Functions)
// int print_floor( int x, int y, int z, int time );

// Moves Robot
Robot move( Robot R, int direction ) {
  // int i; // counter
  int xpos, ypos; // enum direction
  int moveTo[ 3 ]; // position robot is moving to
  int goToNext[ 3 ]; // next store for robot

  if ( direction == 0 ) {
    xpos = -1;
    ypos = 0;
  }
  if ( direction == 1 ) {
    xpos = -1;
    ypos = -1;
  }
  if ( direction == 2 ) {
    xpos = 0;
    ypos = -1;
  }
  if ( direction == 3 ) {
    xpos = 1;
    ypos = -1;
  }
  if ( direction == 4 ) {
    xpos = 1;
    ypos = 0;
  }
  if ( direction == 5 ) {
    xpos = 1;
    ypos = 1;
  }
  if ( direction == 6 ) {
    xpos = 0;
    ypos = 1;
  }
  if ( direction == 7 ) {
    xpos = -1;
    ypos = 1;
  }

  // Set moveTo
  moveTo[ 0 ] = R.location[ 0 ] + xpos;
  moveTo[ 1 ] = R.location[ 1 ] + ypos;
  moveTo[ 2 ] = R.location[ 2 ];

  // Set goToNext
  goToNext[ 0 ] = R.stores[R.current][0];
  goToNext[ 1 ] = R.stores[R.current][1];
  goToNext[ 2 ] = R.stores[R.current][2];

  // Check moveTo and add appropriate time
  if( buildingLayout[ moveTo[0] ][ moveTo[1] ][ moveTo[2] ].valid ) {

    // Move robot
    R.location[0] = moveTo[0];
    R.location[1] = moveTo[1];
    R.location[2] = moveTo[2];

    R.sort = R.sort + 1;

    // Store Case:
    if( buildingLayout[ moveTo[0] ][ moveTo[1] ][ moveTo[2] ].store ) {

      // Update Robot
      R.status = 2; // Store Status

      // OUTPUT: Store Arrival
      printf( " Robot %d arrives at s( %d, %d ) on Floor %d at time %d \n",
	      R.Id + 1, R.location[ 0 ], R.location[ 1 ], R.location[ 2 ] + 1, R.sort + 1 );
      
    }

    // Elevator Case:
    if( buildingLayout[ moveTo[0] ][ moveTo[1] ][ moveTo[2] ].elevator ) {

      if ( moveTo[ 2 ] != goToNext[ 2 ] ) {

        // Update location and OUTPUT
        if (R.location[ 2 ] == 0 ) {
          R.location[ 2 ] = 1; //there was a star before R
          printf( " Robot %d went UP on the elevator at Time %d\n", R.Id + 1, R.sort );
        } else {
          printf( " Robot %d went DOWN on the elevator at Time %d\n", R.Id + 1, R.sort );
          R.location[ 2 ] = 0; // there was a star before R
        }

        // OUTPUT
        printf( " Robot %d got off the elevator at Time %d\n", R.Id + 1, R.sort + 3 );
      }
    }

    // Exit Case:
    if( ((buildingLayout[ R.location[0] ][ R.location[1] ][ R.location[2] ]).exit ) && ( buildingLayout[ goToNext[0] ][ goToNext[1] ][ goToNext[2] ].exit ) ) {
      R.status = 4;
      R.finished = 1;
      R.finishTime = R.sort;
    }
  }

  // End Move
  return R;
}
