from sklearn.metrics import accuracy_score
import re
import ast
from pyjarowinkler import distance

THE_WORD = 'St. Albans'
WORDS = []


def load_data():
    for filename, label in [('positives.txt', 'T'), ('negatives.txt', 'F')]:
        with open(filename, 'r') as f:
            words = ast.literal_eval(f.read())
            WORDS.extend((word, label) for word in words)


def clean_string(s):
    return re.sub(r'[^a-z.]', '', s.lower())


def reclassify(word):
    dist = distance.get_jaro_distance(clean_string(
        word), clean_string(THE_WORD), winkler=True, scaling=0.1)
    return ('T' if dist >= .9 else 'F', dist)


def fix_it(word):
    return THE_WORD if reclassify(word)[0] == 'T' else word


if __name__ == "__main__":
    load_data()

    classification_result = [reclassify(word[0]) for word in WORDS]

    original_classifications = [word[1] for word in WORDS]
    new_classifications = [pair[0] for pair in classification_result]

    print('\nResults:')
    print("{:<23} {:<23} {:<23} {:<23}".format(
        "word", "[original, calculate]", "jaro_winkler_distance", "fixed"))

    for i, (word, match) in enumerate(WORDS):
        fixed_word = THE_WORD if new_classifications[i] == 'T' else word
        print("{:<23} {:<23} {:<23} {:<23}".format(word, f"[{match}, {new_classifications[i]}]", classification_result[i][1], fixed_word))

    accuracy = accuracy_score(original_classifications, new_classifications)
    print('\nAccuracy:', accuracy)