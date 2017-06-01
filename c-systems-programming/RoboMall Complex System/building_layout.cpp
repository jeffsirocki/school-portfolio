// Jeff Sirocki - jasirocki
// Prog2 - CS2303 - A02

#include <stdio.h>
#include "robomall.h"

// Prototypes - (Helper Functions)
int init_zero( void );
int init_valid( void );
int init_store( void );
int init_elevator( void );
int init_exit( void );
int init_logic( void );

// Initialize Building Layout
int build_layout( void ) {
  int i, j, k; // counters

  // Set building layout node values to zero
  init_zero( );

  // Set valid Nodes, Moves, and Logic
  init_valid( );

  // Set the store nodes
  init_store( );

  // Set the elevator nodes
  init_elevator( );

  // Set the exit node
  init_exit( );

  // Set the logic
  init_logic( );

  // Check Building Layout values
  if ( DEBUG ) {
    printf( "\n----------------------------------------\n" );
    printf( "DEBUG: Building Layout Summary Report\n" );
    printf( "----------------------------------------\n" );

    for( k = 0; k < 2; k++ ) {
      
      printf( "\n\t----------------------------------------" );
      printf( "\n\tDEBUG: Floor %d\n", k + 1 );
      printf( "\n\t----------------------------------------\n" );
      
      for( j = 0; j < LENGTH; j++ ) {
	for( i = 0; i < WIDTH; i++ ) {
	  
	  // PRINTS - If valid
	  if ( buildingLayout[ i ][ j ][ k ].valid  ) {
	    printf( "\n\t NodeId %5d = { Valid: %d || Store: %d || Elevator %d || Exit: %d || Moves (%d): { %d, %d, %d } ", 
		    buildingLayout[ i ][ j ][ k ].Id,
		    buildingLayout[ i ][ j ][ k ].valid,
		    buildingLayout[ i ][ j ][ k ].store,
		    buildingLayout[ i ][ j ][ k ].elevator,
		    buildingLayout[ i ][ j ][ k ].exit,
		    buildingLayout[ i ][ j ][ k ].num,
		    buildingLayout[ i ][ j ][ k ].moves[ 0 ],
		    buildingLayout[ i ][ j ][ k ].moves[ 1 ],
		    buildingLayout[ i ][ j ][ k ].moves[ 2 ] );
	  }
	  
	  // PRINTS - If num > 1
	  if ( buildingLayout[ i ][ j ][ k ].num > 1 ) {
	    printf( "\n\t NodeId %5d = Moves (%d): { %d, %d, %d } Here ( %d, %d, %d ) \n\t\t\t LOGIC(0): %5d %5d %5d %5d \n\t\t\t      (1): %5d %5d %5d %5d",
		    buildingLayout[ i ][ j ][ k ].Id,
		    buildingLayout[ i ][ j ][ k ].num,
                    buildingLayout[ i ][ j ][ k ].moves[ 0 ],
                    buildingLayout[ i ][ j ][ k ].moves[ 1 ],
                    buildingLayout[ i ][ j ][ k ].moves[ 2 ],
		    i, j, k,
		    buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ],
		    buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 1 ],
		    buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 2 ],
		    buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 3 ],
		    buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ],
		    buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 1 ],
		    buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 2 ],
		    buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 3 ]);
	  }
	}
      }
    }

    // Print Valid Floor
    for( k = 0; k < 2; k++ ) {
      printf( "\n\t----------------------------------------" );
      printf( "\n\tDEBUG: Floor %d\n", k + 1 );
      printf( "\n\t----------------------------------------\n" );

      for( i = 0; i < LENGTH; i++ ) {
        printf( "\n" );
	for( j = 0; j < WIDTH; j++ ) {
	  printf( " %d ", buildingLayout[ i ][ j ][ k ].valid );
	}
      }
      printf( "\n\n" );
    }
  }

  // End of Initialization
  return 0;
}

