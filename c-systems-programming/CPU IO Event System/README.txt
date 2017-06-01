
Jeff Sirocki - jasirocki
CS2303 - A02
Program 3 - 9/23/14


Program 3(prog3.c) simulates a Computer System with events that arrive in real time and need CPU and I/O processing. The CPU and I/O can run at the SAME time. The program uses an Event List, a CPU Queue, and a IO Queue.

The program takes in command arguments numLines( int ) and time_splice( int ). The numLines refers to the number of sources to be read in from the scripted input file and the time_splice refers to the duration of the time-splice used for RR scheduling in the CPU Queue. Events which consist of { process_id, arrival_time, cpu_time, io_time } are read in from the scripted input file and added into the event list. The program simulates how events are processed in a CPU and I/O, logs meaningful output, and represents how a CPU and I/O System works.

	    Output:
          - When an event arrives at the simulation.
          - When an event arrives at the CPU Queue.
          - When an event arrives at the I/O Queue.
          - When an event has been Complete.

          - Performance Metrics including:
	    o For Each Event ( id, total_time, idle_time )
	    o Overall ( time, avg_time, avg_idle )

Tar File ( Comments included in source code ):

 jasirocki_prog3.tar include:
		     - prog3.c
                     - prog3.h ( headerfile )
                     - eventlist.c
                     - queue.c
                     - simulate.c
		     - process_cpu.c
		     - process_io.c
		     - print.c
                     - Makefile ( make )
                     - prog3a.out ( output for ./prog3 18 2 )
		     - prog3b.out ( output for ./prog3 18 5 )
                     - prog3 ( executable )
		     - prog3.dat ( data file )
                     - README.txt

How to run it:
    o Unpack the tar
    o Use command make
    o ./prog3 18 2 < prog3.dat 
    o ./prog3 18 5 < prog3.dat

Note: Everything is funcional. Two things though:
 
One - My implementation of process_cpu computes the RR scheduling on the event, inserts the processed event into the event list, which is later re-inserted into the CPU for completion.

Two - To turn on debug output( Lots of Info ), change DEBUG in the header 'prog3.h', use make, and run.
