// Least_Recently_Used.cpp
// Author: Bryan Wodi <talk2kamp@gmail.com>

/*
	C++ implementation of LRU cache replacement policy
*/


// Need a cache class 
// 	that hold an array of Data values
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <cassert>
#include <algorithm>

#include "least_recently_used.hpp"


// Data Class Method Definitions:
Data::Data(std::string& s, int age) : m_data(s), m_age_bit(age) {;}
Data::~Data(){;}
inline void Data::update_age(int age) {m_age_bit = age;}
inline std::string Data::get_data() const { return m_data; }
inline int Data::get_age_bit() const { return m_age_bit; }
inline std::ostream& operator<<(std::ostream& os, const Data& d){
	return os << d.m_data << "(" << d.m_age_bit << ")";
}

// Cache Class Method Definitions:

// Private: 
int Cache::m_access_count = 0; // Static variable definition.
inline bool Cache::at_capacity() const { return m_size == m_cache_capacity; }

void Cache::display_cache() const {
	for (auto i = m_cache.begin(); i != m_cache.end(); ++i)
	{
		std::cout << *i << " ";
	}
	std::cout << std::endl;
}

// Public:
Cache::Cache(int size): m_cache_capacity(size) { m_size = 0; }
Cache::~Cache() {;}

void Cache::search_entry_or_replace_lru(std::string& str){
	auto it = std::find_if(m_cache.begin(), m_cache.end(),
		[&str](const Data& d)
		{ return d.get_data() == str;}
		);

	if (! at_capacity()) {
		if (it == m_cache.end()) {
			m_cache.push_back(Data(str, m_access_count++));
			m_size++;
		}
		else{
			it->update_age(m_access_count++);
		}
	}
	else{

		if (it == m_cache.end()) {
			auto min_pos = m_cache.begin();
			for(auto i = m_cache.begin(); i != m_cache.end(); ++i){
				if(i->get_age_bit() < min_pos->get_age_bit())
					min_pos = i;
			}
			*min_pos = Data(str, m_access_count++);
		}
		else{
			it->update_age(m_access_count++);
		}
	}

	display_cache();
}



int main(int argc, char const *argv[])
{
	int cache_size;

	int num_searches;
	std::string user_input;

	std::cout << "How big should cache be?"<< std::endl;
	std::cin >> cache_size;
	Cache my_cache(cache_size);


	std::cout << "How many items do you intend to search for?"<< std::endl;
	std::cin >> num_searches;

	std::cout << "Enter space-delimited strings (e.g) A B C D for 4 searches"<< std::endl;
	for (int i = 0; i < num_searches; ++i)
	{
		std::cin >> user_input;
		my_cache.search_entry_or_replace_lru(user_input);
	}
	
	return 0;
}