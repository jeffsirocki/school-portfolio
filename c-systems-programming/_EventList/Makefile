## Start of the Makefile

lab3: lab3.o event_list.o
	gcc -o lab3 lab3.o event_list.o

lab3.o: lab3.c eventlist.h
	gcc -Wall -c lab3.c

event_list.o: event_list.c eventlist.h
	gcc -Wall -c event_list.c

clean:
	rm *.o

## End of the Makefile
