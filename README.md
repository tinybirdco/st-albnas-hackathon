# Usage
In order to use st_albans.py ensure that positives.txt and negatives.txt are included in the same directory as the .py file itself.

Upon running the file, a new text file "performance.txt" will be written to and includes the number of "St. Albans" detected in each file as well as the AUC

# Method
Fuzzywuzzy ratio, string length differences, starting character being "s" were all conditions included. Otherwise there was an issue with reading the text initially due to a missing comma or two that is rectified in the read_names_from_file function.

Thanks for a fun way to spend a couple of hours.