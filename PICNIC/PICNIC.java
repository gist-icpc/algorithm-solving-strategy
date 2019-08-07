import java.util.*;
import java.io.*;

public class main_{
	static Scanner sc= new Scanner(System.in);
	static int N = 0; //전체 인원수를 받는다.
	static int M = 0; //서로 친구인 모든 쌍의 개수를 받는다.
	static int cnt = 0; //각 상황의 결과를 받는다.
	static boolean[][] friends; // 2치원 행렬을 통해 친구여부를 나타내는 다음값들을 이에 기록한다.
	static boolean[] taken; // 재귀 함수가 돌아가는 과정에서 짝이 지어졌는지 여부를 기록한다.
	static int[] count; // 각 케이스의 결과(cnt)를 배열에 저장한다.
	public static void main(String[] args) {
		int C=sc.nextInt(); //전체 케이스의 수를 받는다.
		count=new int[C];
		for(int i=0;i<C;i++){
			cnt=0;
			solution(); 
			count[i]=cnt; 
		}
		for(int j=0;j<C;j++){
			System.out.println(count[j]);
		}	 
	
	}
	private static void solution() {
		N = sc.nextInt();
		M = sc.nextInt();
		friends = new boolean[N][N]; 
		taken = new boolean[N]; 
		for (int i = 0; i < M; i++) {
			int person1 = sc.nextInt(); 
			int person2 = sc.nextInt(); 
			friends[person1][person2]=friends[person2][person1] = true;
		} 
		countPairings();
	} 
	private static void countPairings() { 
		int person = firstFree(); //중복을 피하기 위해 짝이 지어지지 않은 제일 앞 사람(숫자가 낮은 순)을 구해서 person으로 받는다.
		if(person<0){
			cnt++; //person의 값이 -1로 리턴되면(즉, 짝이 지어지지 않은 사람이 없단 뜻) 결과값에 1을 추가한다.
			return;
		} 
		for (int i = 0; i < N; i++) { 
			if(i == person) continue; 
			if(friends[person][i] && !taken[i]){ 
				taken[person] =	taken[i] = true; //짝을 지을 사람들의 배열값을 true로 바꾼다.
				countPairings(); 
				taken[person] = taken[i] = false; //또 다른 결과값을 도출하기 위해 배열값을 원래 상태로 리셋시킨다.
			} 
		}
	} 
	private static int firstFree() {
		for (int i = 0; i < N; i++) {
			if(!taken[i]) return i;
		}
		return -1; 
	}
}
