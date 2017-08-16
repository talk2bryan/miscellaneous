# Makefile
CXX = g++
CXXFLAGS += -Wall
DEBUGFLAGS = -DDEBUG -g
TARGET = name templates

all:	$(TARGET)

debug:	CXXFLAGS += $(DEBUGFLAGS)
debug:	$(TARGET)

name:	naming.cpp
	$(CXX) $(CXXFLAGS) $< -o $@

templates: intro_templates.cpp
	$(CXX) $(CXXFLAGS) $< -o $@

clean:
	rm -rf $(TARGET) *.dSYM
