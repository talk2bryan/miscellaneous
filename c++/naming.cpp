#include <iostream>
#include "general_utility.hpp"
using namespace std;


int main(int argc, char const *argv[])
{
	cout << "FILENAME: "<< __FILE__ << endl;
	cout <<"LINE NUMBER:" << __LINE__ << endl;
	cout <<"Compiler version:" << __cplusplus << endl;
	cout <<"FUNCTION:" << __func__ << endl;
	#ifdef DEBUG
	VERBOSE("hello");
	VERBOSETP("x: ",5);
	#endif

	return 0;
}
