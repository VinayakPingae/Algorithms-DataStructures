
import java.util.Scanner;
/**
 * 
 * @author Vinayak Subhash Pingale
 * @since 28th Feb 2015
 * @version 1.0
 */
public class CheckCash {

	private int arrayElements[];

	public void assignArray(int[] inputArr) {
		if (inputArr == null || inputArr.length == 0) {
			return;
		}
		this.arrayElements = inputArr;
	}
    /**
     * Description - this is the function that has to check about the coin change which has been implemented in to the program
     * @param n The array length of the array which has been accessed
     * @param m The total debit for the cash which has to be deducted
     */
	private void count(int n, int m) {
		int i, j;
		int totalDebitAmountToCash = m;
		// we need to have a bottom up approach constructed for this value as
		// count as 0
		int table[][] = new int[n + 1][m + 1];
		// as i have considered the 0th row i have filled all the zeroeth rows here
		for (i = 0; i < m + 1; i++)
			table[0][i] = 0;
		for (i = 0; i < n + 1; i++)
			table[i][0] = 0;
		for (i = 1; i < n + 1; i++) {
			for (j = 1; j < m + 1; j++) {
				table[i][j] = 0;
				if (j == arrayElements[i - 1]) {
					table[i][j] = arrayElements[i - 1];
				}

				if (table[i - 1][j] > 0) {
					table[i][j] = table[i - 1][j];
				}
				if (j > arrayElements[i - 1]) {
					if (table[i - 1][j - arrayElements[i - 1]] > 0) { 
						table[i][j] = j;
					}
				}
			}
		}
		// If the table elements last element is equal to zero then "Not Possible"
		if (table[n][m] == 0) {
			System.out.println("Not Possible");
		}
		// Otherwise print out all the elements which has been backtracked
		while (totalDebitAmountToCash > 0 && table[n][m] != 0) {
			int count = 1;
			while (count < n + 1) {
				if (table[count][totalDebitAmountToCash] != 0) {
					System.out.println(count);
					totalDebitAmountToCash = totalDebitAmountToCash - arrayElements[count - 1];
					break;
				}
				count++;
			}
		}
	}
// Driver program to find out the minimum cash 

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); 
		int totalDebit = scanner.nextInt();
		// The total debit that has to be recovered from the given number of cheques otherwise assign the values and run it for the check cashing
		if(totalDebit <= 0) {
			System.exit(1);
		}
		// Number of elements that are accepted from the file input
		int nosElement = scanner.nextInt();
		int[] arrayElements = new int[nosElement];
		for (int countEntered = 0; countEntered < nosElement; ++countEntered) {
			arrayElements[countEntered] = scanner.nextInt();;
		}
		
		CheckCash checkCash = new CheckCash();
		checkCash.assignArray(arrayElements);
		checkCash.count(arrayElements.length, totalDebit);
		scanner.close();
	}
}

