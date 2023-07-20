## Foreword

THANK YOU GUYS!!! I follow you on Twitter/LinkedIn and I am amazed by what you guys are doing. I'm a product manager who started programming as many others... and thanks to your challenge I've had the time and motivation to get back to the IDE... it has made me feel soooo proud of myself (regardless the result, of course) :)))

## Solution

I've needed to use chatGPT as you suggested to find a collection of distance calculation algorithms, and I've tried several until I've found the one it fits with the given datasets. I guess there are other algorithms that may be better with larger datasets (in particular Jaro-Winkler is a good candidate, as it considers several different aspects that can be tweaked) but the one that has worked better for me has been Damerau-Levenshtein.

## How to run

Compile the file `StringDistanceCalculator.java`, it takes three parameters, the first is the string you will be comparing to (the reference string, in this case `St.Albans`). The second gets the name of the file which contains the dataset in the given format (it must be in the same folder as the generated java file. The third param is the threshold we will be using to discern whether the calculate similarity scoring (this is, the Damerau-Levenshtein distance) is enoght to be considered positive or negative. To make the given datasets work, it has to be set to `3`.

Thus, after compiling, you have to run the following commands:

`java StringDistanceCalculator.java "St. Albans" positives.txt 3` to check the positives

and

`java StringDistanceCalculator.java "St. Albans" negatives.txt 3` to check the negatives

For the positives.txt file, you will get the following result:

```
Input: St. Albans output: St. Albans positive with score: 0.0
Input: St.Albans output: St. Albans positive with score: 1.0
Input: St Albans output: St. Albans positive with score: 1.0
Input: St.Ablans output: St. Albans positive with score: 2.0
Input: St.albans output: St. Albans positive with score: 2.0
Input: St. Alans output: St. Albans positive with score: 1.0
Input: S. Albans output: St. Albans positive with score: 1.0
Input: St.. Albans output: St. Albans positive with score: 1.0
Input: S. Albnas output: St. Albans positive with score: 2.0
Input: St. Albnas output: St. Albans positive with score: 1.0
Input: St.Al bans output: St. Albans positive with score: 2.0
Input: St.Algans output: St. Albans positive with score: 2.0
Input: Sl.Albans output: St. Albans positive with score: 2.0
Input: St. Allbans output: St. Albans positive with score: 1.0
Input: St, Albans output: St. Albans positive with score: 1.0
Input: St. Alban output: St. Albans positive with score: 1.0
Input: St. Alban output: St. Albans positive with score: 1.0
Processed 17 inputs resulting in 17 positives and 0 negatives.
```

For the negatives.txt file, you will get the following result

```
Input: St. Paul output: St. Paul negative with score: 5.0
Input: Albans output: Albans negative with score: 4.0
Input: Albna output: Albna negative with score: 6.0
Input: St. Alberts output: St. Alberts negative with score: 3.0
Input: Alberta output: Alberta negative with score: 8.0
Input: St. Johnsbury, VT output: St. Johnsbury, VT negative with score: 12.0
Input: Alban St. output: Alban St. negative with score: 8.0
Input: State of Alban output: State of Alban negative with score: 7.0
Input: Albany output: Albany negative with score: 5.0
Input: Albania output: Albania negative with score: 6.0
Input: Ban output: Ban negative with score: 8.0
Input: Alfred output: Alfred negative with score: 8.0
Input: St. Alfred output: St. Alfred negative with score: 4.0
Input: Saint output: Saint negative with score: 8.0
Input: St output: St negative with score: 8.0
Input: Alps output: Alps negative with score: 7.0
Input: Alloy output: Alloy negative with score: 8.0
Input: Alban Street output: Alban Street negative with score: 11.0
Input: AL output: AL negative with score: 9.0
Input: Alabama output: Alabama negative with score: 7.0
Input: Alexa output: Alexa negative with score: 8.0
Input: Alfonso Soriano output: Alfonso Soriano negative with score: 12.0
Input: Snabla output: Snabla negative with score: 7.0
Input: Peoria output: Peoria negative with score: 9.0
Input: I ran out of ideas output: I ran out of ideas negative with score: 15.0
Processed 25 inputs resulting in 0 positives and 25 negatives.
```

