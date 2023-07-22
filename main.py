import re


def converge_to_st_albans(text):
    patterns = [
        r"St\.\s*Albans",
        r"St\.?Albans",
        r"S\.\s*Albans",
        r"St\.\s*Ablans",
        r"St\.\s*Alans",
        r"St\.?Albnas",
        r"St\.\s*Al\sbans",
        r"Sl\.?Albans",
        r"St\.\s*Allbans",
        r"St,?\s*Albans",
        r"St\.? Alban",
    ]

    word_boundary = r"\b"

    full_pattern = word_boundary + "|".join(patterns) + word_boundary

    result = re.sub(full_pattern, "St. Albans", text, flags=re.IGNORECASE)
    return result


with open("positives.txt", "r") as positive_file:
    positive_content = positive_file.read()

converged_text = converge_to_st_albans(positive_content)

print(converged_text)
