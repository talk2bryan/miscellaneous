CC=clang
CFLAGS=-Wall -Wextra -pedantic -std=c99

all: manage_contacts

manage_contacts: manage_contacts.c
	$(CC) $(CFLAGS) -o $@ $^

clean:
	rm -f manage_contacts *.dSYM
