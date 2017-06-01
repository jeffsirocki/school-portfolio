// Created by Jeff Sirocki
// CS2303 - A02

#include <iostream>
#include <string>
#include "tree.h"
using namespace std;

// TreeNode Constructor
TreeNode::TreeNode( void ) {
  leftPtr = NULL;
  item = "";
  list = NULL;
  rightPtr = NULL;
} // end of constructor TreeNode



// INSERT Item into tree
void TreeNode::insert( Item I ) {

  string value = I.ID;

  // If empty tree
  if ( this->item.compare("") == 0 ) {

    this->item = value;
    this->list = new ListNode();
    this->list->addItem( &this->list, I );

    //cout << " Added Item " << endl;

    // Not Empty
  } else {
    // id is less
    if ( value[0] < ( this )->item[0] ) {

      //cout << "Go Left" << endl;
            
      // Go LEFT
      if ( this->leftPtr == NULL ) {
      this->leftPtr = new TreeNode();
      }
      this->leftPtr->insert( I ); // RECURSION
    } else if ( value[0] > ( this )->item[0] ) {
      
      //cout << "Go Right" << endl;
      
      // Go RIGHT
      if ( this->rightPtr == NULL ) {
      this->rightPtr = new TreeNode();
      }
      this->rightPtr->insert( I ); // RECURSION
    } else {
      
      
      
      // LETTER MATCH
      if ( value[1] < ( this )->item[1] ) {
	
	// Go LEFT
	if ( this->leftPtr == NULL ) {
	  this->leftPtr = new TreeNode();
	}
	this->leftPtr->insert( I ); // RECURSION
      } else if ( value[1] > ( this )->item[1] ) {
	

	// Go RIGHT
	if ( this->rightPtr == NULL ) {
	  this->rightPtr = new TreeNode();
	}
	this->rightPtr->insert( I ); // RECURSION
      } else {
	
        // Duplicate Value
        this->list->addItem( &this->list, I );
        //cout << "Duplicate Value" << endl;
      }
    }
  }
}

// Prints tree in order
void TreeNode::print_tree() {

  //cout << " ROOT: " << this->item << " " << this->leftPtr << " " << this->rightPtr << endl;

  // If tree not empty
  if (this->item.compare("") != 0 ) {
    
    // Not Null, GO LEFT
    if ( this->leftPtr != NULL ) {
      this->leftPtr->print_tree( );
    }

    // PRINT STATEMENT
    cout << " ITEM " << this->item << ":" << endl;
    this->list->printList();
    cout << endl << endl;
    
    if ( this->rightPtr != NULL ) {
      this->rightPtr->print_tree( );
    }
  }
}
