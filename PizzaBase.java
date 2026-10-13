//Abstract parent class for the shared pizza details and methods
public abstract class PizzaBase {

    //Encapsulating the pizza type so it cannot be changed directly
    private final String pizzaType;

    //Construction of the pizza type
    protected PizzaBase(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    //get the type of pizza for the order details
    public final String getPizzaType() {
        return this.pizzaType;
    }

    //convert the entered diameter from inches to centimeters
    public final double getDiameterInCentimeters() {
        return this.getDiameter() * 2.54;
    }

    //methods that every pizza class needs to provide
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

    //calculatePrice will be different for regular and special pizza
    public abstract double calculatePrice();
}
