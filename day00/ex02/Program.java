import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int countOfCoffeeRequest = 0;
        int sumOfNumbers = 0;
        while(n != 42){
            sumOfNumbers = ft_sumOfNumbers(n);
            if (is_prime(sumOfNumbers))
                countOfCoffeeRequest++;
            n = sc.nextInt();
        }
        System.out.println("Count of coffee-request – " + countOfCoffeeRequest);
    }

    private static boolean is_prime(int number){
        boolean is_primed = true;
        int sqrt;

        if (number < 2)
            return false;
        else if (number == 2)
            return true;
        else{
            sqrt = ft_sqrt(number);
            for (int i = 2; i <= sqrt + 1; i++){
                if (number % i == 0) {
                    is_primed = false;
                    break;
                }
            }
        }
        return (is_primed);
    }

    private static int ft_sqrt(int number){
        long begin = 1;
        long last = number;
        long middle;
        long res = 0;

        while (begin != last){
            middle = (begin + last) / 2;
            if (middle * middle == number)
                return (int)middle;
            else if (middle * middle < number) {
                begin = middle + 1;
                res = middle;
            } else {
                last = last - 1;
            }
        }
        return (int)res;
    }

    private static int ft_sumOfNumbers(int numbers){
        int number = numbers;
        int result = 0;
        int division = 10;
        while (number != 0) {
            result += number % division;
            number /= 10;
        }
        return result;
    }
}