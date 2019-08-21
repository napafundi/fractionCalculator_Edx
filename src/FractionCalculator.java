import java.util.Scanner;

public class FractionCalculator {

    public static void main(String[] args) {

    }

    private String getOperation(Scanner input) {
        System.out.print("Please enter an operation (+, -, /, *, = or Q to quit):");
        String operation = input.next();
        char testChar = operation.charAt(0);
        while ("+-/*=Q".indexOf(testChar) == -1) {
            System.out.print("Invalid input (+, -, /, *, = or Q to quit)");
            operation = input.next();
            testChar = operation.charAt(0);
        }
        return operation;
    }

}
