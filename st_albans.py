from fuzzywuzzy import fuzz
from fuzzywuzzy import process as fuzz_process
import re
import ast

# Specify the input file names
positives_file = "positives.txt"
negatives_file = "negatives.txt"

target_string = "St. Albans"


def correct_names(names):
    corrected_names = []
    for name in names:
        name_cleaned = re.sub(r'[^a-zA-Z0-9]', '', name.lower())
        if name_cleaned.startswith('s'):
            ratio = fuzz.ratio(name_cleaned, target_string.lower())
            diff = abs(len(name_cleaned) - len(target_string))
            if ratio >= 70:
                if diff <= 3:
                    corrected_names.append(target_string)
                else:
                    corrected_names.append(name)
            else:
                corrected_names.append(name)
        else:
            corrected_names.append(name)

    return corrected_names


def count_occurrences(names):
    count = 0
    for name in names:
        if name == target_string:
            count += 1
    return count


def read_names_from_file(filename):
    with open(filename, 'r') as file:
        content = file.read()
        # Extract names using regular expressions
        names = re.findall(r"'([^']+)'", content)
        return names

# Read names from the positive and negative files
positives = read_names_from_file(positives_file)
negatives = read_names_from_file(negatives_file)

# Correct the names in the positive and negative files
corrected_positives = correct_names(positives)
corrected_negatives = correct_names(negatives)

# Count occurrences of "St. Albans" in the positive and negative files
positives_count = count_occurrences(corrected_positives)
negatives_count = count_occurrences(corrected_negatives)

# AUC calculation from Readme
AUC = (positives_count + (len(negatives) - negatives_count))/(len(negatives) + len(positives))

print("Number of occurrences of 'St. Albans' in positive file:", positives_count)
print("Number of occurrences of 'St. Albans' in negative file:", negatives_count)
print("AUC:", AUC)