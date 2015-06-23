
import java.util.Scanner;

/**
 * Description - This class has been added to perform the optimal binary search
 * tree operations using Dynamic programming.
 * 
 * @author Vinayak Subhash Pingale
 * @since March 10th 2015
 * @version 1.0
 */

public class OptimalBinarySearchTree {
	private int arrayElements[];
	private final int MAX = 99999;
	private int table[][] = new int[100][100];

	public void assignValue(int[] inputArr) {
		if (inputArr == null || inputArr.length == 0) {
			return;
		}
		this.arrayElements = inputArr;
		System.out.println(obst(arrayElements.length));
	}

	private int frequencySum(int i, int j) {
		int k,sum = 0;
		for (k = i; k <= j; k++) {
			sum = sum + arrayElements[k];
		}
		return sum;
	}
	private int obst(int numberOfElement) {
		int i, j, k;
		int totalCost;
		int optimalCost = 0;
		for (j = 1; j < numberOfElement; j++) {
			for (i = 0; i < numberOfElement - j; i++) {
				optimalCost = MAX;
				for (k = i; k < i + j; k++) {
					totalCost = (table[i][k] + frequencySum(i, k)) + ( table[k + 1][i + j] + frequencySum(k + 1, i + j));
					if ( totalCost < optimalCost) {
						optimalCost = totalCost;
					}
				}
				table[i][i + j] = optimalCost;
			}
		}
		return optimalCost;
	}

	
	public static void main(String[] args) {
		int countEntered, nosElement = 0;
		Scanner scanValue = new Scanner(System.in);
		nosElement = scanValue.nextInt();
		int[] arrayElements = new int[nosElement];

		for (countEntered = 0; countEntered < nosElement; ++countEntered) {
			arrayElements[countEntered] = scanValue.nextInt();
		}
		OptimalBinarySearchTree optimalBinarySearchTree = new OptimalBinarySearchTree();
		optimalBinarySearchTree.assignValue(arrayElements);
		scanValue.close();
	}
}
