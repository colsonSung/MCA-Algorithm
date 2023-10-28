package rookie;

public class PrintB {
    //print each bit of the int number
    public static void print(int num){
        for (int i=31;i>=0;i--){
            System.out.print((num & 1<<i) == 0 ? "0":"1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //PrintB.print(Integer.MAX_VALUE);

        int a = 78896;
        int b = 33366;
        PrintB.print(a);
        PrintB.print(b);

        System.out.println("--------");
        PrintB.print(a^b); //相同为0，不同为1

    }
}
