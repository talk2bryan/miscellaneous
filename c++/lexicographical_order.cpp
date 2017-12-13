// Lexicographical_Order.cpp
// This answers the question:
// 		Find next greater number with same set of digits.
// Steps:
		// 1. identify the starting index for the rightmost descending suffix(d)
		// 2. if this index is 0, then it needs no permutation.
		// 3. swap rightmost value in prefix with the lowest value in the suffix
		// 	  that is greater than this value.
		// 4. rearrange the suffix in ascending order.
#include <iostream>
#include <string>
#include <algorithm>


void swap(std::string& str, int first, int second){
	char temp = str[first];
	str[first] = str[second];
	str[second] = temp;
}
int get_lex_order(int number){
	int result = number; // If it is not possible, return the number

	std::string data = std::to_string(number);
	// Possiblilities:
	int index = data.length() - 1;

	if(index <= 1) // Number has to be at least 3 digits long
		return result;
	while(index > 0 ){
		if( (data[index-1] - '0') - (data[index] - '0') < 0 )
			break;
		index--;
	}
	index -=  1; // Set index at proper index.

	if (index <= 0)
	{
		// In non-ascending order, cannot perform operation.
		return number;
	}
	else{
		int right_part_min = index+1;
		int i = right_part_min;
		while(i < data.length()){
			if(data[i] < data[right_part_min] )
				right_part_min = i;
			i++;
		}
		swap(data, index, right_part_min);
		// Now, sort right side...
		auto start = data.begin() + index+1;
		std::sort(start, data.end());
		result = stoi(data);

	}

	return result;
}
int main(int argc, char const *argv[])
{
	// Input prompt
	int input;

	do{
		std::cout << "Please enter an integer value: " << std::endl;
		std::cin >> input;

		// Output
		if (input == 0)
		{
			std::cout<<"Fin."<<std::endl;
		}else{
			std::cout << "The next greater number with same set of digits is: " 
				<< get_lex_order(input) << std::endl;
		}
	}while(input != 0);

	

	return 0;
}
