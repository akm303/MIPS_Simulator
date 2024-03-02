# Example Usage

### Start the Simulator

Launch the program by navigating to the root level of this project in a terminal:

```bash
cd <path/to/this/directory>
```

Then, run the Simulator jar with the following command (NOTE: It may take up to 10 seconds to launch):

```bash
java --module-path out/artifacts/MIPS_Simulator_jar/ \
     --add-modules javafx.controls,javafx.fxml,javafx.graphics \
     -jar out/artifacts/MIPS_Simulator_jar/MIPS_Simulator.jar
```

![image info](./pictures/ProjectView_Default.png)

### Load the .bi file

Click on the IPL button in the bottom right. This will open a file selector dialogue window.
Select the example file found in the location: `src/main/java/group1/mips_simulator/simulator/programs/LoadStore.bi`

![image info](./pictures/LoadBi.png)

### Confirm the file loaded properly

Press the `Load+` button and look and the MAR and MBR registers. 
After the first press it should be
```
MAR: 0000_0000_0001
MBR: 0000_0000_0000_0000
```