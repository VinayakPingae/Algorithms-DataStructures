/*
 * Name  : Vinayak Subhash Pingale
 * Date  : 12th Feb 2015
 * Email : vpingal1@binghamton.edu
 * 
 */

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class QuickSort {
	private int arrayElements[];
	private boolean debug = false;
	public void debug(String message) {
		if(debug) {
			System.out.println(message);
		}
	}
	
	public void sort(int[] inputArr) {
        
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.arrayElements = inputArr;
        quickSort_randomPartition(0, this.arrayElements.length - 1);
	    quickSort_hoarePartition(0, this.arrayElements.length - 1);  
    }
	
	// QuickSort for Random partition Algorithm
	private void quickSort_randomPartition(int firstElement, int lastElement) { // Index of the First Element and Last Element to be sorted are passed
		int returnValue;
		if(firstElement < lastElement) {
			returnValue = randomPartition (firstElement,lastElement);
			quickSort_randomPartition(firstElement, returnValue);
			quickSort_randomPartition(returnValue + 1, lastElement);
		}
	}
	private int randomPartition(int firstElement, int lastElement) {
		Random random = new Random(); 
		int randomNum = random.nextInt((lastElement - firstElement) + 1) + firstElement;
		int temp = arrayElements[randomNum];
        arrayElements[randomNum] = arrayElements[lastElement];
        arrayElements[lastElement] = temp;
        int pivotValue = arrayElements[randomNum],i,j;
		i = firstElement - 1;
		j = lastElement + 1;
		int temp1 ;// Used for exchanging the two number's
			while(true) {
				do {
					j = j - 1;
					debug("J loop i value : "+i+ " j value : "+j + " pivot Value:"+pivotValue);
				}while(arrayElements[j] > pivotValue);
				do {
					i = i + 1;
					debug("I Loop i value : "+i+ " j value : "+j + " pivot Value:"+pivotValue);
				}while(arrayElements[i] < pivotValue);
				if(i < j) {
					debug("exchangeNumbers i value : "+i+ " j value : "+j + " pivot Value:"+pivotValue);
					temp1 = arrayElements[i];
			        arrayElements[i] = arrayElements[j];
			        arrayElements[j] = temp1;
				} else {
					return j;
				}
			}
	}
	
	// Code for Hoare Partition Algorithm
	private void quickSort_hoarePartition(int firstElement, int lastElement) { // Index of the First Element and Last Element to be sorted are passed
		int returnValue;
		if(firstElement < lastElement) {
			returnValue = hoarePartition (firstElement,lastElement);
			quickSort_hoarePartition(firstElement, returnValue);
			quickSort_hoarePartition(returnValue + 1, lastElement);
		}
	}
	private int hoarePartition( int firstElement, int lastElement) {
		int pivotValue = arrayElements[firstElement],i,j;
		i = firstElement - 1;
		j = lastElement + 1;
		int temp ;// Used for exchanging the two number's
			while(true) {
				do {
					j = j - 1;
					debug("J loop i value : "+i+ " j value : "+j + " pivot Value:"+pivotValue);
				}while(arrayElements[j] > pivotValue);
				do {
					i = i + 1;
					debug("I Loop i value : "+i+ " j value : "+j + " pivot Value:"+pivotValue);
				}while(arrayElements[i] < pivotValue);
				if(i < j) {
					debug("exchangeNumbers i value : "+i+ " j value : "+j + " pivot Value:"+pivotValue);
					temp = arrayElements[i];
			        arrayElements[i] = arrayElements[j];
			        arrayElements[j] = temp;
				} else {
					return j;
				}
			}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		//int arrayElements[] = {13, 19, 9, 5, 12, 8, 7, 4, 11, 2, 6, 21};
		//int arrayElements[] = {2,34,56,78,89,91,2,3,4,5,11,90,0};
		//int arrayElements[] = {5,5,5,5,5,5};
		//int arrayElements[] = {0,1,9,8,7,6,5};
		//int arrayElements[] = {};
		//int arrayElements[] = {12312312,12312312,12312312,12312312,546,7,8,890,234,56789,901917,2345,122332,4657,12312312,65546};
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
		System.out.println();
		QuickSort quicksort = new QuickSort();
		quicksort.sort(arrayElements);
		for(int i:arrayElements){
            System.out.print(i);
            System.out.print(" ");
        }
		scanner.close();
	}
}
