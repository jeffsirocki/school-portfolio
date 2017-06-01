// Jeff Sirocki
// CS2303

#define DEBUG 0
#define LENGTH 17
#define WIDTH 17

#ifndef BUILDING_H
#define BUILDING_H

typedef struct NodeTag{
  int Id;
  int valid;
  int elevator;
  int store;
  int exit;
  int moves[ 3 ];
  int num; //number of moves
  int logic[ 3 ][ 4 ];
} Node;

Node buildingLayout[ LENGTH ][ WIDTH ][ 2 ];

#endif
