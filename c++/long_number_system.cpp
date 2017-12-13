// long_number_system.cpp

// A system that handles long numbers entered by the user.
// Current functions: add.
// TODO (whoami): subtract

// Author: Bryan Wodi
// Nov. 16, 2017


#include <iostream>
#include <string>
#include <algorithm>
#include <cassert>

#include "general_utility.hpp"

class Number
{
public:
	Number(const std::string& str): m_num(str) { size = str.length();}
	~Number();
	// Passing LHS by value helps optimize chained operations: a+b+c
	friend Number operator+(Number lhs, const Number& rhs); 
	friend std::ostream& operator<<(std::ostream& os, const Number& num);
private:
	std::string m_num;
	int size;
};

Number::~Number(){ ; }

std::ostream& operator<<(std::ostream& os, const Number& num){
	os << num.m_num;
	return os;
}

Number operator+(Number lhs, const Number& rhs){
	std::string bigger, smaller;

	if(rhs.size > lhs.size){
		bigger = rhs.m_num;
		smaller = lhs.m_num;
	} else{
		bigger = lhs.m_num;
		smaller = rhs.m_num;
	}

	int carry = 0;
	int partial_sum;
	int big_index, small_index;
	int offset = bigger.length() - smaller.length();


	for (small_index = smaller.length()-1; small_index >=0 ; small_index--)
	{
		partial_sum = bigger[small_index+offset] - '0' 
				+ smaller[small_index] - '0' + carry;

		carry = partial_sum / 10;
		partial_sum = partial_sum % 10;

		bigger[small_index+offset] = '0' + partial_sum;
	}

	
	for(big_index = offset-1; big_index >= 0; big_index--){
		partial_sum = bigger[big_index] - '0' + carry;
		
		carry = partial_sum / 10;
		partial_sum = partial_sum % 10;

		bigger[big_index] = '0' + partial_sum;
	}

	if(carry > 0){
		std::string begin = std::to_string(carry);
		bigger.insert(0, begin);
	}
	
	lhs.m_num = bigger;

	return lhs;
}


int main(int argc, char const *argv[])
{
	std::string u_input;

	VERBOSE("Please enter a long number: ");
	std::cin >> u_input;

	// Ensure that input contains all digits before calling Number constructor.
	assert( std::all_of(u_input.begin(), 
		u_input.end(), [](unsigned char c){ return std::isdigit(c);}) );
	Number a = Number(u_input);

	VERBOSE("Please enter a long integer value: ");
	std::cin >> u_input;

	assert( std::all_of(u_input.begin(), 
		u_input.end(), [](unsigned char c){ return std::isdigit(c);}) );

	Number b = Number(u_input);

	Number result = a + b ;

	VERBOSE(result);

	return 0;
}