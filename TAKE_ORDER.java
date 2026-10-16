import java.util.Scanner;

public class TAKE_ORDER {
    private static final int REGULAR_PIZZA_CHOICE = 1;
    private static final int SPECIAL_PIZZA_CHOICE = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printHeading();

        PizzaBase pizza = createPizza(readPizzaChoice(scanner));
        pizza.setToppings(readTopping(scanner));
        pizza.setDiameter(readPositiveInt(scanner, "Enter Diameter in inches: "));
        pizza.setHowMany(readPositiveInt(scanner, "Enter How Many?: "));
        pizza.setDiscount(readDiscount(scanner));
        pizza.calculatePrice();

        double amountDue = new BILL_ORDER().OUT(pizza);
        double payment = readPayment(scanner, amountDue);
        printChange(payment - amountDue);
        scanner.close();
    }

    private static PizzaBase createPizza(int pizzaChoice) {
        return pizzaChoice == SPECIAL_PIZZA_CHOICE ? new SPECIAL_PIZZA() : new PIZZA();
    }

    private static int readPizzaChoice(Scanner scanner) {
        while (true) {
            int choice = readInteger(scanner, "Choice: ");
            if (choice == REGULAR_PIZZA_CHOICE || choice == SPECIAL_PIZZA_CHOICE) {
                return choice;
            }
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }

    private static String readTopping(Scanner scanner) {
        while (true) {
            System.out.print("Enter Toppings (" + PIZZA.TOPPING_PEPPERONI + " / "
                    + PIZZA.TOPPING_HAM_AND_CHEESE + " / " + PIZZA.TOPPING_HAWAIIAN + "): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase(PIZZA.TOPPING_PEPPERONI)) return PIZZA.TOPPING_PEPPERONI;
            if (input.equalsIgnoreCase(PIZZA.TOPPING_HAM_AND_CHEESE)) return PIZZA.TOPPING_HAM_AND_CHEESE;
            if (input.equalsIgnoreCase(PIZZA.TOPPING_HAWAIIAN)) return PIZZA.TOPPING_HAWAIIAN;
            System.out.println("Invalid topping. Accept only Pepperoni, Ham and Cheese, or Hawaiian.");
        }
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            int value = readInteger(scanner, prompt);
            if (value > 0) return value;
            System.out.println("Value must be greater than zero.");
        }
    }

    private static String readDiscount(Scanner scanner) {
        while (true) {
            System.out.print("Enter Type of Discount (" + PIZZA.DISCOUNT_SENIOR_CITIZEN + " / "
                    + PIZZA.DISCOUNT_VIP_CARD + " / " + PIZZA.DISCOUNT_NONE + "): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase(PIZZA.DISCOUNT_SENIOR_CITIZEN)) return PIZZA.DISCOUNT_SENIOR_CITIZEN;
            if (input.equalsIgnoreCase(PIZZA.DISCOUNT_VIP_CARD)) return PIZZA.DISCOUNT_VIP_CARD;
            if (input.equalsIgnoreCase(PIZZA.DISCOUNT_NONE)) return PIZZA.DISCOUNT_NONE;
            System.out.println("Invalid discount. Accept only Senior Citizen, VIPCard, or No Discount.");
        }
    }

    private static double readPayment(Scanner scanner, double amountDue) {
        while (true) {
            double payment = readDouble(scanner, "Enter Amount Received: PHP ");
            if (Double.isFinite(payment) && payment > amountDue) return payment;
            System.out.println("Amount Received must be strictly greater than Amount Due.");
        }
    }

    private static int readInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }

    private static void printHeading() {
        System.out.println("PIZZA ORDERING SYSTEM");
        System.out.println("Enter Pizza Type:");
        System.out.println("1. Regular Pizza");
        System.out.println("2. Special Pizza");
    }

    private static void printChange(double change) {
        System.out.printf("Change: PHP %.2f%n", change);
    }
}
