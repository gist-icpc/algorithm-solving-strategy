#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cmath>

#define INF 100*1000*1000

int n, s;
int data[100];
int rsums[100][100];
int dp[100][11];

int eval(int ind, int k) {
	//ind : current index, k : remaining 'means'
	
	if (ind == n)
		return 0;
	
	if (k == 0 && ind < n) {
		//used up 'means' too early
		return INF;	//something really big
	}
	
	if (dp[ind][k] != -1) {
		return dp[ind][k];
	}
	
	int x = INF;
	for (int i=ind;i<n;i++) {
		x = std::min(x, rsums[ind][i] + eval(i+1, k-1));
		//printf("%d %d %d %d\n", ind, k, i, x);
	}
	
	return dp[ind][k] = x;
}


int main() {
	int C;
	scanf("%d\n", &C);
	for (int test=0;test<C;test++) {
		scanf("%d %d\n", &n, &s);
		
		for (int i=0;i<n;i++) {
			for (int j=1;j<=s;j++) {
				dp[i][j] = -1;
			}
		}
		
		for (int i=0;i<n;i++) {
			scanf("%d", &data[i]);
		}
		
		std::sort(data, data+n);
		
		for (int i=0;i<n; i++) {
			int len=0, sum=0, ssum=0;
			
			for (int j=i; j<n; j++) {
				int m, rs=0;
				float mean;
				
				len++;
				sum+= data[j];
				ssum+= data[j]*data[j];
				
				mean = (float)sum / len;
				m = mean - (int)mean > 0.5f ? (int)mean+1 : (int)mean;
				
				rsums[i][j] = len*m*m - 2*m*sum + ssum;
				
				//printf("%2d ", rsums[i][j]);
			}
			//printf("\n");
		}
	
	
		printf("%d\n", eval(0, s));
	}
}