import ast
from pathlib import Path

import marvin
from marvin import ai_fn


# marvin settings
if not marvin.settings.openai_api_key:
    marvin.settings.openai_api_key = "<YOUR_API_KEY>"
marvin.settings.llm_model = "gpt-4"
marvin.settings.llm_temperature = 0


@ai_fn
def fix_typos(locations: list[str]) -> list[str]:
    """Fix any typos in the list of locations, returning a corrected list"""


def load_data(path: Path) -> list[str]:
    with open(path) as f:
        text = f.read()
    fixed_text = text.replace("\n", " ").replace("'", '"')
    return ast.literal_eval(fixed_text)


if __name__ == "__main__":
    # load data
    positives = load_data(Path(__file__).parent / "positives.txt")
    negatives = load_data(Path(__file__).parent / "negatives.txt")

    # process data
    fixed_positives = fix_typos(positives)
    fixed_negatives = fix_typos(negatives)

    # check accuracy
    positive_correct = sum(1 for p in fixed_positives if p == "St. Albans")
    negative_correct = sum(1 for n in fixed_negatives if n != "St. Albans")
    accuracy = (positive_correct + negative_correct) / (len(positives) + len(negatives))
    print(f"Accuracy: {accuracy:.2%}")
