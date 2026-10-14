public class BILL_ORDER {

    public double OUT(PizzaBase pizza) {
        // reject null
        if (pizza == null) {
            throw new IllegalArgumentException("Pizza order cannot be null.");
        }

        System.out.println("===== ORDER DETAILS =====");
        System.out.println("Pizza Type : " + pizza.getPizzaType());
        System.out.println("Topping    : " + pizza.getToppings());
        System.out.printf("Diameter   : %.2f cm%n", pizza.getDiameterInCentimeters());
        System.out.println("Quantity   : " + pizza.getHowMany());
        System.out.println("Discount   : " + pizza.getDiscount());
        System.out.printf("Disc. Rate : %.2f%%%n", pizza.getDiscountRate() * 100);

        double amountDue = pizza.getPrice();
        System.out.printf("Amount Due : PHP %.2f%n", amountDue);

        return amountDue;
    }
}
