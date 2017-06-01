// Tyler Bennett and Jeff Sirocki
// Due: 02-17-15

FILES INCLUDED:
		phase1.c
		phase2.c
		queue.c
		traffic.c
		prog3.h
		Makefile
		output1.txt
		output2.txt
		README.txt


Problem 1: For this problem, we decided to implement mutexes in our solution. We protected every global variable
with a mutex that would only allow one thread to access the variable at a time. Main has the responsibility of initializing
variables and mutexes, as well as creating the threads, giving them a job to do, and entering them into the queue. From 
there, the threads all simultaneously run their given job named "run_job". Each thread's respective function keeps track of 
that thread's id & security level. The function each thread runs contains a while loop that is responsible for making the thread
wait its turn to be dequeued from the top of the queue, find its correct place in the cluster, "execute", print results, leave
the cluster, and then enqueue itself back into the queue at the end of the while loop after a random amount of time.

	OUTPUT:
	 - Added Job #0 to Queue

	 - Initializing SecuriOS Cluster...

	 - DEQUEUE: Thread #8: 505604416

	 - Thread 10 - SecuriOS (2) Job #7 started (RIGHT CLUSTER)

	 - Thread 10 - SecuriOS (2) Job #7 finished (RIGHT CLUSTER)

	 - REORDER: Thread #10: 526584128
         REORDER: Thread #1 MOVED.
         REORDER: Thread #0 MOVED.

	
	HOW TO RUN:
		
		COMPILE:
			$bash make
		
		RUN:
			$bash ./phase1

		CLEAN:
			$bash make clean
	
	NOTE: 
- Several warnings appear during compilation with the make command. 
- There are DEBUG and TEST switches to increase the amount of output.
- We did not implement the swap function to fully to increase concurrency.
		

----------------- End of Problem 1 ----------------------------
		
Problem 2: For this problem, we decided to implement semaphores in our solution. We used 11 semaphores in total for our 
implementation. Four were to represent the four quadrants of the intersection, and each were initialized to a value of 
1, due to the fact that only one car could be in a quadrant at one time to avoid crashes. To avoid deadlocks, we used a 
semaphore that was initialized to the value of 3, with the purpose of not allowing more than 3 cars into the intersection 
at one time that wanted to go left or straight. Any car that wanted to go straight or left would have to call wait on the 
deadlock semaphore. We also implement a queue, that handles the case in which 2 or more cars approach the intersection from
the same directions. The cars are enqueued in their respective lines, and must wait until they are the head pointer within
their queue to proceed to tr and enter the intersection. Enqueuing and dequeuing is facilitated by 4 semaphores, each of which
control their respective line. They are initialized to 1 and only allow 1 car to work its respective queue at a time.

For our motorcades and emergency vehicle cases, we used a semaphore that would allow the emergency 
vehicle or motorcade to completely lock down the intersection by blocking regular cars from proceeding in their code. The
special cases will wait until the cars within the intersection have vacated, and then proceed.

	OUTPUT:

	- Initializing Traffic Simulator...

	- ENQUEUE: Car 0 in traffic line 3.

	- Traffic Simulator Initialization Complete. Running...

	- DEQUEUE: Car 0 from traffic line: 3

	- Thread 0 - Type (1) Car #1 started (Left Turn 3to2)

	- Thread 0 - Type (1) Car #1 moved (Left Turn 3to2)

	- Thread 0 - Type (1) Car #1 finished (Left Turn 3to2)

	- EMERGENCY GET OUT THE INTERSECTION

	- --------------------------------------------
	  EMERGENCY VEHICLE HAS ENTERED THE INTERSECTION.

      	  thread 0 - Emergency Vehicle started (Left Turn 3to2)
	  thread 0 - Emergency Vehicle finished (Left Turn 3to2)

	  EMERGENCY VEHICLE HAS LEFT THE INTERSECTION.
	  --------------------------------------------

	- MOTORCADE GET OUT THE INTERSECTION

	- --------------------------------------------
	  MOTORCADE VEHICLE 1 HAS ENTERED THE INTERSECTION.

	  Thread 1 - Motorcade Vehicle started (Right Turn 0to1)
	  Thread 1 - Motorcade Vehicle finished (Right Turn 0to1)

	  MOTORCADE VEHICLE 1 HAS LEFT THE INTERSECTION.
	  --------------------------------------------

	- Traffic Simulator Complete.


	HOW TO RUN:
	
		COMPILE:
			$bash make
			
		RUN:
			$bash ./phase2
			
		CLEAN:
			$bash make clean
		
	NOTE: 
	
	- Output that resembles this "Thread 0 - Type (1) Car #1 started (Left Turn 3to2)" can be interpreted as 
	  Thread 0 (or car 0, same thing) of type regular car, making its 1st pass through the intersection, entered
	  the intersection.

	- Another point to note is that our 20 car threads only run through the intersection once before finishing,
	  however we have a while loop tha can be uncommented tha allows the cars to travel forever through the 
	  intersection.
