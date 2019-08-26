
import java.util.Arrays;
import java.util.Scanner;

public class PI {
    static int C;
    static String N;
    static int[] cache;
    static final int INF = 987654321;
    static int[] result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        C = sc.nextInt();
        result = new int[C];
        cache = new int[10002];
        for (int i = 0; i < C; i++) {
            N = sc.next();
            Arrays.fill(cache, -1);
            result[i] = memorize(0);
        }

        for (int x=0; x<result.length;x++){
            System.out.println(result[x]);
        }
    }

    public static int memorize(int begin) {
        if (begin == N.length()) return 0;
        if (cache[begin] != -1) return cache[begin];
        cache[begin] = INF;
        for (int i = 3; i <=5; i++) {
            if (begin + i <= N.length()) {
                cache[begin] = Math.min(cache[begin], memorize(begin + i) + classify(begin, begin + i - 1));
            }

        }
        return cache[begin];
    }

    public static int classify(int a, int b) {
        int len=b-a+1;
        String M = N.substring(a, a+len);
        char[] C = M.toCharArray();
        //난이도 1인 동일한 숫자인가를 확인
        if (isSameChar(C)) return 1;
        //난이도 2, 5인 등차수열 여부를 확인하고 차수가 1인지 아닌지에 따라 난이도 2 혹은 5 리턴
        boolean progressive = true;
        for (int i = 0; i < M.length()-1; i++) {
            if (C[i + 1] - C[i] != C[1] - C[0]) {
                progressive = false;
                break;
            }
        }
        if (progressive && Math.abs(C[1] - C[0]) == 1) return 2;
        if (progressive) return 5;
        //난이도 4인 교대 순열 확인
        boolean alternating = true;
        for (int i = 0; i < M.length(); i++) {
            if (C[i] != C[i % 2]) {
                alternating = false;
                break;
            }
        }
        if (alternating) return 4;
        return 10; //그 외 모든 수열은 난이도 10

    }

    public static boolean isSameChar(char[] C) {
        for (char ch : C) {
            if (C[0] != ch)
                return false;
        }
        return true;
    }
}
