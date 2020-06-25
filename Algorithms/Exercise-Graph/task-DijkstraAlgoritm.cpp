Строим покриващо дърво в ширина от избран връх върху неориентиран граф с еднакви цени по ребрата. Напишете програма, която да връща колко ребра включва най-краткия път до най-отдалечения връх от корена на дървото и колко е неговата дължина. Пример: Вход: 5 6 3 1 2 1 3 2 3 1 5 3 4 4 5 1 Изход: 4 2 6

Input Format

N - брой върхове; R - брой ребра; w - тегло за всяко ребро. Въвеждаме графа чрез ребрата: i - начален връх, j - краен връх. Въвеждаме начален връх за корен на дървото: i0.

Constraints

Без да правим проверки, следим за: - От всеки връх към всеки връх може да има не повече от едно ребро. - Теглата на ребрата са положителни. - Ако имаме N върха, то ги номерираме от 1 до N. - Допускаме, че работим със свързан граф.

Output Format

Изход: * най-отдалеченият връх; * колко ребра има до него по най-късия път от корена; * общата дължина на този път.

Sample Input 0

5 6 2
1 2
1 3
2 3 
1 5 
3 4 
4 5
2
Sample Output 0

4
2
4

#include <cmath>
#include <cstdio>
#include <vector>
#include <stdio.h>
#include <iostream>
#include <algorithm>
using namespace std;
#define MAX 1000

int G[MAX][MAX], //adj list
    c[MAX][MAX], //weight
    U[MAX],     //mark visited vertices
    p[MAX],     //p[i] - predecessor of i
    d[MAX],     //d[i] curr found shortest path (from 0 to n)
    n, m, r;

void dijkstra(int r)
{
    int v,w,min,i,j;
    for(i=1; i<=n; i++)
    {
        U[i]=0;
        p[i]=r;
        d[i]=MAX;
    }
    d[r]=p[r]=0;
    for(i=1; i<=n; i++)
    {
        v=0;
        min=MAX;
        for(j=1; j<=n; j++)
            if(U[j]==0 && d[j]<min)
            {
                v=j;
                min=d[j];
            }
        U[v]=1;
        for(j=1; j <= G[v][0]; j++)
        {
            w=G[v][j];
            if(U[w]== 0 && d[v]+c[v][w]<d[w])
            {
                d[w]=d[v]+c[v][w];
                p[w]=v;
            }
        }
    }
}

int main()
{
    int u,v,w,i,j;
    scanf("%d %d %d",
          &n, &m, &w);

    for(i=1; i<=n; i++)
    {
        G[i][0]=0;
        for(j=1; j<=n; j++)
            c[i][j]=MAX;
        if(i==j)
            c[i][j]=0;
    }

    for(i=1; i<=m; i++)
    {
        scanf("%d %d",
              &u, &v);
        G[u][++G[u][0]]=v;
        G[v][++G[v][0]]=u;
        c[u][v]=c[v][u]=w;
    }

    scanf("%d", &r);
    dijkstra(r);

    int max = -10, countDepth, flagRow, flagCol;
    for(i=1; i<=n; i++){
        //cout << p << " " << p[i] << " ";
        countDepth = 0;
        for (j=1; j <= n; j++) {
            if (G[i][j] > 0)
                countDepth++;

        }
        if (max <= countDepth) {
            max = countDepth;
            flagRow = i;
            flagCol = max;
        }
        //cout << countDepth << "\n";

    }

    cout << G[flagRow][flagCol] << "\n";
    cout << max-1 << "\n";
    cout << (max-1)*w;
    return 0;
}
