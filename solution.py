import json

import pandas as pd
from jellyfish import jaro_winkler_similarity


def load_data(file_name):
    with open(file_name) as f:
        words = list(set(json.load(f)))
    return words


def calculate_accuracy():
    return round(
        (len(converted_positives) + len(ignored_negatives))
        / (len(positives) + len(negatives))
        * 100,
        2,
    )


def display_metrics(metric, metric_name, sort_ascending=True):
    columns = ["Word", "Similarity Score"]

    print(f"\n[x] {metric_name}:")
    df = pd.DataFrame(list(metric.items()), columns=columns).sort_values(
        "Similarity Score", ascending=sort_ascending
    )
    df.reset_index(inplace=True, drop=True)
    df.index += 1
    print(df.to_markdown(tablefmt="rounded_grid"))


if __name__ == "__main__":
    target = "St. Albans"
    threshold = 0.85

    positives = load_data("positives.txt")
    negatives = load_data("negatives.txt")
    print(f"\n[x] {len(positives)=}")
    print(f"[x] {len(negatives)=}")
    print(f"[x] {threshold=}")

    converted_positives, ignored_positives = {}, {}
    converted_negatives, ignored_negatives = {}, {}

    for word in positives:
        similarity_score = round(jaro_winkler_similarity(word, target), 2)
        if similarity_score >= threshold:
            converted_positives[word] = similarity_score
        else:
            ignored_positives[word] = similarity_score

    for word in negatives:
        similarity_score = round(jaro_winkler_similarity(word, target), 2)
        if similarity_score < threshold:
            ignored_negatives[word] = similarity_score
        else:
            converted_negatives[word] = similarity_score

    print(f"\n[x] Positive Words: Words to converge to {target=}")
    display_metrics(converted_positives, "Converted Positives", sort_ascending=False)
    display_metrics(ignored_positives, "Ignored Positives", sort_ascending=False)
    print(f"\n[x] Negative Words: Words to not converge to {target=}")
    display_metrics(ignored_negatives, "Ignored Negatives")
    display_metrics(converted_negatives, "Converted Negatives")

    print(f"\nAccuracy: {calculate_accuracy()}%")
