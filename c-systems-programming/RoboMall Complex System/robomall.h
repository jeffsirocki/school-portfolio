// Jeff Sirocki
// Building layout Header

#ifndef ROBOMALL_H
#define ROBOMALL_H

#define DEBUG 0
#define MAX_ROBOTS 9
#define MAX_STORES 20
#define LENGTH 17
#define WIDTH 17


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

extern Node buildingLayout[ LENGTH ][ WIDTH ][ 2 ];

#endif
