## Start of the Makefile

prog4: prog4.o item.o treenode.o listnode.o
	g++ -o prog4 prog4.o item.o treenode.o listnode.o

prog4.o: prog4.cpp prog4.h
	g++ -Wall -c prog4.cpp

item.o: item.cpp prog4.h
	g++ -Wall -c item.cpp

treenode:o treenode.cpp prog4.h
	g++ -Wall -c treenode.cpp

listnode:o listnode.cpp prog4.h
	g++ -Wall -c listnode.cpp

clean:
	rm *.o

## End of the Makefile