// Initialize all Node values to zero and sets node Id as FRRCC where F = Floor, R = Row, and C = Column
//            (e.g. For {8, 12, 1} and {0, 8, 0} Id is 18012 and 08000 )  
int init_zero( void ) {
  int i, j, k, m; // counters
  int nodeId; // nodeid in form FRRCC

  for( k = 0; k < 2; k++ ) {
    for( j = 0; j < LENGTH; j++ ) {
      for( i = 0; i < WIDTH; i++ ) {

	// Set Node Id
	nodeId = (k * 10000) + (i * 100) + j;
	buildingLayout[ i ][ j ][ k ].Id = nodeId; 

        //Set all to zero first
        buildingLayout[ i ][ j ][ k ].valid = 0;
        buildingLayout[ i ][ j ][ k ].store = 0;
        buildingLayout[ i ][ j ][ k ].elevator = 0;
        buildingLayout[ i ][ j ][ k ].exit = 0;

        for ( m = 0; m < 3; m++ ) {
          buildingLayout[ i ][ j ][ k ].moves[ m ] = 0;
        }
      }
    }
  }
  // End of initialize zero
  return 0;
}



// Initalize valid nodes and moves
int init_valid( void ) {
  int i, j, k; // counters

  // Floor CROSS rows
  for( k = 0; k < 2; k++ ) {
    for( i = 1; i < 16; i++ ) {
      
      // First Floor
      if ( k == 0 ) {
	buildingLayout[ i ][ 8 ][ k ].valid = 1;
	buildingLayout[ 8 ][ i ][ k ].valid = 1;
	buildingLayout[ i ][ 8 ][ k ].moves[ 0 ] = 4; // South
	buildingLayout[ 8 ][ i ][ k ].moves[ 0 ] = 6; // East
	buildingLayout[ i ][ 8 ][ k ].num = 1;
        buildingLayout[ 8 ][ i ][ k ].num = 1;

	// Elevator
	if ( i == 8 ) {
	  buildingLayout[ i ][ 8 ][ k ].moves[ 0 ] = 4;  // South or East
	  buildingLayout[ i ][ 8 ][ k ].moves[ 1 ] = 6;
	  buildingLayout[ i ][ 8 ][ k ].num = 2;
	}
	// Middle Coloumn and Row
	if ( i == 4 ) {
	  buildingLayout[ i ][ 8 ][ k ].moves[ 0 ] = 6; // South, West, or East
	  buildingLayout[ i ][ 8 ][ k ].moves[ 1 ] = 2; 
	  buildingLayout[ i ][ 8 ][ k ].moves[ 2 ] = 4;
	  buildingLayout[ i ][ 8 ][ k ].num = 3;

	  buildingLayout[ 8 ][ i ][ k ].moves[ 0 ] = 4; // North, East, or South
	  buildingLayout[ 8 ][ i ][ k ].moves[ 1 ] = 0;
	  buildingLayout[ 8 ][ i ][ k ].moves[ 2 ] = 6;
	  buildingLayout[ 8 ][ i ][ k ].num = 3;

	  buildingLayout[ 16 - i ][ 8 ][ k ].moves[ 0 ] = 6; // South, West, or East
	  buildingLayout[ 16 - i ][ 8 ][ k ].moves[ 1 ] = 2; 
	  buildingLayout[ 16 - i ][ 8 ][ k ].moves[ 2 ] = 4; 
	  buildingLayout[ 16 - i ][ 8 ][ k ].num = 3;

	  buildingLayout[ 8 ][ 16 - i ][ k ].moves[ 0 ] = 4; // North, East, or South
	  buildingLayout[ 8 ][ 16 - i ][ k ].moves[ 1 ] = 0;
	  buildingLayout[ 8 ][ 16 - i ][ k ].moves[ 2 ] = 6;
	  buildingLayout[ 8 ][ 16 - i ][ k ].num = 3;
	}
      }

      // Second Floor
      if ( k == 1 ) {
	buildingLayout[ i ][ 8 ][ k ].valid = 1;
	buildingLayout[ 8 ][ i ][ k ].valid = 1;
	buildingLayout[ i ][ 8 ][ k ].moves[ 0 ] = 0; // North
	buildingLayout[ 8 ][ i ][ k ].moves[ 0 ] = 2; // West
	buildingLayout[ i ][ 8 ][ k ].num = 1;
        buildingLayout[ 8 ][ i ][ k ].num = 1;

	// Elevator
	if ( i == 8 ) {
	  buildingLayout[ i ][ 8 ][ k ].moves[ 0 ] = 0; // North or West
	  buildingLayout[ i ][ 8 ][ k ].moves[ 1 ] = 2;
	  buildingLayout[ i ][ 8 ][ k ].num = 2;
	} 
	// Middle Column and Row
	if ( i == 4 ) {
	  buildingLayout[ i ][ 8 ][ k ].moves[ 0 ] = 6; // North, West, or East
          buildingLayout[ i ][ 8 ][ k ].moves[ 1 ] = 2;
          buildingLayout[ i ][ 8 ][ k ].moves[ 2 ] = 0;
	  buildingLayout[ i ][ 8 ][ k ].num = 3;

	  buildingLayout[ 8 ][ i ][ k ].moves[ 0 ] = 4; // North, West, or South
          buildingLayout[ 8 ][ i ][ k ].moves[ 1 ] = 0;
          buildingLayout[ 8 ][ i ][ k ].moves[ 2 ] = 2;
	  buildingLayout[ 8 ][ i ][ k ].num = 3;
	} 
	if ( i == 12 ) {
	  buildingLayout[ i ][ 8 ][ k ].moves[ 0 ] = 6; // North, West, or East
          buildingLayout[ i ][ 8 ][ k ].moves[ 1 ] = 2;
          buildingLayout[ i ][ 8 ][ k ].moves[ 2 ] = 0;
	  buildingLayout[ i ][ 8 ][ k ].num = 3;

	  buildingLayout[ 8 ][ i ][ k ].moves[ 0 ] = 4; // North, West, or South
          buildingLayout[ 8 ][ i ][ k ].moves[ 1 ] = 0;
          buildingLayout[ 8 ][ i ][ k ].moves[ 2 ] = 2;
	  buildingLayout[ 8 ][ i ][ k ].num = 3;
	}
      }
    }

    // Floor DIAGONALS
    for( i = 2; i < 7; i++ ) {
      
      // First Floor
      if ( k == 0 ) {
	buildingLayout[ 9 + i ][ i - 1 ][ k ].valid = 1;
	buildingLayout[ 7 - i ][ i - 1 ][ k ].valid = 1;
	buildingLayout[ 9 + i ][ 17 - i ][ k ].valid = 1;
	buildingLayout[ 7 - i ][ 17 - i ][ k ].valid = 1;
      
	buildingLayout[ 9 + i ][ i - 1 ][ k ].num = 1;
        buildingLayout[ 7 - i ][ i - 1 ][ k ].num = 1;
        buildingLayout[ 9 + i ][ 17 - i ][ k ].num = 1;
        buildingLayout[ 7 - i ][ 17 - i ][ k ].num = 1;

	buildingLayout[ 9 + i ][ 17 - i ][ k ].moves[ 0 ] = 7; // NE
	buildingLayout[ 9 + i ][ i - 1 ][ k ].moves[ 0 ] = 5; // SE
	buildingLayout[ 7 - i ][ i - 1 ][ k ].moves[ 0 ] = 3; // SW
	buildingLayout[ 7 - i ][ 17 - i ][ k ].moves[ 0 ] = 1; // NW
	
	// Junction with Store on Diagonal
	if ( i == 4 ) {
	  buildingLayout[ 9 + i ][ 17 - i ][ k ].moves[ 0 ] = 7; // NE + NW
	  buildingLayout[ 9 + i ][ 17 - i ][ k ].moves[ 1 ] = 1;
	  buildingLayout[ 9 + i ][ 17 - i ][ k ].num = 2;

	  buildingLayout[ 9 + i ][ i - 1 ][ k ].moves[ 0 ] = 5; // SE + NE
	  buildingLayout[ 9 + i ][ i - 1 ][ k ].moves[ 1 ] = 7;
	  buildingLayout[ 9 + i ][ i - 1 ][ k ].num = 2;

	  buildingLayout[ 7 - i ][ i - 1 ][ k ].moves[ 0 ] = 3; // SW + SE
	  buildingLayout[ 7 - i ][ i - 1 ][ k ].moves[ 1 ] = 5;
	  buildingLayout[ 7 - i ][ i - 1 ][ k ].num = 2;

	  buildingLayout[ 7 - i ][ 17 - i ][ k ].moves[ 0 ] = 1; // NW + SW
	  buildingLayout[ 7 - i ][ 17 - i ][ k ].moves[ 1 ] = 3;
	  buildingLayout[ 7 - i ][ 17 - i ][ k ].num = 2;
	}
      }

      // Second Floor
      if ( k == 1 ) {
        buildingLayout[ 9 + i ][ i - 1 ][ k ].valid = 1;
        buildingLayout[ 7 - i ][ i - 1 ][ k ].valid = 1;
        buildingLayout[ 9 + i ][ 17 - i ][ k ].valid = 1;
        buildingLayout[ 7 - i ][ 17 - i ][ k ].valid = 1;
	
	buildingLayout[ 9 + i ][ i - 1 ][ k ].num = 1;
        buildingLayout[ 7 - i ][ i - 1 ][ k ].num = 1;
        buildingLayout[ 9 + i ][ 17 - i ][ k ].num = 1;
        buildingLayout[ 7 - i ][ 17 - i ][ k ].num = 1;

        buildingLayout[ 9 + i ][ i - 1 ][ k ].moves[ 0 ] = 1; // NW
        buildingLayout[ 9 + i ][ 17 - i ][ k ].moves[ 0 ] = 3; // SW
        buildingLayout[ 7 - i ][ 17 - i ][ k ].moves[ 0 ] = 5; // SE
	buildingLayout[ 7 - i ][ i - 1 ][ k ].moves[ 0 ] = 7; // NE

        if ( i == 4 ) {
	  buildingLayout[ 9 + i ][ i - 1 ][ k ].moves[ 0 ] = 1; // NW + NE
          buildingLayout[ 9 + i ][ i - 1 ][ k ].moves[ 1 ] = 7;
	  buildingLayout[ 9 + i ][ i - 1 ][ k ].num = 2;

          buildingLayout[ 9 + i ][ 17 - i ][ k ].moves[ 0 ] = 3; // SW + NW
          buildingLayout[ 9 + i ][ 17 - i ][ k ].moves[ 1 ] = 1;
	  buildingLayout[ 9 + i ][ 17 - i ][ k ].num = 2;

          buildingLayout[ 7 - i ][ 17 - i ][ k ].moves[ 0 ] = 5; // SE + SW
          buildingLayout[ 7 - i ][ 17 - i ][ k ].moves[ 1 ] = 3;
	  buildingLayout[ 7 - i ][ 17 - i ][ k ].num = 2;

          buildingLayout[ 7 - i ][ i - 1 ][ k ].moves[ 0 ] = 7; // NE + SE
          buildingLayout[ 7 - i ][ i - 1 ][ k ].moves[ 1 ] = 5;
	  buildingLayout[ 7 - i ][ i - 1 ][ k ].num = 2;
	}
      }
    }

    // Floor MIDDLE and TOP lines of 5 and STORES
    for( i = 0; i < 5; i++ ) {
      
      // Set Valid Nodes
      buildingLayout[ 10 - i ][ 0 ][ k ].valid = 1;
      buildingLayout[ 10 - i ][ 16 ][ k ].valid = 1;
      buildingLayout[ 10 - i ][ 4 ][ k ].valid = 1;
      buildingLayout[ 10 - i ][ 12 ][ k ].valid = 1;
      buildingLayout[ 0 ][ 10 - i ][ k ].valid = 1;
      buildingLayout[ 16 ][ 10 - i ][ k ].valid = 1;
      buildingLayout[ 4 ][ 10 - i ][ k ].valid = 1;
      buildingLayout[ 12 ][ 10 - i ][ k ].valid = 1;

      buildingLayout[ 4 ][ 4 ][ k ].valid = 1;
      buildingLayout[ 4 ][ 12 ][ k ].valid = 1;
      buildingLayout[ 12 ][ 4 ][ k ].valid = 1;
      buildingLayout[ 12 ][ 12 ][ k ].valid = 1;
	
      // Set Number of Moves
      buildingLayout[ 10 - i ][ 0 ][ k ].num = 1;
      buildingLayout[ 10 - i ][ 16 ][ k ].num = 1;
      buildingLayout[ 10 - i ][ 4 ][ k ].num = 1;
      buildingLayout[ 10 - i ][ 12 ][ k ].num = 1;
      buildingLayout[ 0 ][ 10 - i ][ k ].num = 1;
      buildingLayout[ 16 ][ 10 - i ][ k ].num = 1;
      buildingLayout[ 4 ][ 10 - i ][ k ].num = 1;
      buildingLayout[ 12 ][ 10 - i ][ k ].num = 1;

      buildingLayout[ 4 ][ 4 ][ k ].num = 1;
      buildingLayout[ 4 ][ 12 ][ k ].num = 1;
      buildingLayout[ 12 ][ 4 ][ k ].num = 1;
      buildingLayout[ 12 ][ 12 ][ k ].num = 1;


      // Set MOVES First Floor
      if ( k == 0 ) {
        for ( j = 0; j < 4; j++ ) {
          // Column
          buildingLayout[ 6 + j ][ 0 ][ k ].moves[ 0 ] = 4; // South, SouthEast, or East
          buildingLayout[ 8 ][ 0 ][ k ].moves[ 1 ] = 6; 
          buildingLayout[ 10 ][ 0 ][ k ].moves[ 0 ] = 5;
	  buildingLayout[ 8 ][ 0 ][ k ].num = 2;

          buildingLayout[ 6 ][ 16 ][ k ].moves[ 0 ] = 1; // North or NorthWest
          buildingLayout[ 7 + j ][ 16 ][ k ].moves[ 0 ] = 0; 

          buildingLayout[ 6 ][ 4 ][ k ].moves[ 0 ] = 4; // North, South, or East 
          buildingLayout[ 7 ][ 4 ][ k ].moves[ 0 ] = 4;
	  buildingLayout[ 9 ][ 4 ][ k ].moves[ 0 ] = 4;
	  buildingLayout[ 10 ][ 4 ][ k ].moves[ 0 ] = 0;
          buildingLayout[ 9 ][ 4 ][ k ].moves[ 1 ] = 0;
          buildingLayout[ 7 ][ 4 ][ k ].moves[ 1 ] = 0; 
	  buildingLayout[ 9 ][ 4 ][ k ].num = 2;
	  buildingLayout[ 7 ][ 4 ][ k ].num = 2;
	  buildingLayout[ 8 ][ 4 ][ k ].num = 3;

          buildingLayout[ 6 ][ 12 ][ k ].moves[ 0 ] = 4; // North, South, or East
	  buildingLayout[ 7 ][ 12 ][ k ].moves[ 0 ] = 4;
	  buildingLayout[ 9 ][ 12 ][ k ].moves[ 0 ] = 4;
	  buildingLayout[ 10 ][ 12 ][ k ].moves[ 0 ] = 0;
          buildingLayout[ 9 ][ 12 ][ k ].moves[ 1 ] = 0;
          buildingLayout[ 7 ][ 12 ][ k ].moves[ 1 ] = 0;
	  buildingLayout[ 9 ][ 12 ][ k ].num = 2;
	  buildingLayout[ 7 ][ 12 ][ k ].num = 2;
	  buildingLayout[ 8 ][ 12 ][ k ].num = 3;

	  // Rows

	  buildingLayout[ 0 ][ 7 + j ][ k ].moves[ 0 ] = 2; // West, SouthWest, or Sout
          buildingLayout[ 0 ][ 8 ][ k ].moves[ 1 ] = 4;
          buildingLayout[ 0 ][ 6 ][ k ].moves[ 0 ] = 3;
          buildingLayout[ 0 ][ 8 ][ k ].num = 2;

          buildingLayout[ 16 ][ 10 ][ k ].moves[ 0 ] = 7; // North or NorthWest
          buildingLayout[ 16 ][ 6 + j ][ k ].moves[ 0 ] = 6;

          buildingLayout[ 4 ][ 6 ][ k ].moves[ 0 ] = 6; // West or  East
          buildingLayout[ 4 ][ 7 ][ k ].moves[ 0 ] = 6;
          buildingLayout[ 4 ][ 9 ][ k ].moves[ 0 ] = 6;
          buildingLayout[ 4 ][ 10 ][ k ].moves[ 0 ] = 2;
          buildingLayout[ 4 ][ 9 ][ k ].moves[ 1 ] = 2;
          buildingLayout[ 4 ][ 7 ][ k ].moves[ 1 ] = 2;
          buildingLayout[ 4 ][ 9 ][ k ].num = 2;
          buildingLayout[ 4 ][ 7 ][ k ].num = 2;
	  buildingLayout[ 4 ][ 8 ][ k ].num = 3;

          buildingLayout[ 12 ][ 6 ][ k ].moves[ 0 ] = 6; // North, South, or East
          buildingLayout[ 12 ][ 7 ][ k ].moves[ 0 ] = 6;
          buildingLayout[ 12 ][ 9 ][ k ].moves[ 0 ] = 6;
          buildingLayout[ 12 ][ 10 ][ k ].moves[ 0 ] = 2;
          buildingLayout[ 12 ][ 9 ][ k ].moves[ 1 ] = 2;
          buildingLayout[ 12 ][ 7 ][ k ].moves[ 1 ] = 2;
          buildingLayout[ 12 ][ 9 ][ k ].num = 2;
          buildingLayout[ 12 ][ 7 ][ k ].num = 2;
	  buildingLayout[ 12 ][ 8 ][ k ].num = 3;

	  // Corner Stores
	  buildingLayout[ 4 ][ 4 ][ k ].moves[ 0 ] = 1;   // SE
	  buildingLayout[ 4 ][ 12 ][ k ].moves[ 0 ] = 7;  // SW
	  buildingLayout[ 12 ][ 4 ][ k ].moves[ 0 ] = 3;  // NE
	  buildingLayout[ 12 ][ 12 ][ k ].moves[ 0 ] = 5; // NW
	}
      }

      // Set MOVES on Second Floor
      if ( k == 1 ) {
        for ( j = 0; j < 4; j++ ) {
          // Column 
          buildingLayout[ 7 + j ][ 0 ][ k ].moves[ 0 ] = 0; // North, NorthEast
          buildingLayout[ 6 ][ 0 ][ k ].moves[ 0 ] = 7;

          buildingLayout[ 6 + j ][ 16 ][ k ].moves[ 0 ] = 4; // South, SouthWest, or West
	  buildingLayout[ 8 ][ 16 ][ k ].moves[ 1 ] = 2;
	  buildingLayout[ 10 ][ 16 ][ k ].moves[ 0 ] = 3;
	  buildingLayout[ 8 ][ 16 ][ k ].num = 2;

          buildingLayout[ 6 ][ 12 ][ k ].moves[ 0 ] = 4; // North, South, or West
          buildingLayout[ 7 ][ 12 ][ k ].moves[ 0 ] = 4;
	  buildingLayout[ 9 ][ 12 ][ k ].moves[ 0 ] = 4;
	  buildingLayout[ 10 ][ 12 ][ k ].moves[ 0 ] = 0;
          buildingLayout[ 9 ][ 12 ][ k ].moves[ 1 ] = 0;
          buildingLayout[ 7 ][ 12 ][ k ].moves[ 1 ] = 0;
	  buildingLayout[ 7 ][ 12 ][ k ].num = 2;
          buildingLayout[ 8 ][ 12 ][ k ].num = 3;
          buildingLayout[ 9 ][ 12 ][ k ].num = 2;


          buildingLayout[ 6 ][ 4 ][ k ].moves[ 0 ] = 4; // North, South, or West
          buildingLayout[ 7 ][ 4 ][ k ].moves[ 0 ] = 4;
	  buildingLayout[ 9 ][ 4 ][ k ].moves[ 0 ] = 4;
	  buildingLayout[ 10 ][ 4 ][ k ].moves[ 0 ] = 0;
          buildingLayout[ 9 ][ 4 ][ k ].moves[ 1 ] = 0;
          buildingLayout[ 7 ][ 4 ][ k ].moves[ 1 ] = 0;
	  buildingLayout[ 7 ][ 4 ][ k ].num = 2;
          buildingLayout[ 8 ][ 4 ][ k ].num = 3;
          buildingLayout[ 9 ][ 4 ][ k ].num = 2;


          // Rows
          buildingLayout[ 16 ][ 7 + j ][ k ].moves[ 0 ] = 2; // West, NorthWest, North
          buildingLayout[ 16 ][ 8 ][ k ].moves[ 1 ] = 0;
          buildingLayout[ 16 ][ 6 ][ k ].moves[ 0 ] = 1;
	  buildingLayout[ 16 ][ 8 ][ k ].num = 2;

          buildingLayout[ 0 ][ 6 + j ][ k ].moves[ 0 ] = 6; // East, SouthEast
          buildingLayout[ 0 ][ 10 ][ k ].moves[ 0 ] = 5;

          buildingLayout[ 4 ][ 6 ][ k ].moves[ 0 ] = 6; // East, West, North
	  buildingLayout[ 4 ][ 7 ][ k ].moves[ 0 ] = 6;
	  buildingLayout[ 4 ][ 9 ][ k ].moves[ 0 ] = 6;
	  buildingLayout[ 4 ][ 10 ][ k ].moves[ 0 ] = 2;
          buildingLayout[ 4 ][ 9 ][ k ].moves[ 1 ] = 2;
          buildingLayout[ 4 ][ 7 ][ k ].moves[ 1 ] = 2;
	  buildingLayout[ 4 ][ 7 ][ k ].num = 2;
          buildingLayout[ 4 ][ 8 ][ k ].num = 3;
          buildingLayout[ 4 ][ 9 ][ k ].num = 2;


          buildingLayout[ 12 ][ 6 ][ k ].moves[ 0 ] = 6; // East, West, North
          buildingLayout[ 12 ][ 7 ][ k ].moves[ 0 ] = 6;
	  buildingLayout[ 12 ][ 9 ][ k ].moves[ 0 ] = 6;
	  buildingLayout[ 12 ][ 10 ][ k ].moves[ 0 ] = 2;
          buildingLayout[ 12 ][ 9 ][ k ].moves[ 1 ] = 2;
          buildingLayout[ 12 ][ 7 ][ k ].moves[ 1 ] = 2;
	  buildingLayout[ 12 ][ 7 ][ k ].num = 2;
          buildingLayout[ 12 ][ 8 ][ k ].num = 3;
          buildingLayout[ 12 ][ 9 ][ k ].num = 2;

	  // Corner Stores
	  buildingLayout[ 4 ][ 4 ][ k ].moves[ 0 ] = 1;   // SE
	  buildingLayout[ 4 ][ 12 ][ k ].moves[ 0 ] = 7;  // SW
	  buildingLayout[ 12 ][ 4 ][ k ].moves[ 0 ] = 3;  // NE
	  buildingLayout[ 12 ][ 12 ][ k ].moves[ 0 ] = 5; // NW
   
	}
      }
    }
  }

  // Account for random Cases
  buildingLayout[ 12 ][ 8 ][ 0 ].moves[ 0 ] = 6;
  buildingLayout[ 12 ][ 8 ][ 0 ].moves[ 1 ] = 2;
  buildingLayout[ 12 ][ 8 ][ 0 ].moves[ 2 ] = 4;
  buildingLayout[ 12 ][ 8 ][ 0 ].num = 3;

  buildingLayout[ 8 ][ 12 ][ 0 ].moves[ 0 ] = 4;
  buildingLayout[ 8 ][ 12 ][ 0 ].moves[ 1 ] = 0;
  buildingLayout[ 8 ][ 12 ][ 0 ].moves[ 2 ] = 6;
  buildingLayout[ 8 ][ 12 ][ 0 ].num = 3;


  // End of initialize valid
  return 0;
}




