// Jeff Sirocki
// CS2303 - A02

#include <iostream>
#include "lab5.h"
using namespace std;

// Shop Constructors

Shop::Shop( int x, int y, int time ) {
  xpos = x;
  ypos = y;
  service = time;
} // end of constructor

Shop::Shop( void ) {
  xpos = 0;
  ypos = 0;
  service = -1;
} // end of constructor
