import java.util.*;
public class Main {
    static int C;
    static int n;
    static int cache[][];
    static int tr[][];
    static int res[];
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        C= sc.nextInt();
        res = new int[C];
        for(int i=0;i<C; i++){
            n= sc.nextInt();
            tr = new int[n][n];
            cache = new int [n][n];
            for(int j=0;j<n;j++){
                for(int k=0;k<=j;k++){
                    tr[j][k]=sc.nextInt();
                    cache[j][k]=-1;
                }
            }
        res[i]= path(0, 0);
        }
    for(int x=0; x<res.length;x++){
        System.out.println(res[x]);
    }
    }
    public static int path(int y, int x){
        if (y==n-1) return tr[y][x];
        if (cache[y][x]!=-1) return cache[y][x];
        return cache[y][x]=Math.max(path(y+1, x), path(y+1, x+1))+tr[y][x];
    }
}
