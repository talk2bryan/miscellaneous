#ifndef _GENERAL_UTILITY_H_
#define _GENERAL_UTILITY_H_
#define VERBOSE_HEADER "[VERBOSE] " << __FILE__ << ":" << __LINE__ << ") "
#define VERBOSE(x) std::cout<< VERBOSE_HEADER << x <<std::endl;
#define VERBOSETP(x,y) std::cout<< VERBOSE_HEADER << x << y <<std::endl;
#endif