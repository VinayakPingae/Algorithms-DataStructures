/*
 * Name  : Vinayak Subhash Pingale
 * Date  : February 3rd 2015
 * Bmail : vpingal1@binghamton.edu
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InsertionSort {

	// A logger message for printing the user related informatory data
	private static void log(String aMessage) {
		System.out.println(aMessage);
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nosElement, keyValue, emptySpace;
		log("Enter the number of elements : ");
		// Accept the number of elements that need to sorted 
		
		nosElement = Integer.parseInt(br.readLine());
		int[] elementsArray = new int[nosElement];
		
		// Enter the numbers that need to be sorted for easy convenience
		
		for (int countEntered = 0; countEntered < nosElement; ++countEntered) {
			elementsArray[countEntered] = Integer.parseInt(br.readLine());
		}
		log("\nSorted array after insertion sort is as follows : \n");
		
		// Logic for sorting the array using insertion sort
		
		for (int j = 1; j < elementsArray.length; j++) {
			keyValue = elementsArray[j];
			emptySpace = j;
			while (emptySpace > 0 && elementsArray[emptySpace - 1] > keyValue) {
				elementsArray[emptySpace] = elementsArray[emptySpace - 1];
				emptySpace = emptySpace - 1;
			}
			elementsArray[emptySpace] = keyValue;
		}
		
		// Display the output as a sorted array
		for (int countEntered = 0; countEntered < nosElement; countEntered++) {
			log("" + elementsArray[countEntered]);
		}

	}
}
