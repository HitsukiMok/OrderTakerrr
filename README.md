# Pizza Ordering and Billing System

A console-based Java project for **Programming Activity #6 — PIZZA PIE**. It demonstrates classes and objects, encapsulation, getters and setters, abstraction, interfaces, inheritance, method overriding, runtime polymorphism, object passing, and input validation.

The submission consists of six separate Java files:

```text
PizzaBase.java
Discountable.java
PIZZA.java
SPECIAL_PIZZA.java
BILL_ORDER.java
TAKE_ORDER.java
```

Do not combine these classes into one file. The uppercase class names are unusual in Java but are retained because the activity requires them.

## Program Flow

1. Ask whether the customer wants a regular or special pizza.
2. Ask for the topping, diameter in inches, quantity, and discount type.
3. Validate every input and store it using setters.
4. Calculate the price through the selected pizza object.
5. Pass the pizza object to `BILL_ORDER.OUT()`.
6. Display the order details and amount due.
7. Keep requesting payment until it is greater than the amount due.
8. Calculate and display the customer's change.

Pricing belongs only in the pizza classes. Do not duplicate it in `TAKE_ORDER` or `BILL_ORDER`.

## Pricing Rules

### Toppings

| Topping | Price |
|---|---:|
| Pepperoni | PHP 100.00 |
| Ham and Cheese | PHP 150.00 |
| Hawaiian | PHP 200.00 |

### Size charge

```text
diameterCm = diameterInInches × 2.54
sizeCharge = diameterCm × PHP 5.00
```

### Discounts

| Discount | Rate |
|---|---:|
| Senior Citizen | 20% |
| VIPCard | 12% |
| No Discount | 0% |

### Regular pizza formula

```text
unitPrice = toppingPrice + sizeCharge
subtotal = unitPrice × quantity
discountAmount = subtotal × discountRate
totalPrice = subtotal - discountAmount
```

### Special pizza rule

This project defines the required special behavior as a **PHP 50.00 charge per pizza**. It is added after the regular pizza total and discount are calculated, so the special charge is not discounted.

```text
specialCharge = PHP 50.00 × quantity
specialTotal = regularDiscountedTotal + specialCharge
```

## Authoritative Class and Method Contract

Everyone must use the names and types below. Do not rename a public or protected member without coordinating with the entire team.

### `Discountable.java`

```java
public interface Discountable {
    double getDiscountRate();
}
```

`getDiscountRate()` returns the selected rate as a decimal: `0.20`, `0.12`, or `0.00`.

### `PizzaBase.java`

```java
public abstract class PizzaBase {
    private final String pizzaType;

    protected PizzaBase(String pizzaType);

    public final String getPizzaType();

    public final double getDiameterInCentimeters();

    public abstract String getToppings();

    public abstract void setToppings(String toppings);

    public abstract String getDiscount();

    public abstract void setDiscount(String discount);

    public abstract double getDiscountRate();

    public abstract int getDiameter();

    public abstract void setDiameter(int diameter);

    public abstract int getHowMany();

    public abstract void setHowMany(int howMany);

    public abstract double getPrice();

    public abstract double calculatePrice();
}
```

Responsibilities:

- Store the shared pizza type as a private field.
- Return `"Regular Pizza"` or `"Special Pizza"` through `getPizzaType()`.
- Convert inches to centimeters in the concrete `getDiameterInCentimeters()` method using `getDiameter() * 2.54`.
- Define the operations needed to work with any pizza through a `PizzaBase` reference.
- Never be instantiated directly.

### `PIZZA.java`

```java
public class PIZZA extends PizzaBase implements Discountable {
    public static final String TOPPING_PEPPERONI = "Pepperoni";
    public static final String TOPPING_HAM_AND_CHEESE = "Ham and Cheese";
    public static final String TOPPING_HAWAIIAN = "Hawaiian";

    public static final String DISCOUNT_SENIOR_CITIZEN = "Senior Citizen";
    public static final String DISCOUNT_VIP_CARD = "VIPCard";
    public static final String DISCOUNT_NONE = "No Discount";

    private static final double PEPPERONI_PRICE = 100.00;
    private static final double HAM_AND_CHEESE_PRICE = 150.00;
    private static final double HAWAIIAN_PRICE = 200.00;
    private static final double SIZE_PRICE_PER_CENTIMETER = 5.00;

    private String toppings;
    private String discount;
    private int diameter;
    private int howMany;
    private double price;

    public PIZZA();

    protected PIZZA(String pizzaType);

    @Override
    public String getToppings();

    @Override
    public void setToppings(String toppings);

    @Override
    public String getDiscount();

    @Override
    public void setDiscount(String discount);

    @Override
    public double getDiscountRate();

    @Override
    public int getDiameter();

    @Override
    public void setDiameter(int diameter);

    @Override
    public int getHowMany();

    @Override
    public void setHowMany(int howMany);

    @Override
    public double getPrice();

    protected final void setPrice(double price);

    protected final double getToppingPrice();

    @Override
    public double calculatePrice();
}
```

Required private instance fields:

