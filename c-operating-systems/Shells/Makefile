## Start of the Makefile

all: phase1 phase2 phase3

phase1: runCommands.o
	gcc -o phase1 runCommands.o

phase2: shell.o
	gcc -o phase2 shell.o

phase3: shell2.o
	gcc -o phase3 shell2.o

runCommands.o: runCommands.c
	gcc -c runCommands.c

shell.o: shell.c
	gcc -c shell.c

shell2.o: shell2.c
	gcc -c shell2.c

clean:
	rm *.o

## End of the Makefile
