// Jeff Sirocki and Joseph Kaiser

#include <iostream>
#include <string>
using namespace std;

enum eventType{Arrival, Moving, InStore, Done};
class Shopper {

 public:
  // Constructor
  Shopper( void );

  // Functions
  void process_arrival();
  void process_move();
  void process_rr();
  void process_exit();
  void run_app();

  // Fields
  int id;
  int location[3];
  int status;
  //ListNode *storeList;
  //ListNode *shoppingList;
  int startTime;
  int finishTime;
  int sort; // event time

}; // end of class Shopper

