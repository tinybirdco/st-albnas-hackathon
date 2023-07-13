const fs = require('fs');

/**
 * 1. Check if string starts with "S"
 * 2. Followed by either "t" or "l"
 * 3. Optionally followed by either 1 or 2 ".", or a ","
 * 4. Optionally followed by an empty space " "
 * 5. Followed by either "a" or "A"
 * 6. Followed by either "l" or "b"
 * 7. Where an additional "a" needs to exist somewhere
 */
const regex = /S[tl]?(\.{1,2}|,)? ?[aA][lb].*a/;

function convertToStAlbans(str) {
  return regex.test(str) ? "St. Albans" : String(str)
}

function readFile(name) {
  fs.readFile(name, 'utf8', (err, data) => {
    if (err) {
      console.error(err);
      return;
    }
    const array = data.split(",\n").filter(line => line.trim().length > 0).map(line => line.replace(/\[|\]|\'/g, '').trim());
    console.log(array.map(str => convertToStAlbans(str)));
  });
}

readFile("./negatives.txt");
readFile("./positives.txt");