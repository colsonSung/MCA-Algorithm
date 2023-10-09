package rookie;

public class FactorialSum {
    public static void main(String[] arg){
        int n = 3;

        System.out.println(fs(n));
    }

    private static int fs(int n) {
        int sum = 0;

        int tmp = 1;
        for(int i=1; i<=n ;i++){
            tmp = tmp * i;
            sum = sum + tmp;
        }
        return sum;
    }
}
