// Jeff Sirocki
// CS2303 - A02

#include <iostream>
#include <string>
#include "tree.h"
using namespace std;

// Item Constructor
Item::Item( string id, int c, int x, int y, int z ) {
  ID = id;
  count = c;
  xpos = x;
  ypos = y;
  zpos = z;
} // end constructor Item

// Item Constructor
Item::Item( void ) {
  ID = "";
  count = 0;
  xpos = 0;
  ypos = 0;
  zpos = 0;
} // end constructor Item
