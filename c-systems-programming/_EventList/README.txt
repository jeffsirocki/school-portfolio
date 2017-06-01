 Jeff Sirocki - jasirocki
 CS 2303 - A02
 Lab3 - 9/17/14

 jasirocki_lab3.tar includes:
                    - lab3.c
                    - eventlist.h
                    - event_list.c
                    - Makefile
                    - eventlist.txt (output)
                    - lab3  (excecutable)
                    - README.txt

Program is fully functional. The program reads in one command line argument num (num of inputs) 
and the lines of input from a script file lab3.dat. Each time an input is entered it is added 
to an event list. Following the final input, the event list is output and get_nextevent takes 
the first event, returns it, and deletes the event from the event list.

 Compile with make command:

    $ make

 To run program, run the executable with command line arg num and 
    input script file lab3.dat:

    $ ./lab3 num < lab3.dat

 The output for the following command is in eventlist.txt.
