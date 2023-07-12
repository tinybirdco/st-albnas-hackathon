# Tinybird "St. Albnas" Hackathon
*Clean some data, win some swag*

## The Problem
The "St. Albnas" problem has become quite a meme on the internet. It seems to have originated [here](https://www.linkedin.com/posts/aesroka_management-we-have-great-datasets-the-datasets-activity-7072180991229874176-p8PX/) (h/t to Adam Sroka).

![image](/img/st-albnas.webp)

The meme captures a well known issue with data quality: Free-text fields aren't consistent!

## The Solution

This solution uses [Marvin](https://www.askmarvin.ai) to write an AI Function that cleans location data. This is the entire function:

```python
@ai_fn
def fix_typos(locations: list[str]) -> list[str]:
    """Fix any typos in the list of locations, returning a corrected list"""
```

After loading the data and calling this function, the resulting dataset is 100% clean. 

### Running the solution

1. Install Marvin: `pip install marvin`
2. Set your OpenAI API key as `MARVIN_OPENAI_API_KEY` or edit `marvin-solution.py` to add your API key
3. run `python marvin-solution.py`
4. ???
5. profit 

## The Goal
Write some code (SQL, GPT, regex, whatever you want) that will accurately, precisely, and consistently converge all of the elements in the [`positives.txt`](/positives.txt) file to the correct spelling: `St. Albans`.

The code should repeatably converge as many of the entries as possible into the correct spelling while also avoiding false positives (e.g. you can't just replace the entire string with `St. Albans` everytime by brute force). To ensure that your code avoids false positives, we've included a [`negatives.txt`](/negatives.txt) file. Your code should avoid converting any of these to `St. Albans`.

## The Rules
- To submit an entry, clone this repo, checkout a new branch, and submit a pull request.
- Use whatever language you want, whatever libraries you want, whatever. But it must be code and it must compile. (You can use GPT or any other LLM, but simple text GPT prompts will not be accepted!)
- Any entry that converts the elements by brute force (i.e. by individual string matching) will be rejected.
- Valid entries must include:
  - All necessary code to do the data cleansing
  - A README explaining how to run the code over the included `.txt` files.
  - A demonstration of the results you achieved (image, file, etc.) that should be repeatable
  - Optional: Include your Twitter handle in your GitHub profile if you'd like to be mentioned on Twitter.
- Submissions are due by Friday, July 21st at 5 PM GMT
- Tinybird employees may not participate

## Scoring
Aim for [Accuracy (ACC)](https://en.wikipedia.org/wiki/Accuracy_and_precision#In_binary_classification), measured as (true positives + true negatives) / (all possibilities).

For example, a submission that accurately converts 15 out of the 17 elements in `positives.txt` and accurately ignores 24 out of the 25 elements in `negatives.txt` would score (15 + 24) / (17 + 25) = 92.8%

Submissions that correctly convert all 17 of the elements in `positives.txt` and none of the 25 elements in `negatives.txt` to `St. Albans` would score a 100%.

## Participation Award!
Let's be honest, this problem isn't *that* hard, but it should be fun! All you need to do is submit a working attempt, and you'll get $20 off at [The Tinyshop](https://shop.tinybird.co). That's enough for a t-shirt, a coffee mug, or 2 sticker sheets!

Also, as an incentive to score well, we'll tweet the final leaderboard when this ends :).

#### Here's what you have to do to get the participation award:
- Submit a valid entry (see above) 
- Star this repo
- Follow Tinybird on Twitter [@tinybirdco](https://twitter.com/tinybirdco) and/or LinkedIn
- Share your submission on Twitter/LinkedIn using #stalbnashackathon (tag us too!)

## Need help?
Join our [Community Slack](https://www.tinybird.co/join-our-slack-community)!

