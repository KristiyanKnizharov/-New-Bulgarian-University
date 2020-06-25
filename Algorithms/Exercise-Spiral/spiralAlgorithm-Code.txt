#include <iostream>
#include <vector>
#include <math.h>

using namespace std;

int spiralLevelSearch(vector<int> arr, int l, int r, int x) {
    int curRight = r;
    if (r >= l) {

        int mid = l + (r - l) / 2;

        if (arr[mid] == x){
            return mid + 1;
	}

        if (arr[mid] > x){
            return spiralLevelSearch(arr, l, mid - 1, x);
	}

        return spiralLevelSearch(arr, mid + 1, r, x);
    }

    return curRight + 2;
}

int main() {

    cout << "Enter your number : ";
    int num;
    cin >> num;

    vector<int> borders(10);

    for (int i = 2, j = 0; j < borders.size(); i += 2) {
        borders[j] = i * i;
        j++;
    }

    if (num > borders.size()) {
        while (num > borders[borders.size() - 1]) {
            int nextEl = pow(sqrt(borders[borders.size() - 1]) + 2, 2);
            borders.push_back(nextEl);
        }
    }

    cout << spiralLevelSearch(borders, 0, borders.size() - 1, num);

    return 0;
}
