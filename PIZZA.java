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

    public PIZZA() {
        this("Regular Pizza");
    }

    protected PIZZA(String pizzaType) {
        super(pizzaType);
        this.discount = DISCOUNT_NONE;
    }

    @Override
    public String getToppings() {
        return this.toppings;
    }

    @Override
    public void setToppings(String toppings) {
        if (TOPPING_PEPPERONI.equals(toppings) ||
                TOPPING_HAM_AND_CHEESE.equals(toppings) ||
                TOPPING_HAWAIIAN.equals(toppings)) {
            this.toppings = toppings;
        } else {
            throw new IllegalArgumentException("Invalid topping: " + toppings);
        }
    }

    @Override
    public String getDiscount() {
        return this.discount;
    }

    @Override
    public void setDiscount(String discount) {
        if (DISCOUNT_SENIOR_CITIZEN.equals(discount) ||
                DISCOUNT_VIP_CARD.equals(discount) ||
                DISCOUNT_NONE.equals(discount)) {
            this.discount = discount;
        } else {
            throw new IllegalArgumentException("Invalid discount: " + discount);
        }
    }

    @Override
    public int getDiameter() {
        return this.diameter;
    }

    @Override
    public void setDiameter(int diameter) {
        if (diameter <= 0) {
            throw new IllegalArgumentException("Diameter must be greater than zero.");
        }
        this.diameter = diameter;
    }

    @Override
    public int getHowMany() {
        return this.howMany;
    }

    @Override
    public void setHowMany(int howMany) {
        if (howMany <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.howMany = howMany;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    protected final void setPrice(double price) {
        this.price = price;
    }

    protected final double getToppingPrice() {
        if (TOPPING_PEPPERONI.equals(this.toppings)) {
            return PEPPERONI_PRICE;
        } else if (TOPPING_HAM_AND_CHEESE.equals(this.toppings)) {
            return HAM_AND_CHEESE_PRICE;
        } else if (TOPPING_HAWAIIAN.equals(this.toppings)) {
            return HAWAIIAN_PRICE;
        }
        return 0.0;
    }

    @Override
    public double getDiscountRate() {
        if (DISCOUNT_SENIOR_CITIZEN.equals(this.discount)) {
            return 0.20;
        } else if (DISCOUNT_VIP_CARD.equals(this.discount)) {
            return 0.12;
        } else {
            return 0.00;
        }
    }

    @Override
    public double calculatePrice() {
        double diameterCm = getDiameterInCentimeters();
        double sizeCharge = diameterCm * SIZE_PRICE_PER_CENTIMETER;
        double unitPrice = getToppingPrice() + sizeCharge;
        double subtotal = unitPrice * this.howMany;
        double discountAmount = subtotal * getDiscountRate();
        double totalPrice = subtotal - discountAmount;

        setPrice(totalPrice);
        return totalPrice;
    }
}