| Field | Type | Meaning |
|---|---|---|
| `toppings` | `String` | Selected topping |
| `discount` | `String` | Selected discount |
| `diameter` | `int` | Diameter in inches |
| `howMany` | `int` | Number of pizzas |
| `price` | `double` | Calculated total |

Required behavior:

- `PIZZA()` calls the protected constructor with `"Regular Pizza"`.
- `setToppings()` accepts only the three topping constants.
- `setDiscount()` accepts only the three discount constants.
- `setDiameter()` and `setHowMany()` accept only values greater than zero.
- Invalid setters throw `IllegalArgumentException` with a clear message.
- `getToppingPrice()` maps the selected topping to its official price.
- `getDiscountRate()` maps the selected discount to its official decimal rate.
- `calculatePrice()` performs the complete regular pricing formula, stores the result with `setPrice()`, and returns it.
- `setPrice()` remains protected so only `PIZZA` and its subclasses can update a calculated price.

Use the public constants instead of rewriting topping and discount strings in other files.

### `SPECIAL_PIZZA.java`

```java
public class SPECIAL_PIZZA extends PIZZA {
    private static final double SPECIAL_CHARGE_PER_PIZZA = 50.00;

    public SPECIAL_PIZZA();

    public double getSpecialChargePerPizza();

    @Override
    public double calculatePrice();
}
```

Required behavior:

- The constructor calls `super("Special Pizza")`.
- `getSpecialChargePerPizza()` returns `50.00`.
- `calculatePrice()` calls `super.calculatePrice()` instead of copying the parent formula.
- It adds `getSpecialChargePerPizza() * getHowMany()`, stores the result with `setPrice()`, and returns it.

Expected override structure:

```java
@Override
public double calculatePrice() {
    double regularTotal = super.calculatePrice();
    double specialTotal = regularTotal
            + (getSpecialChargePerPizza() * getHowMany());

    setPrice(specialTotal);
    return specialTotal;
}
```

### `BILL_ORDER.java`

```java
public class BILL_ORDER {
    public double OUT(PizzaBase pizza);
}
```

`OUT(PizzaBase pizza)` must:

- Reject `null` with `IllegalArgumentException`.
- Read order information only through public getters.
- Display pizza type, topping, diameter in centimeters, quantity, discount, discount rate, and amount due.
- Format centimeter, percentage, and currency values to two decimal places.
- Read the already calculated total through `pizza.getPrice()` and return it.
- Work with both `PIZZA` and `SPECIAL_PIZZA` objects.

It must not access private fields, repeat the pricing formula, process payment, or calculate change.

### `TAKE_ORDER.java`

```java
import java.util.Scanner;

public class TAKE_ORDER {
    private static final int REGULAR_PIZZA_CHOICE = 1;
    private static final int SPECIAL_PIZZA_CHOICE = 2;

    public static void main(String[] args);

    private static PizzaBase createPizza(int pizzaChoice);

    private static int readPizzaChoice(Scanner scanner);

    private static String readTopping(Scanner scanner);

    private static int readPositiveInt(Scanner scanner, String prompt);

    private static String readDiscount(Scanner scanner);

    private static double readPayment(Scanner scanner, double amountDue);

    private static int readInteger(Scanner scanner, String prompt);

    private static double readDouble(Scanner scanner, String prompt);

    private static void printHeading();

    private static void printChange(double change);
}
```

Method responsibilities:

| Method | Responsibility |
|---|---|
| `main()` | Coordinate the overall program flow |
| `createPizza()` | Return a `PIZZA` or `SPECIAL_PIZZA` as `PizzaBase` |
| `readPizzaChoice()` | Accept only `1` or `2` |
| `readTopping()` | Display and validate the official toppings |
| `readPositiveInt()` | Read a diameter or quantity greater than zero |
| `readDiscount()` | Display and validate the official discounts |
| `readPayment()` | Continue until payment is greater than amount due |
| `readInteger()` | Parse an integer without crashing on invalid text |
| `readDouble()` | Parse a decimal without crashing on invalid text |
| `printHeading()` | Print the program title |
| `printChange()` | Format and display the customer's change |

Demonstrate runtime polymorphism without casting:

```java
PizzaBase pizza = createPizza(pizzaChoice);

pizza.setToppings(topping);
pizza.setDiameter(diameter);
pizza.setHowMany(quantity);
pizza.setDiscount(discount);
pizza.calculatePrice();

BILL_ORDER bill = new BILL_ORDER();
double amountDue = bill.OUT(pizza);
```

Read input with `Scanner.nextLine()` and parse it in validation loops. This avoids skipped prompts caused by mixing `nextInt()` or `nextDouble()` with `nextLine()`.

## Input Validation

| Input or condition | Required behavior |
|---|---|
| Pizza type is not `1` or `2` | Ask again |
| Diameter is `<= 0` | Request a positive diameter |
| Quantity is `<= 0` | Request a positive quantity |
| Invalid topping | Accept only the three official toppings |
| Invalid discount | Accept only the three official discounts |
| Non-numeric numeric input | Reject without crashing |
| Amount received is `<=` amount due | Continue asking |

