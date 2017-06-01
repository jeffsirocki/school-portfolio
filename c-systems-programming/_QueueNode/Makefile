## Start of the Makefile

lab5: lab5.o shop.o queuenode.o
	g++ -o lab5 lab5.o shop.o queuenode.o

lab5.o: lab5.cpp lab5.h
	g++ -Wall -c lab5.cpp

shop.o: shop.cpp lab5.h
	g++ -Wall -c shop.cpp

queuenode.o: queuenode.cpp lab5.h
	g++ -Wall -c queuenode.cpp

clean:
	rm *.o

## End of the Makefile
