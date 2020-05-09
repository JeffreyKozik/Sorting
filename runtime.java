/** @author Jeffrey Kozik */

/**
 * References:
 * https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#nanoTime-- -> Java API on nanoTime method
 */
import java.util.Random;

/** Class to test how fast this implementation of merge sort actually works */
public class runtime {
    /** Method to measure runtime */
    public static void measuringRuntime(){
        /** To create random integers */
        Random randNums = new Random();
        /** To store the different array sizes to test */
        int[] diffSizes = {250000, 500000, 1000000, 1250000};
        /** To store the median runtimes of each of the array sizes */
        long[] medianRuntime = new long[4];
        /** To array of random numbers to sort */
        int[] randomArr;
        /** The runtimes for each random array of a given size, median is taken from here and put in medianRuntime array */
        long[] threeRuntimes = new long[3];
        /** Iterates through the diffSizes array so that arrays of each of those sizes can be made and analyzed */
        for(int i = 0; i < diffSizes.length; i++){
            randomArr = new int[diffSizes[i]];
            /** Three random arrays of a given size are made */
            for(int j = 0; j < 3; j++){
                /** The random array is populated with random numbers */
                for(int k = 0; k < randomArr.length; k++){
                    randomArr[k] = randNums.nextInt();
                }
                /** To track how long mergeSort takes */
                long startTime = System.nanoTime();
                Sorting.mergeSort(randomArr);
                long estimatedTime = System.nanoTime() - startTime;
                threeRuntimes[j] = estimatedTime;
            }
            /** Series of if and else statements to determine median runtime */
            if(threeRuntimes[0] >= threeRuntimes[1]){
                if(threeRuntimes[0] <= threeRuntimes[2]){
                    medianRuntime[i] = threeRuntimes[0];
                }
                else if(threeRuntimes[1] >= threeRuntimes[2]){
                    medianRuntime[i] = threeRuntimes[1];
                }
                else{
                    medianRuntime[i] = threeRuntimes[2];
                }
            }
            else if(threeRuntimes[1] <= threeRuntimes[2]){
                medianRuntime[i] = threeRuntimes[1];
            }
            else if(threeRuntimes[2] > threeRuntimes[0]){
                medianRuntime[i] = threeRuntimes[2];
            }
            else{
                medianRuntime[i] = threeRuntimes[0];
            }
        }
        /** Iterates through all of the stored median runtime values and prints out their runtimes */
        for(int i = 0; i < medianRuntime.length; i++){
            System.out.print("An array of size " + diffSizes[i] + " takes ");
            System.out.println(medianRuntime[i] + " nanoseconds to run.");
        }
    }
    /**
     * Allows the user to measure median runtime of mergeSort from the command line
     * @param args Inputted arguments
     */
    public static void main (String[] args){
        measuringRuntime();
    }
}