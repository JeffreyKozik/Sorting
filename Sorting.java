/** @author Jeffrey Kozik */

/**
 * References:
 * https://canvas.case.edu/courses/21069/files/folder/Lecture%20Notes?preview=2407529 -> Data Structures notes on Merge Sort
 */

 /** Class that contains mergeSort method which is used by fnTest and runtime programs */
public class Sorting{
    /**
     * Used purely for testing purposes to print out contents of a given array 
     * @param arr The array to iterate through
     * @param start Starting index (inclusive)
     * @param end Ending index (exclusive)
     */
    public static void printArr(int[] arr, int start, int end){
        /** Iterates through the desired portion of the array and prints out the contents */
        for(int i = start; i < end; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    /**
     * Sorts an array by iteratively merging subarrays and using a temporary array instead of inefficiently back copying
     * @param arr The array to sort
     */
    public static void mergeSort(int[] arr){
        /** An initially empty array of the same size of the inputted array. Switches back and forth for efficiency */
        int[] arr2 = new int[arr.length];
        /** The array whose contents are currently being read */
        int[] currentArr = arr;
        /** The array to merge the current arrays "subarrays" to */
        int[] tempArr = arr2;
        /** The inital size of the "subarrays" */
        int subArrSize = 1;
        /** Keeps track of where in the current array to look at */
        int currentArrPtr = 0;
        /** Keeps track of where in the tempoarary array to merge to */
        int tempArrPtr = 0;
        /** Keeps track of where the left "subarray" starts, inclusive */
        int leftArrStart = 0;
        /** Keeps track of where the left subarray ends, exclusive, and where the right subarray begins, inclusive */
        int leftArrEnd = 0;
        /** Keeps track of where the right subarray ends, exclusive */
        int rightArrEnd = 0;
        /** Iterates until the subarray size is as big as the inputted array size at which point the array has been sorted */
        while(subArrSize < currentArr.length){
            /** Makes one pass through the current array. Each time breaks it into subarrays and merges to temporary array */
            while(currentArrPtr < currentArr.length){
                leftArrStart = currentArrPtr;
                /** If there is room for a whole subarray, it is formed */
                if(currentArrPtr + subArrSize <= currentArr.length){
                    leftArrEnd = currentArrPtr + subArrSize;
                }
                /** Else the subarray is just made as big as it can be without going over the size of the current array */
                else{
                    leftArrEnd = currentArr.length;
                }
                /** 
                 * Increments the current array pointer (this will be bigger than the current array size if the else statement above
                 * is enacted, but it doesn't matter as the end of the current array has been reached)
                */
                currentArrPtr += subArrSize;
                //Sorting.printArr(currentArr, leftArrStart, leftArrEnd);

                /** Creates a right subarray of the subArrSize if there's room */
                if(currentArrPtr + subArrSize <= currentArr.length){
                    rightArrEnd = currentArrPtr + subArrSize;
                }
                /** If there's not room the sub array is made as big as it can be  */
                else{
                    rightArrEnd = currentArr.length;
                }
                currentArrPtr += subArrSize;
                //Sorting.printArr(currentArr, leftArrEnd, rightArrEnd);

                /** Keeps track of where in the left subarray the comparison is being made from */
                int leftArrPtr = leftArrStart;
                /** Keeps track of where in the right subarray the comparison is being made from */
                int rightArrPtr = leftArrEnd;
                /** Runs until one array has been exhausted through. Each iteration compares elements and adds the smaller to the temp array */
                while(leftArrPtr < leftArrEnd && rightArrPtr < rightArrEnd){
                    /** If the current element in the left array is smaller or equal to the right arr element, that is added to the temp arrray */
                    if(currentArr[leftArrPtr] <= currentArr[rightArrPtr]){
                        tempArr[tempArrPtr] = currentArr[leftArrPtr];
                        leftArrPtr++;
                    }
                    /** Otherwise the right array element is added */
                    else {
                        tempArr[tempArrPtr] = currentArr[rightArrPtr];
                        rightArrPtr++;
                    }
                    tempArrPtr++;
                }

                /** Iterates through the rest of the left array (if there is any to iterate through), adds the rest to temp array */
                while(leftArrPtr < leftArrEnd){
                    tempArr[tempArrPtr] = currentArr[leftArrPtr];
                    leftArrPtr++;
                    tempArrPtr++;
                }

                /** Same as above, but for right array. Also important to note that only either this loop or the one above will run through. */
                while(rightArrPtr < rightArrEnd){
                    tempArr[tempArrPtr] = currentArr[rightArrPtr];
                    rightArrPtr++;
                    tempArrPtr++;
                }

                //Sorting.printArr(tempArr, 0, tempArr.length);
            }
            /** To alternate the current and temp arrays */
            int[] switching = currentArr;
            currentArr = tempArr;
            tempArr = switching;
            currentArrPtr = 0;
            tempArrPtr = 0;
            subArrSize *= 2;
        }
        arr = currentArr;
    }
}