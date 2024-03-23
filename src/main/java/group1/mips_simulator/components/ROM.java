package group1.mips_simulator.components;

import group1.mips_simulator.FileReader;
import group1.mips_simulator.FileReaderSwing;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * gets loaded into ROM Loader in CPU
 * Contains program to be run(?)
 **/
public class ROM {
    Vector<String[]> dataFromFile;

    /**
     * The main functional use of a ROM is to take data and load
     * it into computer memory. This function will do that, taking
     * the entire contents of a program file and place it into
     * memory.
     *
     * @param biFile The file to extract the data from
     */
    public void readFromFile(File biFile) throws IOException {
        // TODO: Implement this function
        System.out.println("ROM reading from bi file: " + biFile.getAbsolutePath());
        FileReaderSwing fr = new FileReaderSwing();
        try {
            dataFromFile = fr.readBinaryFile(biFile.getAbsolutePath());
        } catch (Exception e) {
            throw new IOException("Ran into an error when trying to parse .bi file.\n" +
                    "File name: " + biFile.getAbsolutePath() +
                    "\nError encountered: " + e);
        }
    }

    public Vector<String[]> getInstructions(){
        return dataFromFile;
    }
}
