# Design Notes

The project source code can be found at the location `src/main/java/group1/mips_simulator/*.java`.

This project is designed with the Model-View-Controller design pattern. The majority of the code is dedicated to the
Model, which represents how the MIPS computer operates, holds data, executes instructions, etc.

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
- `ROM`

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
