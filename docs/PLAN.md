# Implementation Plan

## 1. Approve and Freeze the Contract

All members read the authoritative class and method contract in [`../README.md`](../README.md). Before implementation begins, confirm the following team-defined rule:

```text
SPECIAL_PIZZA adds PHP 50.00 per pizza after the regular discount.
```

After approval, do not rename shared fields, constants, constructors, or methods without informing the whole team.

## 2. Build the Foundation

Member 1 implements and publishes:

- `Discountable.java`
- `PizzaBase.java`

Check that these files compile before the other implementations are integrated.

## 3. Implement in Parallel

Once the contract is available:

- Member 2 implements `PIZZA.java`.
- Member 3 implements `SPECIAL_PIZZA.java` using the published `PIZZA` contract.
- Member 4 implements `BILL_ORDER.java` against `PizzaBase` getters.
- Member 5 implements `TAKE_ORDER.java` against the `PizzaBase` setters and methods.

Each member uses a separate Git branch and opens a pull request. Member 3 should coordinate with Member 2 if `PIZZA.java` has not yet been merged, but must not change the documented contract independently.

## 4. Integrate in Dependency Order

Member 1 reviews and merges in this order:

1. `PizzaBase.java` and `Discountable.java`
2. `PIZZA.java`
3. `SPECIAL_PIZZA.java`
4. `BILL_ORDER.java`
5. `TAKE_ORDER.java`

Compile all available source files after every merge. Fix contract mismatches before merging the next dependency.

## 5. Test the Complete Program

Test these groups:

1. Every topping with `No Discount`.
2. `Senior Citizen` and `VIPCard` discounts.
3. Equivalent regular and special orders to verify the special charge.
4. Invalid text, menu choices, zero, and negative numeric values.
5. Payment below, equal to, and greater than the amount due.

Run the PDF sample last:

```text
Regular Pizza
Pepperoni
10 inches
2 pizzas
VIPCard
```

Expected results:

```text
Amount due: PHP 399.52
Change from PHP 500.00: PHP 100.48
```

## Definition of Done

- All six required Java source files compile together.
- The PDF sample produces the expected total and change.
- All invalid input is rejected without crashing.
- `PIZZA` fields remain private and other classes use getters and setters.
- Pricing logic exists only in `PIZZA` and `SPECIAL_PIZZA`.
- `SPECIAL_PIZZA.calculatePrice()` reuses `super.calculatePrice()`.
- `BILL_ORDER.OUT()` accepts either pizza type through `PizzaBase`.
- `TAKE_ORDER` demonstrates runtime polymorphism.
- Every pull request is reviewed before integration.
