#include<stdio.h>
 
void count( int S[], int n, int m )
{
    int i, j;
 	int totalDebitAmountToCash = m;
 	//   we need to have a bottom up approach constructed for this value as count as 0
	int table[n+1][m+1];
    //printf("%d %d",m,n);
    // as i have considered the 0th row i have filled all the zeroeth rows here
    for (i=0; i< m+1; i++)
        table[0][i] = 0;
	for (i = 0; i < n+1; i++)
		table[i][0] = 0;
	// I am using bottom up arppoach for filling up the rest of the values
    for (i = 1; i < n+1; i++)
    {
		//printf("\n");
        for (j = 1; j < m+1 ; j++)
        {
            // Count of solutions including S[j]
			  table[i][j] = 0;
			  if(j == S[i-1]) {
				table[i][j] = S[i-1];
			  }
			
			 if(table[i-1][j] > 0) {
				table[i][j] = table[i-1][j];
			  }
			 if(j > S[i-1]) {
				if(table[i-1][j-S[i-1]] > 0 ) { // Greater than
					table[i][j] = j;
				} 
			 }
       }
    }
	if(table[n][m] == 0)  {
	  printf(" Not Possible \n");	
	}
	
	while(totalDebitAmountToCash > 0) {
		int count = 1;
		while(count < n+1){
			if(table[count][totalDebitAmountToCash] != 0) {
				printf("%d\n",count);
				totalDebitAmountToCash = totalDebitAmountToCash - S[count-1];
				break;
			}
			count++;
		}
	}		
}
 
// Driver program to test above function
int main()
{
    int arr[] = {1,3,2,7,5};
 	count(arr,5,10);
    return 0;
}
