#include<stdio.h>
#define MAX 99999
int m[100][100] = {{0}};

int summation(int *w,int i,int j){
	int k,sum=0;
	for(k=i;k<=j;k++){
		sum = sum + w[k];
	}
	return sum;
}

int OBST(int *w,int i,int j){
	int q,r;
	if(i==j){
		return 0;
	}
	if(m[i][j] > 0){
		return m[i][j];
	}
	m[i][j] = MAX;
	for(r=i;r<j;r++){
		q = OBST(w,i,r) + OBST(w,r+1,j) + summation(w,i,j);
		if(q < m[i][j]){
			m[i][j] = q;
		}
	}
	return m[i][j];
}

int main(){
	int num,i,minCost;
	scanf("%d",&num);
	int w[num];
	for(i=0;i<num;i++){
		scanf("%d",&w[i]);
	}
	minCost = OBST(w,0,num-1);
	printf("Minimum cost is=%d\n",minCost);
	return 0;
}