The PDF requires payment to be **strictly greater than** the amount due. Exact payment must therefore be rejected.

## Team Distribution

### Member 1 — Architecture Lead

Assigned files:

```text
PizzaBase.java
Discountable.java
README.md
docs/PLAN.md
```

Responsibilities:

- Implement and publish the shared contract first.
- Implement the abstract class and interface.
- Review changes for incompatible names or duplicated logic.
- Integrate the six Java files and compile after each merge.
- Run the final tests and audit the 100-point rubric.

### Member 2 — Regular Pizza

Assigned file:

```text
PIZZA.java
```

Responsibilities:

- Implement the required private fields, constants, constructors, getters, and setters.
- Validate topping, discount, diameter, and quantity.
- Implement topping prices, discount rates, and the regular pricing formula.
- Test every topping and discount option.

### Member 3 — Special Pizza

Assigned file:

```text
SPECIAL_PIZZA.java
```

Responsibilities:

- Extend `PIZZA` and identify the object as `"Special Pizza"`.
- Add the PHP 50.00 per-pizza charge.
- Override `calculatePrice()` and reuse `super.calculatePrice()`.
- Verify that an equivalent special pizza costs more than a regular pizza.

### Member 4 — Billing

Assigned file:

```text
BILL_ORDER.java
```

Responsibilities:

- Implement `OUT(PizzaBase pizza)`.
- Read details only through getters.
- Print a clear receipt and return the amount due.
- Test billing with both concrete pizza types.

### Member 5 — Main Program

Assigned file:

```text
TAKE_ORDER.java
```

Responsibilities:

- Implement all prompts and validation loops.
- Create the selected object through a `PizzaBase` reference.
- Store input with setters and call `calculatePrice()`.
- Pass the pizza to `BILL_ORDER.OUT()`.
- Validate payment and calculate change.

## Naming Rules

1. Use the class, field, constant, and method names exactly as documented.
2. Use `toppings`, `howMany`, `getToppings()`, and `getDiameterInCentimeters()`.
3. Use the public topping and discount constants from `PIZZA`.
4. Keep `setPrice()` protected; do not create a public price setter.
5. Do not put pricing formulas in `TAKE_ORDER` or `BILL_ORDER`.

## Short Git and GitHub Guide

Each member works on a separate branch. Do not commit directly to `main`.

### First-time setup

```powershell
git clone <repository-url>
Set-Location .\OrderTakerrr
git status
```

### Start a branch

```powershell
git switch main
git pull --ff-only origin main
git switch -c member-2-pizza
```

Recommended branch names:

```text
member-1-architecture
member-2-pizza
member-3-special-pizza
member-4-bill-order
member-5-take-order
```

### Save and upload work

Example for Member 2:

```powershell
git status
git add .\PIZZA.java
git commit -m "Implement regular pizza pricing"
git push -u origin member-2-pizza
```

Add only assigned files. After pushing, open GitHub, select **Compare & pull request**, target `main`, describe what was tested, and request Member 1's review.

If Git reports a conflict, do not delete either version or force-push. Run `git status` and send its output to Member 1.

## Compile and Run

Compile all required files from the project folder:

```powershell
javac .\PizzaBase.java .\Discountable.java .\PIZZA.java .\SPECIAL_PIZZA.java .\BILL_ORDER.java .\TAKE_ORDER.java
```

Run the program:

```powershell
java TAKE_ORDER
```

## PDF Sample Calculation

Order:

```text
Pizza Type: Regular Pizza
Topping: Pepperoni
Diameter: 10 inches
Quantity: 2
Discount: VIPCard
```

Expected calculation:

```text
Diameter: 10 × 2.54 = 25.40 cm
Size charge: 25.40 × 5.00 = PHP 127.00
Unit price: 100.00 + 127.00 = PHP 227.00
Subtotal: 227.00 × 2 = PHP 454.00
Discount: 454.00 × 0.12 = PHP 54.48
Amount due: 454.00 - 54.48 = PHP 399.52
Change from PHP 500.00: PHP 100.48
```

PHP 300.00 and PHP 399.52 must both be rejected because payment must be greater than the amount due.

## Final Checklist

- [ ] All six required `.java` files exist and compile together.
- [ ] All five required `PIZZA` fields are private.
- [ ] Input is stored through setters and billing uses getters.
- [ ] `PIZZA` extends `PizzaBase` and implements `Discountable`.
- [ ] `SPECIAL_PIZZA` extends `PIZZA` and meaningfully overrides `calculatePrice()`.
- [ ] A `PizzaBase` reference demonstrates runtime polymorphism.
- [ ] Topping prices, discount rates, and diameter conversion match the PDF.
- [ ] Invalid text, menus, zero, and negative values do not crash the program.
- [ ] Payment must be greater than the amount due.
- [ ] Pricing is not duplicated or hard-coded outside the pizza classes.
- [ ] The sample produces PHP 399.52 due and PHP 100.48 change.

See [`docs/PLAN.md`](docs/PLAN.md) for the implementation order and integration checklist.
