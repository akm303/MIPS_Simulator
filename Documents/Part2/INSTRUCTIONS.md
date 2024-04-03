# INSTRUCTIONS

To reset our simulator:
---
    1. Click the IPL button on the Front Panel
    2. You may select a `.bi` file to load, or Esc out of the file selection


To run a program:
---
    1. Select the IPL Button on the Front Panel
    2. Navigate to this project's directory
    3. Choose a `.bi` file to load from our project's `Programs` folder
    4. Enter the Key (provided below) into the `Octal Input` field
        + Each key is provided in this instruction document
        + The key is the starting address for that program
    5. Load the Key into the PC register
    6. Click Run, then follow the on-screen instructions

> To see an example run, please see `Program1_ExampleRun.mov` <br> 
> located in `"<path to project>/Documents/Example Runs/"`.
> Further instructions for specific programs below:


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


