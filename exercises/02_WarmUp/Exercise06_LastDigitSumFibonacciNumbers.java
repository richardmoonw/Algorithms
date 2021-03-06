// The goal in this problem is to find the last digit of a sum of the first n
// Fibonacci numbers.

import java.util.Scanner;

public class Exercise06_LastDigitSumFibonacciNumbers {

    public static void main(String[] args) {

        // Create the scanner for user input.
        Scanner input = new Scanner(System.in);

        long huge_number, number_of_chunks, last_chunk_size;
        int pisano_period_of_10, sum_last_digit;

        // The Pisano Period of 10 is 60.
        pisano_period_of_10 = 60;

        // Ask the user to enter the input.
        huge_number = input.nextLong();

        input.close();

        // The sum of the last digits of a Fibonacci Sequence can be seen as:
        // { 
        //   [ 
        //     Number of chunks of size 60 that fit in the number (the Pisano Period of 10 is 60) * 
        //     last digits' sum of each chunk 
        //   ] +
        //   Last digits sum of an additional chunk of N % 60 size (being N a huge number) 
        // } % 10. 
        // The reason for using Pisano is because we can reuse the sum of a chuck in all 
        // the others.
        // The reason for using the Pisano Period of 10 is because any number 
        // modulo 10 gives us its last digit.
        number_of_chunks = huge_number / pisano_period_of_10;
        last_chunk_size = huge_number % pisano_period_of_10;

        // Calculate the last digits' sum of a chunk.
        sum_last_digit = calculateLastDigitOfFibonacciSum(pisano_period_of_10 - 1);

        // Calculate the last digit's sum of all the chunks.
        sum_last_digit = (int) ((number_of_chunks % 10) * (sum_last_digit % 10)) % 10;

        // Add the last digits' sum of the last chunk.
        sum_last_digit = (sum_last_digit + calculateLastDigitOfFibonacciSum((int) last_chunk_size)) % 10;
        
        System.out.println(sum_last_digit);
    }

    // Method used to calculate the addition of the last digits of a Fibonacci sequence.
    private static int calculateLastDigitOfFibonacciSum(int number) {
        int temp, previous, current, sum_last_digit;
        sum_last_digit = 0;
        previous = 0;
        current = 1;

        // If the Fibonacci last digit sum requested is for F0 or F1, then return the values directly.
        if (number == 0) {
            return 0;
        } else if (number == 1) {
            return 1;
        }

        for(int i = 2; i <= number; i++) {

            // If it is the first iteration (we shall add the value of F1).
            if(i == 2) {
                sum_last_digit = (sum_last_digit + current) % 10;
            }

            temp = previous;
            previous = current;
            current = (temp + current) % 10;
            sum_last_digit = (sum_last_digit + current) % 10;
        }

        return sum_last_digit;
    }

}