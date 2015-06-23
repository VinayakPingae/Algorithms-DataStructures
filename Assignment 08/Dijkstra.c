/*

Name - Vinayak subhash pingale
Email - vpingal1@binghamton.edu
Programming Assignment no 08 - Dijkstra Algorithm

*/

#include <stdlib.h>
#include <stdio.h>
#define MAXINT 999999

// Constructing min heap node
typedef struct MinHeapNode
{
    int  v, dist;
}minHeapNode;

typedef struct MinHeap
{
    int size;   
    int capacity;
    int* pos;
    minHeapNode **array;  
}minHeap;

// Constructing node
typedef struct Node
{
	int d, w;
	struct Node* next;
}node;
// Constructing Adjacency list
typedef struct List
{
	node *head;
}list;
// Constructing graph
typedef struct Graph
{
	int V;
	list* array;
}graph;


minHeap* 
createMinHeap(int capacity)
{
	minHeap* mHeap = ( minHeap*) malloc(sizeof( minHeap));
	
	mHeap->size = 0;  
	mHeap->capacity = capacity;
	mHeap->pos = (int *)malloc(sizeof(int)*capacity);
	mHeap->array = (minHeapNode**) malloc(sizeof(minHeapNode*) * mHeap->capacity);// Adding node to the list
	return mHeap;
}

// Creating node with the defined distance and weight
node* 
createNode(int d, int w)
{
	node *newN = (node*) malloc(sizeof(node));
	newN->d = d;
	newN->w = w;
	newN->next = NULL;
	return newN;
}

minHeapNode* 
createHeapNode(int v, int dist)
{
    minHeapNode* newN = (minHeapNode *) malloc(sizeof(minHeapNode));
    newN->v = v;
    newN->dist = dist;
    return newN;
}

void 
swapNode( minHeapNode** a,  minHeapNode** b)
{
    minHeapNode* temp = *a;
	*a = *b;
	*b = temp;
}

// Min heapify logic picked up from the CLRS book
void 
heapify(minHeap* mHeap, int i)
{
	int l = 2 * i + 1;
	int r = 2 * i + 2;
	int smallest = i;
 
	if (l < mHeap->size && mHeap->array[l]->dist < mHeap->array[smallest]->dist)

		smallest = l;

	if (r < mHeap->size && mHeap->array[r]->dist < mHeap->array[smallest]->dist)
		smallest = r;

	if (smallest != i)
	{
		minHeapNode *sNode = mHeap->array[smallest];
		minHeapNode *iNode = mHeap->array[i];

		mHeap->pos[sNode->v] = i;
		mHeap->pos[iNode->v] = smallest;
			    	
		swapNode(&mHeap->array[smallest], &mHeap->array[i]);
		heapify(mHeap, smallest);
	}
}


minHeapNode* 
extractMin(minHeap* mHeap)
{
	if (mHeap->size==0)
	    return NULL;
	
	minHeapNode* root = mHeap->array[0];
	minHeapNode* lastNode = mHeap->array[mHeap->size - 1];
	mHeap->array[0] = lastNode;
	
	mHeap->pos[root->v] = mHeap->size - 1;
	mHeap->pos[lastNode->v] = 0;
	
	mHeap->size = mHeap->size - 1;
	heapify(mHeap, 0);
	
	return root;
}

int 
isInMinHeap(minHeap *minHeap, int v)
{
	if (minHeap->pos[v] < minHeap->size)
		return 1;
	return 0;
}


void 
decreaseKey(minHeap* mHeap, int v, int dist)
{
	int i;
	
	i = mHeap->pos[v];

	mHeap->array[i]->dist = dist;
	while (i && mHeap->array[i]->dist < mHeap->array[(i - 1) / 2]->dist)
	{
		mHeap->pos[mHeap->array[i]->v] = ((i - 1) / 2);
		mHeap->pos[mHeap->array[((i - 1) / 2)]->v] = i;
		swapNode(&mHeap->array[i],  &mHeap->array[((i - 1) / 2)]);
		i = ((i - 1) / 2);
	}
}

 
// Adds an edge to an undirected graph

void 
addEdge(graph* g, int src, int dest, int weight)
{
	// add an edge from src to dest
	node* nNode = createNode(dest,weight);
	nNode->next = g->array[src].head;
	g->array[src].head = nNode;
	// add an edge from dest to src
	nNode = createNode(src,weight);
	nNode->next = g->array[dest].head;
	g->array[dest].head = nNode;
}

// Dijkstra's algorithm

void 
dijkstra(graph* g, int src)
{
	int v, V = g->V; // getting the number of indices from the graph.
	int dist[V];
	int i,max = 0;
	
	minHeap* mHeap = createMinHeap(V);

	for(v = 0; v < V; v++)
	{
		dist[v] = MAXINT;	
		mHeap->array[v] = createHeapNode(v, dist[v]);
		mHeap->pos[v] = v;
	}
	mHeap->array[src] = createHeapNode(src, dist[src]);
	mHeap->pos[src]   = src;
	dist[src] = 0;
	decreaseKey(mHeap, src, dist[src]);
		
	mHeap->size = V;
	
	while ((mHeap->size!=0))
	{		
		minHeapNode* mHeapNode = extractMin(mHeap);
		int u = mHeapNode->v;
	
		node* pNode = g->array[u].head;
		while (pNode != NULL)
		{
			int v = pNode->d;
	
			if ((isInMinHeap(mHeap, v) && dist[u] != MAXINT) && ((pNode->w + dist[u]) < dist[v]))
			{
				dist[v] = dist[u] + pNode->w;
				decreaseKey(mHeap, v, dist[v]);
			}
			pNode = pNode->next;
		}
	}
	
	//print the shortest path from distant vertex
	for(i = 0; i < V; i++)
	{
		if(max < dist[i])
			max = dist[i];
	}
	
	printf("most distant vertex is %d\n",max);
}

int 
main(int argc,char* argv[])
{
    int V, E;
	int u, v, w, i;	

	scanf("%d",&V);
	scanf("%d",&E);
	
	graph* g = (graph *) malloc(sizeof(graph));
	g->V = V;
	g->array = (list *) malloc(sizeof(list) * V);
	
	for(i = 0; i < V; i++)
	{
		g->array[i].head = NULL;
	}

	for(i = 0; i < E; i++)
	{
		scanf("%d %d %d",&u,&v,&w);
		addEdge(g, u, v, w);
	}
	// considering 0 as the path for the dijkstra source
	dijkstra(g, 0);

	return 0;
}


