# Advanced-Database

Consider the two relations T1 and T2, given the test data, find match-able tuples in T1 and T2 having the same department value. 
This is to be done by building a bitmap index on the attribute Department, one for each relation, T1 and T2. Also, in order to 
reduce the space requirement for storing the two indexes, compression is to be implemented.

Report the time for performing the join but ignore the time to write the join results to the disk.

Use the indexes to find the answer to the following query (after the join is done):  Given a particular Department, 
how many records were generated in the join result?  

