# Tinybird "St. Albnas" Hackathon

This is my completely-over-engineered and worse-than-regex-performing solution to the St. Albans hackathon!

To classify, we use a k=2 k-nearest-neighbours algorithm and for each word, check if the nearest neighbor is a hit or miss. If it is a hit, we predict the word to be a hit, and vice versa. This is stupid and results in an overall accuracy of 95%.

To run, just check out the `albans.ipynb` notebook. I used Python 3.11.4 to run it locally.
