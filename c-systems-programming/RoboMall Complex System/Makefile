## Start of the Makefile

prog5: prog5.o item.o robot.o shopper.o treenode.o listnode.o eventlist.o simulate.o process_move.o move.o process_store.o building_layout.o
	g++ -o prog5 prog5.o item.o robot.o shopper.o treenode.o listnode.o eventlist.o simulate.o process_move.o move.o process_store.o building_layout.o

prog5.o: prog5.cpp tree.h robomall.h robot.h eventlist.h
	g++ -Wall -c prog5.cpp

item.o: item.cpp tree.h
	g++ -Wall -c item.cpp

robot.o: robot.cpp robot.h
	g++ -Wall -c robot.cpp

shopper.o: shopper.cpp shopper.h tree.h
	g++ -Wall -c shopper.cpp

treenode.o: treenode.cpp tree.h
	g++ -Wall -c treenode.cpp

listnode.o: listnode.cpp tree.h
	g++ -Wall -c listnode.cpp

eventlist.o: eventlist.cpp eventlist.h
	g++ -Wall -c eventlist.cpp

simulate.o: simulate.cpp robot.h eventlist.h
	g++ -Wall -c simulate.cpp

process_move.o: process_move.cpp robot.h robomall.h
	g++ -Wall -c process_move.cpp

move.o: move.cpp robot.h robomall.h
	g++ -Wall -c move.cpp

process_store.o: process_store.cpp robot.h
	g++ -Wall -c process_store.cpp

building_layout.o: building_layout.cpp robomall.h
	g++ -Wall -c building_layout.cpp

clean:
	rm *.o

## End of the Makefile