// Initialize the STORE nodes
int init_store( void ) {
  int i, k; // counters

  for( k = 0; k < 2; k++ ) {
    for ( i = 0; i < 2; i++ ) {
      buildingLayout[ 10 - (4*i) ][ 4 ][ k ].store = 1;
      buildingLayout[ 4 ][ 10 - (4*i) ][ k ].store = 1;
      buildingLayout[ 10 - (4*i) ][ 12 ][ k ].store = 1;
      buildingLayout[ 12 ][ 10 - (4*i) ][ k ].store = 1;
    }

    buildingLayout[ 4 ][ 4 ][ k ].store = 1;
    buildingLayout[ 4 ][ 12 ][ k ].store = 1;
    buildingLayout[ 12 ][ 4 ][ k ].store = 1;
    buildingLayout[ 12 ][ 12 ][ k ].store = 1;
  }
  // End of initalize store
  return 0;
}


// Initialize the ELEVATOR nodes
int init_elevator( void ) {

  buildingLayout[ 8 ][ 8 ][ 0 ].elevator = 1;
  buildingLayout[ 8 ][ 8 ][ 1 ].elevator = 1;

  // End of initialize elevator
  return 0;
}

// Initialize the EXIT node
int init_exit( void ) {

  buildingLayout[ 8 ][ 16 ][ 0 ].exit = 1;

  // End of initialize exit
  return 0;
}

