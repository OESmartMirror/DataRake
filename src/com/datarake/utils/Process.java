package com.datarake.utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Process
{
    public static void main(String[] args)
    {
        if(args.length > 0)
        {
            File file = new File(args[0]);
            DataProcessor proc = new DataProcessor(Utils.readCsv(file));
            File output = CreateOutPutFile(file);
            printEval(proc, output);
        }
        else
        {
            System.err.println("No file passed in argument!");
        }
    }

    private static void printEval(DataProcessor proc, File output) {
        try {
            FileWriter myWriter = new FileWriter(output);
            myWriter.write("Percent of mismatched users ".concat(proc.percentOfMismatchedUser().toString()));
            myWriter.write(System.lineSeparator());
            myWriter.write("Percent of mismatched users and their emotions ".concat(proc.percentOfMismatchedUserAndEmotion().toString()));
            myWriter.write(System.lineSeparator());
            myWriter.write("Index of first mismatch: " .concat(proc.indexOfFirstMismatched().toString()).concat(" with distance of: ").concat(proc.distanceOfFirstMismatched().toString()));
            myWriter.write(System.lineSeparator());
            myWriter.write("Index of first mismatched user: " .concat(proc.indexOfFirstMismatchedUser().toString()).concat(" with distance of: ").concat(proc.distanceOfFirstMismatchedUser().toString()));
            myWriter.write(System.lineSeparator());
            myWriter.write("Index of first mismatch emotion: " .concat(proc.indexOfFirstMismatchedEmotion().toString()).concat(" with distance of: ").concat(proc.distanceOfFirstMismatchedEmotion().toString()));
            myWriter.write(System.lineSeparator());
            myWriter.write("User by mismatch based on lowest distance");
            myWriter.write(System.lineSeparator());
            myWriter.write(Utils.createMapString(proc.absoluteMatch()));
            myWriter.write(System.lineSeparator());
            myWriter.write("Dataset User Emotion Pair;Eval User Emotion Pair;Distance");
            myWriter.write(System.lineSeparator());
            myWriter.write(Utils.createListString(proc.getMatchedDistances()));
            myWriter.flush();
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e)
        {
            System.err.println("An io error occurred.");
        }
    }

    private static File CreateOutPutFile(File file)
    {
        File myObj = null;
        try
        {
            myObj = new File(file.getName().concat("_eval.txt"));
            if (myObj.createNewFile())
            {
                System.out.println("File created: " + myObj.getName());
            }
            else
            {
                myObj = new File(file.getName().concat(new Timestamp(System.currentTimeMillis()).toString()).replace(".","").concat("_eval.txt"));
                myObj.createNewFile();
            }
        }
        catch (IOException e)
        {
            System.err.println("An io error occurred.");
        }
        return myObj;
    }
}
