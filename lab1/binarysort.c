#include <stdio.h>
#include <math.h>

int main () {

	int test[] = {1, 3, 6, 8, 9, 10, 14};

	printf("%u\n", binary_sort(test, 10, 0, 6));

}



int binary_sort (int a[], int x, int min, int max) {

	int half = a[(int)(max+min)/2];

	if (min > max)
		return -1;
	else

		if (half > x) {
			binary_sort(a, x, min, ((max+min)/2) - 1);
		}
		else if (half < x) {
			binary_sort(a, x, ((max+min)/2) + 1, max);
		}
		else {
			return (max+min)/2;
		}

}
