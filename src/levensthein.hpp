#ifndef LEVENSTHEIN_HPP
#define LEVENSTHEIN_HPP

#include <string>

#define LEVENSTHEIN_CUTOFF 3

int levensthein_algorithm(const std::string &text,
                          const std::string &pattern);

#endif