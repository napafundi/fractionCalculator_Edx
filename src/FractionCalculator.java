import java.util.Scanner;

public class FractionCalculator {
    private static boolean negativeNumber = false;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        intro();
        String operation = getOperation(scan);
        while (!operation.equals("Q")) {
            Fraction fraction1 = getFraction(scan);
            Fraction fraction2 = getFraction(scan);
            System.out.println(calculation(fraction1, fraction2, operation));
            operation = getOperation(scan);
        }
        scan.close();
    }

    private static void intro() {
        System.out.println("This is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers");
    }

    private static String getOperation(Scanner scan) {
        System.out.print("Please enter an operation (+, -, /, *, = or Q to quit):");
        String operation = scan.next();
        char testChar = operation.charAt(0);
        while ("+-/*=Q".indexOf(testChar) == -1) {
            System.out.print("Invalid input (+, -, /, *, = or Q to quit)");
            operation = scan.next();
            testChar = operation.charAt(0);
        }
        return operation;
    }

    private static boolean isNumber(String input) {
        if (input.length() == 0) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean validFraction(String input) {
        if (input.length() == 0) {
            return false;
        }
        if (input.charAt(0) == '-') {
            negativeNumber = true;
            input = input.substring(1);
        } else if (input.indexOf('-',1) != -1) {
            return false;
        }
        if (isNumber(input)) {
            return true;
        } else if (input.indexOf('/') != -1) {
            String[] fraction = input.split("/");
            if (isNumber(fraction[0]) && isNumber(fraction[1]) && Integer.parseInt(fraction[1]) != 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    private static Fraction getFraction(Scanner scan) {
        String fraction;
        int multFact = 1;
        System.out.print("Please enter a fraction (a/b) or integer (a) (negative signs must be at the beginning): ");
        fraction = scan.next();
        while (!validFraction(fraction)) {
            System.out.println("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
            fraction = scan.next();
        }
        if (isNumber(fraction)) {
            return new Fraction(Integer.parseInt(fraction), 1);
        }
        if (negativeNumber) {
            negativeNumber = false;
            multFact = -1;
            fraction = fraction.substring(1);
        }
        if (isNumber(fraction)) {
            return new Fraction(Integer.parseInt(fraction) * multFact, 1);
        }
        String[] newFraction = fraction.split("/");
        return new Fraction(Integer.parseInt(newFraction[0]) * multFact, Integer.parseInt(newFraction[1]));
    }

    private static String calculation(Fraction fraction1, Fraction fraction2, String operation) {
        String str = "";
        Fraction fraction3;
        switch (operation) {
            case "+":
                fraction3 = fraction1.add(fraction2);
                break;
            case "-":
                fraction3 =  fraction1.subtract(fraction2);
                break;
            case "*":
                fraction3 = fraction1.multiply(fraction2);
                break;
            case "/":
                fraction3 = fraction1.divide(fraction2);
                break;
            case "=":
                str = fraction1.toString() + " = " + fraction2.toString() + " is " + fraction1.equals(fraction2);
                return str;
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
        String answer = "";
        fraction3.toLowestTerms();
        if (fraction3.getDenominator() == 1) {
            answer += fraction3.getNumerator();
        } else {
            answer += fraction3.toString();
        }
        str = fraction1.toString() + " " + operation + " " + fraction2.toString() + " = " + answer;
        return str;
    }

}
