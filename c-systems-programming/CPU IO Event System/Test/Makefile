## Start of the Makefile

prog3: prog3.o eventlist.o queue.o simulate.o process_cpu.o process_io.o print.o
	gcc -o prog3 prog3.o eventlist.o queue.o simulate.o process_cpu.o process_io.o print.o

prog3.o: prog3.c prog3.h
	gcc -Wall -c prog3.c

eventlist.o: eventlist.c prog3.h
	gcc -Wall -c eventlist.c

queue.o: queue.c prog3.h
	gcc -Wall -c queue.c

simulate.o: simulate.c prog3.h
	gcc -Wall -c simulate.c

process_cpu.o: process_cpu.c prog3.h
	gcc -Wall -c process_cpu.c

process_io.o: process_io.c prog3.h
	gcc -Wall -c process_io.c

print.o: print.c prog3.h
	gcc -Wall -c print.c

clean:
	rm *.o

## End of the Makefile
