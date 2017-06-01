## Start of the Makefile

main: main.o building_layout.o
	g++ -o main main.o building_layout.o

main.o: main.cpp building_layout.h shopper.h
	g++ -Wall -c main.cpp

building_layout.o: building_layout.cpp building_layout.h
	g++ -Wall -c building_layout.cpp

clean:
	rm *.o

## End of the Makefile
