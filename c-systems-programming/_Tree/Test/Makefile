## Start of the Makefile

main: main lab4.o tree.o
	gcc -o main lab4.o tree.o

lab4.o: lab4.c lab4.h
	gcc -Wall -c lab4.c

tree.o: tree.c lab4.h
	gcc -Wall -c tree.c

clean:
	rm *.o

## End of the Makefile
