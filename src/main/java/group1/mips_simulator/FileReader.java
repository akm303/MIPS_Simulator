package group1.mips_simulator;

import group1.mips_simulator.components.instructionParts.Instruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class FileReader {

    public Vector<Instruction> readBinaryFile(String fileToRead) {
        BufferedReader reader;
        Vector<Instruction> result = new Vector<>();

        try {
            reader = new BufferedReader(new java.io.FileReader(fileToRead));
            String line = reader.readLine();
            while (line != null) {
                if (line.isEmpty()) {
                    line = reader.readLine();
                    continue;
                }
                StringTokenizer st = new StringTokenizer(line, " \t");
                Vector<String> lineSplitIntoTokens = new Vector<>(st.countTokens());
                while (st.hasMoreTokens()) {
                    lineSplitIntoTokens.add(st.nextToken());
                }

                String memoryLocationInOctal = lineSplitIntoTokens.get(0);
                String instructionInOctal = lineSplitIntoTokens.get(1);

                Instruction newInstruction = Instruction.buildInstruction_fromOctal(instructionInOctal);
                result.add(newInstruction);

                // grab next line from file
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Encountered an error when reading the file: " + e);
        }

        return result;
    }
}
