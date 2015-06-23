import java.util.Scanner;
/**
 * Description - This program is for dynamic programming Ferry Loading
 * @author Vinayak Subhash Pingale
 * @email - vpingal1@binghamton.edu
 * @since - 21st March 2015
 */
public class FerryLoading {
	private static int arrayElements[];
	private int ferrySize;
	private int table[][] = new int[100][100];
	public void assignArray(int ferry,int[] inputArr) {
		if (inputArr == null || inputArr.length == 0) {
			return;
		}
		FerryLoading.arrayElements = inputArr;
		this.ferrySize = ferry;
		count(ferrySize,arrayElements.length - 1);
	} 
	
	void count(int ferrySize,int noOfElements){
		int i,j,sum,carSize;
		int resultantArray[] = new int[noOfElements+1];
		sum=0;
		for(i=0;i<noOfElements+1;i++){
			carSize = arrayElements[i];
			sum += arrayElements[i];
			for(j=0;j<ferrySize+1;j++){
				table[i][j] = 0;
				if(i!=0){
					if(j==0){
						if(sum <= ferrySize){
							table[i][j] = 1;
						}
						continue;
					}
					if(j == carSize){
						if(sum - j <= ferrySize){
							table[i][j] = 1;
						}
						continue;				
					}
					if(table[i-1][j] == 1){
						if(sum - j <= ferrySize){
							table[i][j] = 1;
						}
						continue;				
					}
					if((j-carSize) >= 0) {
						if(table[i-1][j-carSize] == 1){
							table[i][j] = 1;
							continue;
						}
					}
				}
			}
		}
		// Back-tracking the table matrix for the possible solutions
		i=noOfElements;
		j=ferrySize;
		while(i>0 && j>=0){
			carSize = arrayElements[i];
			resultantArray[i] = -1;
			if(table[i][j] == 0){
				i--;			
				continue;
			}
			if(table[i][j] == 1){
				if(table[i-1][j] == 1){
					resultantArray[i] = 0;
				}else if(table[i-1][j] == 0){
					resultantArray[i] = 1;
					j = j - carSize;
				}
			}
			i--;
		}
		
		// To display the number of cars that can be accommodated in the ferry
		int total = 0;
		for(i=1;i<=noOfElements;i++) {
			if(resultantArray[i] == -1) 
				break;
			total += 1;
		}
		System.out.println(total);
		// print the contents of left and right array where the 1 is represented as L and 0 is represented as R
		for(i=1;i<=noOfElements;i++){
			if(resultantArray[i] == 1)
				System.out.println("L");
			else if(resultantArray[i] == 0)
				System.out.println("R");
		}
	//
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in); 
		int ferry = scanner.nextInt();
		if(ferry <= 0) {
			System.exit(1);
		}
		int nosElement = scanner.nextInt();
		int[] arrayElements = new int[nosElement+1];
		arrayElements[0] = 0;
		for (int countEntered = 1; countEntered < nosElement+1; ++countEntered) {
			arrayElements[countEntered] = scanner.nextInt();;
		}
		
		FerryLoading ferryLoading = new FerryLoading();
		ferryLoading.assignArray(ferry,arrayElements);
		scanner.close();
	}

}
