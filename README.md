# Tinybird "St. Albnas" Hackathon
*Clean some data, win some swag*

## The Problem
The "St. Albnas" problem has become quite a meme on the internet. It seems to have originated [here](https://www.linkedin.com/posts/aesroka_management-we-have-great-datasets-the-datasets-activity-7072180991229874176-p8PX/) (h/t to Adam Sroka).

![image](/img/st-albnas.webp)

The meme captures a well known issue with data quality: Free-text fields aren't consistent!
## The C++ Solution

The implementation is based into  `Levensthein distance` algorithm ([https://en.wikipedia.org/wiki/Levenshtein_distance](https://en.wikipedia.org/wiki/Levenshtein_distance)) measuring the difference of two sequences using minimum number of single-chracters insertions, deletions or substitutions to transform a word into the another one.

This C++ implementation uses this algorithm following an iterative approach with a full matrix according with [Wagner-Fischer algorithm](https://dl.acm.org/doi/10.1145/321796.321811).

### Execution

- To compile code:

```
cd build/
g++ ../src/main.cpp ../src/preprocessing.cpp ../src/levensthein.cpp -o main
```

- To execute the program:

```
./main "St. Albans" ../data/positives.txt ../data/negatives.txt
```
