## Start of the Makefile

all: phase1 phase2

phase1: phase1.o queue.o
	gcc -pthread -o phase1 phase1.o queue.o

phase1.o: phase1.c prog3.h
	gcc -Wall -c phase1.c

queue.o: queue.c prog3.h
	gcc -Wall -c queue.c

phase2: phase2.o traffic.o
	gcc -pthread -o phase2 phase2.o traffic.o

phase2.o: phase2.c prog3.h
	gcc -Wall -c phase2.c

traffic.o: traffic.c prog3.h
	gcc -Wall -c traffic.c

clean:
	rm *.o

## End of the Makefile
