# INSTRUCTIONS

To reset our simulator:
---
    1. Click the IPL button on the Front Panel
    2. You may select a `.bi` file to load, or Esc out of the file selection


To run a program:
---
    1. Select the IPL Button on the Front Panel
    2. Navigate to this project's directory 
        (Typically either `~/Downloads/_Submission_Part2` or `~/Documents/_Submission_Part2` 
        depending on where you download this ziped project to)
    3. Choose a `.bi` file to load from our project's `./Programs` folder
    4. Enter the Key (provided below) into the `Octal Input` field
        + Each key is provided in this instruction document
        + The key is the starting address for that program
    5. Load the Key into the PC register
    6. Click Run, then follow the on-screen instructions

> To see an example run, please see `Program1_ExampleRun.mov` <br> 
> located in `"./Documents/Example Runs/"`.
> Further instructions for specific programs below:

`Program2.bi`
---
    Key: 3000 

    1. Select `Program2.bi` from within the project `Programs` directory
    2. User will be asked to input sentences. 
        - Please enter all sentences at once 
        - you can copy and paste a paragraph into the console keyboard
    3. User will be asked to enter a word.
        - The Program will return either the sentence number and word number for the first match, 
          or will tell user there were no matches.
This is assembly Program 2, as per submission requirements.

`Program1.bi`
---
    Key: 2000 

    1. Select `Program1.bi` from within the project `Programs` directory
    2. User will be asked to input 20x numbers within a range. 
        Please enter a number in that range.
    3. User will be asked to input one more number.
        The Program will return the closest of the previous 20 numbers
This is the normal assembly Program 1, as per submission requirements.

`short_Program1.bi`
---
    Key: 2000

    1. Select `Program1_short.bi` from within the project `Programs` directory
    2. User will be asked to input 3x numbers within a range.
        Please enter a number in that range.
    3. User will be asked to input one more number.
        The Program will return the closest of the previous 3 numbers
This is a short version of assembly Program 1, and requires fewer inputs than the original.

Please note, due to Java's handling of all its integers as signed, we actually recommend entering numbers
in the range 0 to 32767, rather than up to 65536. Numbers larger than 2^15 are converted to signed negative,
and your selected check number may be closer to one of these negative conversions. If this is the case, our
printer print a 00000.
