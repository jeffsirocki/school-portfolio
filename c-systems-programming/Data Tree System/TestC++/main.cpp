// Jeff Sirocki
// C++ Test file

#include <iostream>
using std::cout;
using std:endl;


#include <string>
using std::string;
using std::getline;


// #ifndef
// #define

Class Node {

 public: // Put functions here
  Node (string s);

 private: // Put parameters here
  string data;
  Node *link;
}; // End of class

int main( ) {
  
  string name;
  Node mynode;

  cout << "Please enter a name:" << endl;
  getline( cin, name );
  cout << endl;

  mynode.data = name;

}
