// Jeff Sirocki and Joseph Kaiser
// CS2303 A02

#include <iostream>
#include <string>
#include "prog4.h"
using namespace std;

int main( void ) {
  
  int i; // counter

  int xpos, ypos, zpos; // Store Coordinates
  int k_items; // number of unique items on line of input
  string ID;
  int count;

  Item I;
  TreeNode root;
  //TreeNode BST;

  // Initialize BINARY TREE

  // While input is not termination case
  while( xpos != 16 && ypos != 16 && zpos != 16 ) {

    // Prompt
    cout << "Enter store( x, y, z ) k-itmes Item-1 count-1 ... Item-n count-n " << endl;

    // End case for { 16, 16, 16 }
    cin >> xpos >> ypos >> zpos;
    if ( xpos == 16 && ypos == 16 && zpos == 16 ) {
      cout << xpos << ypos << zpos << endl;
      // end while
      break; 
    }

    cin >> k_items;

    for( i = 0; i < k_items; i++ ) {
      cin >> ID >> count;

      // Set Item
      I.ID = ID;
      I.count = count;
      I.xpos = xpos;
      I.ypos = ypos;
      I.zpos = zpos;

      //cout << I.ID << " " << I.count << " " << I.xpos << " " << I.ypos << " " << I.zpos << endl;


      //cout << " ROOT: " << root.item << " " << root.leftPtr << " " << root.rightPtr << endl;
      // Insert into tree
      root.insert( I );
    
      // Print it
      //cout << ID << " " << count << endl;
    }
  }

  cout << " Got to Print Tree " << endl << endl;
  cout << " *********** PRINTING TREE ***************** " << endl << endl;

  // Print Binary Tree
  root.print_tree( );



  // End of Main
  return 0;
}
