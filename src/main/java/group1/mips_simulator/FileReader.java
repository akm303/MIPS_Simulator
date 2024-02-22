package group1.mips_simulator;

import group1.mips_simulator.components.instruction.Instruction;
import group1.mips_simulator.components.instruction.InstructionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Responsible for reading in the program binary file and converting it into
 * objects that this simulator can understand and process.
 */
public class FileReader {

    /**
     * Open the target file, process it line by line. Return the contents
     * of each line converted into Instruction objects.
     * Each file of the file should contain at least 2 columns (delineated with ' '
     * white space or '\t' tab characters).
     * Both columns must be a octal number.
     * The first column is a memory location (ignored for now)
     * The second column is an instruction.
     *
     * Example input file structure:
     * 000011	003242		ldr 2,2,2,1
     * 000012	005242		str 2,2,2,1
     * 000013	007242		lda 2,2,2,1
     * 000014	010242		ldx 2,2,1
     *
     * @param fileToRead The target file to read.
     * @return All the lines in the program file converted into Instructions
     */
    public Vector<Instruction> readBinaryFile(String fileToRead) {
        BufferedReader reader;
        Vector<Instruction> result = new Vector<>();

        try {

            reader = new BufferedReader(new java.io.FileReader(fileToRead));
            String line = reader.readLine();

            // Process the file line by line
            while (line != null) {
                if (line.isEmpty()) {
                    line = reader.readLine();
                    continue;
                }
                Instruction newInstruction = processLine(line);
                result.add(newInstruction);

                // grab next line from file
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Encountered an error when reading the file: " + e);
        }

        return result;
    }

    /**
     * Take a single line from a source octal file and convert it into an instruction
     *
     * Example input line:
     * 000014	010242		ldx 2,2,1
     * @param line
     * @return
     */
    protected Instruction processLine(String line) {
        InstructionFactory factory = new InstructionFactory();

        // Split the line into it's various columns
        StringTokenizer st = new StringTokenizer(line, " \t");
        Vector<String> lineSplitIntoTokens = new Vector<>(st.countTokens());
        while (st.hasMoreTokens()) {
            lineSplitIntoTokens.add(st.nextToken());
        }

        String memoryLocationInOctal = lineSplitIntoTokens.get(0); // 0th is mem
        String instructionInOctal = lineSplitIntoTokens.get(1); // 1st is instruction

        // Turn instruction octal into Instruction object
        return factory.buildInstruction_fromOctal(instructionInOctal);

    }
}
