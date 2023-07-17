import difflib

# Load the positives.txt and negatives.txt files
with open('positives.txt', 'r') as positives_file:
    positives = positives_file.readlines()
positives = [entry.strip() for entry in positives]

with open('negatives.txt', 'r') as negatives_file:
    negatives = negatives_file.readlines()
negatives = [entry.strip() for entry in negatives]

# Define the correct spelling
correct_spelling = "St. Albans"

# Track the count of true positives and true negatives
true_positives = 0
true_negatives = 0

# List to store the results
results = []

# Process positives.txt
results.append("-------------------------------------------- positives.txt --------------------------------------------")
converted_count_positives = 0
not_converted_count_positives = 0
for entry in positives:
    # Check if the entry is already correct
    if entry == correct_spelling:
        true_positives += 1
        converted_count_positives += 1
        results.append(f"Original: {entry}\nCorrected: {correct_spelling}\n-----")
        continue

    # Use difflib to find the closest match to the correct spelling
    closest_match = difflib.get_close_matches(entry, [correct_spelling], n=1, cutoff=0.6)

    if closest_match and closest_match[0] == correct_spelling:
        # If the closest match is the correct spelling, consider it a true positive
        true_positives += 1
        converted_count_positives += 1
        results.append(f"Original: {entry}\nCorrected: {closest_match[0]}\n-----")
    else:
        # If the closest match is not the correct spelling, consider it not converted
        not_converted_count_positives += 1
        results.append(f"Not Converted: {entry}\n-----")

results.append(f"------------------- Elements converted: {converted_count_positives} -------------------")
results.append(f"------------------- Elements not converted: {not_converted_count_positives} -------------------")
results.append("")
results.append("")


# Process negatives.txt
results.append("-------------------------------------------- negatives.txt --------------------------------------------")
results.append("")
not_converted_count_negatives = 0
for entry in negatives:
    # Use difflib to find the closest match to the correct spelling
    closest_match = difflib.get_close_matches(entry, [correct_spelling], n=1, cutoff=0.8)
    if not closest_match:
        true_negatives += 1
    else:
        not_converted_count_negatives += 1

        

results.append(f"----------------- Elements not converted: {true_negatives}----------------------")
results.append(f"----------------- Elements converted: {not_converted_count_negatives}----------------------")

# Calculate the accuracy score
all_possibilities = len(positives) + len(negatives)
print()
accuracy = (true_positives + true_negatives) / all_possibilities * 100
results.append("")
results.append("")
results.append(f"--- Accuracy: {accuracy}% ---")

# Write the results to a file
with open('tinybird_results.txt', 'w') as output_file:
    output_file.write('\n'.join(results))
