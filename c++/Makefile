# Makefile
CXX = g++
CXXFLAGS += -Wall -std=c++11
DEBUGFLAGS = -DDEBUG -g
TARGET = name templates lex_order long_number

all:	$(TARGET)

debug:	CXXFLAGS += $(DEBUGFLAGS)
debug:	$(TARGET)

name:	naming.cpp
	$(CXX) $(CXXFLAGS) $< -o $@

templates: intro_templates.cpp
	$(CXX) $(CXXFLAGS) $< -o $@

lex_order: lexicographical_order.cpp
	$(CXX) $(CXXFLAGS)  $< -o $@

long_number: long_number_system.cpp
	$(CXX) $(CXXFLAGS)  $< -o $@

clean:
	rm -rf $(TARGET) *.dSYM
