# Tinybird "St. Albnas" Hackathon
*Clean some data, win some swag*

## The Problem
The "St. Albnas" problem has become quite a meme on the internet. It seems to have originated [here](https://www.linkedin.com/posts/aesroka_management-we-have-great-datasets-the-datasets-activity-7072180991229874176-p8PX/) (h/t to Adam Sroka).

The meme captures a well known issue with data quality: Free-text fields aren't consistent!

## The Goal
Write some code (SQL, GPT, regex, whatever you want) that will accurately, precisely, and consistently converge all of the elements in the [`positives.txt`](/positives.txt) file to the correct spelling: `St. Albans`.

The code should repeatably converge as many of the entries as possible into the correct spelling while also avoiding false positives (e.g. you can't just replace the entire string with `St. Albans` everytime by brute force). To ensure that your code avoids false positives, we've included a [`negatives.txt`](/negatives.txt) file. Your code should avoid converting any of these to `St. Albans`.

## SOLUTION
- My solution uses the library difflib. 
- It calculates the % accuracy and the results --> see [`tinybird_results.txt`](/tinybird_results.txt) 
- twitter --> [@irene_aguerri](https://twitter.com/irene_aguerri) 




:sparkles: Thanks for the swaggggggggggggggggg :sparkling_heart: 
