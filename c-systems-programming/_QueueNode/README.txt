created by Jeffrey Sirocki and Joseph Kaiser
10/9/2014

jas_jtk_lab5.tar includes:
        lab5.cpp - Jeff S
        lab5.h - Jeff and Joe
        shop.cpp - Jeff S
        queuenode.cpp - Joe
        Makefile - Jeff S
        lab5 (executable)
        lab5.dat
        README.txt - Jeff and Joe

To compile the program, enter "make" in the command line.

Input data is to be redirected using the command line in the case of reading in from an ASCII file, or 
otherwise by terminal input.

This program for lab5 is incomplete. Our goal of this lab was to read in shop data for a shopper and 
simulate the shopper within the store queue using RR scheduling. We planned to output the time when the shopper 
enters and leaves the store as well as when a shopper exits the simulation.

To run the program, run the executable file by entering the following into the command line:

$ ./lab5 4 50 < lab5.dat
         (Note: 4 is numStores and 50 is time_slice )

Our queue appeared to be functional but linking it proved troublesome. We read in the data fine but the simulation
code is commented out because of segfaults and other errors.
