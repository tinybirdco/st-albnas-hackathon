/*
A C++ solution for Tinybird St Albnas solution using
Levensthein distance algorithm
*/

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <exception>
#include <algorithm>

#include "levensthein.hpp"
#include "preprocessing.hpp"

std::vector<std::string> read_file(const std::string input_file) {

    // Open input file
    std::ifstream input(input_file);

    // Check if input file has been opened
    if (!input.is_open()) {
        throw std::runtime_error("Error opening the input file");
    }

    // Read line by line and append to vector
    std::string line;
    std::vector<std::string> lines;
    while(getline(input, line)) {

        // Apply preprocessing to current line
        filter_characters(line);

        lines.push_back(line);
    }

    // Return lines
    return lines;
}

int show_results(const std::vector<std::string> &data,
                 const std::string &pattern) {

    unsigned int counter = 0;
    for (auto it: data) {
        std::cout << "---------------" << std::endl;
        std::cout << "Data:" << it << std::endl;
        const unsigned int distance = levensthein_algorithm(it,
                                                            pattern);
        std::cout << "Distance: " << distance << std::endl;
        if (distance <= LEVENSTHEIN_CUTOFF) {
            std::cout << "OK: " << pattern << std::endl;
            counter++;
        } else {
            std::cout << "NOK!" << std::endl;
        }
    }

    return counter;
}

int main(int argc, char *argv[]) {

    // Check number of arguments
    if (argc != 4) {
        std::cout << "Please use the command: main <pattern> <positive_file> <negative_file>" << std::endl;
        return 1;
    }

    std::string pattern = argv[1];

    // // Show results from positive file
    std::cout << "POSITIVES" << std::endl;
    std::vector<std::string> positive_lines = read_file(argv[2]);
    const unsigned int true_positives = show_results(positive_lines, pattern);

    // Show results from negative file
    std::cout << "---------------" << std::endl;
    std::cout << "NEGATIVES" << std::endl;
    std::vector<std::string> negative_lines = read_file(argv[3]);
    unsigned int true_negatives = show_results(negative_lines, pattern);
    true_negatives = negative_lines.size() - true_negatives;

    // Compute the accuracy
    const unsigned int all_possibilites = positive_lines.size() + negative_lines.size();

    const float acc = (true_positives + true_negatives) / static_cast<float>(all_possibilites);

    std::cout << "+++++++++++++++++" << std::endl;
    std::cout << "All posibilities = " << all_possibilites << std::endl;
    std::cout << "+++++++++++++++++" << std::endl;
    std::cout << "True positives = " << true_positives << std::endl;
    std::cout << "+++++++++++++++++" << std::endl;
    std::cout << "True negatives = " << true_negatives << std::endl;
    std::cout << "+++++++++++++++++" << std::endl;
    std::cout << "ACC = " << acc << std::endl;
    std::cout << "+++++++++++++++++" << std::endl;

    return 0;
}