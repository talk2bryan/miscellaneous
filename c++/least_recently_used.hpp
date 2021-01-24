// least_recently_used.hpp


#ifndef __LEAST_RECENTLY_USED_H_
#define __LEAST_RECENTLY_USED_H_ 

class Data
{
private:
	std::string m_data;
	int m_age_bit;
public:
	Data(std::string& s, int age);
	~Data();
	void update_age(int age);
	std::string get_data() const;
	int get_age_bit() const;
	friend std::ostream& operator<<(std::ostream& os, const Data& d);
};



class Cache
{
private:
	int m_size;
	std::vector<Data> m_cache;
	
	static int m_access_count;
	const int m_cache_capacity;
	bool at_capacity() const;
	void display_cache() const;
public:
	Cache(int size);
	~Cache();
	// Add entry if already present. 
	// Replace the least used data if not.
	void search_entry_or_replace_lru(std::string& str);
};

#endif