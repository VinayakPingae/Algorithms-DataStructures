/*

Name - Vinayak Subhash Pingale
bmail -  vpingal1@binghamton.edu
Assignment - Assignment 07 Kruskal
Date - April, 21st 2014

*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Data structure for edges 
typedef struct Edge
{
    int source;
	int destination;
	int weight;
}Edge;

// Data structure for Graph
struct Graph
{
    int V;
	int E;
    struct Edge* edge;
};
 
struct subset
{
    int parent;
    int rank;
};
//D isjoin data set for the find function
int 
find(struct subset subsets[], int i)
{
    if (subsets[i].parent != i)
        subsets[i].parent = find(subsets, subsets[i].parent);
    return subsets[i].parent;
}

void 
link(struct subset subsets[],int x, int y) {
	if (subsets[x].rank < subsets[y].rank)
        subsets[x].parent = y;
    else if (subsets[x].rank > subsets[y].rank)
        subsets[y].parent = x;
    else
    {
        subsets[y].parent = x;
        subsets[x].rank++;
    }
}
//Disjoin data set for the Union function
void 
Union(struct subset subsets[], int x, int y)
{
   link(subsets,find(subsets, x),find(subsets, y));    
}

int 
myComp(const void* a, const void* b)
{
    return (*(Edge *)a).weight - (*(Edge *)b).weight;
}
 
int 
KruskalMST(struct Graph* graph)
{
    int V = graph->V;
    struct Edge result[V];  
    int e = 0;  
    int i = 0;  
 	int totalWeight = 0; 
    qsort(graph->edge, graph->E, sizeof(graph->edge[0]), myComp);
    
	struct subset *subsets = (struct subset*) malloc( V * sizeof(struct subset) );
 	int v;
    for (v = 0; v < V; ++v)
    {
        subsets[v].parent = v;
        subsets[v].rank = 0;
    }

    while (e < V - 1)
    {
        struct Edge next_edge = graph->edge[i++];
 
        int x = find(subsets, next_edge.source);
        int y = find(subsets, next_edge.destination);
        if (x != y)
        {
            result[e++] = next_edge;
            Union(subsets, x, y);
        }
    }
	for (i = 0; i < e; ++i) {
        printf("%d %d %d\n", result[i].source, result[i].destination,result[i].weight);
		totalWeight+=result[i].weight; 
	}
	free(subsets);
	return totalWeight;
}
 
int 
main()
{
 	int V,E,i,cost;	
	scanf("%d",&V);
	scanf("%d",&E);
    struct Graph* graph = (struct Graph*) malloc( sizeof(struct Graph) );
    graph->V = V;
    graph->E = E;
 
    graph->edge = (struct Edge*) malloc( graph->E * sizeof( struct Edge ) );
 
 	for(i = 0; i < E;i++) {
		scanf("%d",&graph->edge[i].source);
		scanf("%d",&graph->edge[i].destination);
		scanf("%d",&graph->edge[i].weight);
	}   
    cost = KruskalMST(graph);
 	printf("Total Cost: %d\n",cost);    
	free(graph);
    return 0;
}
