//written by Joseph Kaiser

#include "shopper.h"
#include "tree.h"

// Constructor
Shopper::Shopper( void ) {
  id = 0;
  location[0] = 0;
  location[1] = 0;
  location[2] = 0;
  // stores
  startTime = 0;
  finishTime = 0;
}

/*
void Shopper::run_app()                       //processes the shopper's shopping list and determines the location o\
f the stores where it will need to travel to
{
  ListNode *currentPtr = this.shoppingList;
  while(currentPtr != NULL)                   //goes through each node of the list
    {
      ****;                                                 //locates the first item in the inventory tree that has\
 the enough of the item in stock
   *currentPtr.shoppingList->store[] = {****};           //sets the array containing the coordinates of the obje\
ctive store equal to the data from the inventory tree
  currentPtr = currentPtr->nextPtr;
    }
}
*/



// COMMENTED OUT BELOW HERE
/*
void Shopper::process_arrival()         //enters the shopper to begin acting within the simulation
{
  this.eventSts = Moving;       //the next event the shopper will do is moving
  this.x = 16;                  //sets the respective coordinates of the shopper to the entrance of the mall
  this.y = 8;
  this.z = 0;
  this.linePos = 9999;          //assumes shopper is not near the front of any line it will enter
  this.runApp();                //runs the app on the shopper to determine where it will go to get the items it nee\
ds
}

void Shopper::process_move()                      //instructs the shopper how to move to approach its destination
{
  int xdest = this.shoppingList->store[0];        //sets variables for the coordinates of the destination based on the location of the first store currently on the shopping list
  int ydest = this.shoppingList->store[1];
  int zdest = this.shoppingList->store[2];

  **moveTo**(x, y, z, (**navigation**(xdest, ydest, zdest));             // changes the location of the shopper to move it closer to the destination
  
	     if((this.x == xdest) && (this.y == ydest) && (this.z == zdest)){     //changes shopper's status if the shopper has reached its destination
	       this.eventSts = InStore;
	     }
}

    void Shopper::process_rr()                       //processes the behavior of a shopper while it is at a store attempting to aquire items
{
  if (this.shoppingList->needCount == 0){            //checks to see if the shopper still needs more items
    if(this.shoppingList != NULL){                   //if there are more items on the shopping list, the shopping list is updated and the shopper begins moving again
      this.eventSts = Moving;
      this.shoppingList = this.shoppingList->nextPtr;
    }
    else{
      this.eventSts = done;             //if there are no more items on the shopping list, the shopper is done
    }
    this.linePos = 9999;                //effectively removes the shopper from whatever line it was in
  }
  else{                                               //if the shopper still needs more of the item at this store, checks all shoppers to see if there is a shopper at the same store
    Shopper* currentPtr = shopperListPtr;
    while((currentPtr != NULL) &&  !((this.id == currentPtr->id) && (currentPtr->x == this.x) && (currentPtr->y == this.y) && (currentPtr->z == this.z))){        //looks for other shoppers that are currently at the same store
    currentPtr = currentPtr->nextShopper;
    }
    if (currentPtr == NULL){                  //if there are no other shoppers at the store, the shopper takes the first postion in the line
      this.linePos = 1;
      if(this.z == 0){                                          //if the shopper is at a store on the first store, then the shopper recieves the quantity it needs of that item and changes sorting time according to the number of items  
	this.sort = (time + this.shoppingList->needCount);
	this.shoppingList->needCount = 0;
      }
      else{
	if(this.shoppingList->needCount >= time_slice){                          //if the shopper is at a store on the second floor, then the robot recieves up to one time slice worth of items, and the sort time is updated accordingly
	  this.sort = (time + time_slice);
	  this.shoppingList->needCount = (this.shoppingList.needCount - time_slice);
	}
	else{
	  this.sort = (time + this.shoppingList->needCount);
	  this.shoppingList->needCount = 0;
	}
	this.linePos = 9999;                            //the shopper is sent to the back of the line
      }
    }
    else{
      int endPos = 0;                 //assumes the end of the line is the front
      Shopper* tmpPtr = currentPtr;
      while((currentPtr != NULL) && !((this.id == currentPtr->id) && (currentPtr->x == this.x) && (currentPtr->y == this.y) && (currentPtr->z == this.z))){   //looks at each other shopper at the store
	if((currentPtr->linePos > endPos) && (currentPtr->linePos < this.linePos)){                //if the shopper being looked at is further back than the assumed front, but is in front of the shopper, assumes new end of line 
	  endPos = currentPtr->linePos;
	}
	currentPtr = currentPtr->nextShopper;
      }
      if(endPos == 0){                        //if no shoppers were found in front of the shopper, the shopper assume the front of the line
	*tmpPtr.linePos = 1;
      }
      else{
	*tmpPtr.linePos = (endPos + 1);       //otherwise, sets the shopper's position to be 1 place behind the shopper furthest back 
      }
    }
  }
 }

void Shopper::run_app()                       //processes the shopper's shopping list and determines the location of the stores where it will need to travel to
{
  ListNode *currentPtr = this.shoppingList;   
  while(currentPtr != NULL)                   //goes through each node of the list
    {
      ****;                                                 //locates the first item in the inventory tree that has the enough of the item in stock
      *currentPtr.shoppingList->store[] = {****};           //sets the array containing the coordinates of the objective store equal to the data from the inventory tree
      currentPtr = currentPtr->nextPtr;
    }
}

void Shopper::proecess_exit()
{                      
  this.x = 0;      //sets the coordinates of the shopper to be outside the realm of the rest of the simulation 
  this.y = 0;
  this.z = 0;
  this.sort = 99999999;        //sets the sort time so it will not be processed again in the rest of the simulation
  this.finishTime = time;      //records the time that the shopper leaves the simulation
}
*/
