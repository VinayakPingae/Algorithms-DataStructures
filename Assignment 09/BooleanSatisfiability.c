/*

Name - Vinayak subhash pingale
Email - vpingal1@binghamton.edu
Programming Assignment no 09 - Boolean Satisfiability Algorithm

*/

#include <stdlib.h>
#include <stdio.h>
// Maximum literals we are doing for this assignment is 3-CNF Satisfiability problems
#define MAXLITERALS 3

int nVariables, nClause;
int isClauseTrue;
// A utility complement function for performing operations related to the complementing value depending on the inputs provided.
int 
complement(int value)
{
	return (value==0) ? 1 : 0;
}

// 3-CNF Satisfiability function
int 
CNF_3sat(int depth,int x[],int inputValues[nClause][nVariables], int nVariables,int  nClause) {
		int i,j;
		if(depth == nVariables) {
			for(i=0; i<nClause; i++) {
					int checkClause = 0;	
					/* Checking the literals in the clause to find out atleast there is no true value for a clause.*/
					for(j=0; j < MAXLITERALS; j++) {
						int literalValue = inputValues[i][j];
						if(literalValue < 0) {
							int absoluteLiteral = abs(literalValue);
							checkClause = checkClause + complement(x[absoluteLiteral-1]);
						} else {
							checkClause = checkClause + x[literalValue-1];
						}
					}
				// There is atleast one possible solution available
				if(checkClause > 0) {
					isClauseTrue=1;
				} else {
					isClauseTrue=0;
					break;
				}	
			}
			// There is possible solution for every value of the truth table.
			if(isClauseTrue==1)
			{
				for(i=0;i<nVariables;i++)
				{
					printf("%d ",x[i]);
				}
				printf("\n");
				// Only value is required hence exiting after printing that particular value.
				exit(1);
			} 
			return isClauseTrue;
		}

		/* Recursive calls that has been created for the truth table implementation 
		First the depth which is zero for the first iteration will pick up value as zero for all the x array and then it will keep on making the truth table depending on the recursion.	
		*/
		x[depth]=0;
		CNF_3sat(depth+1,x,inputValues,nVariables,nClause);
		x[depth]=1;
		CNF_3sat(depth+1,x,inputValues,nVariables,nClause);
}

// Driver function for this 3-CNF Satisfiability problem
int 
main(int argc,char* argv[])
{
    int i,j;
	// Accepting the number of variables and clauses from the user
	scanf("%d",&nVariables);
	scanf("%d",&nClause);
	// defining the array depending upon the variables for representing the values of the truth table for that many iterations
	int x[nVariables];
	int inputValues[nClause][nVariables];
	// Initializing it to the value 0
	for(i=0; i<nVariables; i++) {
		x[i] = 0;
	}
	// Accpeting the clauses from the user
	for(i=0; i<nClause; i++) {
		for(j=0;j<MAXLITERALS;j++) {
			scanf("%d",&inputValues[i][j]);
		}
	}
	// Calling the CNF_3Sat function with passed values and depth for that particular function
	int isResultExist = CNF_3sat(0,x,inputValues,nVariables,nClause);
	// Check is represnted here to determine whethere there are any satisfiable solution or not
	if(isResultExist == 0) {
		printf("\n No Satisfiable Solution \n");
	}
	return 0;
}

