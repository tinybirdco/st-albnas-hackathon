#ifndef PREPROCESSING_HPP
#define PREPROCESSING_HPP

#include <string>
#include <vector>

const std::vector<std::string> to_filter_characters {"[", "]", "'", ","};

void filter_characters(std::string& target);

#endif