import java.util.Scanner;

public class MissingNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long n = scanner.nextInt();
        long totalSum = n * (n + 1) / 2;

        long sumOfNMinus1 = 0;

        for (long i = 1; i < n; i++) {
            long num = scanner.nextLong();
            sumOfNMinus1 += num;
        }

        long difference = totalSum - sumOfNMinus1;

        System.out.println(difference);

        scanner.close();
    }
}
