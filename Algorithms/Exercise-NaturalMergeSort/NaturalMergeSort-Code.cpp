#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int countRuns(int arr[], int n){
	int count = 1;
	for(int i = 0; i < n-1; i++){
	    if(arr[i] > arr[i+1]){
		count++;
	    }
	}
	
	return count;
}

int main() {
       
	int n, temp;
	cin >> n;
	int arr[n];
	for(int i = 0; i < n; i++){
	    cin >> temp;
	    arr[i] = temp;
	}

	int runs = countRuns(arr, n);
	cout << runs << endl;

	cout << (runs < n/2) << endl;

   	return 0;
}
