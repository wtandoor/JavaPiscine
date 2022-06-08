package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime (int number){
        if (number < 2)
            throw new IllegalNumberException();
        else if (number == 2){
            return true;
        } else {
            int i = 2;
            while (i * i <= number){
                if (number % i == 0)
                    return false;
                i++;
            }
        }
        return true;
    }

    public int digitSum(int number){
        int result = 0;
        if (number < 1)
            number *= -1;
        while (number != 0){
            result += number % 10;
            number /= 10;
        }
        return result;
    }
}

class IllegalNumberException extends RuntimeException{
    public IllegalNumberException(){
        System.out.println("Invalid input data");
    }
}
