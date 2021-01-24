//testVector.cpp
#include <iostream>
#include <vector>

/////////////
class point
{
	double x;
	double y;
public:
	point(){}
	point(double xx, double yy) : x(xx), y(yy) {}
	friend std::ostream& operator<<(std::ostream& os, point& p){return os<<p.x<<", "<<p.y;}
	friend std::istream& operator>>(std::istream& is, point& p){return is>>p.x>>p.y;}
};

////////////

std::vector<point> my_vec();

int main(int argc, char const *argv[])
{
	int x;
	std::vector<point> v = my_vec();
	std::cout<< v.at(0) <<std::endl;
	std::cout<< v.at(1) <<std::endl;

	std::cin>>x;

	std::cout<< x;

	return 0;
}
std::vector<point> my_vec()
{
	std::vector<point> a_vec(10);
	a_vec.at(0) = point(-1,-1);
	a_vec.at(1) = point(6,8);

	return a_vec;
}
