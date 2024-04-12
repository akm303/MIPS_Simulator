# Example Usage

### Start the Simulator

To run the simulator:

1. Open terminal/cmd in this directory `<path/to/documents>/Team 1 Machine Simulator Part II CSCI6461Section12Spring2024March32024/`
2. Run the following command in terminal/cmd:

```bash
java -jar MIPS_Simulator.jar
```

NOTE: This command may take up to ~10 seconds for the application window to launch.
Please be patient.

![image info](pictures/ProjectView_Default.png)

### Load the .bi file

Click on the IPL button in the bottom right. This will open a file selector dialogue window.
Select the example file found in the location: `src/main/java/group1/mips_simulator/simulator/programs/LoadStore.bi`

![image info](pictures/LoadBi.png)

### Confirm the file loaded properly

Press the `Load+` button and look and the MAR and MBR registers.
After the first press it should be:

```
MAR: 0000_0000_0001
MBR: 0000_0000_0000_0000
```

In the `Octal Input` field, type `7` (or the value `111` in binary). Press the button next to the `MAR` Memory Address
Register. Then press `Load`:

```
Octal Input: 7
Press MAR button
Press Load
```

Then notice the value in `MBR` memory buffer register should be: the value `0000_0000_0000_0011`
![image info](pictures/MAR7.png)

### Run the first instruction

Enter the number 16 in the Octal Input (memory address of the first instruction).
Press the button next to MAR, then press Load.

```
Octal Input: 16
Press MAR button
Press Load
```

The MAR and MBR should show the following data:

```
MAR: 0000_0000_1110
MBR: 0001_0000_1000_0111
```

![image info](pictures/MAR16.png)

The MBR (memory buffer register) shows the first real instruction of the `LoadStore.bi` file!

Load the value 16 into the PC (program counter), then press Step

```
Press PC button
Press Step
```

Notice the IR (Instruction Register) gets loaded with the same value that's currently in the MBR.
Notice the IXR1 gets new data! Therefore, the first instruction "X2 GETS 3" has executed properly

Notice all the things that have changed:

```
IR = c(PC) = 0001_0000_1000_0111
IX2 = 0000_0000_0000_0011
PC = PC + 1 = 0000_0000_1111
```

![image info](pictures/Step16.png)

## Run all the instructions

Now click the `Run` button. This will execute all the remaining instructions in the `LoadStore.bi` ROM (from the current
PC of 0000_0000_1111). This machine has `hlt` instructions implemented so the program will automatically halt.

```
Presss Run Button
```

Here is the final screen:

![image info](pictures/Finish.png)
