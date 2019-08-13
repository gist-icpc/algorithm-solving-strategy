import java.util.*;

public class Main {
	static int C;
	static int n;
	static int Board[][];
	static int Cache[][];

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		C=sc.nextInt(); //전체 케이스의 수를 받는다.
		boolean end[]=new boolean[C];
		for(int cnt=1; cnt<=C; cnt++) {
			n= sc.nextInt(); //각 케이스 당 게임판의 크기를 받는다.
			Board=new int[n][n];
			Cache=new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					Board[i][j]=sc.nextInt(); //게임판의 숫자들을 각각 받는다.
					Cache[i][j]=-1; // //캐시 행렬의 모든 값들을 -1로 초기화시킨다.
				}
			}
			end[cnt-1] = jump2(0, 0); //각 케이스의 결과를 저장한다.
		}
		for(int x=0;x<C;x++) {
			System.out.println(end[x]? "YES" : "NO");
		}
	}
	public static boolean jump2(int y, int x) {
		if(y>=n||x>=n) {return false;} //게임판 범위를 벗어나면 false 출력
		if(y==n-1&&x==n-1) {return true;} //맨 끝에 도달하면 true 출력
		if(Cache[y][x]==1) {return false;} //이미 한번 계산했던 위치인 경우 더 이상 계산을 하지 않는다.
		else Cache[y][x]=1; //처음 계산하는 위치인 경우 1을 대입하여 계산 여부를 바꾼다.
		int jumpsize=Board[y][x];
		return (jump2(y+jumpsize, x)||jump2(y, x+jumpsize)); //오른쪽이나 아래로 이동한다.
	}
}
