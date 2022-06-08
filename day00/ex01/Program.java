import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int iterator = 0;
        boolean is_prime = true;
        int exit_code = 0;
        int sqrt;

        if (n < 2)
            exit_code = IllegalArgument();
        else if (n == 2)
            System.out.println(is_prime + " 1");
        else{
            sqrt = ft_sqrt(n);
            for (int i = 2; i <= sqrt + 1; i++){
                iterator++;
                if (n % i == 0) {
                    is_prime = false;
                    break;
                }
            }
            System.out.println(is_prime + " " + iterator);
        }
        System.exit(exit_code);
    }

    private static int IllegalArgument(){
        System.err.println("IllegalArgument");
        return -1;
    }

    private static int ft_sqrt(int n){
        long begin = 1;
        long last = n;
        long middle;
        long res = 0;

        while (begin != last){
            middle = (begin + last) / 2;
            if (middle * middle == n)
                return (int)middle;
            else if (middle * middle < n) {
                begin = middle + 1;
                res = middle;
            } else {
                last = last - 1;
            }
        }
        return (int)res;
    }
}