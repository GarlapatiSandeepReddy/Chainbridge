Write a program in java which reads data from the attached packed-decimal formatted file and writes it out to a new file which should be the 
same data but represented as a pipe delimited text formated file.  Use the attached record layout file to identify the fields in the records of the packed decimal file
and as the source for the header line for the new output file.

for example:

if the input from the packed decimal file is: 1222345678010000032.3K13 
with a record layout of:
ID 1
SN 9
FS 1
RS 1
Income 10
TUNO 1
NTUF 1

then the ouput to the new file would be:
ID|SN|FS|RS|Income|TUNO|NTUF|
1|222345678|0|1|-32.3|1|3|

Additional Guidance:

1.) read in each record from the attached data file
2.) for each field in the record (which you can identify from the attached record layout file) determine if it is a packed decimal field or text field.
3.) for text based fields, you can just write them out to the new file.
4.) for packed decimal fields, you will need to convert them to the correct text based representation before writing them out.
5.) the new file should have one record per line
6.) the delimiter you should use will be the pipe "|" character.
7.) the code should be written using Java
8.) packed decimals will have a field length of 10 which can be used as a basis for identifying them
9.) the sign of the packed decimal can be determined by the right most character
10.) Positive numbers are identified by the following characters "{,A,B,C,D,E,F,G,H,I"
11.) Negative numbers are itdentied by the following characters "},J,K,L,M,N,O,P,Q,R"
12.) Keep in mind code commenting and the clarity of the written code in addition to the code producing the correct results.

If you need clarification or additional information, you can contact Jaime Copenhaver at jcopenha@chainbridge.com



