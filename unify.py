import re
from fuzzywuzzy import fuzz

def parse_and_rename_names(file_name, target_name, threshold=80):
    with open(file_name, 'r') as file:
        content = file.read()

    # Remove the leading and trailing brackets
    content = content[1:-1]

    names = [line.strip().strip('\'"') for line in content.split('\n')]

    corrected_names = []

    for name in names:
        name = name.replace("',", '')

        if fuzz.ratio(name.lower(), target_name.lower()) > threshold:
            corrected_names.append(target_name)
        else:
            corrected_names.append(name)
    print(corrected_names)


if __name__ == '__main__':
    parse_and_rename_names('positives.txt', 'St. Albans', 77)

    parse_and_rename_names('negatives.txt', 'St. Albans', 77)
