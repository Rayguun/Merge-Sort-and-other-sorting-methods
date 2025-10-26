import java.util.ArrayList;

public class App
{
	public static void main(String[] args) {
	    
	    
	    int sizeOfArray = 100000; //(int)(Math.random() * 1000000); 
	    int[] randomArray = new int[sizeOfArray];
	    int arrayNumber = (int)(Math.random() * 1000000);
	    for (int i = 0; i < sizeOfArray; i++){
	        randomArray[i] = arrayNumber;
	        arrayNumber = (int)(Math.random() * 1000000);
	    }
        int[] newArray = {1,5,3,2,10,24,55,44,22,11,55,44,33,63,23};
        
        long timerStart = System.nanoTime();
        QuickSort(randomArray);
	    
	    long timerEnd = System.nanoTime();
        long duration = timerEnd - timerStart;
        System.out.println("Sorting an array of " + sizeOfArray + " took QuickSort: ");
        System.out.println(duration + " nanoseconds.");
        System.out.printf("%.6f seconds%n", duration /1_000_000_000.0);
        System.out.println();
        
        timerStart = System.nanoTime();
	    MergeSort(randomArray);
	    
	    timerEnd = System.nanoTime();
        duration = timerEnd - timerStart;
        System.out.println("Sorting an array of " + sizeOfArray + " took MergeSort: ");
        System.out.println(duration + " nanoseconds.");
        System.out.printf("%.6f seconds%n", duration /1_000_000_000.0);
        System.out.println();
        
        timerStart = System.nanoTime();
	    DynamicShellSort(randomArray);
	    
	    timerEnd = System.nanoTime();
        duration = timerEnd - timerStart;
        System.out.println("Sorting an array of " + sizeOfArray + " took ShellSort: ");
        System.out.println(duration + " nanoseconds.");
        System.out.printf("%.6f seconds%n", duration /1_000_000_000.0);
        System.out.println();
        
        timerStart = System.nanoTime();
	    BubbleSort(randomArray);
	    
	    timerEnd = System.nanoTime();
        duration = timerEnd - timerStart;
        System.out.println("Sorting an array of " + sizeOfArray + " took BubbleSort: ");
        System.out.println(duration + " nanoseconds.");
        System.out.printf("%.6f seconds%n", duration /1_000_000_000.0);
        System.out.println();
        
        timerStart = System.nanoTime();
	    insertionSort(randomArray);
	    
	    timerEnd = System.nanoTime();
        duration = timerEnd - timerStart;
        System.out.println("Sorting an array of " + sizeOfArray + " took InsertionSort: ");
        System.out.println(duration + " nanoseconds.");
        System.out.printf("%.6f seconds%n", duration /1_000_000_000.0);
        System.out.println();
        
        
        
        
	}
	
	public static int[] BubbleSort(int[] arr) {
        int[] result = arr.clone();
    
        for (int i = 0; i < result.length; i++) {
            boolean checked = true;
            for (int a = 0; a < result.length - 1; a++) {
                if (result[a] > result[a + 1]) {
                    checked = false;
                    int temp = result[a];
                    result[a] = result[a + 1];
                    result[a + 1] = temp;
                }
            }
            if (checked) break;
        }
    
        return result;
    }
    
    
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
    
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
    
            arr[j + 1] = key;
        }
    }
    
	
	public static int[] QuickSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
    
        int pivotIndex = arr.length / 2;
        int pivot = arr[pivotIndex];
    
        ArrayList<Integer> lowerArray = new ArrayList<>();
        ArrayList<Integer> higherArray = new ArrayList<>();
    
        for (int i = 0; i < arr.length; i++) {
            if (i == pivotIndex) continue;
            if (arr[i] <= pivot) {
                lowerArray.add(arr[i]);
            } else {
                higherArray.add(arr[i]);
            }
        }
    
        int[] lowerSorted = QuickSort(lowerArray.stream().mapToInt(x -> x).toArray());
        int[] higherSorted = QuickSort(higherArray.stream().mapToInt(x -> x).toArray());
    
        int[] sorted = new int[lowerSorted.length + 1 + higherSorted.length];
        sorted[lowerSorted.length] = pivot;
    
        return sorted;
    }
	 
	
	public static int[] DynamicShellSort(int[] arr){
	    //Makes the dynamic intervals
        int n = 0;
        while ((1 << (n + 1)) < arr.length) {
            n++;
        }
        int[] intervals = new int[n];
        for (int i = 0; i < n; i++) {
            intervals[i] = 1 << (n - i - 1);  
        }
        
	    int[] result = arr.clone();  
        for (int gap : intervals) {
            for (int i = gap; i < result.length; i++) {
                int temp = result[i];
                int j = i;
    
                while (j >= gap && result[j - gap] > temp) {
                    result[j] = result[j - gap];
                    j -= gap;
                }
    
                result[j] = temp;
            }
        }
    
        return result;
	}
	
	
	public static int[] MergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
    
        int splitPoint = arr.length / 2;
        
        ArrayList<Integer> lowerArray = new ArrayList<>();
        ArrayList<Integer> higherArray = new ArrayList<>();
    
        for (int i = 0; i < arr.length; i++) {
            if (i < splitPoint) {
                lowerArray.add(arr[i]);
            } else {
                higherArray.add(arr[i]);
            }
        }
        
        int[] lowerSorted = MergeSort(lowerArray.stream().mapToInt(x -> x).toArray());
        int[] higherSorted = MergeSort(higherArray.stream().mapToInt(x -> x).toArray());
        int[] combinedSortedArray = new int[lowerSorted.length + higherSorted.length];
        int counter1 = 0;
        int counter2 = 0;
        int index = 0;
        
        while (counter1 < lowerSorted.length && counter2 < higherSorted.length) {
            if (lowerSorted[counter1] <= higherSorted[counter2]) {
                combinedSortedArray[index++] = lowerSorted[counter1++];
            } else {
                combinedSortedArray[index++] = higherSorted[counter2++];
            }
        }
        
        // Add remaining elements from lowerSorted
        while (counter1 < lowerSorted.length) {
            combinedSortedArray[index++] = lowerSorted[counter1++];
        }
        
        // Add remaining elements from higherSorted
        while (counter2 < higherSorted.length) {
            combinedSortedArray[index++] = higherSorted[counter2++];
        }
    
        return combinedSortedArray;
    }
	
	
}
