# Design Notes

The project source code can be found at the location `src/main/java/group1/mips_simulator/*.java`.

This project is designed with the Model-View-Controller design pattern. The majority of the code is dedicated to the
model, which represents how the MIPS computer operates, holds data, executes instructions, etc.

The main workflow for the simulator is as follows:

- Load the .bi file into memory via the rom loader (See the Rom Loader section below)
- Set the PC to the location of the first instruction
- Run the program

## Model

The majority of the code that represents the MIPS computer can be found
in `src/main/java/group1/mips_simulator/components/*.java`.

The main design philosophy behind the simulation model is to match as close to the physical hardware as possible. For
example, our `Computer` has a `CPU`, which in turn has a `RegisterFile`, which in turn has a collection of `Register`s.
Each `Register` has a size (usually 16 bits) and a value. This design philosophy of matching the hardware as closely as
reasonably possible allows us to make our simulation code operate more closely to how a physical machine would operate.

The top level file is the `Computer.java`, which represents the hardware machine that is being simulated. A `Computer`
is a class that has-a:

- `Memory`
- `CPU`
- `RomLoader`

The `Computer` is just the top level object that represents the data and allows a user to `runCurrentPC()` which will
use the Program Counter `Register` to load an `Instruction` from `Memory` and execute it. Most instructions will modify
the `Computer` instance in some way, by updating `Register`s, `Memory`, or other state in the `Computer`.

#### CPU, RegisterFile, Register

The `CPU` is a component that has-a `RegisterFile`, and a `RegisterFile` is a class that holds all of the `Register`
objects. The `RegisterFile` represents the hardware chip that manages the various `Register`s in the computer.
Currently, (Project submission 1) this computer supports the following registers:

- GPR (16 bit)
    - There are 4 GPRs
- IXR (16 bit)
    - There are 3 IXRs
- PC (12 bit)
- MAR (12 bit)
- CC (4 bit)
- MBR (16 bit)
- IR (16 bit)
- MFR (16 bit)

#### Memory

A `Memory` class is a type of `Storage`, which functionally is an array of `Value` objects. A `Value` object is a class
that represents a machine "word". Currently, a `Value` is only every 16 bits, but with our Object-Oriented design it
would be relatively simple to adjust the size of a `Value` to be more or less bits.

The `Memory` class only exposes a `get(...)` and `write(...)` function to allow for reads and writes to specified
addresses.
The size of the memory is controlled via the `Config.java` file's `MEM_SIZE` constant.

#### Instructions

While the `Memory` and `Register`s operate on `Value` objects, we also have a class representation for `Instruction`s.
These `Instruction` objects can be converted to and from `Value` objects easily. An `Instruction` has-a `OpCode` (which
is a 6 bit long binary number) and a collection of `Field`s (which are variable bit length arguments). Some instructions
may be specialized to allow for more convenient accessing of the fields, or for more convenient execution.

## View

This project is using JavaFX as the library for our Front End view. The layout of the front end may be found in the
file `src/main/resources/group1/mips_simulator/hello-view.fxml`.

The binding of front end elements to display details from the model is managed via the Controller. See that section next
for more details.

## Controller

The controller binds front end elements to the underlying model. So when the model changes, the controller triggers a
re-draw of the front end with the new data.

Additionally, the controller is responsible for passing data from the user into the model. This can be done via button
clicks, a user typing data into the input fields, or other actions.

Each possible action a user my take (via the Front End View) has a function in the `SimController` class that is
executed. The controller class has-a `Computer` model and will call the appropriate functions to modify/ update the
Model. Then, once the model is ready, the controller will tell the Front End to re-draw itself with this new data.

## ROM and ROM-Loader

The `ROM` class (`src/main/java/group1/mips_simulator/components/ROM.java`) represents a disk, cartridge, or some other
storage device with Read Only Memory. A ROM may be created via a `.bi` file, which should be the output from the Project
0 Assembler. To summarize, the `.bi` file is a file contain two columns of octal numbers. The first octal number is a
location in memory, and the second octal number is the data to place into memory at that location.

Example programs may be found in `src/main/java/group1/mips_simulator/simulator/programs/`

```txt
000000	000000	LOC	6			;BEGIN AT LOCATION 6
000006	000012	Data	10			;PUT 10 AT LOCATION 6
000007	000003	Data 	3			;PUT 3 AT LOCATION 7
000010	002000	Data	End			;PUT 1024 AT LOCATION
000011	000000	Data	0
000012	000014	Data	12
000013	000011	Data	9
000014	000022	Data	18
000015	000014	Data	12
000016	010207	LDX	2,7   			; X2 GETS 3
000017	003412	LDR	3,0,10			;R3 GETS 12
000020	003212	LDR	2,2,10			;R2 GETS 12
000021	002652	LDR	1,2,10,1		;R1 GETS 18
000022	006000	LDA	0,0,0			;R0 GETS 0
000023	010110	LDX	1,8			;X1 GETS 1024
000026	000000	LOC	1024
002000 	000000 End:	HLT				;STOP
```

Once a `ROM` object has been created, it may be passed into the `Computer` via a `RomLoader` which is a component of
the `Computer` that will read the `ROM` and place copy its contents into `Memory`.

For more detail, consider this line: `000006	000012	Data	10 ;PUT 10 AT LOCATION 6`.
Only the first 2 columns are considered: `000006	000012`
The first column `000006` is a memory location, while the second column is the data to place in that location `000012`.
So after the `RomLoader` has considered the `ROM`, the following will be true in memory:
`c(000006) <-- 000012`.

Notice, neither the `ROM` nor the `RomLoader` will attempt to convert the data into `Instructions`. The `RomLoader` is "
dumb" and will just blindly place data from the ROM into Memory. That data may represent an instruction, or it may
represent an immediate operand.

To summarize the main data flow here:

Assembly File --> Assembler --> .bi file --> Rom --> Rom Loader --> Memory
