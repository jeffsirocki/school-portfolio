// Jeff Sirocki
// CS2303 - A02

#include "robot.h"

// Robot Constructors

Robot::Robot( void ) {
  Id = 0;
  location[0] = 0;
  location[1] = 0;
  location[2] = 0;
  numStore = 0;
  current = 0;
  // stores
  startTime = 0;
  finishTime = 0;
  finished = 0;
}


