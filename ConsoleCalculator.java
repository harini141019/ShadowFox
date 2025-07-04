import java.util.Scanner;

public class ConsoleCalculator {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Console Calculator =====");
            System.out.println("1. Basic Arithmetic");
            System.out.println("2. Scientific Calculations");
            System.out.println("3. Unit Conversions");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    basicArithmetic(scanner);
                    break;
                case 2:
                    scientificCalculations(scanner);
                    break;
                case 3:
                    unitConversions(scanner);
                    break;
                case 4:
                    System.out.println("Exiting Calculator. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Basic Arithmetic
    public static void basicArithmetic(Scanner scanner) {
        System.out.println("\n--- Basic Arithmetic ---");
        System.out.print("Enter first number: ");
        double a = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double b = scanner.nextDouble();

        System.out.println("Choose operation:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        int op = scanner.nextInt();

        switch (op) {
            case 1:
                System.out.println("Result: " + (a + b));
                break;
            case 2:
                System.out.println("Result: " + (a - b));
                break;
            case 3:
                System.out.println("Result: " + (a * b));
                break;
            case 4:
                if (b != 0)
                    System.out.println("Result: " + (a / b));
                else
                    System.out.println("Error: Division by zero");
                break;
            default:
                System.out.println("Invalid operation.");
        }
    }

    // Scientific Calculations
    public static void scientificCalculations(Scanner scanner) {
        System.out.println("\n--- Scientific Calculations ---");
        System.out.println("1. Square Root");
        System.out.println("2. Exponential (e^x)");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter number: ");
                double num = scanner.nextDouble();
                if (num >= 0)
                    System.out.println("Square root: " + Math.sqrt(num));
                else
                    System.out.println("Error: Cannot take square root of negative number.");
                break;
            case 2:
                System.out.print("Enter exponent value: ");
                double exp = scanner.nextDouble();
                System.out.println("e^" + exp + " = " + Math.exp(exp));
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Unit Conversions
    public static void unitConversions(Scanner scanner) {
        System.out.println("\n--- Unit Conversions ---");
        System.out.println("1. Temperature (Celsius ↔ Fahrenheit)");
        System.out.println("2. Currency (INR ↔ USD)");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                temperatureConversion(scanner);
                break;
            case 2:
                currencyConversion(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Temperature Conversion
    public static void temperatureConversion(Scanner scanner) {
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        int choice = scanner.nextInt();

        System.out.print("Enter temperature: ");
        double temp = scanner.nextDouble();

        switch (choice) {
            case 1:
                double f = (temp * 9 / 5) + 32;
                System.out.println("Fahrenheit: " + f);
                break;
            case 2:
                double c = (temp - 32) * 5 / 9;
                System.out.println("Celsius: " + c);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Currency Conversion
    public static void currencyConversion(Scanner scanner) {
        final double USD_RATE = 83.0; // 1 USD = 83 INR (you can update this rate)
        System.out.println("1. INR to USD");
        System.out.println("2. USD to INR");
        int choice = scanner.nextInt();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        switch (choice) {
            case 1:
                System.out.println("USD: " + (amount / USD_RATE));
                break;
            case 2:
                System.out.println("INR: " + (amount * USD_RATE));
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
