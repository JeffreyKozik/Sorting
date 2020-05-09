/** @author Jeffrey Kozik */

/**
 * References:
 * https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#decode-java.lang.String- -> Java API on Integer Class
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;

/** Class to test functionality of mergeSort method in Sorting class by sorting an input file into an output file */
public class fnTest{
    /**  
     * Method to test functionality of sorting method and sort inputted file into outputted file
     * @param inputFile File to be sorted
     * @param outputFile File to output to
     * @return String to tell the user whether or not the method worked
    */
    public static String functionalityTest(String inputFile, String outputFile){
        /** If everything goes as planned */
        try{
            /** To read through the file and determine the length -> used to determine array size */
            BufferedReader findingLength = new BufferedReader(new FileReader(inputFile));
            /** The current line being read */
            String lengthLine = findingLength.readLine();
            /** The current number of lines (and thus elements) in the file */
            int length = 0;
            /** Goes through file until reaching the end, each time incrementing the length and reading next line */
            while(lengthLine != null){
                length++;
                lengthLine = findingLength.readLine();
            }
            findingLength.close();

            /** To read through file and add each number to an array to be sorted */
            BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile));
            /** The contents of the current line being read */
            String currentLine = inputFileReader.readLine();
            /** Corresponding index in the array to insert the number */
            int index = 0;
            /** Array to store the numbers */
            int[] arrToSort = new int[length];
            /** Iterates through entire file until end, each iteration adds the line contents to array, reads next line, and increments index */
            while(currentLine != null){
                arrToSort[index] = Integer.parseInt(currentLine);
                currentLine = inputFileReader.readLine();
                index++;
            }
            inputFileReader.close();
            Sorting.mergeSort(arrToSort);

            /** To print the contents of the sorted array to the output file */
            PrintStream outputFilePrinter = new PrintStream(new FileOutputStream(outputFile));
            /** Iterates through entire array, each iteration prints out the next element in the array followed by a new line */
            for(int i = 0; i < arrToSort.length; i++){
                outputFilePrinter.print(arrToSort[i] + "\n");
            }
            outputFilePrinter.close();
            
            return "OK";
        }
        /** If the input file is not found, the user is told so */
        catch(FileNotFoundException e){
            return "File not found";
        }
        /** If there is a problem with the inputting and outputting procedure the user is told so */
        catch(IOException e){
            return "Problem with inputting and outputting";
        }
    }

    /**
     * Method allowing user to run program from command line
     * @param args Inputted arguments
     */
    public static void main(String[] args){
        /** If the correct amount of arguments are inputted, the inputted file is sorted into the outputted file */
        try{
            System.out.println(functionalityTest(args[0], args[1]));
        }
        /** If the user did not input the correct amount of arguments they are told what to do */
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Please type the file you'd like sort followed by the file to output the sorted numbers to");
        }
    }
}