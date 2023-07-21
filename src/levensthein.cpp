#include "levensthein.hpp"

int levensthein_algorithm(const std::string &text,
                          const std::string &pattern) {
    // Initialize the matrix of distances.
    int distance[text.length() + 1][pattern.length() + 1];
    for (int i = 0; i <= text.length() + 1; i++) {
        for (int j = 0; j <= pattern.length() + 1; j++) {
        if (i == 0 || j == 0) {
            distance[i][j] = i + j;
        } else if (text[i - 1] == pattern[j - 1]) {
            distance[i][j] = distance[i - 1][j - 1];
        } else {
            const int min_aux = std::min(distance[i - 1][j] + 1,
                                      distance[i][j - 1] + 1);
            distance[i][j] = std::min(min_aux,
                                      distance[i - 1][j - 1] + 1);
        }
        }
    }

    return distance[text.length()][pattern.length()];
}