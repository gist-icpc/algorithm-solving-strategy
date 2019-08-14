import java.util.*;

public class Main{
    static int C;
    static int N;
    static int S[];
    static int cache[];
    static int result[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        C = sc.nextInt();
        result = new int[C];
        for (int i = 0; i < C; i++) {
            N = sc.nextInt();
            int maxlen = 0;
            S = new int[N];
            cache = new int[N];
            for (int j = 0; j < N; j++) {
                S[j] = sc.nextInt();
                cache[j]=-1;
            }
            for (int k = 0; k < N; k++) {
                maxlen = Math.max(maxlen, lis(k));
            }
            result[i] = maxlen;
        }
        for (int l = 0; l < result.length; l++) {
            System.out.println(result[l]);
        }
    }
    public static int lis(int start){
        if (cache[start]!=-1) return cache[start];
        cache[start]=1;
        for(int next=start+1;next<N;next++){
            if(S[start]<S[next]){
                cache[start]=Math.max(cache[start], lis(next)+1);
            }
        }
        return cache[start];
    }
}
