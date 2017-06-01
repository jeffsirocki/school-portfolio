// Tyler Bennett and Jeff Sirocki
// Due: 02-27-15


FILES INCLUDED:
		prog4.c
		userAPI.c
		tester.c (unimplemented)
		prog4.h
		Makefile
		output1 (Single User)
		output2 (Multi-User (Maxer))
		output3 (Multi-User (Cacher))
		README.txt
		testing_methodology.txt
		
For this project, we created 2 C files and 1 header file.
The header file is used to name global variables, such as memory arrays, memory allocation arrays, 
and semaphores. It also contains the struct for a page in our page table, the typedef for the 
unsigned short vAddr, and function prototypes as well. 
Our prog4.c file contains our main in which we initialize threads, semaphores, and variables. 
We then create user threads that each contain different stress testing functions. 
Our stress tests include:
	- Calling allocateNewInt() when there is no memory available
	- Calling accessIntPtr() when all of RAM is already locked
	- Calling run_user1(User *uptr) is the memory maxer function
	- Calling run_user2(User *uptr) is the memory cacher function
	
Those testing functions will call functions housed within our userAPI.c code. userAPI.c has
the responsibility of creating and initializing the page table, as well as the implementation 
of four functions: allocateNewInt(), accessIntPtr(), unlockMemory(), and freeMemory(). The user 
can call these functions which allow the user to create, access, update, and free memory. 
The four functions called by the user are helped out by our evict algorithms, findIndex(), 
and typeCounter() functions, unseen to the user. 

Our simple eviction algorithm finds the first unlocked page (FIFO) it can to swap out of a given memory type. 

Our second eviction algorithm takes into account time (LRU). The time stamp of a page in the page table is set
when allocateNewInt() is successful in allocating a spot in memory for the caller. Whenever 
accessIntPtr() is called by the user on a given address, the time stamp of that address in the page 
table is updated, giving the evict algorithm a sense of which pages have been recently used. The job
of our evict algorithm is to take in a type of memory, and a place in the next lowest part of memory,
and find the least recently used page in that given type of memory and swap it into the place in lower
memory that has been provided. To find the least recently used page, it loops through the page table
and compares all the time stamps of pages of a certain type of memory, picking out the one with the 
largest time stamp as its final candidate.

Both eviction algorithms add the correct usleep() delays to the process when swapping takes place.

	OUTPUT:
		-  USER 1 on Job 2 working on Page 19:  
		-  => RAM[0] Unlock
		-  => Swap Out RAM[0] to SSD[0] => RAM[0] Unlock
		-  => Swap Out SDD[0] to HDD[0] => Swap Out RAM[0] to SSD[0] => RAM[0] Unlock
		-  Total Simulation took 94.096012 seconds
		-  

	HOW TO RUN:
		
		COMPILE:
			$bash make all
		
		RUN:
			$bash ./prog4

		CLEAN:
			$bash make clean
	
	NOTE:
	
	- Comments are included within the files.

	- tester.c is unimplemented. Was going to be used to test allocateNewInt() and accessIntPtr()