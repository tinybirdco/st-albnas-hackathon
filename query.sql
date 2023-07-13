-- author: @polyrand
-- Instead of using CTEs, I'll create multiple views to make it easier to follow the logic


-- Files are not valid JSON, so I read them as CSV and then trim the extra characters
-- See also: https://duckdbsnippets.com/snippets/11
create view neg0 as select * from read_csv('negatives.txt', header=False, delim='\0', columns={c: 'VARCHAR'});
create view pos0 as select * from read_csv('positives.txt', header=False, delim='\0', columns={c: 'VARCHAR'});

-- clean strings
create view neg as select trim(c, ' [],'' ') as c from neg0;
create view pos as select trim(c, ' [],'' ') as c from pos0;

-- Store objective string
create view objective as select 'St. Albans' as c;

-- Use damerau_levenshtein instead of levenshtein, because it takes into account
-- transpositions, otherwise both 'St. Alberts' (negative) and 'S. Albnas'
-- (positive) have an edit distance of 3

create view neg_distances as
select damerau_levenshtein(neg.c, objective.c) as dist
       , neg.c as neg
       , objective.c as objective 
from neg, objective order by dist;


create view pos_distances as
select damerau_levenshtein(pos.c, objective.c) as dist
       , pos.c as pos
       , objective.c as objective 
from pos, objective order by dist;


-- Get the maximum distance of the positive strings, everything below this
-- distance will be considered a match and converted to the objective string.
-- Everything above this distance will be left as is
create view threshold as select max(dist) as value from pos_distances;


--Ccreate a function to correct the name based on the threshold
CREATE MACRO correct_name(t) AS (
    CASE
    WHEN damerau_levenshtein(t, (SELECT c FROM objective)) <= (SELECT value FROM threshold) THEN (SELECT c FROM objective)
    ELSE t
    END
);


CREATE OR REPLACE VIEW final_results AS
select correct_name(neg.c) as converted, neg.c as original, 'negative' as label from neg
UNION
select correct_name(pos.c) as converted, pos.c as original, 'positive' as label from pos;

COPY (SELECT * FROM final_results) TO 'output.csv' (HEADER TRUE);


