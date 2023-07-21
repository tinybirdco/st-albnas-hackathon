#include <boost/algorithm/string.hpp>
#include <boost/algorithm/string/trim.hpp>

#include "preprocessing.hpp"

void filter_characters(std::string& target) {

    // Remove all desired characters from target
    for (auto it: to_filter_characters) {
        boost::erase_all(target, it);
    }

    // Trim target
    boost::algorithm::trim(target);
}