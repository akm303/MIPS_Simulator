# MIPS Simulator

This project is part 1 of a MIPS Simulator.

## Running the Simulator

### Mac

To run the simulator, simply run the following java command:

NOTE: This command may take up to ~10 seconds for the application window to launch.
Please be patient.

```bash
java --module-path out/artifacts/MIPS_Simulator_jar/ \
     --add-modules javafx.controls,javafx.fxml,javafx.graphics \
     -jar out/artifacts/MIPS_Simulator_jar/MIPS_Simulator.jar
```

#### Windows:

When testing on Windows 11 we ran into some issues with compatability between Java FX and the default Orical JDK installed.

Please download these items directly from their respetive hosts.
- OpenJDK: https://jdk.java.net/21/
    - Diret Download Link: https://download.java.net/java/GA/jdk21.0.2/f2283984656d49d69e91c558476027ac/13/GPL/openjdk-21.0.2_windows-x64_bin.zip
- JavaFX lib: https://gluonhq.com/products/javafx/
    - Direct Download Link: https://download2.gluonhq.com/openjfx/21.0.2/openjfx-21.0.2_windows-x64_bin-sdk.zip

Extract/ unzip the above downloaded folders and place them into the root level of this project.

- javafx-sdk-21.0.2.zip
- jdk-21.0.2.zip

Use the OpenJDK v21 with the access to the JavaFX SDK modules with this command:
```bash
jdk-21.0.2/bin/java  --module-path javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.web --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED --add-exports javafx.base/com.sun.javafx.event=ALL-UNNAMED -jar out/artifacts/MIPS_Simulator_jar/MIPS_Simulator.jar
```

NOTE: The Fonts used in the Windows are different than the font used in Mac, so some of the Register Display windows are a little too small.


![image info](./pictures/ProjectView_Default.png)

## Input Fields

The `Octal Input` and `Binary Input` fields only accept number.
As their names suggest, `Octal Input` only accepts octal number, while `Binary Input` only accepts binary values.
Updating one field will automatically convert and update the other.

If an invalid character is put into either field, then it will be highlighted red.

Once a valid number has been inserted into the `Input` fields, clicking any of the buttons next to the various registers
will load that register with the value from the Inputs.

## Load, Load+, Store, Store+

These action buttons give the user functionality for directly accessing the contents of Memory.

### Load

The `Load` button looks up the contents of memory at the address specified by MAR, and puts it into MBR

```
MBR <- c(MAR)
```

### Load+

The `Load+` button is similar to the `Load` button, but will increase the MAR value after the load operation.

```
MBR <- c(MAR)
MAR <- MAR+1
```

### Store

The `Store` button takes the contents of the MBR, and puts it into memory at the address specified by the MAR

```
c(MAR) <- MBR
```

### Store+

The `STORE+` button is similar to the `Store` button, but it will increase the value of the MAR before executing the
store.

```
MAR <- MAR+1
c(MAR) <- MBR
```

## Run Step Halt

The Run Step and Halt buttons instruct the machine to process instructions at the current `PC` value.

### Run

This will tell the machine simulator to read the instruction from memory (specified by the PC), execute it, and repeat
forever. Execution will only stop for one of 2 reasons:

- The User presses the `Halt` button
- The program signals a stop, either from a `hlt` instruction or some other error

### Step

Similar to the `Run` button, this will read the instruction (found in memory at the PC address) and execute it. However,
the `Step` button only runs a single command, and will not repeatedly execute more instructions.

### Halt

A button that allows the user to directly halt the program. Only functional when the program has
been `Run`.

## .bi File Input

The user may specify a `.bi` (or a `.txt`) file that contains machine code. This file will be loaded into memory via the
RomLoader.
