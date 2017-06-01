// Jeff Sirocki
// Program5

#include <iostream>
#include <stdio.h>
#include "robot.h"

// Adds sort time to store
// Sets next robot destination
// returns robot
Robot process_store( Robot R ) {
  
  // Update
  R.sort = R.sort + R.stores[R.current][3];
 
  // OUTPUT for Delivering
  printf( " Robot %d delivered %d items to store s( %d, %d ).\n",
	  R.Id + 1,
	  R.stores[R.current][3],
	  R.stores[R.current][0],
	  R.stores[R.current][1] );

  // OUTPUT for leaving Stores
  printf( " Robot %d leaves store s( %d, %d ) on the %d Floor at Time %d.\n",
          R.Id + 1,
          R.stores[R.current][0],
          R.stores[R.current][1],
	  R.stores[R.current][2] + 1,
	  R.sort );

  R.current = R.current + 1;

  if (R.current >= R.numStore) {
    R.status = 3; // Exit Status
    R.stores[R.current][0] = 8;
    R.stores[R.current][1] = 16;
    R.stores[R.current][2] = 0;
  } else {
    R.status = 1; // Move Status
  }

  return R;
}
