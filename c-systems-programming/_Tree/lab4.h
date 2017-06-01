// Jeff Sirocki
// Lab4

#include <stdio.h>
#include <stdlib.h>

#define DEBUG 0

typedef struct studentTag {
  int id;
  float g1;
  float g2;
  float g3;
  float average;
} Student;

typedef struct NodeTag {
  Student student;
  struct NodeTag *leftPtr;
  struct NodeTag *rightPtr;
} Node;

typedef Node *NodePtr;

// Prototypes
void insert( NodePtr *node, Student s );
void print_tree( NodePtr node );




/*
class Student {
  
 public:
  Student( int d1, float f1, float f2, float f3 );
 
 private:
  int id;
  float g1;
  float g2;
  float g3;
  float average;
}

// constructor
Student::Student( int d1, float f1, float f2, float f3 )
  : id( d1 ), g1( f1 ), g2( f2 ), g3( f3 ), average( (f1 + f2 + f3 ) / 3 ) 
{
  // empty body
}

class Node {
  
 public:
  Node( Student student, Node left, Node right );

 private:
  Student student;
  Node *left;
  Node *right;
}

// constructor
Node::Node( Student s, Node *l, Node *r )
  : student( s ), left( l ), right( r )
{
  // empty body
}
*/

/*
class Student {
 
 public:
  int id;
  float g1, g2, g3, average;
 
  Student( int d1, float f1, float f2, float f3 ) {
    this->id = d1;
    this->g1 = f1;
    this->g2 = f2;
    this->g3 = g3;
    this->average = ( f1 + f2 + f3 ) / 3;
  }
}

class Node {
 public:
  Student student;
  Node left;
  Node right;

  Node( Student s, Node l, Node r ) {
    this->student = s;
    this->left = l;
    this->right = r;
  }
}
*/



/*
class Student {
  int id;
  float g1, g2, g3, average;
 public:
  void set_student (int,float,float,float);
};

void Student::set_values ( int d1, float f1, float f2, float f3 ) {
    id = d1;
    g1 = f1;
    g2 = f2;
    g3 = f3;
    average = (g1 + g2 + g3) / 3;
}


class Node {
  Student student;
  Node left;
  Node right;
 public:
  void set_node (Student,Node,Node);
};

void Node::set_node( Student s, Node l, Node r ) {
    student = s;
    left = l;
    right = r;
  }
}
*/
