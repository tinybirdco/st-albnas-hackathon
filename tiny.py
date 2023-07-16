import difflib
import ast

def correct_spellings(input_filename, correct_spelling, negatives_filename):
    # Reading files
    with open(input_filename, 'r') as f:
        positives = ast.literal_eval(f.read())
    with open(negatives_filename, 'r') as f:
        negatives = ast.literal_eval(f.read())

    corrected = []

    # Terminal paiting
    print("Positives:")
    print("----------")
    for word in positives:
        matches = difflib.get_close_matches(word, [correct_spelling], n=1, cutoff=0.6)
        if matches:
            print(f"Original: {word} \t Corrected: {matches[0]}")
            corrected.append(matches[0])
        else:
            print(f"Original: {word} \t Corrected: {word}")
            corrected.append(word)

    print("\nNegatives:")
    print("----------")
    for word in negatives:
        if word in corrected:
            print(f"Word '{word}' \t Corrected: true")
        else:
            print(f"Word '{word}' \t Corrected: false")

correct_spellings('positives.txt', 'St. Albans', 'negatives.txt')
