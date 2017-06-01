all: prog4

prog4: prog4.o userAPI.o
	gcc -pthread prog4.o userAPI.o -o prog4

prog4.o: prog4.c prog4.h
	gcc -c prog4.c 

userAPI.o: userAPI.c prog4.h
	gcc -c userAPI.c

clean:
	rm -rf *.o prog4