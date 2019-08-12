import java.util.*;
public class Main {
    static int c;
    static char W[]; //패턴
    static int n;
    static char S[]; //문자열
    static int cache[][]; //캐시

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        for (int i = 0; i < c; i++) {
            String wildcard = sc.next();
            n = sc.nextInt();
            W = wildcard.toCharArray(); //패턴을 문자열로 받아서 배열로 바꾼다.
            ArrayList <String> result = new ArrayList <String>(); //결과값을 각 케이스마다 초기화 시켜줘야 하므로 이 반복문에 집어넣는다.
            ArrayList<String> fileName = new ArrayList<String>(); //파일내용도 동일한 이유로 이 반복문에 집어넣는다.
            cache = new int[101][101];
            for (int j = 0; j < n; j++) {
                fileName.add(sc.next());
                String temp = fileName.get(j);
                S = temp.toCharArray();
                for(int[] arr : cache)
                    Arrays.fill(arr, -1); //-1로 캐시를 초기화시킨다.
                if (matchMemoized(0, 0) == 1) {
                    result.add(temp);
                }
            }
            Collections.sort(result); //각 케이스마다 결과값을 정렬 후 출력
            for(int x=0;x<result.size();x++){
                System.out.println(result.get(x));
            }
        }
    }
    public static int matchMemoized(int w, int s) {
        if(cache[w][s]!=-1) return cache[w][s]; //이미 비교를 한 위치일 경우 다시 계산하지 않고 기존값 리턴
        if(s<S.length && w<W.length && (W[w]=='?'||W[w]==S[s])) {
            return cache[w][s] = matchMemoized(w+1, s+1); //문자열이 동일하거나 ?에 의해 통과되는 경우 다음 문자열을 비교한다.
        }
        if(w==W.length)
            return cache[w][s]=(s==S.length)? 1: 0; //패턴 끝에 도달해서 끝난 경우 문자열도 끝났어야 대응되므로 이를 확인
        if(W[w]=='*') {
            if(matchMemoized(w+1, s)==1||(s<S.length && matchMemoized(w, s+1)==1)){
                return cache[w][s]=1; //*이후로도 패턴과 문자열이 쭉 대응되거나 *에 문자열이 더 대응되는지 확인해서 리턴
            }
        }
        return cache[w][s]=0; //이 외의 경우에는 모두 대응되지 않는 것으로 간주
    }
}