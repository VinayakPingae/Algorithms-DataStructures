/*
 * Name  : Vinayak Subhash Pingale
 * Date  : 19th Feb 2015
 * Email : vpingal1@binghamton.edu
 * 
 */

import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Vinayak
 *
 */
public class HeapSort {
	private int arrayElements[];
	private int arrayElementsLength;
	private static int HeapSize;
	/**
	 * 
	 * @param inputArr
	 */
	/**
	 Description :  his function has been added to initialize the global array the way it was done in my previous program.
	 arrayElements[]  is the global array and arrayElementsLength has been added to be decisive about the length.
	*/
	public void sort(int[] inputArr) {
        
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.arrayElements = inputArr;
        this.arrayElementsLength = this.arrayElements.length;
		        
		buildMaxHeap();
        
        for (int i = HeapSize; i > 0; i--)
        {
        	int temp = arrayElements[0];
			arrayElements[0] = arrayElements[i];
			arrayElements[i] = temp;
			HeapSize = HeapSize - 1;
            maxHeapify(0);
        }
    }
 	
	public  void buildMaxHeap () {
		HeapSize = this.arrayElementsLength - 1;
		for (int i = HeapSize/2; i >= 0; i--) {
			maxHeapify(i);
		}
	}
	// Determine the Left element by using 2*i criteria
	private int Left( int a) {
		return (2*a + 1);
	}
	// Determine the right element by using 2*i + 1 criteria
	private int Right( int a) {
		return (2*a + 2);
	}
	/**
	 * Description :  This function has been added to determine the eft and right child of the maxheap and then determing what needs to be swapped with the   
     * elements above it.
	 * @param i
	 */
	private void maxHeapify(int i) {
		int l = Left(i);
		int r = Right(i);
		int largest = i;
		// Check if left element is larger than the current heap or not
		if(l <= HeapSize && arrayElements[l] > arrayElements[i]) {
			largest = l;
		} 
		// Check if right element is larger than the current heap or not
		if(r <= HeapSize && arrayElements[r] > arrayElements[largest] ) {
			largest = r;
		}
		// Swap the elements if the largest elemtn is not equal to the current slement selcted to swap.
		if (largest != i) {
			int temp = arrayElements[i];
			arrayElements[i] = arrayElements[largest];
			arrayElements[largest] = temp;
			maxHeapify(largest);
		}
	}
	/**
	 * 
	 * @param args
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scanner = new Scanner(System.in); 
		int nosElement;
		nosElement = scanner.nextInt();
		int[] arrayElements = new int[nosElement];
		for (int countEntered = 0; countEntered < nosElement; ++countEntered) {
			arrayElements[countEntered] = scanner.nextInt();;
		}
		
		for(int i:arrayElements){
            System.out.print(i);
            System.out.print(" ");
        }
		System.out.println("\n");
		HeapSort heapSort = new HeapSort();
		heapSort.sort(arrayElements);
		for(int i:arrayElements){
            System.out.print(i);
            System.out.print(" ");
        }
		scanner.close();
	}
}