// Initialize the LOGIC for nodes
int init_logic( void ) {
  int i, j, k; // counters
  
  for( k = 0; k < 2; k++ ) {
    for( i = 0; i < 17; i++ ) {
      for( j = 0; j < 17; j++ ) {
	
	// Floor 1
	if ( buildingLayout[ i ][ j ][ k ].Id == 800 ) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 1204;
	}
	
	if ( buildingLayout[ i ][ j ][ k ].Id == 303 ) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 404;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 1303) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 1204;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 704) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 604;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 804) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 1004;
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 604;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 904) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 1004;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 407) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 406;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 1207 ) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 1206;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 8) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 404;
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 1 ] = 604;
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 2 ] = 1004;
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 3 ] = 1204;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 408) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 410;
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 406;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 808) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 1206;
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 1 ] = 1210;
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 2 ] = 1212;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 1208) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 1206;
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 1210;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 409 ) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 410;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 1209) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 1210;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 712) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 612;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 812) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 1012;
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 612;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 912) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 1012;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 313) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 412;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 1313) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 1212;
        }

	// Floor 2

	if ( buildingLayout[ i ][ j ][ k ].Id == 10303) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 10404;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 11303) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 11204;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10704) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 10604;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10804) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 11004;
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 10604;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10904) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 11004;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10407) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 10406;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 11207) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 11206;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10408) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 10406;
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 10410;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10808) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 10404;
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 1 ] = 10604;
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 2 ] = 11004;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 11208) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 11210;
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 11206;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 11608) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 11204;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10409) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 10410;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 11209) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 11210;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10712) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 10612;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10812) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 10612;
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 11012;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10912) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 11012;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10313) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 10412;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 11313) {
	  buildingLayout[ i ][ j ][ k ].logic[ 1 ][ 0 ] = 11212;
        }

	if ( buildingLayout[ i ][ j ][ k ].Id == 10816) {
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 0 ] = 11212;
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 1 ] = 11204;
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 2 ] = 11206;
	  buildingLayout[ i ][ j ][ k ].logic[ 0 ][ 3 ] = 11210;
        }
      }
    }
  }

  // End of initialize logic
  return 0;
}
