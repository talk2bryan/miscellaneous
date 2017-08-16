// Intro to Templates.
// File: intro_templates.cpp
#include <iostream>
#include "assert.h"

#include "general_utility.hpp"


#define SIZE 10

// Function Template that finds  
// the square of a parameter.
template <class T>
T square(T param){
	return param*param;
}

/* ***************************************************************** */
// /////////////////////////////////////////
// Class Template: A container holds an
// array of elements.
// /////////////////////////////////////////

template <class T>
class Container
{
public:
	Container() :size(0) {;}
	~Container();
	T get(int) const;
	inline void push(T);
	void display() const;
	int get_size()const {return size;}
private:
	int size;
	T array[SIZE];
};

template <class T>
Container<T>::~Container<T>() {;}

template <class T>
void Container<T>::display() const{
	for (int i = 0; i < size; ++i)
	{
		VERBOSE(array[i]);
	}
}

template <class T>
T Container<T>::get(int index) const{
	assert(0 <= index);
	assert(index<size);
	return array[index];
}

template <class T>
inline void Container<T>::push(T value){
	assert(size<SIZE);
	array[size++] = value;
}

template <class T>
Container<T> operator*(const Container<T>& lhs, const Container<T>& rhs) {
	Container<T> result;

	for (int i = 0; i < lhs.get_size(); ++i)
	{
		T temp = lhs.get(i) * rhs.get(i);
		result.push(temp);
	}

	return result;
}
/* ***************************************************************** */


/* ***************************************************************** */
// /////////////////////////////////////////////////////////
// Specialized Class Template: A container holds an
// array of elements. The * operator behaves differently,
// in the sense that it converts lower case to upper, and
// vice versa. Non-alphabets reamin unaltered.
// /////////////////////////////////////////////////////////

template <>
class Container <char>
{
public:
	Container() :size(0) {;}
	~Container();
	char get(int) const;
	inline void push(char);
	void display() const;
	int get_size()const {return size;}
private:
	int size;
	char array[SIZE];
};

Container<char>::~Container<char>() {;}

void Container<char>::display() const{
	for (int i = 0; i < size; ++i)
	{
		VERBOSE(array[i]);
	}
}

char Container<char>::get(int index) const{
	assert(0 <= index);
	assert(index<size);
	return array[index];
}

inline void Container<char>::push(char value){
	assert(size<SIZE);
	array[size++] = value;
}

Container<char> operator*(const Container<char>& lhs, const Container<char>& rhs) {
	Container<char> result;

	for (int i = 0; i < lhs.get_size(); ++i)
	{
		char temp = lhs.get(i);
		if ('a' <= temp && temp <= 'z'){
			temp -= 32;
		}
		else if ('A' <= temp && temp <= 'Z'){
			temp += 32;
		}
		// else, leave as is.
		result.push(temp);
	}
	return result;
}

/* ***************************************************************** */



int main(int argc, char const *argv[])
{
	int five = 5;
	VERBOSE( square(five) );

	Container<double> vals;
	vals.push(2.2);
	vals.push(4.5);
	vals.push(2.1);
	vals.push(3.3);
	vals.push(1.1);
	vals.push(8.2);

	Container<double> n_sq =  square(vals);
	n_sq.display();

	Container<char> chars;
	chars.push('a');
	chars.push('b');
	chars.push('c');
	chars.push('D');
	chars.push('E');
	chars.push('F');
	chars.push('0');
	chars.push('$');
	chars.push('%');

	Container<char> c_sq =  square(chars);
	c_sq.display();

	return 0;
}