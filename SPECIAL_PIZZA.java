
public class SPECIAL_PIZZA extends PIZZA {

    private static final double SPECIAL_CHARGE_PER_PIZZA = 50.00;

    public SPECIAL_PIZZA() {
        super("Special Pizza");
    }

    public double getSpecialChargePerPizza() {
        return SPECIAL_CHARGE_PER_PIZZA;
    }

    @Override
    public double calculatePrice() {
        double regularTotal = super.calculatePrice();

        double specialTotal = regularTotal
                + (getSpecialChargePerPizza() * getHowMany());

        setPrice(specialTotal);
        return specialTotal;
    }
}
